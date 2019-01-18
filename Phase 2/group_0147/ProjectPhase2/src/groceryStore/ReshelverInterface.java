package groceryStore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ReshelverInterface extends GenericInterface {
  ReshelverInterface(Store store, String name) {
    super();
    setTitle("Reshelver Functions");
    Reshelver re = new Reshelver(name);

    JButton setSectionAisle = new JButton("Set Aisle of Section");
    JButton setAisle = new JButton("Set Aisle of Product");
    JButton currentQuantity = new JButton("Check Current Quantity of Product");
    JButton orderHistory = new JButton("Check Order History of Product");
    JButton damaged = new JButton("Scan Damaged Item");
    top.add(setSectionAisle);
    top.add(setAisle);
    top.add(currentQuantity);
    top.add(orderHistory);
    top.add(damaged);

    send.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        data = input.getText();
        switch(lastButton) {
          case "Set Aisle of Section":
            String[] info = data.split(", ");
            re.setSectionAisle(store, info[0], Integer.parseInt(info[1]));
            output.setText("Grouped " + info[0] + " in aisle " + info[1]);
            break;
          case "Set Aisle of Product":
            String[] aisleInfo = data.split(", ");
            re.setAisle(store.getAllProducts().get(aisleInfo[0]), Integer.parseInt(aisleInfo[1]));
            output.setText("Put " + store.getAllProducts().get(aisleInfo[0]).getName() +
                " in aisle " + aisleInfo[1]);
            break;
          case "Check Current Quantity of Product":
            output.setText("Current quantity of " +
                store.getAllProducts().get(data).getName() + " = " +
                re.currentQuantity(store.getAllProducts().get(data)));
            break;
          case "Check Order History of Product":
            output.setText("Order history of " + store.getAllProducts().get(data).getName()
                + ":" + "\n" + re.orderHistory(store.getAllProducts().get(data)).toString());
            break;
          case "Scan Damaged Item":
            re.scanDamagedItem(store, store.getAllProducts().get(data));
            output.setText("Damaged product: " + store.getAllProducts().get(data).getName());
            break;
        }
      }
    });

    setSectionAisle.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("Erase this and enter section and aisle in format (ex. Drinks, 4)");
      }
    });

    setAisle.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("Erase this and enter UPC and aisle in format (ex. 0000002, 2)");
      }
    });

    currentQuantity.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("Erase this and enter UPC in format (ex. 0000002)");
      }
    });

    orderHistory.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("Erase this and enter UPC in format (ex. 0000002)");
      }
    });

    damaged.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lastButton = e.getActionCommand();
        input.setText("Erase this and enter UPC in format (ex. 0000002)");
      }
    });
  }
}
