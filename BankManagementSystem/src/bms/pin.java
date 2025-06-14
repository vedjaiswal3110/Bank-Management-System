package bms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class pin extends JFrame implements ActionListener {
	String pin;
	JLabel lb1,lb2;
	JButton b1, b2;
	JPasswordField p1,p2;
	Connection con;
	PreparedStatement pst1,pst2,pst3;
	ResultSet rs;
	
	pin(String pin)
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
		lb1 = new JLabel("CHANGE YOUR PIN");
		lb1.setForeground(Color.WHITE);
		lb1.setFont(new Font("System", Font.BOLD, 16));
		lb1.setBounds(550, 180, 400, 35);
		image.add(lb1);
		
		lb2 = new JLabel(" NEW PIN");
		lb2.setForeground(Color.WHITE);
		lb2.setFont(new Font("System", Font.BOLD, 16));
		lb2.setBounds(430, 220, 150, 25);
		image.add(lb2);
		
		p1=new JPasswordField();
		p1.setBackground(new Color(65, 125, 128));
		p1.setForeground(Color.WHITE);
		p1.setBounds(600, 220, 180, 25);
		p1.setFont(new Font("Raleway", Font.BOLD, 18));
		image.add(p1);
		
		lb2 = new JLabel("RE-ENTER NEW PIN");
		lb2.setForeground(Color.WHITE);
		lb2.setFont(new Font("System", Font.BOLD, 16));
		lb2.setBounds(435, 270, 300, 25);
		image.add(lb2);
		
		p2=new JPasswordField();
		p2.setBackground(new Color(65, 125, 128));
		p2.setForeground(Color.WHITE);
		p2.setBounds(600, 270, 180, 25);
		p2.setFont(new Font("Raleway", Font.BOLD, 18));
		image.add(p2);
		
		b1 = new JButton("CHANGE");
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
	
	public void actionPerformed(ActionEvent ae)
	{
		try {
			String pin1=p1.getText();
			String pin2=p2.getText();
			
			if (ae.getSource() == b1) {
			    if (p1.getText().equals("")) {
			        JOptionPane.showMessageDialog(null, "Enter New PIN");
			        return;
			    }
			    if (p2.getText().equals("")) {
			        JOptionPane.showMessageDialog(null, "Enter New PIN");
			        return;
			    }
			    if (!pin1.equals(pin2)) {
			        JOptionPane.showMessageDialog(null, "Entered PIN does not match");
			        return;
			    }

			    try {
			        pst1 = con.prepareStatement("UPDATE bank SET pin = ? WHERE pin = ?");
			        pst1.setString(1, pin1);  // New PIN
			        pst1.setString(2, pin);   // Old PIN
			        int rows1 = pst1.executeUpdate();

			        pst2 = con.prepareStatement("UPDATE login SET pin = ? WHERE pin = ?");
			        pst2.setString(1, pin1);
			        pst2.setString(2, pin);
			        int rows2 = pst2.executeUpdate();

			        pst3 = con.prepareStatement("UPDATE signup3 SET pin = ? WHERE pin = ?");
			        pst3.setString(1, pin1);
			        pst3.setString(2, pin);
			        int rows3 = pst3.executeUpdate();

			        if (rows1 > 0 || rows2 > 0 || rows3 > 0) {
			            JOptionPane.showMessageDialog(null, "PIN changed successfully");
			            setVisible(false);
			            new maintransaction(pin1);
			        } else {
			            JOptionPane.showMessageDialog(null, "No matching record found for the given PIN");
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}

			
			if(ae.getSource()==b2)
			{
				
				new maintransaction(pin);
				setVisible(false);
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();		}
		
	}
	public static void main(String[]args)
	{
		new pin("");
	}
}
