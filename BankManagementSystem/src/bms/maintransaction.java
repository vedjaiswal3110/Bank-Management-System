package bms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class maintransaction extends JFrame implements ActionListener {
	String pin;
	JLabel lb1;
	JTextField txt1;
	JButton b1, b2,b3,b4,b5,b6,b7;
	maintransaction(String pin)
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
		lb1 = new JLabel("Please Select Your Transaction");
		lb1.setForeground(Color.WHITE);
		lb1.setFont(new Font("System", Font.BOLD, 28));
		lb1.setBounds(430, 180, 700, 35);
		image.add(lb1);

	
		b1 = new JButton("DEPOSIT");
		b1.setBounds(406, 258, 150, 35);
		b1.setBackground(new Color(65, 125, 128));
		b1.setForeground(Color.WHITE);
		image.add(b1);

		b2 = new JButton("FAST CASH");
		b2.setBounds(406, 303, 150, 35);
		b2.setBackground(new Color(65, 125, 128));
		b2.setForeground(Color.WHITE);
		image.add(b2);
		
		b3 = new JButton("PIN CHANGE");
		b3.setBounds(406, 348, 150, 35);
		b3.setBackground(new Color(65, 125, 128));
		b3.setForeground(Color.WHITE);
		image.add(b3);
		
		b4 = new JButton("CASH WITHDRAWL");
		b4.setBounds(700, 258, 150, 35);
		b4.setBackground(new Color(65, 125, 128));
		b4.setForeground(Color.WHITE);
		image.add(b4);
		
		b5 = new JButton("MINI STATEMENT");
		b5.setBounds(700, 303, 150, 35);
		b5.setBackground(new Color(65, 125, 128));
		b5.setForeground(Color.WHITE);
		image.add(b5);
		
		b6 = new JButton("BALANCE ENQUIRY");
		b6.setBounds(700, 348, 150, 35);
		b6.setBackground(new Color(65, 125, 128));
		b6.setForeground(Color.WHITE);
		image.add(b6);
		
		b7 = new JButton("EXIT");
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
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			new deposit(pin);
			setVisible(false);
		}
		if(ae.getSource()==b7)
		{
			System.exit(0);
		}
		if(ae.getSource()==b4)
		{
			new withdrawl(pin);
			setVisible(false);
		}
		if(ae.getSource()==b6)
		{
			new balanceenquiry(pin);
			setVisible(false);
		}
		if(ae.getSource()==b2)
		{
			new fastcash(pin);
			setVisible(false);
		}
		if(ae.getSource()==b3)
		{
			new pin(pin);
			setVisible(false);
		}
		if(ae.getSource()==b5)
		{
			new mini(pin);
			setVisible(false);
		}
		
	}
	
	public static void main(String[] args) {
		new maintransaction("");
	}
	

}
