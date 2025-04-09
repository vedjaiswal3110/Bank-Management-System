package bms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SignUp3 extends JFrame implements ActionListener{

	String formno;

	JLabel lb1, lb2, lb3, lba, lbc, lbcd, lbno, lbnod, lbpin, lbpinn, lbpinno, lbs;
	JComboBox account1, account2;
	JTextField txt1, txt2;
	JRadioButton r1, r2, r3, r4;
	JButton b1, b2;
	JCheckBox c1, c2, c3, c4, c5, c6, c;
	Connection con;
	PreparedStatement pst, pst1;

	SignUp3(String first) {
		getContentPane().setBackground(new Color(222, 255, 228));
		setLayout(null);
		setTitle("Application Form");
		setBounds(400, 10, 850, 800);

		ImageIcon i1 = new ImageIcon(getClass().getResource("/bms/bank.png"));
		Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3); // ImageIcon cannot be added directly to JFrame
		image.setBounds(25, 5, 100, 100);
		add(image);

		this.formno = first;

		lb1 = new JLabel("Page 3");
		lb1.setFont(new Font("Arial", Font.BOLD, 20));
		lb1.setBounds(200, 45, 600, 30);
		add(lb1);

		lb2 = new JLabel("Account Details");
		lb2.setFont(new Font("Arial", Font.BOLD, 20));
		lb2.setBounds(200, 80, 600, 30);
		add(lb2);

		lb3 = new JLabel("Form No:" + formno);
		lb3.setFont(new Font("Arial", Font.BOLD, 18));
		lb3.setBounds(620, 10, 600, 30);
		add(lb3);

		// Account Type
		lba = new JLabel("Account Type:");
		lba.setFont(new Font("Arial", Font.BOLD, 18));
		lba.setBounds(100, 150, 200, 30);
		add(lba);

		r1 = new JRadioButton("Saving Account");
		r1.setFont(new Font("Arial", Font.BOLD, 16));
		r1.setBackground(new Color(222, 255, 228));
		r1.setBounds(100, 180, 300, 30);
		add(r1);

		r2 = new JRadioButton("Fixed Deposit Account");
		r2.setFont(new Font("Arial", Font.BOLD, 16));
		r2.setBackground(new Color(222, 255, 228));
		r2.setBounds(400, 180, 300, 30);
		add(r2);

		r3 = new JRadioButton("Current Account");
		r3.setFont(new Font("Arial", Font.BOLD, 16));
		r3.setBackground(new Color(222, 255, 228));
		r3.setBounds(100, 210, 300, 30);
		add(r3);

		r4 = new JRadioButton("Recurring Deposit Account");
		r4.setFont(new Font("Arial", Font.BOLD, 16));
		r4.setBackground(new Color(222, 255, 228));
		r4.setBounds(400, 210, 300, 30);
		add(r4);

		ButtonGroup btng = new ButtonGroup();
		btng.add(r1);
		btng.add(r2);
		btng.add(r3);
		btng.add(r4);

		// Card Number
		lbc = new JLabel("Card Number:");
		lbc.setFont(new Font("Arial", Font.BOLD, 18));
		lbc.setBounds(100, 270, 200, 30);
		add(lbc);

		lbcd = new JLabel("(Your 16 digit Card number)");
		lbcd.setFont(new Font("Arial", Font.BOLD, 10));
		lbcd.setBounds(100, 290, 300, 30);
		add(lbcd);

		lbno = new JLabel("XXXX-XXXX-XXXX-4814");
		lbno.setFont(new Font("Arial", Font.BOLD, 18));
		lbno.setBounds(350, 270, 3200, 30);
		add(lbno);

		lbnod = new JLabel("(It would appears on Card/Cheque book and Statements)");
		lbnod.setFont(new Font("Arial", Font.BOLD, 10));
		lbnod.setBounds(350, 290, 300, 30);
		add(lbnod);

		// Pin
		lbpin = new JLabel("PIN:");
		lbpin.setFont(new Font("Arial", Font.BOLD, 18));
		lbpin.setBounds(100, 330, 200, 30);
		add(lbpin);

		lbpinn = new JLabel("(4 digit password)");
		lbpinn.setFont(new Font("Arial", Font.BOLD, 10));
		lbpinn.setBounds(100, 350, 300, 30);
		add(lbpinn);

		lbpinno = new JLabel("XXXX");
		lbpinno.setFont(new Font("Arial", Font.BOLD, 18));
		lbpinno.setBounds(350, 330, 320, 30);
		add(lbpinno);

		// Services
		lbs = new JLabel("Services Required:");
		lbs.setFont(new Font("Arial", Font.BOLD, 18));
		lbs.setBounds(100, 400, 200, 30);
		add(lbs);

		c1 = new JCheckBox("ATM CARD");
		c1.setFont(new Font("Arial", Font.BOLD, 14));
		c1.setBackground(new Color(222, 255, 228));
		c1.setBounds(100, 430, 200, 30);
		add(c1);

		c2 = new JCheckBox("Mobile Banking");
		c2.setFont(new Font("Arial", Font.BOLD, 14));
		c2.setBackground(new Color(222, 255, 228));
		c2.setBounds(100, 470, 200, 30);
		add(c2);

		c3 = new JCheckBox("Cheque Book");
		c3.setFont(new Font("Arial", Font.BOLD, 14));
		c3.setBackground(new Color(222, 255, 228));
		c3.setBounds(100, 510, 200, 30);
		add(c3);

		c4 = new JCheckBox("Internet Banking");
		c4.setFont(new Font("Arial", Font.BOLD, 14));
		c4.setBackground(new Color(222, 255, 228));
		c4.setBounds(400, 430, 200, 30);
		add(c4);

		c5 = new JCheckBox("EMAIL Alerts");
		c5.setFont(new Font("Arial", Font.BOLD, 14));
		c5.setBackground(new Color(222, 255, 228));
		c5.setBounds(400, 470, 200, 30);
		add(c5);

		c6 = new JCheckBox("E-Statements");
		c6.setFont(new Font("Arial", Font.BOLD, 14));
		c6.setBackground(new Color(222, 255, 228));
		c6.setBounds(400, 510, 200, 30);
		add(c6);

		c = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge.");
		c.setFont(new Font("Arial", Font.BOLD, 10));
		c.setBackground(new Color(222, 255, 228));
		c.setBounds(100, 580, 600, 30);
		add(c);

		b1 = new JButton("Submit");
		b1.setFont(new Font("Arial", Font.BOLD, 15));
		b1.setBounds(270, 620, 100, 30);
		b1.setForeground(Color.WHITE);
		b1.setBackground(Color.BLACK);
		add(b1);

		b2 = new JButton("Cancel");
		b2.setFont(new Font("Arial", Font.BOLD, 15));
		b2.setBounds(440, 620, 100, 30);
		b2.setForeground(Color.WHITE);
		b2.setBackground(Color.BLACK);
		add(b2);
		
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

	public void actionPerformed(ActionEvent e) {

		String accountType = "";
		if (r1.isSelected())
			accountType = "Saving Account";
		else if (r2.isSelected())
			accountType = "Fixed Deposit Account";
		else if (r3.isSelected())
			accountType = "Current Account";
		else if (r4.isSelected())
			accountType = "Recurring Deposit Account";

		Random ran = new Random();
		long first7 = (ran.nextLong() % 90000000L) + 1409963000000000L;
		String cardno = "" + Math.abs(first7);

		long first3 = (ran.nextLong() % 9000L) + 1000L;
		String pin = "" + Math.abs(first3);

		String services = "";
		if (c1.isSelected())
			services += "ATM CARD, ";
		if (c2.isSelected())
			services += "Mobile Banking, ";
		if (c3.isSelected())
			services += "Cheque Book, ";
		if (c4.isSelected())
			services += "Internet Banking, ";
		if (c5.isSelected())
			services += "EMAIL Alerts, ";
		if (c6.isSelected())
			services += "E-Statements, ";

		try {

			if (e.getSource() == b1) {
				if (accountType.isEmpty() || services.isEmpty()) {
					JOptionPane.showMessageDialog(this, "All fields must be filled!");

				} else {
					pst = con.prepareStatement(
							"INSERT INTO signup3 (form_no, account_type, card_number,pin,facility) VALUES (?, ?, ?, ?, ?)");
					pst.setString(1, formno);
					pst.setString(2, accountType);
					pst.setString(3, cardno);
					pst.setString(4, pin);
					pst.setString(5, services);

					pst.executeUpdate();

					pst1 = con.prepareStatement("INSERT INTO login(form_no,card_number,pin) VALUES(?,?,?)");
					pst1.setString(1, formno);
					pst1.setString(2, cardno);
					pst1.setString(3, pin);
					pst1.executeUpdate();

					JOptionPane.showMessageDialog(null, "Card Number : "+cardno+"\n Pin : "+pin);

					new maintransaction(pin);
					setVisible(false);
				}

			} else if (e.getSource() == b2) {
				System.exit(0);
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, "Error in storing data");
			e1.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new SignUp3("");
	}
}
