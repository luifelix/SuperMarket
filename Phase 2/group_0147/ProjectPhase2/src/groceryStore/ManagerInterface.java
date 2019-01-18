package groceryStore;

import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Formatter;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;
import java.io.*;

/**
* Created by squinto on 2017-08-07.
*/
public class ManagerInterface extends GenericInterface {

 ManagerInterface(Store store, String name) {
    super();
    setTitle("Manager Functions");
    Manager m = new Manager(name);

    JButton pendingOrders = new JButton("Check Pending Orders");
    JButton profit = new JButton("Check Profit on Specific Date");
    JButton revenue = new JButton("Check Revenue on Specific Date");
    JButton backOrders = new JButton("Check Products on Back Order");
    JButton lastOrder = new JButton("Check Last Order of Product");
    JButton stockProducts = new JButton("Check Products in Stock");
    JButton setSale = new JButton("Set Sale Dates for Product");
    JButton putOnSale = new JButton("Put Product on Sale");
    JButton takeOffSale = new JButton("Take Product off Sale");
    JButton setThreshold = new JButton("Set Threshold of Product");
    top.add(pendingOrders);
    top.add(profit);
    top.add(revenue);
    top.add(backOrders);
    top.add(lastOrder);
    top.add(stockProducts);
    top.add(setSale);
    top.add(putOnSale);
    top.add(takeOffSale);
    top.add(setThreshold);

   send.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
       data = input.getText();
       switch(lastButton) {
         case "Check Profit on Specific Date":
           String[] proDate = data.split(", ");
           output.setText("The total profit of " + proDate[0] + " " + proDate[1] + " = $" +
               String.format("%.2f", m.profit(proDate[0], proDate[1])));
           break;
         case "Check Revenue on Specific Date":
           String[] revDate = data.split(", ");
           output.setText("The total revenue of " + revDate[0] + " " + revDate[1] + " = $" +
               String.format("%.2f", m.revenue(revDate[0], revDate[1])));
           break;
         case "Check Last Order of Product":
           output.setText(m.getLastOrder(store.getAllProducts().get(data)));
           break;
         case "Set Sale Dates for Product":
             try{
                 String[] saleInfo = data.split(", ");
                 Date startDate = new SimpleDateFormat("yyyy/MM/dd" ).parse(saleInfo[2]);
                 Date endDate = new SimpleDateFormat("yyyy/MM/dd" ).parse(saleInfo[3]);
                 store.getAllProducts().get(saleInfo[0]).setSale(Double.parseDouble(saleInfo[1]), startDate,
                         endDate);
                 output.setText("Set " + store.getAllProducts().get(saleInfo[0]).getName() +
                         " to sale starting from " + saleInfo[2] + " to " + saleInfo[3]);
             } catch (ParseException exception){
                 exception.printStackTrace();
            }
           break;
         case "Put Product on Sale":
           store.getAllProducts().get(data).putOnSale();
           output.setText("Put " + store.getAllProducts().get(data).getName() + " on sale");
           break;
         case "Take Product off Sale":
           store.getAllProducts().get(data).takeOffSale();
           output.setText("Took " + store.getAllProducts().get(data).getName() + " off sale");
           break;
         case "Set Threshold of Product":
           String[] info = data.split(", ");
           output.setText("New threshold of " + store.getAllProducts().get(info[0]).getName()
               + " = " +
               store.getAllProducts().get(info[0]).setThreshold(Integer.parseInt(info[1])));
           break;
       }
     }
   });

    pendingOrders.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            output.setText(m.pendingOrders().toString());
        }
    });
    
    profit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            lastButton = e.getActionCommand();
            input.setText("Erase this and enter date in format (ex. Aug 07, 2017)");
        }
     });
    
    revenue.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            lastButton = e.getActionCommand();
            input.setText("Erase this and enter date in format (ex. Aug 07, 2017)");
        }
    });
    
    backOrders.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.allBackOrder(store);
            try
                {
                  FileReader reader = new FileReader("BackOrderProducts.txt");
                  BufferedReader br = new BufferedReader(reader);
                  output.read(br,null );
                  br.close();
                }
                catch(Exception e2) { System.out.println(e2); }

        }
    });
    
    lastOrder.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            lastButton = e.getActionCommand();
            input.setText("Erase this and enter UPC in format (ex. 0000002)");
        }
    });

   stockProducts.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
       m.productsInStock(store);
       try
       {
         FileReader reader = new FileReader("ProductsInStock.txt");
         BufferedReader br = new BufferedReader(reader);
         output.read(br,null );
         br.close();
       }
       catch(Exception e2) { System.out.println(e2); }
     }
   });

   setSale.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
       lastButton = e.getActionCommand();
       input.setText("0000008, 2.99, 2017/07/25, 2017/07/28");
     }
   });

   putOnSale.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
       lastButton = e.getActionCommand();
       input.setText("Erase this and enter UPC in format (ex. 0000002)");
     }
   });

   takeOffSale.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
       lastButton = e.getActionCommand();
       input.setText("Erase this and enter UPC in format (ex. 0000002)");
     }
   });

   setThreshold.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
       lastButton = e.getActionCommand();
       input.setText("Erase this and enter UPC and new threshold in format (ex. 0000005, 15)");
     }
   });
}
}