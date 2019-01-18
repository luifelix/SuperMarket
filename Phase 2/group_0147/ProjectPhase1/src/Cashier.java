/**
 * This class represents the interface for the user, Cashiers.
 * Each Cashier will have a name and can call the various methods
 * in the class. This includes scanning items (selling the item),
 * checking for sale dates, and requesting for an order manually.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */

import java.io.*;
import java.util.Date;
import java.util.Formatter;

public class Cashier extends Users {
  private Date today = new Date();
  private static int orderNum = 1;

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
     * @return   void        Does not return any values.
     */

  public void scan(Product product){
    product.decrement();
      try{
          FileWriter fileWriter = new FileWriter("SaleHistory", true);
          Formatter f = new Formatter(fileWriter);
          f.format(product.getName() + ", " + product.getPrice() + ", " + (product.getPrice()- product.getCost()) + ", " + today + "\n");
          f.close();
      }catch (Exception e){
          System.out.println("File does not exist.");
      }
    if (product.underThreshold() && !(product.onBackOrder())){
      // reorder
      product.setOnBackOrder();
     try{
        FileWriter fileWriter = new FileWriter("OrderHistory" , true);
        Formatter f = new Formatter(fileWriter);
        f.format(orderNum + ", " + product.getName() + ", " + (product.getThreshold())*3 + ", " + product.getPrice() +
                ", " + product.getCost() + ", " + product.getSection() + ", " + product.getUPC() + ", " + today +
                ", N/A\n");
        product.setOrderNum(orderNum);
        orderNum++;
        f.close();
      }catch (Exception e) {
        System.out.println("File does not exist.");
      }
    }
  }

    /**
     * Allows the user, Cashier, to manually add an order to the distributor.
     *
     * Does not return any value, but edits a pre-existing OrderHistory.txt file.
     *
     * @param    product     An instance of Product and the item that will be ordered.
     * @param    quantity    An Integer of the total number of product to order.
     * @return   void
     */
    public void manualOrder(Product product, int quantity) {
        try {
            FileWriter fileWriter = new FileWriter("OrderHistory", true);
            Formatter f = new Formatter(fileWriter);
            f.format(orderNum + ", " + product.getName() + ", " + quantity + ", " + product.getPrice() +
                    ", " + product.getCost() + ", " + product.getSection() + ", " + product.getUPC() + ", " + today +
                    ", N/A\n");
            product.setOrderNum(orderNum);
            orderNum++;
            f.close();
        } catch (Exception e) {
            System.out.println("File does not exist.");
        }
    }
}