package groceryStore;
import java.util.Date;

public class Price {
    private Double price;
    private Double salePrice;
    private Double cost;
    private Double currPrice;
    private boolean sale;
    private Date startDate;
    private Date endDate;

    public Price(Double price, Double cost){
        this.cost = cost;
        this.price = price;
        this.currPrice = price;
    }

    public void priceSetSale(Double price, Date startDate, Date endDate){
        salePrice = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void putOnSale(){
        currPrice = salePrice;
        sale = true;
    }

    public void takeOffSale(){
        currPrice = price;
        sale = false;
    }

    public boolean onSale(){
        return this.sale;
    }

    public Double getPrice(){
        return this.price;
    }

    public Double getCurrPrice(){
        return this.currPrice;
    }

    public Double getCost(){
        return this.cost;
    }

    public Date getStartDate(){
        return this.startDate;
    }

    public Date getEndDate(){
        return this.endDate;
    }

}
