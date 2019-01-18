package groceryStore;

/**
 * This class Receiver, which extends from abstract class User.
 * Each Receiver will have a name and can call the various methods
 * in the class. This includes scanning items orders (order arrivals) and adding
 * them to the inventory, checking the location of an item, checking the cost
 * of an item, checking the current price of a product, and checking for the
 * price history of the product.
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
  public FileUtility fileUtility;

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
   */

  public void scan(Store store, int orderNum, String name, int quantity, Double price,
                   Double cost, String section, String subsection, String UPC) {
    if (store.getAllProducts().containsKey(UPC)) {  //existing products
      store.getStoreMap().get(section).getSubsection(subsection).get(UPC).add(quantity);
      if (store.getStoreMap().get(section).getSubsection(subsection).get(UPC).getOrderNum() == orderNum) {  //checks if previously on backorder
        store.getStoreMap().get(section).getSubsection(subsection).get(UPC).setOnBackOrder();
      }
    } else {  //new products
      Price p = new Price(price, cost);
      Location l = new Location(section, subsection);
      Product product = new Product(name, quantity, p, l, UPC);
      if (store.getStoreMap().containsKey(section)) { //existing section
        if (store.getStoreMap().get(section).getSection().containsKey(subsection)) {  //existing subsection
          store.getStoreMap().get(section).addProduct(product);
        } else {  //new subsection
          store.getStoreMap().get(section).addSubsection(subsection);
          store.getStoreMap().get(section).addProduct(product);
        }
      } else {  //new section
        store.addSection(section);
        store.getStoreMap().get(section).addSubsection(subsection);
        store.getStoreMap().get(section).addProduct(product);
      }
    }
    try {
      File orderHistory = new File("OrderHistory.txt");
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

  /**
   * Returns the location of Product product in the Store.
   *
   * @param    product     A product of which its location will be found.
   * @return   aisle       The aisle the product is found in.
   */

  public int location(Product product){
    return product.getLocation().getAisle();
  }

  /**
   * Returns the cost of Product product in the Store.
   *
   * @param    product     A product of which its location will be found.
   * @return   cost        The cost of the product in Store.
   */

  public Double checkCost(Product product){
    return product.getPrice().getCost();
  }

  /**
   * Returns the current price of Product product in the Store.
   *
   * @param    product     A product of which its price will be found.
   * @return   price       The price of the product.
   */

  public Double checkCurrPrice(Product product){
    return product.getPrice().getCurrPrice();
  }

  /**
   * Prints out the price history of a particular Product product from a
   * pre-existing file.
   *
   * @param    product     An instance of the Product class. The particular
   *                       product of which the wanted price history is for.
   */

  public StringBuffer priceHistory(Product product) {
    String object = product.getName();
    return fileUtility.ProductFileScanner("PriceHistory.txt", object);
  }
}
