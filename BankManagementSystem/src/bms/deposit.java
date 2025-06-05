package bms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;


import javax.swing.*;

public class deposit extends JFrame implements ActionListener {

	String pin;
	JLabel lb1;
	JTextField txt1;
	JButton b1, b2;
	Connection con;
	PreparedStatement pst;

	deposit(String pin) {
		this.pin = pin;
		setLayout(null);
		setBounds(0, 0, 1550, 1080);

		ImageIcon i1 = new ImageIcon(getClass().getResource("/bms/atm2.png"));
		Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3); // ImageIcon cannot be added directly to JFrame
		image.setBounds(0, 0, 1550, 830);
		add(image);

		// first
		lb1 = new JLabel("ENTER AMOUNT YOU WANT TO ENTER");
		lb1.setForeground(Color.WHITE);
		lb1.setFont(new Font("System", Font.BOLD, 16));
		lb1.setBounds(460, 180, 400, 35);
		image.add(lb1);

		txt1 = new JTextField();
		txt1.setBackground(new Color(65, 125, 128));
		txt1.setForeground(Color.WHITE);
		txt1.setBounds(460, 230, 320, 25);
		txt1.setFont(new Font("Raleway", Font.BOLD, 22));
		image.add(txt1);

		b1 = new JButton("DEPOSIT");
		b1.setBounds(700, 362, 150, 35);
		b1.setBackground(new Color(65, 125, 128));
		b1.setForeground(Color.WHITE);
		image.add(b1);

		b2 = new JButton("BACK");
		b2.setBounds(700, 406, 150, 35);
		b2.setBackground(new Color(65, 125, 128));
		b2.setForeground(Color.WHITE);
		image.add(b2);

		b1.addActionListener(this);
		b2.addActionListener(this);

		setVisible(true);
		// Database Connection
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms", "root", "players004");
			JOptionPane.showMessageDialog(this, "Database connected successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in database connection");
			(e).printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent ae) {
		try {
			String amt = txt1.getText();
			Date date = new Date();
			if (ae.getSource() == b1) {
				if (txt1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
				} else {
					pst = con.prepareStatement("INSERT INTO bank(pin, date, type, amount) VALUES (?, ?, ?, ?)");
					pst.setString(1, pin);
					pst.setString(2, date.toString());
					pst.setString(3, "Deposit"); // Corrected string usage
					pst.setString(4, amt);
					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Rs " + amt + " Deposited Successfully!");
					txt1.setText("");
					setVisible(false);
					new maintransaction(pin);
				}

			} else if (ae.getSource() == b2) { // Back button clicked
				setVisible(false);
				new maintransaction(pin);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new deposit("");
	}

}
