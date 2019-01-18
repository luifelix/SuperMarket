package groceryStore;

/**
 * This class represents the product class. This class is
 * where all Product products will be instantiated.
 * It contains methods such as getting and setting different
 * attributes of each instantiated product, changing sales etc.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

public class Product {

  private String name;
  private int quantity;
  private Price price;
  private Location location;
  private int threshold;
  private String UPC;
  private boolean onBackOrder;
  private int orderNum;
  private Date today = new Date();
  public FileUtility fileUtility;

  /*private Format formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
  private String todayDate = formatter.format(today);*/
  /**
   * Creates a new Product with its name, quantity, price, if it is on sale or not, its section,
   * its threshold, and its UPC.
   *
   * @param name the name of this Product
   * @param quantity the quantity of this Product.
   * @param price the price of this Product
   * @param location the location of this Product
   * @param UPC the UPC of this Product
   */

  public Product(String name, int quantity, Price price, Location location, String UPC) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
    this.location = location;
    this.UPC = UPC;
    //default values
    this.threshold = 10;
    this.onBackOrder = false;
    //add to price history
    String data = UPC + ", " + name + ", " + price.getPrice() + ", " + today + "\n";
    fileUtility.FileWriter("PriceHistory.txt", data);
  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  /**
   * Decreases the quantity of the items in stock by 1. This is called by
   * cashier.scan(). This method does not return anything.
   *
   */

  public void decrement(){
    if (!(quantity == 0)) {
      quantity --;
    }
  }

  public void add(int quantity){
    this.quantity += quantity;
  }

  public String getUPC() {
    return UPC;
  }

  /**
   * Writes the sale of an item with a start and end date onto
   * the PriceHistory.txt file.
   *
   * @param    price       The sale price of the product.
   * @param    startDate   The Date of which the sale starts.
   * @param    endDate     The Date of which the sale ends
   */

  public void setSale(Double price, Date startDate, Date endDate){
    this.price.priceSetSale(price, startDate, endDate);
    String data = UPC + ", " + name + ", " + this.price.getCurrPrice() + ", " + startDate + "\n";
    fileUtility.FileWriter("PriceHistory.txt", data);
    //add to price history
  }

  /**
   * Sets the sale on the item to be True and adds the item to the
   * SaleProduct.txt.
   *
   */

  public void putOnSale(){
    this.price.putOnSale();
    String data = UPC + ", " + name + "\n";
    fileUtility.FileWriter("SaleProducts.txt", data);
  }

  /**
   * Sets the sale of an item to be false and and removes the product from
   * the SaleProducts.txt. Also changes the sale price back to the original
   * price.
   *
   */
  public void takeOffSale(){
    this.price.takeOffSale();
    String data = UPC + ", " + name + ", " + this.price.getCurrPrice() + ", " + this.price.getEndDate() + "\n";
    fileUtility.FileWriter("PriceHistory.txt", data);
    String line;
    ArrayList<String> lines = new ArrayList<>();
    try {
      File orderHistory = new File("SaleProducts.txt");
      FileReader fileReader = new FileReader(orderHistory);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while ((line = bufferedReader.readLine()) != null) {
        if (line.contains(UPC)) {
          line = bufferedReader.readLine();
          if(line == null){
            break;
          }
        }
        lines.add(line + "\n");
      }
      fileReader.close();
      bufferedReader.close();

      FileWriter fileWriter = new FileWriter(orderHistory);
      BufferedWriter out = new BufferedWriter(fileWriter);
      for (String s : lines)
        out.write(s);
      out.flush();
      out.close();

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public Price getPrice(){
    return price;
  }

  public Location getLocation(){
    return location;
  }

  public int getThreshold() {
    return threshold;
  }

  public int setThreshold(int threshold){
    this.threshold = threshold;
    return this.threshold;
  }

  public boolean underThreshold(){
    return quantity < threshold;
  }

  public boolean onBackOrder(){
    return onBackOrder;
  }

  public void setOnBackOrder(){
    onBackOrder = !onBackOrder;
  }

  public int getOrderNum(){
    return orderNum;
  }

  public void setOrderNum(int num){
    orderNum = num;
  }
}
