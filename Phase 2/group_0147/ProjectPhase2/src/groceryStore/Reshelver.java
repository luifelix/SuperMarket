package groceryStore;

/**
 * This class Reshelver, which extends from abstract class User.
 * Each Cashier will have a name and can call the various methods
 * in the class. This includes checking the order history, finding out the
 * quantity of items, and changing the aisles for specific items or a whole
 * section of items.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

public class Reshelver extends Users {

  private Date today = new Date();
  public FileUtility fileUtility;

  public Reshelver(String name) {
    super(name);
  }

  /**
   * Prints out the order history of a particular product given by the user.
   * The method searches within a pre-existing OrderHistory.txt file.
   *
   * @param product A Product that the order history will be found for.
   */

  public StringBuffer orderHistory(Product product) {
    String object = product.getName();
    return fileUtility.ProductFileScanner("OrderHistory.txt", object);
  }

  /**
   * Returns the total amount of Product product is found within the inventory of
   * the program.
   *
   * @param    product     A product of which its' quantity will be found.
   * @return   quantity    The integer of the total amount of specified product.
   */

  public int currentQuantity(Product product){
    return product.getQuantity();
  }

  /**
   * Changes the original aisle number of a specified Product product to a specified
   * new aisle number.
   *
   * @param    product     The product of which the aisle number is to be changed.
   * @param    aisle       The integer of which the product's aisle number
   *                       will change to.
   */

  public void setAisle(Product product, int aisle){
    product.getLocation().setAisle(aisle);
  }

  /**
   * Changes the aisle number for a particular section of products.
   *
   * @param    section     The string of which the section is to be changed.
   * @param    aisle       The integer of the new aisle of the section.
   */

  public void setSectionAisle(Store store, String section, int aisle){
    for (HashMap<String, Product>  subsection : store.getStoreMap().get(section).getSection().values()) {
      for (Product product: subsection.values()) {
        product.getLocation().setAisle(aisle);
      }
    }
  }

  public void scanDamagedItem(Store store, Product product){
    product.decrement();
    String data = product.getUPC() + ", " + product.getName() + ", " + today + "\n";
    fileUtility.FileWriter("DamagedProducts.txt", data);
    if (product.underThreshold() && !(product.onBackOrder())){ // reorder
      product.setOnBackOrder();
      String info = store.getOrderNum() + ", " + product.getName() + ", " + (product.getThreshold())*3 + ", "
          + product.getPrice().getPrice() + ", " + product.getPrice().getCost() + ", "
          + product.getLocation().getSection() + ", " + product.getUPC() + ", " + today + ", N/A\n";
      product.setOrderNum(store.getOrderNum());
      fileUtility.FileWriter("OrderHistory.txt", info);
      store.incrementOrderNum();
    }
  }
}
