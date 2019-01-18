/**
 * This class represents the interface for the user, Receivers.
 * Each Receiver will have a name and can call the various methods
 * in the class. This includes scanning items orders (order arrivals),
 * checking for the location of items, and checking for the priceHistory
 * of a particular product.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Receiver extends Users {

  private String line = null;
  private ArrayList<String> lines = new ArrayList<>();
  private Date today = new Date();

  public Receiver(String name) {
    super(name);
  }

  /**
   * Adds in the scanned product into the inventory and also updates the pre-existing
   * OrderHistory.txt file to show that the particular order has arrived by replacing
   * the arrival date from "N/A" to the current date. It checks the UPC and the order
   * number to determine which is the correct order.
   *
   * @param    orderNum    A unique integer given to this particular order.
   * @param    name        A string name given to particular product.
   * @param    quantity    The amount of the product arrived in the order.
   * @param    price       The price of which each piece of product will be sold.
   * @param    cost        The cost of which each piece of product was bought.
   * @param    section     A string for which the product will be categorized as.
   * @param    subsection  A more specific category than the section for which
   *                       the product belongs under.
   * @param    UPC         A unique string given to each item for identification.
   * @return   void
   */

  public void scan(int orderNum, String name, int quantity, Double price, Double cost, String section, String subsection, String UPC) {
    if (Section.allProducts.containsKey(UPC)) {
      Store.storeMap.get(section).getSubsection(subsection).get(UPC).add(quantity);
      if (Store.storeMap.get(section).getSubsection(subsection).get(UPC).getOrderNum() == orderNum) {
        Store.storeMap.get(section).getSubsection(subsection).get(UPC).setOnBackOrder();
      }
    } else {
      Product product = new Product(name, quantity, price, cost, section, subsection, UPC);
      if (Store.storeMap.containsKey(section)) {
        if (Store.storeMap.get(section).getSection().containsKey(subsection)) {
          Store.storeMap.get(section).addProduct(product);
        } else {
          Store.storeMap.get(section).addSubsection(subsection);
          Store.storeMap.get(section).addProduct(product);
        }
      } else {
        Store.addSection(section);
        Store.storeMap.get(section).addSubsection(subsection);
        Store.storeMap.get(section).addProduct(product);
      }
    }
    try {
      File orderHistory = new File("OrderHistory");
      FileReader fileReader = new FileReader(orderHistory);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while ((line = bufferedReader.readLine()) != null) {
        String[] lineList = line.split(", ");
        if ((lineList[0].equals(Integer.toString(orderNum))) && line.contains("N/A")) {
          line = line.replace("N/A", today.toString());
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

  public Double cost(Product product) {
    return product.getCost();
  }

  public Double currentPrice(Product product) {
    return product.getCurrPrice();
  }

  /**
   * Prints out the price history of a particular Product product from a
   * pre-existing file.
   *
   * @param    product     An instance of the Product class. The particular
   *                       product of which the wanted price history is for.
   * @return   void
   */

  public void priceHistory(Product product){
    try{
      File file = new File("PriceHistory");
      Scanner sc = new Scanner(file);
      while(sc.hasNext()){
        line = sc.nextLine();
        if (line.contains(product.getName())){
          System.out.println(line);
        }
      }
    }catch (Exception e){
      System.out.println("File does not exist.");
    }
  }
}