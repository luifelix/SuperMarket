/**
 * This class represents the interface for the user, Reshelver.
 * Each Cashier will have a name and can call the various methods
 * in the class. This includes checking the order history,
 * finding out the quantity of items, changing the aisles for specific
 * items or a whole section of items.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Reshelver extends Users {

  public Reshelver(String name) {
    super(name);
  }

  public int location(Product product){
    return product.getAisle();
  }

  /**
   * Prints out the order history of a particular product given by the user.
   * The method searches within a pre-existing OrderHistory.txt file.
   *
   * @param    product    A Product that the order history will be found for.
   * @return   void
   */

  public void orderHistory(Product product){
    String line = "";
    try{
      File file = new File("OrderHistory");
      Scanner sc = new Scanner(file);
      while(sc.hasNext()){
        line = sc.nextLine();
        if (line.contains(product.getName())){
          System.out.println(line);
        }
      }
    }catch (Exception e) {
      System.out.println("File does not exist.");
    }
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
   * @return   void
   */

  public void setAisle(Product product, int aisle){
    product.setAisle(aisle);
  }

  /**
   * Changes the aisle number for a particular section of products.
   *
   * @param    section     The string of which the section is to be changed.
   * @param    aisle       The integer of the new aisle of the section.
   * @return   void
   */

  public void setSectionAisle(String section, int aisle){
    for (HashMap<String, Product>  subsection : Store.storeMap.get(section).getSection().values()) {
      for (Product product: subsection.values()) {
        product.setAisle(aisle);
      }
    }
  }
}
