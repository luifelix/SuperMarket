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
            PrintWriter writer = new PrintWriter("OrderHistory");
            PrintWriter writer1 = new PrintWriter("PriceHistory");
            PrintWriter writer2 = new PrintWriter("SaleHistory");
            PrintWriter writer3 = new PrintWriter("BackOrderProducts");
            PrintWriter writer4 = new PrintWriter("SaleProducts");
            writer.print("");
            writer.close();
            writer1.print("");
            writer1.close();
            writer2.print("");
            writer2.close();
            writer3.print("");
            writer3.close();
            writer4.print("");
            writer4.close();
        } catch (Exception e) {
            System.out.println("There seems to be an error.");
        }
        try {
            File file = new File("events");
            Formatter f = new Formatter("log");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                line = sc.nextLine();
                String[] lineList = line.split(", ");

                Store store = new Store("Sam's Market");
                Receiver r = new Receiver("Amy");
                Cashier c = new Cashier("Bob");
                Reshelver re = new Reshelver("Cathy");
                Manager m = new Manager("Douglas");

                switch (lineList[0]) {
                    case "Product":
                        switch (lineList[1]){
                            case "setSale":
                                Date startDate = new SimpleDateFormat("yyyy/MM/dd" ).parse( lineList[4]);
                                Date endDate = new SimpleDateFormat("yyyy/MM/dd" ).parse( lineList[5]);
                                Section.allProducts.get(lineList[2]).setSale(Double.parseDouble(lineList[3]), startDate,
                                        endDate);
                                System.out.println("Set " + Section.allProducts.get(lineList[2]).getName() +
                                        " to sale starting from " + lineList[4] + " to " + lineList[5]);
                                f.format("Set " + Section.allProducts.get(lineList[2]).getName() +
                                        " to sale starting from " + lineList[4] + " to " + lineList[5] + "\n");
                                break;
                            case "putOnSale":
                                Section.allProducts.get(lineList[2]).putOnSale();
                                System.out.println("Put " + Section.allProducts.get(lineList[2]).getName() + " on sale");
                                f.format("Put " + Section.allProducts.get(lineList[2]).getName() + " on sale" + "\n");
                                break;
                            case "takeOffSale":
                                Section.allProducts.get(lineList[2]).takeOffSale();
                                System.out.println("Took " + Section.allProducts.get(lineList[2]).getName() + " off sale");
                                f.format("Took " + Section.allProducts.get(lineList[2]).getName() + " off sale" + "\n");
                                break;
                            case "onSale":
                                System.out.println(Section.allProducts.get(lineList[2]).getName() + " on sale? " +
                                        Section.allProducts.get(lineList[2]).onSale());
                                f.format(Section.allProducts.get(lineList[2]).getName() + " on sale? " +
                                        Section.allProducts.get(lineList[2]).onSale() + "\n");
                                break;
                            case "setThreshold":
                                System.out.println("New threshold of " + Section.allProducts.get(lineList[2]).getName()
                                        + " = " +
                                        Section.allProducts.get(lineList[2]).setThreshold(Integer.parseInt(lineList[3])));
                                f.format("New threshold of " + Section.allProducts.get(lineList[2]).getName() + " = " +
                                        Section.allProducts.get(lineList[2]).setThreshold(Integer.parseInt(lineList[3]))
                                        + "\n");
                                break;
                        }
                    case "Receiver":
                        switch (lineList[1]) {
                            case "scan":
                                r.scan(Integer.parseInt(lineList[2]), lineList[3], Integer.parseInt(lineList[4]),
                                        Double.parseDouble(lineList[5]), Double.parseDouble(lineList[6]), lineList[7],
                                        lineList[8], lineList[9]);
                                System.out.println("Received order #" + lineList[2]);
                                f.format("Received order #" + lineList[2] + "\n");
                                break;
                            case "priceHistory":
                                System.out.println("Price history of " + Section.allProducts.get(lineList[2]).getName()
                                        + ":");
                                r.priceHistory(Section.allProducts.get(lineList[2]));
                                f.format("Price history of " + Section.allProducts.get(lineList[2]).getName() +
                                        " print to screen\n");
                                break;
                        }
                    case "Cashier":
                        switch (lineList[1]) {
                            case "cscan":
                                c.scan(Section.allProducts.get(lineList[2]));
                                System.out.println("Took out one " + Section.allProducts.get(lineList[2]).getName() +
                                        " from the inventory");
                                f.format("Took out one " + Section.allProducts.get(lineList[2]).getName() +
                                        " from the inventory" + "\n");
                                break;
                            case "manualOrder":
                                c.manualOrder(Section.allProducts.get(lineList[2]), Integer.parseInt(lineList[3]));
                                System.out.println("Order of " + lineList[3] + " " +
                                        Section.allProducts.get(lineList[2]).getName() + " sent manually");
                                f.format("Order of " + lineList[3] + " " +
                                        Section.allProducts.get(lineList[2]).getName() + " sent manually" + "\n");
                                break;
                        }
                    case "Reshelver":
                        switch (lineList[1]) {
                            case "setSectionAisle":
                                re.setSectionAisle(lineList[2], Integer.parseInt(lineList[3]));
                                System.out.println("Grouped " + lineList[2] + " in aisle " + lineList[3]);
                                f.format("Grouped " + lineList[2] + " in aisle " + lineList[3] + "\n");
                                break;
                            case "setAisle":
                                re.setAisle(Section.allProducts.get(lineList[2]), Integer.parseInt(lineList[3]));
                                System.out.println("Put " + Section.allProducts.get(lineList[2]).getName() +
                                        " in aisle " + lineList[3]);
                                f.format("Put " + Section.allProducts.get(lineList[2]).getName() + " in aisle " +
                                        lineList[3] + "\n");
                                break;
                            case "currentQuantity":
                                System.out.println("Current quantity of " +
                                        Section.allProducts.get(lineList[2]).getName() + " = " +
                                        re.currentQuantity(Section.allProducts.get(lineList[2])));
                                f.format("Current quantity of " + Section.allProducts.get(lineList[2]).getName() +
                                        " = " + re.currentQuantity(Section.allProducts.get(lineList[2])) + "\n");
                                break;
                            case "location":
                                System.out.println(Section.allProducts.get(lineList[2]).getName() + " is in aisle " +
                                        re.location(Section.allProducts.get(lineList[2])));
                                f.format(Section.allProducts.get(lineList[2]).getName() + " is in aisle " +
                                        re.location(Section.allProducts.get(lineList[2])) + "\n");
                                break;
                            case "orderHistory":
                                System.out.println("Order history of " + Section.allProducts.get(lineList[2]).getName()
                                        + ":");
                                re.orderHistory(Section.allProducts.get(lineList[2]));
                                f.format("Order history of " + Section.allProducts.get(lineList[2]).getName() +
                                        " print to screen\n");
                        }
                    case "Manager":
                        switch (lineList[1]) {
                            case "pendingOrder":
                                System.out.println("Pending order(s):");
                                m.pendingOrders();
                                f.format("Pending order(s): print to screen\n");
                                break;
                            case "backOrderProducts":
                                m.allBackOrder();
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
                        }
                }
            } f.close();
        } catch (Exception e) {
            System.out.println("File does not exist.");
        }

    }
}
