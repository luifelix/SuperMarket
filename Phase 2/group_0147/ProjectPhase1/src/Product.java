import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

public class Product {

  private String name;
  private int quantity;
  private Double price;
  private Double currPrice;
  private Double cost;
  private String section;
  private String subsection;
  private int aisle;
  private int threshold;
  private String UPC;
  private boolean sale;
  private Date startDate;
  private Date endDate;
  private boolean onBackOrder;
  private int orderNum;

  private Date today = new Date();
  /*private Format formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
  private String todayDate = formatter.format(today);*/
  /**
   * Creates a new Product with its name, quantity, price, if it is on sale or not, its section,
   * its threshold, and its UPC.
   *
   * @param name the name of this Product
   * @param quantity the quantity of this Product.
   * @param price the price of this Product
   * @param cost the cost of this Product
   * @param section the section of this Product
   * @param UPC the UPC of this Product
   */

  public Product(String name, int quantity, Double price, Double cost, String section, String subsection, String UPC) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
    this.cost = cost;
    this.section = section;
    this.subsection = subsection;
    this.UPC = UPC;
    //default values
    this.currPrice = price;
    this.sale = false;
    this.threshold = 10;
    this.onBackOrder = false;
    this.aisle = 0;
    //add to price history
    try{
      FileWriter fileWriter = new FileWriter("PriceHistory" , true);
      Formatter f = new Formatter(fileWriter);
      f.format(UPC + ", " + name + ", " + price + ", " + today + "\n");
      f.close();
    }catch (Exception e) {
      System.out.println("File does not exist.");
    }
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
   * @return   void
   */

  public void decrement(){
    if (!(quantity == 0)) {
      quantity --;
    }
  }

  public void add(int quantity){
    this.quantity += quantity;
  }

  public Double getPrice() {
    return price;
  }

  public Double getCurrPrice(){
    return this.currPrice;
  }

  public Double getCost(){
    return this.cost;
  }

  public String getSection() {
    return section;
  }

  public String getSubsection(){
    return this.subsection;
  }

  public String getUPC() {
    return UPC;
  }

  public boolean onSale() {
    return this.sale;
  }

  /**
   * Writes the sale of an item with a start and end date onto
   * the PriceHistory.txt file.
   *
   * @param    price       The sale price of the product.
   * @param    startDate   The Date of which the sale starts.
   * @param    endDate     The Date of which the sale ends
   * @return   void
   */

  public void setSale(Double price, Date startDate, Date endDate){
    currPrice = price;
    this.startDate = startDate;
    this.endDate = endDate;
    //add to price history
    try{
      FileWriter fileWriter = new FileWriter("PriceHistory" , true);
      Formatter f = new Formatter(fileWriter);
      f.format(UPC + ", " + name + ", " + currPrice + ", " + startDate + "\n");
      f.close();
    }catch (Exception e) {
      System.out.println("File does not exist.");
    }
  }

  /**
   * Sets the sale on the item to be True and adds the item to the
   * SaleProduct.txt.
   *
   * @return    void
   */

  public void putOnSale(){
    sale = true;
    try{
      FileWriter fileWriter = new FileWriter("SaleProducts" , true);
      Formatter f = new Formatter(fileWriter);
      f.format(UPC + ", " + name + "\n");
      f.close();
    }catch (Exception e) {
      System.out.println("File does not exist.");
    }
  }

/**
 * Sets the sale of an item to be false and and removes the product from
 * the SaleProducts.txt. Also changes the sale price back to the original
 * price.
 *
 * @return   void
 */
  public void takeOffSale(){
    sale = false;
    currPrice = price;
    try{
      FileWriter fileWriter = new FileWriter("PriceHistory" , true);
      Formatter f = new Formatter(fileWriter);
      f.format(UPC + ", " + name + ", " + currPrice + ", " + endDate + "\n");
      f.close();
    }catch (Exception e) {
      System.out.println("File does not exist.");
    }
    String line = "";
    ArrayList<String> lines = new ArrayList<String>();
    try {
      File orderHistory = new File("SaleProducts");
      FileReader fileReader = new FileReader(orderHistory);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while ((line = bufferedReader.readLine()) != null) {
        String[] lineList = line.split(", ");
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

  public Date getStartDate() {
    return this.startDate;
  }

  public Date getEndDate() {
    return this.endDate;
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

  public int getAisle(){
    return aisle;
  }

  public void setAisle(int num){
    aisle = num;
  }
}