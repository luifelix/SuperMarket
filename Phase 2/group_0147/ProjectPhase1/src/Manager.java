/**
 * This class represents the interface for the user, Manager.
 * Each Manager will have a name and can call the various methods
 * in the class. This includes checking on pending orders,
 * and finding out the profits and revenue of a particular day.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;

public class Manager extends Users {
  private String line = "";
  public Manager(String name){
    super(name);
  }

  /**
   * Goes over a pre-existing OrderHistory.txt file and prints out the orders that
   * have yet to arrive, also known as "pending".
   *
   * This method does not return any values, but prints all pending orders.
   *
   */

  public void pendingOrders(){
    try{
      File file = new File("OrderHistory");
      Scanner sc = new Scanner(file);
      while(sc.hasNext()){
        line = sc.nextLine();
        if (line.contains("N/A")){
          System.out.println(line);
        }
      }
    }catch (Exception e){
      System.out.println("File does not exist.");
    }
  }

  /**
   * Prints out the total profit of a particular given date by reading from
   * a pre-existing SaleHistory.txt file.
   *
   * Does not return any value.
   *
   * @param    date    A string that contains the day and month of the desired profit.
   * @param    year    A string that contains the year of the desired profit.
   * @return   void
   */

  public Double profit(String date, String year) {
    Double profit = 0.0;
    String[] stringSale;
    try {
      File file = new File("SaleHistory");
      Scanner sc = new Scanner(file);
      while (sc.hasNext()) {
        line = sc.nextLine();
        if (line.contains(date) && line.contains(year)) {
          stringSale = line.split(", ");
          profit += Double.parseDouble(stringSale[2]);
        }
      }
    } catch (Exception e) {
      System.out.println("File does not exist.");
    }
    return profit;
  }

  /**
   * Prints out the total revenue of a particular date by reading from a
   * pre-existing SaleHistory.txt file.
   *
   * Does not return any value.
   *
   * @param    date    A string that contains the day and month of the desired revenue.
   * @param    year    A string that contains the year of the desired revenue.
   * @return   void
   */
  public Double revenue(String date, String year) {
    Double revenue = 0.0;
    String[] stringSale;
    try {
      File file = new File("SaleHistory");
      Scanner sc = new Scanner(file);
      while (sc.hasNext()) {
        line = sc.nextLine();
        if (line.contains(date) && line.contains(year)) {
          stringSale = line.split(",");
          revenue += Double.parseDouble(stringSale[1]);
        }
      }
    } catch (Exception e) {
      System.out.println("File does not exist.");
    }
    return revenue;
  }

  public void allBackOrder(){
    try {
      PrintWriter writer3 = new PrintWriter("BackOrderProducts");
      writer3.print("");
      writer3.close();
    }catch (Exception e) {
      System.out.println("There seems to be an error.");
    }
      try{
        FileWriter fileWriter = new FileWriter("BackOrderProducts", true);
        Formatter f = new Formatter(fileWriter);
        for(Product product: Section.allProducts.values()){
          if(product.onBackOrder()){
            f.format(product.getUPC() + ", " + product.getName() + "\n");
          }
        }f.close();
      }catch (Exception e){
      System.out.println("File does not exist.");
      }

  }
}

