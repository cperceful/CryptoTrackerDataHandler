import java.math.BigDecimal;
import java.math.RoundingMode;

public class Position
{
    private String token;
    private double amountPurchased;
    private double amount;
    private BigDecimal totalCost;
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
        amount = amountPurchased - totalFees;
        //need amount in BigDecimal
        BigDecimal bdAmount = BigDecimal.valueOf(amount);

        averageCost = totalCost.divide(bdAmount, RoundingMode.HALF_EVEN);
        costBasis = bdAmount.multiply(averageCost);
        currentValue = bdAmount.multiply(costBasis);
        netProfit = currentValue.subtract(costBasis);
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

    public BigDecimal getAverageCost()
    {
        return averageCost;
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

    public BigDecimal getNetProfit()
    {
        return netProfit;
    }

    public void calculateNetProfit()
    {
        BigDecimal bdAmount = BigDecimal.valueOf(amount);
        BigDecimal value = lastPrice.multiply(bdAmount);
        this.netProfit = value.subtract(this.getCostBasis());
    }

    public double getTotalFees()
    {
        return totalFees;
    }

    public void setTotalFees(double totalFees)
    {
        this.totalFees = totalFees;
    }

    public void setAmountPurchased(double amountPurchased)
    {
        this.amountPurchased = amountPurchased;
    }

    public void setTotalCost(BigDecimal totalCost)
    {
        this.totalCost = totalCost;
    }

}
