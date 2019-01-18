package groceryStore;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

/**
 * This class groceryInterface. This is the first place that users interact
 * with to log in as either Manager, Reshelver, Cashier, or Receiver.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */

public class groceryInterface extends JFrame {

  public static void main(String[] args) {
    JFrame firstFrame = new JFrame();
    JPanel firstPanel = new JPanel();
    JButton loginButton = new JButton("Log In");
    JButton exitButton = new JButton("Exit");
    JTextField userField = new JTextField("Enter Username:");
    JTextField passwordField = new JTextField("Enter Password:");
    JLabel labelMessage = new JLabel("Welcome to Sam's Market! Please log in.");
    firstFrame.setVisible(true);

    Store store = new Store("Sam's Market");

    loginButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        String user = userField.getText();
        String password = passwordField.getText();
        String line;
        try {
          File file = new File("login");
          Scanner sc = new Scanner(file);
          while(sc.hasNext()) {
            line = sc.nextLine();
            if (line.contains(user) && line.contains(password)) {
              String[] lineContain = line.split(",");
              switch(lineContain[2]) {
                case "Manager":
                  labelMessage.setText("Welcome " + lineContain[3] + "!");
                  ManagerInterface mr = new ManagerInterface(store, lineContain[3]);
                  mr.setVisible(true);
                  break;
                case "Receiver":
                  labelMessage.setText("Welcome " + lineContain[3] + "!");
                  ReceiverInterface rr = new ReceiverInterface(store, lineContain[3]);
                  rr.setVisible(true);
                  break;
                case "Cashier":
                  labelMessage.setText("Welcome " + lineContain[3] + "!");
                  CashierInterface cr = new CashierInterface(store, lineContain[3]);
                  cr.setVisible(true);
                  break;
                case "Reshelver":
                  labelMessage.setText("Welcome " + lineContain[3] + "!");
                  ReshelverInterface rsr = new ReshelverInterface(store, lineContain[3]);
                  rsr.setVisible(true);
                  break;
              }
              break;
            } else {
              labelMessage.setText("The username and/or password is incorrect. Please try again!");
            }
          }
        }catch(Exception e) {
          System.out.println("File does not exist.");
        }
      }
    });

    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });


    userField.setPreferredSize(new Dimension(150, 20));
    passwordField.setPreferredSize(new Dimension(150, 20));

    firstFrame.setTitle("Grocery Store");
    firstFrame.setSize(new Dimension(500,400));
    firstFrame.setPreferredSize(new Dimension(500,400));
    firstFrame.setLocationRelativeTo(null);
    firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //firstFrame.setVisible(true);
    firstFrame.setResizable(true);

    firstPanel.add(userField);
    firstPanel.add(passwordField);
    firstPanel.add(loginButton);
    firstPanel.add(exitButton);
    firstPanel.add(labelMessage);

    firstFrame.add(firstPanel);

  }
}
