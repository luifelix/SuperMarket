package groceryStore;
import java.awt.*;
import javax.swing.*;

/**
 * This class GenericInterface, which extends from JFrame.
 * This interface is responsible for the overall format of which
 * all of the other interfaces such as Manager, Cashier, Receiver
 * and Reshelvers need to follow.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */

public abstract class GenericInterface extends JFrame {
  JPanel bottom;
  JPanel top;
  JButton send;
  JTextArea output;
  JTextField input;
  String data;
  String lastButton = "";

  GenericInterface() {
    JSplitPane split = new JSplitPane();
    top = new JPanel();
    bottom = new JPanel();
    JScrollPane scroll = new JScrollPane();
    JPanel inputPanel = new JPanel();
    output = new JTextArea();
    input = new JTextField();
    send = new JButton("Send");

    setPreferredSize(new Dimension(500, 400));
    split.setOrientation(JSplitPane.VERTICAL_SPLIT);
    split.setDividerLocation(200);
    split.setTopComponent(top);
    split.setBottomComponent(bottom);
    add(split);

    bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

    bottom.add(inputPanel);
    inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

    inputPanel.add(input);
    inputPanel.add(send);
    bottom.add(scroll);
    scroll.setViewportView(output);

    pack();
  }
  }
