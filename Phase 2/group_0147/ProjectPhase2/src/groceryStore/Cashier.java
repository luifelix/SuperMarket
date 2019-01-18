package groceryStore;

import java.util.Date;

/**
 * This class Cashier, which extends from abstract class User.
 * Each Cashier will be given a name and can call various methods
 * in this class including scanning products to take out of the
 * inventory, checking if an item is on sale, and manually ordering products.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */

public class Cashier extends Users {
  public FileUtility fileUtility;
  private Date today = new Date();

  public Cashier(String name) {
    super(name);
  }

  /**
   * Takes a Product product and each time said product is scanned, one is
   * removed from the Inventory. Then, product will be added to the
   * SaleHistory.txt. If decrement of product lowers the quantity to below
   * threshold, an order will be automatically made to the distributor
   * to request for more.
   *
   * This method does not return any value, but will edit pre-existing data.
   * @param    product     An instance of Product and contains the item of the
   *                       item being scanned through.
   * @param    store       An instance of Store and contains information such as
   *                       name of the store and specific orders for the particular
   *                       store.
   */
  public void scan(Store store, Product product) {
    product.decrement();
    String data = product.getName() + ", " + product.getPrice().getPrice() + ", " + (product.getPrice().getPrice()
            - product.getPrice().getCost()) + ", " + today + "\n";
    fileUtility.FileWriter("SaleHistory.txt", data);
    if (product.underThreshold() && !(product.onBackOrder())) {
      // reorder
      product.setOnBackOrder();
      String info = store.getOrderNum() + ", " + product.getName() + ", " + (product.getThreshold()) * 3 + ", "
              + product.getPrice().getPrice() + ", " + product.getPrice().getCost() + ", "
              + product.getLocation().getSection() + ", " + product.getUPC() + ", " + today + ", N/A\n";
      fileUtility.FileWriter("OrderHistory.txt" , info);
      store.incrementOrderNum();
    }
  }

  /**
   * Returns whether Product product is on sale.
   *
   * @param    product     A product to check whether it is on sale.
   * @return   boolean     The answer to whether the product is on sale.
   */

  public boolean checkOnSale(Product product){
    return product.getPrice().onSale();
  }

  /**
   * Allows the user, Cashier, to manually add an order to the distributor.
   *
   * Does not return any value, but edits a pre-existing OrderHistory.txt file.
   *
   * @param    store       An instance of Store of which the products is found.
   * @param    product     An instance of Product and the item that will be ordered.
   * @param    quantity    An Integer of the total number of product to order.
   */
  public void manualOrder(Store store, Product product, int quantity) {
    String data = store.getOrderNum() + ", " + product.getName() + ", " + quantity + ", "
            + product.getPrice().getPrice() + ", " + product.getPrice().getCost() + ", "
            + product.getLocation().getSection() + ", " + product.getUPC() + ", " + today + ", N/A\n";
    fileUtility.FileWriter("OrderHistory.txt", data);
    store.incrementOrderNum();
  }
}
