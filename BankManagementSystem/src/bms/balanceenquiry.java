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


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class balanceenquiry extends JFrame implements ActionListener{
	
	
	String pin;
	JLabel lb1, lb2;
	JButton b1, b2;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	balanceenquiry(String pin)
	{
		this.pin=pin;
		setLayout(null);
		setBounds(0, 0, 1550, 1080);

		ImageIcon i1 = new ImageIcon(getClass().getResource("/bms/atm2.png"));
		Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3); // ImageIcon cannot be added directly to JFrame
		image.setBounds(0, 0, 1550, 830);
		add(image);

		// first
		lb1 = new JLabel("Your Current Balance is Rs");
		lb1.setForeground(Color.WHITE);
		lb1.setFont(new Font("System", Font.BOLD, 16));
		lb1.setBounds(430, 180, 700, 35);
		image.add(lb1);

		lb2 = new JLabel();
		lb2.setForeground(Color.WHITE);
		lb2.setFont(new Font("System", Font.BOLD, 16));
		lb2.setBounds(430, 220, 400, 35);
		image.add(lb2);
		
		b1 = new JButton("Back");
		b1.setBounds(700, 408, 150, 35);
		b1.setBackground(new Color(65, 125, 128));
		b1.setForeground(Color.WHITE);
		image.add(b1);
		b1.addActionListener(this);
		
		
		
		setVisible(true);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms", "root", "players004");
			JOptionPane.showMessageDialog(this, "Database connected successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in database connection");
			(e).printStackTrace();
		}
		
		int balance =0;
		try {
			
			pst=con.prepareStatement("select * from bank where pin='"+pin+"'");
			rs=pst.executeQuery();
			while(rs.next())
			{
				if (rs.getString("type").equals("Deposit")) {
					balance += Integer.parseInt(rs.getString("amount"));
				} else {
					balance -= Integer.parseInt(rs.getString("amount"));
				}
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		lb2.setText(""+balance);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			setVisible(true);
			new maintransaction(pin);
		}
		
	}
	public static void main(String[] args)
	{
		new balanceenquiry("");
	}
	

}
