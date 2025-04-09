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

public class fastcash extends JFrame implements ActionListener{
	
	String pin;
	JLabel lb1;
	JTextField txt1;
	JButton b1, b2,b3,b4,b5,b6,b7;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	fastcash(String pin)
	{
		this.pin = pin;
		setLayout(null);
		setBounds(0, 0, 1550, 1080);

		ImageIcon i1 = new ImageIcon(getClass().getResource("/bms/atm2.png"));
		Image i2 = i1.getImage().getScaledInstance(1550, 800, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3); // ImageIcon cannot be added directly to JFrame
		image.setBounds(0, 0, 1550, 800);
		add(image);

		// first
		lb1 = new JLabel("SELECT WITHDRAWL AMOUNT");
		lb1.setForeground(Color.WHITE);
		lb1.setFont(new Font("System", Font.BOLD, 24));
		lb1.setBounds(440, 180, 700, 35);
		image.add(lb1);

	
		b1 = new JButton("Rs. 100");
		b1.setBounds(406, 258, 150, 35);
		b1.setBackground(new Color(65, 125, 128));
		b1.setForeground(Color.WHITE);
		image.add(b1);

		b2 = new JButton("Rs. 500");
		b2.setBounds(406, 303, 150, 35);
		b2.setBackground(new Color(65, 125, 128));
		b2.setForeground(Color.WHITE);
		image.add(b2);
		
		b3 = new JButton("Rs. 1000");
		b3.setBounds(406, 348, 150, 35);
		b3.setBackground(new Color(65, 125, 128));
		b3.setForeground(Color.WHITE);
		image.add(b3);
		
		b4 = new JButton("Rs. 2000");
		b4.setBounds(700, 258, 150, 35);
		b4.setBackground(new Color(65, 125, 128));
		b4.setForeground(Color.WHITE);
		image.add(b4);
		
		b5 = new JButton("Rs. 5000");
		b5.setBounds(700, 303, 150, 35);
		b5.setBackground(new Color(65, 125, 128));
		b5.setForeground(Color.WHITE);
		image.add(b5);
		
		b6 = new JButton("Rs. 10000");
		b6.setBounds(700, 348, 150, 35);
		b6.setBackground(new Color(65, 125, 128));
		b6.setForeground(Color.WHITE);
		image.add(b6);
		
		b7 = new JButton("BACK");
		b7.setBounds(700, 393, 150, 35);
		b7.setBackground(new Color(65, 125, 128));
		b7.setForeground(Color.WHITE);
		image.add(b7);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		

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
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b7)
		{
			setVisible(false);
			new maintransaction(pin);
		}
		else {
			String amount=((JButton)ae.getSource()).getText().substring(4);
			Date date = new Date();
			try {
				pst=con.prepareStatement("select * from bank where pin='"+pin+"'");
				rs=pst.executeQuery();
				int balance=0;
				while(rs.next())
				{
					if(rs.getString("type").equals("Deposit"))
					{
						balance += Integer.parseInt(rs.getString("amount"));
					} else {
						balance -= Integer.parseInt(rs.getString("amount"));
					}String num="17";
					
					if(ae.getSource()!=b7 && balance<Integer.parseInt(amount))
					{
						JOptionPane.showMessageDialog(null, "Insufficient Balance");
						return;
					}
					
					pst=con.prepareStatement("insert into bank values('"+pin+"','"+date+"','Withdraw','"+amount+"')");
					pst.executeUpdate()	;
					

					JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully!");
					setVisible(false);
					new maintransaction(pin);
					
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	public static void main(String[]args)
	{
		new fastcash("");
	}

}
