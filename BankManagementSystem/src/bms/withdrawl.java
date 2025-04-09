package bms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class withdrawl extends JFrame implements ActionListener {
	String pin;
	JLabel lb1, lb2;
	JTextField txt1;
	JButton b1, b2;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	withdrawl(String pin) {
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
		lb1 = new JLabel("MAXIMUM WITHDRAWL IS RS.10,000");
		lb1.setForeground(Color.WHITE);
		lb1.setFont(new Font("System", Font.BOLD, 16));
		lb1.setBounds(480, 180, 700, 35);
		image.add(lb1);

		lb2 = new JLabel("ENTER AMOUNT YOU WANT TO WITHDRAW");
		lb2.setForeground(Color.WHITE);
		lb2.setFont(new Font("System", Font.BOLD, 16));
		lb2.setBounds(460, 220, 700, 35);
		image.add(lb2);

		txt1 = new JTextField();
		txt1.setBackground(new Color(65, 125, 128));
		txt1.setForeground(Color.WHITE);
		txt1.setBounds(470, 260, 320, 25);
		txt1.setFont(new Font("Raleway", Font.BOLD, 22));
		image.add(txt1);

		b1 = new JButton("WITHDRAW");
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
		
		if(ae.getSource()==b1)
		{
		try {
			String amount = txt1.getText();
			Date date = new Date();
			if (txt1.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Withdraw");
			} else {
				pst = con.prepareStatement("select * from bank where pin='" + pin + "'");
				rs = pst.executeQuery();
				int balance = 0;
				while (rs.next()) {
					if (rs.getString("type").equals("Deposit")) {
						balance += Integer.parseInt(rs.getString("amount"));
					} else {
						balance -= Integer.parseInt(rs.getString("amount"));
					}
				}
				if (balance < Integer.parseInt(amount)) {
					JOptionPane.showMessageDialog(null, "Insufficient Balance");
				}

				pst = con.prepareStatement(
						"insert into bank values('" + pin + "','" + date + "','Withdraw','" + amount + "')");
				pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully!");
				setVisible(false);
				new maintransaction(pin);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		}else if(ae.getSource()==b2)
		{
			setVisible(false);
			new maintransaction(pin);
		}
	}

	public static void main(String[] args) {
		new withdrawl("");
	}

}
