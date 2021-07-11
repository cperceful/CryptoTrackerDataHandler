import java.math.BigDecimal;

public class Transaction
{
    private String token;
    private String date;
    private double amount;
    private eTransactionType transactionType;
    private BigDecimal averageCost;
    private BigDecimal fee;

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

    public BigDecimal getFee()
    {
        return fee;
    }

    public void setFee(BigDecimal fee)
    {
        this.fee = fee;
    }

    enum eTransactionType
    {
        BUY,
        SELL
    }

}


