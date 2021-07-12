import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.reflect.Type;

public class DataHelper
{
    private static String ids;
    private static List<Transaction> transactions;
    private static HashMap<String, List<Transaction>> transactionHashMap;
    private static List<Position> positions;

    private static final String nomicsBaseURL = "https://api.nomics.com/v1";
    private static final String nomicsRequestURL = MessageFormat.format(
            "{0}/currencies/ticker?key={1}&ids={2}&interval=1d", nomicsBaseURL, Credentials.NOMICS_KEY, ids);

    private static final String pantryRequestURL = MessageFormat.format(
            "https://getpantry.cloud/apiv1/pantry/{0}/basket/transactions", Credentials.PANTRY_ID);

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static void processData() throws Exception
    {
        //Master method for various data processing methods
        loadFile();
        calculatePositions();
        getAllTransactions();
        buildIdString();
        getPrices();
        calculateProfits();
    }

    private static void loadFile() throws Exception
    {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(pantryRequestURL))
                .build();
        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, List<Transaction>>>(){}.getType();
        transactionHashMap = gson.fromJson(json, type);
    }

    private static void calculatePositions()
    {
        positions = new ArrayList<>();
        transactionHashMap.forEach((token, transactionList) -> {
            Position position = new Position();
            position.setToken(token);
            BigDecimal totalCost = new BigDecimal("0.00");
            double amountPurchased = 0;
            for (Transaction transaction: transactionList)
            {
                amountPurchased += transaction.getAmount();
                totalCost = totalCost.add(
                        transaction.getAverageCost()
                                .multiply(BigDecimal.valueOf(
                                        transaction.getAmount()
                                        )));
                position.setTotalFees(position.getTotalFees() + transaction.getFee());
            }
            position.setTotalCost(totalCost);
            position.setAmountPurchased(amountPurchased);
            position.calculate();
        });
    }

    private static void calculateProfits()
    {
        for (Position position : positions)
        {
            position.calculateNetProfit();
        }
    }

    private static void getAllTransactions()
    {
        transactions = new ArrayList<>();
        transactionHashMap.forEach((token, tranactionList) -> {
            for (Transaction transaction : tranactionList)
            {
                transactions.add(transaction);
            }
        });
    }

    private static void buildIdString()
    {
        List<String> idList = new ArrayList<>();
        for (Position position : positions)
        {
            idList.add(position.getToken());
        }
        ids = String.join(",", idList);
    }

    private static void getPrices() throws Exception
    {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(nomicsRequestURL))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        List<PriceResult> priceResults = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<List<PriceResult>>(){}.getType();
        priceResults = gson.fromJson(json, type);

        for (PriceResult priceResult : priceResults)
        {
            for (Position position : positions)
            {
                if (position.getToken().equalsIgnoreCase(priceResult.getId()))
                {
                    BigDecimal bdLastPrice = new BigDecimal(priceResult.getPrice());
                    position.setLastPrice(bdLastPrice);
                }
            }
        }

    }

    public static void saveFile() throws Exception
    {
        Gson gson = new Gson();
        String json = gson.toJson(transactionHashMap);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(pantryRequestURL))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        httpClient.send(request, HttpResponse.BodyHandlers.discarding());
    }

    public static void addTransaction(Transaction transaction)
    {
        String token = transaction.getToken();

        if (transactionHashMap == null)
        {
            transactionHashMap = new HashMap<String, List<Transaction>>();
        }

        if (transactionHashMap.containsKey(token))
        {
            transactionHashMap.get(token).add(transaction);
        }
        else
        {
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);
            transactionHashMap.put(token, transactions);
        }
    }

    public static String getIds()
    {
        return ids;
    }

    public static List<Transaction> getTransactions()
    {
        return transactions;
    }

    public static HashMap<String, List<Transaction>> getTransactionHashMap()
    {
        return transactionHashMap;
    }

    public static List<Position> getPositions()
    {
        return positions;
    }
}
