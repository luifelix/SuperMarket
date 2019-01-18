package groceryStore;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

public class Simulation {

  public static void main(String[] args) {
    String line;
    try {
      PrintWriter writer1= new PrintWriter("OrderHistory.txt");
      PrintWriter writer2 = new PrintWriter("PriceHistory.txt");
      PrintWriter writer3 = new PrintWriter("SaleHistory.txt");
      PrintWriter writer4 = new PrintWriter("BackOrderProducts.txt");
      PrintWriter writer5 = new PrintWriter("SaleProducts.txt");
      PrintWriter writer6 = new PrintWriter("ProductsInStock.txt");
      PrintWriter writer7 = new PrintWriter("DamagedProducts.txt");
      writer1.print("");
      writer1.close();
      writer2.print("");
      writer2.close();
      writer3.print("");
      writer3.close();
      writer4.print("");
      writer4.close();
      writer5.print("");
      writer5.close();
      writer6.print("");
      writer6.close();
      writer7.print("");
      writer7.close();
    } catch (Exception e) {
      System.out.println("There seems to be an error.");
    }

    Store store = new Store("Sam's Market");
    Receiver r = new Receiver("Amy");
    Cashier c = new Cashier("Bob");
    Reshelver re = new Reshelver("Cathy");
    Manager m = new Manager("Douglas");

    try {
      File file = new File("events.txt");
      Formatter f = new Formatter("log.txt");
      Scanner sc = new Scanner(file);
      while (sc.hasNext()) {
        line = sc.nextLine();
        String[] lineList = line.split(", ");

        switch (lineList[0]) {
          case "Product":
            switch (lineList[1]){
              case "setSale":
                Date startDate = new SimpleDateFormat("yyyy/MM/dd" ).parse( lineList[4]);
                Date endDate = new SimpleDateFormat("yyyy/MM/dd" ).parse( lineList[5]);
                store.getAllProducts().get(lineList[2]).setSale(Double.parseDouble(lineList[3]), startDate,
                    endDate);
                System.out.println("Set " + store.getAllProducts().get(lineList[2]).getName() +
                    " to sale starting from " + lineList[4] + " to " + lineList[5]);
                f.format("Set " + store.getAllProducts().get(lineList[2]).getName() +
                    " to sale starting from " + lineList[4] + " to " + lineList[5] + "\n");
                break;
              case "putOnSale":
                store.getAllProducts().get(lineList[2]).putOnSale();
                System.out.println("Put " + store.getAllProducts().get(lineList[2]).getName() + " on sale");
                f.format("Put " + store.getAllProducts().get(lineList[2]).getName() + " on sale" + "\n");
                break;
              case "takeOffSale":
                store.getAllProducts().get(lineList[2]).takeOffSale();
                System.out.println("Took " + store.getAllProducts().get(lineList[2]).getName() + " off sale");
                f.format("Took " + store.getAllProducts().get(lineList[2]).getName() + " off sale" + "\n");
                break;
              case "setThreshold":
                System.out.println("New threshold of " + store.getAllProducts().get(lineList[2]).getName()
                    + " = " +
                    store.getAllProducts().get(lineList[2]).setThreshold(Integer.parseInt(lineList[3])));
                f.format("New threshold of " + store.getAllProducts().get(lineList[2]).getName() + " = " +
                    store.getAllProducts().get(lineList[2]).setThreshold(Integer.parseInt(lineList[3]))
                    + "\n");
                break;
              case "getSection":
                System.out.println(store.getAllProducts().get(lineList[2]).getName() + " is in "
                    + store.getAllProducts().get(lineList[2]).getLocation().getSubsection() + " under "
                    + store.getAllProducts().get(lineList[2]).getLocation().getSection() );
                f.format((store.getAllProducts().get(lineList[2]).getName() + " is in "
                    + store.getAllProducts().get(lineList[2]).getLocation().getSubsection() + " under "
                    + store.getAllProducts().get(lineList[2]).getLocation().getSection() + "\n"));
                break;
            }
          case "Receiver":
            switch (lineList[1]) {
              case "scan":
                r.scan(store, Integer.parseInt(lineList[2]), lineList[3], Integer.parseInt(lineList[4]),
                    Double.parseDouble(lineList[5]), Double.parseDouble(lineList[6]), lineList[7],
                    lineList[8], lineList[9]);
                System.out.println("Received order #" + lineList[2]);
                f.format("Received order #" + lineList[2] + "\n");
                break;
              case "priceHistory":
                System.out.println("Price history of " + store.getAllProducts().get(lineList[2]).getName()
                    + ":");
                r.priceHistory(store.getAllProducts().get(lineList[2]));
                f.format("Price history of " + store.getAllProducts().get(lineList[2]).getName() +
                    " print to screen\n");
                break;
              case "checkCurrPrice":
                System.out.println("Current price of " + store.getAllProducts().get(lineList[2]).getName()
                    + ": $" + r.checkCurrPrice(store.getAllProducts().get(lineList[2])));
                f.format("Current price of " + store.getAllProducts().get(lineList[2]).getName() + ": $"
                    + r.checkCurrPrice(store.getAllProducts().get(lineList[2])) + "\n");
                break;
              case "location":
                System.out.println(store.getAllProducts().get(lineList[2]).getName() + " is in aisle " +
                    r.location(store.getAllProducts().get(lineList[2])));
                f.format(store.getAllProducts().get(lineList[2]).getName() + " is in aisle " +
                    r.location(store.getAllProducts().get(lineList[2])) + "\n");
                break;
              case "checkCost":
                System.out.println("Cost of " + store.getAllProducts().get(lineList[2]).getName()
                    + ": $" + r.checkCost(store.getAllProducts().get(lineList[2])));
                f.format("Cost of " + store.getAllProducts().get(lineList[2]).getName() + ": $"
                    + r.checkCost(store.getAllProducts().get(lineList[2])) + "\n");
                break;
            }
          case "Cashier":
            switch (lineList[1]) {
              case "cscan":
                c.scan(store, store.getAllProducts().get(lineList[2]));
                System.out.println("Took out one " + store.getAllProducts().get(lineList[2]).getName() +
                    " from the inventory");
                f.format("Took out one " + store.getAllProducts().get(lineList[2]).getName() +
                    " from the inventory" + "\n");
                break;
              case "manualOrder":
                c.manualOrder(store, store.getAllProducts().get(lineList[2]), Integer.parseInt(lineList[3]));
                System.out.println("Order of " + lineList[3] + " " +
                    store.getAllProducts().get(lineList[2]).getName() + " sent manually");
                f.format("Order of " + lineList[3] + " " +
                    store.getAllProducts().get(lineList[2]).getName() + " sent manually" + "\n");
                break;
              case "checkOnSale":
                System.out.println(store.getAllProducts().get(lineList[2]).getName() + " on sale? " +
                    c.checkOnSale(store.getAllProducts().get(lineList[2])));
                f.format(store.getAllProducts().get(lineList[2]).getName() + " on sale? " +
                    c.checkOnSale(store.getAllProducts().get(lineList[2])) + "\n");
                break;
            }
          case "Reshelver":
            switch (lineList[1]) {
              case "setSectionAisle":
                re.setSectionAisle(store, lineList[2], Integer.parseInt(lineList[3]));
                System.out.println("Grouped " + lineList[2] + " in aisle " + lineList[3]);
                f.format("Grouped " + lineList[2] + " in aisle " + lineList[3] + "\n");
                break;
              case "setAisle":
                re.setAisle(store.getAllProducts().get(lineList[2]), Integer.parseInt(lineList[3]));
                System.out.println("Put " + store.getAllProducts().get(lineList[2]).getName() +
                    " in aisle " + lineList[3]);
                f.format("Put " + store.getAllProducts().get(lineList[2]).getName() + " in aisle " +
                    lineList[3] + "\n");
                break;
              case "currentQuantity":
                System.out.println("Current quantity of " +
                    store.getAllProducts().get(lineList[2]).getName() + " = " +
                    re.currentQuantity(store.getAllProducts().get(lineList[2])));
                f.format("Current quantity of " + store.getAllProducts().get(lineList[2]).getName() +
                    " = " + re.currentQuantity(store.getAllProducts().get(lineList[2])) + "\n");
                break;
              case "orderHistory":
                System.out.println("Order history of " + store.getAllProducts().get(lineList[2]).getName()
                    + ":");
                re.orderHistory(store.getAllProducts().get(lineList[2]));
                f.format("Order history of " + store.getAllProducts().get(lineList[2]).getName() +
                    " print to screen\n");
                break;
              case "damaged":
                re.scanDamagedItem(store, store.getAllProducts().get(lineList[2]));
                System.out.println("Damaged product: " + store.getAllProducts().get(lineList[2]).getName());
                f.format("Damaged product: " + store.getAllProducts().get(lineList[2]).getName() + "\n");
                break;
            }
          case "Manager":
            switch (lineList[1]) {
              case "pendingOrder":
                System.out.println("Pending order(s):");
                m.pendingOrders();
                f.format("Pending order(s): print to screen\n");
                break;
              case "backOrderProducts":
                m.allBackOrder(store);
                System.out.println("Created list of all backorder products");
                f.format("Created list of all backorder products\n");
                break;
              case "revenue":
                System.out.println("The total revenue of " + lineList[2] + " " + lineList[3] + " = $" +
                    m.revenue(lineList[2],lineList[3]));
                f.format("The total revenue of " + lineList[2] + " " + lineList[3] + " = $" +
                    m.revenue(lineList[2],lineList[3])+ "\n");
                break;
              case "profit":
                System.out.println("The total profit of " + lineList[2] + " " + lineList[3] + " = $" +
                    m.profit(lineList[2],lineList[3]));
                f.format("The total profit of " + lineList[2] + " " + lineList[3] + " = $" +
                    m.profit(lineList[2],lineList[3]) + "\n");
                break;
              case "getLastOrder":
                System.out.println("Last order of " + store.getAllProducts().get(lineList[2]).getName()
                    + ":\n" + m.getLastOrder(store.getAllProducts().get(lineList[2])));
                f.format("Last order of " + store.getAllProducts().get(lineList[2]).getName() + ":\n"
                    + m.getLastOrder(store.getAllProducts().get(lineList[2])) + "\n");
                break;
              case "inStock":
                m.productsInStock(store);
                System.out.println("List of products in stock created.");
                f.format("List of products in stock created.\n");
                break;
            }
        }
      } f.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
