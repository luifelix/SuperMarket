/*
import java.io.*;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

public class test {
    private static int i = 0;

    public static void main(String[] args) {
        Date today = new Date();
        try{
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
        }catch (Exception e){
            System.out.println("LOL");
        }

        Receiver r1 = new Receiver("Ann");
        r1.scan(0, "Donut", 9, 9.99 , 5.00 , "C" , "B" , "0000000");
        r1.scan(0, "Apple", 8, 1.00 , 0.50 , "A" , "D" , "0000001");
        Cashier c1 = new Cashier("Bob");
        c1.scan(Section.allProducts.get("0000000"));
        c1.scan(Section.allProducts.get("0000001"));
        //product.setSale(5.01, new Date("07/25/2017 01:04:49"), new Date("07/26/2017 01:04:49"));
        System.out.println(today.toString());
        //r1.priceHistory(product);
        Manager m1 = new Manager("Sam Smith");
        m1.profit("Jul 24" , "2017");
        m1.revenue("Jul 24" , "2017");
        //c1.manualOrder(product, 10);
        r1.scan(1, "Cookie", 10, 9.99 , 5.00 , "C" , "B" , "0000002");
        Reshelver boy = new Reshelver("Jasper");
        boy.orderHistory(Section.allProducts.get("0000000"));
        //boy.currentQuantity(product);
        //product.putOnSale();
       // product2.putOnSale();
       // product.takeOffSale();
        //m1.allBackOrder();
        //m1.pendingOrders();
        boy.setSectionAisle("C", 1);
        //System.out.println(product.getAisle());

        */
/*for (Product p : Section.allProducts){
            System.out.println(p.getName());
            System.out.println(p.getAisle());
        }*//*

    }


}
*/
