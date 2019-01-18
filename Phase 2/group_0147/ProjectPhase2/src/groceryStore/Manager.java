package groceryStore;

/**
 * This class Manager, which extends from abstract class User.
 * Each Manager will have a name and can call the various methods
 * in the class. This includes checking on pending orders,
 * checking when a product was last ordered, generating a list
 * of all products in stock, and finding out the profits and
 * revenue of a particular day.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */
import java.io.File;
import java.util.Scanner;

public class Manager extends Users {

  public Manager(String name){
    super(name);
  }
  public FileUtility fileUtility;

  /**
   * Goes over a pre-existing OrderHistory.txt file and prints out the orders that
   * have yet to arrive, also known as "pending".
   *
   * This method does not return any values, but prints all pending orders.
   *
   */

  public StringBuffer pendingOrders(){
    String object = "N/A";
    return fileUtility.ProductFileScanner("OrderHistory.txt", object);
  }

  /**
   * Prints out the total profit of a particular given date by reading from
   * a pre-existing SaleHistory.txt file.
   *
   * Does not return any value.
   *
   * @param    date    A string that contains the day and month of the desired profit.
   * @param    year    A string that contains the year of the desired profit.
   * @return   profit  A double the contains the profit of the given date and year.
   */

  public Double profit(String date, String year) {
    return fileUtility.SumScanner(2, date, year);
  }

  /**
   * Prints out the total revenue of a particular date by reading from a
   * pre-existing SaleHistory.txt file.
   *
   * Does not return any value.
   *
   * @param    date    A string that contains the day and month of the desired revenue.
   * @param    year    A string that contains the year of the desired revenue.
   * @return   revenue A double that contains the revenue of the given date and year.
   */
  public Double revenue(String date, String year) {
    return fileUtility.SumScanner(1, date, year);
  }

  /**
   * Writes into the BackOrderProducts what has yet to arrive in
   * the store.
   *
   * @param    store    The store of which the back order is to be found.
   *
   */
  public void allBackOrder(Store store){
    for(Product product: store.getAllProducts().values()) {
      if (product.onBackOrder()) {
        String data = product.getUPC() + ", " + product.getName() + "\n";
        fileUtility.FileWriter("BackOrderProducts.txt", data);
      }
    }
  }

  /**
   * Returns the most recent order of a particular Product product.
   * A blank string will be returned if the order does not exist.
   *
   *
   * @param    product    An instance of the Product class that represents the
   *                      most recent order of which to be found.
   * @return   lastOrder  A string that represents the last order for Product product.
   */
  public String getLastOrder(Product product){
    String line;
    String lastOrder = "";
    try{
      File file = new File("OrderHistory.txt");
      Scanner sc = new Scanner(file);
      while (sc.hasNext()){
        line = sc.nextLine();
        if (line.contains(product.getName())){
          lastOrder = line;
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return lastOrder;
  }

  /**
   * Writes into the ProductsInStock.txt all of the items
   * that are currently in stock
   * in the inventory. Must have 1 or more quantity.
   *
   * @param   store    The store that contains the inventory that is
   *                   to be found.
   */
  public void productsInStock(Store store) {
    for (Product product : store.getAllProducts().values()) {
      if (product.getQuantity() != 0) {
        String data = product.getName() + "\n";
        fileUtility.FileWriter("ProductsInStock.txt", data);
      }
    }
  }
}
