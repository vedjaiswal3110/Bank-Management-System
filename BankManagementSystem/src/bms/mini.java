package bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener {
    String pin;
    JButton b1;
    JLabel lb1, lb2, lb3, lb4;
    Connection con;
    PreparedStatement pst1, pst2;
    ResultSet rs;
    

    mini(String pin) {
        this.pin = pin;
        setTitle("Mini Statement");
        getContentPane().setBackground(new Color(222, 255, 228));
        setSize(500, 600);
        setLocation(500, 150);
        setLayout(null);

        lb2 = new JLabel("BANK STATEMENT");
        lb2.setFont(new Font("System", Font.BOLD, 20));
        lb2.setBounds(150, 20, 300, 30);
        add(lb2);

        lb3 = new JLabel();
        lb3.setFont(new Font("Arial", Font.BOLD, 14));
        lb3.setBounds(50, 70, 400, 25);
        add(lb3);

        lb1 = new JLabel();
        lb1.setFont(new Font("Arial", Font.PLAIN, 14));
        lb1.setBounds(50, 120, 400, 250);
        add(lb1);

        lb4 = new JLabel();
        lb4.setFont(new Font("Arial", Font.BOLD, 16));
        lb4.setBounds(50, 380, 400, 30);
        add(lb4);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms", "root", "players004");
            
            pst1 = con.prepareStatement("SELECT * FROM login WHERE pin = ?");
            pst1.setString(1, pin);
            rs = pst1.executeQuery();
            
            if (rs.next()) {
                lb3.setText("Card Number: " + rs.getString("card_number").substring(0, 4) + "XXXXXXXX" + rs.getString("card_number").substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int balance = 0;
            pst2 = con.prepareStatement("SELECT * FROM bank WHERE pin = ?");
            pst2.setString(1, pin);
            rs = pst2.executeQuery();
            
            StringBuilder statement = new StringBuilder("<html>");
            while (rs.next()) {
                statement.append(rs.getString("date"))
                         .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                         .append(rs.getString("type"))
                         .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                         .append(rs.getString("amount"))
                         .append("<br><br>");
                
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            statement.append("</html>");
            lb1.setText(statement.toString());
            lb4.setText("Your Total Balance is Rs " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b1 = new JButton("Exit");
        b1.setBounds(200, 500, 100, 30);
        b1.addActionListener(this);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        add(b1);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini("");
    }
}
