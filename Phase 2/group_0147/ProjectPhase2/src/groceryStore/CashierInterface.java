package groceryStore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * This class CashierInterface, which extends from abstract class
 * GenericInterface. This interface is responsible for all of the
 * possible functions that a Cashier can do all in the form of
 * buttons and text input.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */

public class CashierInterface extends GenericInterface {
  CashierInterface(Store store, String name) {
    super();
    setTitle("Cashier Functions");
    Cashier c = new Cashier(name);

    JButton scan = new JButton("Scan Product");
    JButton checkOnSale = new JButton("Check if Product on Sale");
    JButton manualOrder = new JButton("Order a Product");
    top.add(scan);
    top.add(checkOnSale);
    top.add(manualOrder);

    send.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        data = input.getText();
        switch (lastButton) {
          case "Scan Product":
            c.scan(store, store.getAllProducts().get(data));
            output.setText("Took out one " + store.getAllProducts().get(data).getName()
                    + " from the inventory");
            break;
          case "Check if Product on Sale":
            output.setText(store.getAllProducts().get(data).getName() + " on sale? "
                    + c.checkOnSale(store.getAllProducts().get(data)));
            break;
          case "Order a Product":
            String[] info = data.split(", ");
            c.manualOrder(store, store.getAllProducts().get(info[0]), Integer.parseInt(info[1]));
            output.setText("Order of " + info[1] + " " + store.getAllProducts().get(info[0]).getName()
                    + " sent manually");
            break;
        }
      }
    });

    scan.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("Erase this and enter UPC in format (ex. 0000002)");
      }
    });

    checkOnSale.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("Erase this and enter UPC in format (ex. 0000002)");
      }
    });

    manualOrder.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("Erase this and enter UPC and quantity in format (ex. 0000002, 10)");
      }
    });
  }
}

