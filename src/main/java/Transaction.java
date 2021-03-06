import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Transaction
{
    @SerializedName("Token")
    private String token;

    @SerializedName("Date")
    private String date;

    @SerializedName("Amount")
    private double amount;

    @SerializedName("transType")
    private eTransactionType transactionType;

    @SerializedName("AverageCost")
    private BigDecimal averageCost;

    @SerializedName("Fee")
    private double fee;

    public Transaction(){}

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public eTransactionType getTransactionType()
    {
        return transactionType;
    }

    public void setTransactionType(eTransactionType transactionType)
    {
        this.transactionType = transactionType;
    }

    public BigDecimal getAverageCost()
    {
        return averageCost;
    }

    public void setAverageCost(BigDecimal averageCost)
    {
        this.averageCost = averageCost;
    }

    public double getFee()
    {
        return fee;
    }

    public void setFee(double fee)
    {
        this.fee = fee;
    }

    enum eTransactionType
    {
        BUY,
        SELL
    }

}


