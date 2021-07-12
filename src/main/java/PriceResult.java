import com.google.gson.annotations.SerializedName;

public class PriceResult
{
    @SerializedName("id")
    private String id;

    @SerializedName("price")
    private String price;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }
}
