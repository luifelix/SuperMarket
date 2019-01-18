package groceryStore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReceiverInterface extends GenericInterface {
  ReceiverInterface(Store store, String name) {
    super();
    setTitle("Receiver Functions");
    Receiver r = new Receiver(name);

    JButton scan = new JButton("Scan a Received Product in");
    JButton priceHistory = new JButton("Check Price History of Product");
    JButton checkCurrPrice = new JButton("Check Current Price of Product");
    JButton location = new JButton("Check Location of Product");
    JButton checkCost = new JButton("Check Cost to Order of Product");
    top.add(scan);
    top.add(priceHistory);
    top.add(checkCurrPrice);
    top.add(location);
    top.add(checkCost);

    send.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        data = input.getText();
        switch(lastButton) {
          case "Scan a Received Product in":
            String[] info = data.split(", ");
            r.scan(store, Integer.parseInt(info[0]), info[1], Integer.parseInt(info[2]),
                Double.parseDouble(info[3]), Double.parseDouble(info[4]), info[5],
                info[6], info[7]);
            output.setText("Received order #" + info[0]);
            break;
          case "Check Price History of Product":
            output.setText("Price history of " + store.getAllProducts().get(data).getName()
                + ":" +"\n" + r.priceHistory(store.getAllProducts().get(data)).toString());
            break;
          case "Check Current Price of Product":
            output.setText("Current price of " + store.getAllProducts().get(data).getName()
                + ": $" + String.format("%.2f", r.checkCurrPrice(store.getAllProducts().get(data))));
            break;
          case "Check Location of Product":
            output.setText(store.getAllProducts().get(data).getName() + " is in aisle " +
                r.location(store.getAllProducts().get(data)));
            break;
          case "Check Cost to Order of Product":
            output.setText("Cost of " + store.getAllProducts().get(data).getName()
                + ": $" + String.format("%.2f", r.checkCost(store.getAllProducts().get(data))));
            break;
        }
      }
    });

    scan.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("1, White macadamia cookie, 10, 1.25, 0.52, Snacks, Cookies, 0000002");
      }
    });

    priceHistory.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("Erase this and enter UPC in format (ex. 0000002)");
      }
    });

    checkCurrPrice.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("Erase this and enter UPC in format (ex. 0000002)");
      }
    });

    location.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("Erase this and enter UPC in format (ex. 0000002)");
      }
    });

    checkCost.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("Erase this and enter UPC in format (ex. 0000002)");
      }
    });
  }
}