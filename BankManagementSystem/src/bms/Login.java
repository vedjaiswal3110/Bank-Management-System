package bms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JLabel lb1, lb2, lb3;
    JTextField j1;
    JPasswordField jp;
    JButton b1, b2, b3;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    Login() {
        setLayout(null);
        setTitle("Bank Management System");
        setSize(850, 480);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon i1 = new ImageIcon(getClass().getResource("/bms/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(350, 10, 100, 100);
        add(image);

        lb1 = new JLabel("WELCOME TO ATM");
        lb1.setForeground(Color.WHITE);
        lb1.setFont(new Font("AvantGarde", Font.BOLD, 38));
        lb1.setBounds(230, 125, 450, 40);
        add(lb1);

        lb2 = new JLabel("Card No:");
        lb2.setFont(new Font("Ralway", Font.BOLD, 28));
        lb2.setForeground(Color.WHITE);
        lb2.setBounds(200, 190, 375, 30);
        add(lb2);

        lb3 = new JLabel("PIN:");
        lb3.setFont(new Font("Ralway", Font.BOLD, 28));
        lb3.setForeground(Color.WHITE);
        lb3.setBounds(200, 230, 375, 30);
        add(lb3);

        j1 = new JTextField(15);
        j1.setFont(new Font("Arial", Font.BOLD, 16));
        j1.setBounds(350, 190, 230, 30);
        add(j1);

        jp = new JPasswordField();
        jp.setBounds(350, 230, 230, 30);
        add(jp);

        b1 = new JButton("SIGN IN");
        b1.setBounds(300, 300, 100, 30);
        add(b1);
        b1.addActionListener(this);

        b2 = new JButton("CLEAR");
        b2.setBounds(425, 300, 100, 30);
        add(b2);
        b2.addActionListener(this);

        b3 = new JButton("SIGN UP");
        b3.setBounds(300, 350, 225, 30);
        add(b3);
        b3.addActionListener(this);

        ImageIcon bg = new ImageIcon(getClass().getResource("/bms/backbg.png"));
        JLabel image2 = new JLabel(new ImageIcon(bg.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT)));
        image2.setBounds(0, 0, 850, 480);
        add(image2);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms", "root", "players004");
            JOptionPane.showMessageDialog(this, "Database connected successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error in database connection");
            e.printStackTrace();
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                String cardno = j1.getText();
                String pin = new String(jp.getPassword());
                pst = con.prepareStatement("SELECT * FROM login WHERE card_number=? AND pin=?");
                pst.setString(1, cardno);
                pst.setString(2, pin);
                rs = pst.executeQuery();

                if (rs.next()) {
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    new maintransaction(pin);// Add redirection code here (e.g., new MainTransaction(pin));
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                    
                }
            } else if (ae.getSource() == b2) {
                j1.setText("");
                jp.setText("");
            } else if (ae.getSource() == b3) {
                JOptionPane.showMessageDialog(null, "SignUp Page Opening");
                new SignUp();
                setVisible(false);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}