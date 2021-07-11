import java.math.BigDecimal;

public class Position
{
    private String token;
    private double amount;
    private BigDecimal averageCost;
    private BigDecimal lastPrice;
    private BigDecimal costBasis;
    private BigDecimal currentValue;
    private BigDecimal netProfit;
    private double totalFees;

    public Position()
    {
        this.amount = 0;
        this.averageCost = new BigDecimal("0.00");
        this.totalFees = 0;
    }

    public void calculate()
    {
        BigDecimal bdAmount = new BigDecimal(String.valueOf(amount));

        costBasis = bdAmount.multiply(averageCost);
        currentValue = bdAmount.multiply(lastPrice);
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAverageCost()
    {
        return averageCost;
    }

    public void setAverageCost(BigDecimal averageCost)
    {
        this.averageCost = averageCost;
    }

    public BigDecimal getLastPrice()
    {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice)
    {
        this.lastPrice = lastPrice;
    }

    public BigDecimal getCostBasis()
    {
        return costBasis;
    }

    public BigDecimal getCurrentValue()
    {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue)
    {
        this.currentValue = currentValue;
    }

    public BigDecimal getNetProfit()
    {
        return netProfit;
    }

    public void calculateNetProfit()
    {
        BigDecimal bdAmount = new BigDecimal(String.valueOf(amount));
    }

    public double getTotalFees()
    {
        return totalFees;
    }

    public void setTotalFees(double totalFees)
    {
        this.totalFees = totalFees;
    }
}
