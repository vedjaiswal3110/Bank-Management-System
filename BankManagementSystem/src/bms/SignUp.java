package bms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;



import javax.swing.*;

import com.toedter.calendar.JDateChooser;


public class SignUp extends JFrame implements ActionListener {

	JLabel lbhead, lbsub1, lbsub2, lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9, lb10;
	JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7;
	JRadioButton r1, r2, r3, r4, r5;
	JDateChooser dateChoose;
	JButton b1;
	 Connection con;
	  PreparedStatement pst;

	Random ran = new Random();
	long first4 = (ran.nextLong() % 9000L) + 1000L;
	String first = " " + Math.abs(first4);

	SignUp() {

		getContentPane().setBackground(new Color(222, 255, 228));
		setLayout(null);
		setTitle("Application Form");
		setBounds(400, 10, 850, 800);

		ImageIcon i1 = new ImageIcon(getClass().getResource("/bms/bank.png"));
		Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3); // ImageIcon cannot be added directly to JFrame
		image.setBounds(25, 10, 100, 100);
		add(image);

		lbhead = new JLabel("APPLICATION FORM NO. " + first);
		lbhead.setFont(new Font("Arial", Font.BOLD, 36));
		lbhead.setBounds(150, 20, 600, 40);
		add(lbhead);

		lbsub1 = new JLabel("Page 1");
		lbsub1.setFont(new Font("Arial", Font.BOLD, 20));
		lbsub1.setBounds(330, 70, 600, 30);
		add(lbsub1);

		lbsub2 = new JLabel("Personal Details");
		lbsub2.setFont(new Font("Arial", Font.BOLD, 20));
		lbsub2.setBounds(290, 90, 600, 30);
		add(lbsub2);

		// Name
		lb1 = new JLabel("Name:");
		lb1.setFont(new Font("Arial", Font.BOLD, 18));
		lb1.setBounds(100, 180, 100, 30);
		add(lb1);

		txt1 = new JTextField(15);
		txt1.setFont(new Font("Arial", Font.BOLD, 18));
		txt1.setBounds(300, 180, 400, 30);
		add(txt1);

		// Father
		lb2 = new JLabel("Father's Name:");
		lb2.setFont(new Font("Arial", Font.BOLD, 18));
		lb2.setBounds(100, 220, 200, 30);
		add(lb2);

		txt2 = new JTextField(15);
		txt2.setFont(new Font("Arial", Font.BOLD, 18));
		txt2.setBounds(300, 220, 400, 30);
		add(txt2);

		// Gender
		lb3 = new JLabel("Gender:");
		lb3.setFont(new Font("Arial", Font.BOLD, 18));
		lb3.setBounds(100, 260, 100, 30);
		add(lb3);

		r1 = new JRadioButton("Male");
		r1.setFont(new Font("Arial", Font.BOLD, 14));
		r1.setBounds(300, 260, 60, 30);
		r1.setBackground(new Color(222, 255, 228));
		add(r1);
		r2 = new JRadioButton("Female");
		r2.setFont(new Font("Arial", Font.BOLD, 14));
		r2.setBounds(450, 260, 80, 30);
		r2.setBackground(new Color(222, 255, 228));
		add(r2);

		// **Group Gender Radio Buttons**
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(r1);
		genderGroup.add(r2);

		// DOB
		lb4 = new JLabel("Date of Birth:");
		lb4.setFont(new Font("Arial", Font.BOLD, 18));
		lb4.setBounds(100, 300, 200, 30);
		add(lb4);

		dateChoose = new JDateChooser();
		dateChoose.setForeground(new Color(105, 105, 105));
		dateChoose.setBounds(300, 300, 400, 30);
		add(dateChoose);

		// Email
		lb5 = new JLabel("Email Address:");
		lb5.setFont(new Font("Arial", Font.BOLD, 18));
		lb5.setBounds(100, 340, 200, 30);
		add(lb5);

		txt3 = new JTextField(15);
		txt3.setFont(new Font("Arial", Font.BOLD, 18));
		txt3.setBounds(300, 340, 400, 30);
		add(txt3);

		// Marital
		lb6 = new JLabel("Marital Status:");
		lb6.setFont(new Font("Arial", Font.BOLD, 18));
		lb6.setBounds(100, 380, 200, 30);
		add(lb6);

		r3 = new JRadioButton("Married");
		r3.setFont(new Font("Arial", Font.BOLD, 14));
		r3.setBounds(300, 380, 80, 30);
		r3.setBackground(new Color(222, 255, 228));
		add(r3);
		r4 = new JRadioButton("Unmarried");
		r4.setFont(new Font("Arial", Font.BOLD, 14));
		r4.setBounds(450, 380, 100, 30);
		r4.setBackground(new Color(222, 255, 228));
		add(r4);
		r5 = new JRadioButton("Other");
		r5.setFont(new Font("Arial", Font.BOLD, 14));
		r5.setBounds(620, 380, 80, 30);
		r5.setBackground(new Color(222, 255, 228));
		add(r5);

		// **Group Marital Status Radio Buttons** used for only one check option
		ButtonGroup maritalGroup = new ButtonGroup();
		maritalGroup.add(r3);
		maritalGroup.add(r4);
		maritalGroup.add(r5);

		// Address
		lb7 = new JLabel("Address:");
		lb7.setFont(new Font("Arial", Font.BOLD, 18));
		lb7.setBounds(100, 420, 100, 30);
		add(lb7);

		txt4 = new JTextField(15);
		txt4.setFont(new Font("Arial", Font.BOLD, 18));
		txt4.setBounds(300, 420, 400, 30);
		add(txt4);

		// City
		lb8 = new JLabel("City:");
		lb8.setFont(new Font("Arial", Font.BOLD, 18));
		lb8.setBounds(100, 460, 100, 30);
		add(lb8);

		txt5 = new JTextField(15);
		txt5.setFont(new Font("Arial", Font.BOLD, 18));
		txt5.setBounds(300, 460, 400, 30);
		add(txt5);

		// State
		lb9 = new JLabel("Pin Code:");
		lb9.setFont(new Font("Arial", Font.BOLD, 18));
		lb9.setBounds(100, 500, 100, 30);
		add(lb9);

		txt6 = new JTextField(15);
		txt6.setFont(new Font("Arial", Font.BOLD, 18));
		txt6.setBounds(300, 500, 400, 30);
		add(txt6);

		// Pin code
		lb10 = new JLabel("State:");
		lb10.setFont(new Font("Arial", Font.BOLD, 18));
		lb10.setBounds(100, 540, 100, 30);
		add(lb10);

		txt7 = new JTextField(15);
		txt7.setFont(new Font("Arial", Font.BOLD, 18));
		txt7.setBounds(300, 540, 400, 30);
		add(txt7);

		b1 = new JButton("Next");
		b1.setFont(new Font("Arial", Font.BOLD, 18));
		b1.setBounds(620, 590, 80, 30);
		b1.setForeground(Color.WHITE);
		b1.setBackground(Color.BLACK);
		add(b1);

		b1.addActionListener(this);

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
		    String formNo = first;
	        String name = txt1.getText();
	        String fname = txt2.getText();
	        String dob = ((JTextField) dateChoose.getDateEditor().getUiComponent()).getText();
	        String gender = r1.isSelected() ? "Male" : (r2.isSelected() ? "Female" : "");
	        String email = txt3.getText();
	        String marital = null;
	        if(r3.isSelected())
	        {
	        	marital = "Married";
	        }else if(r4.isSelected())
	        {
	        	marital="Unmarried";
	        }else if(r5.isSelected())
	        {
	        	marital = "Other";
	        }
	        
	        String address=txt4.getText();
	        String city = txt5.getText();
	        String pincode = txt6.getText();
	        String state = txt7.getText();
	        
	        
	        try {
	            if (name.isEmpty() || fname.isEmpty() || dob.isEmpty() || gender.isEmpty() || email.isEmpty()|| marital.isEmpty() || address.isEmpty() || city.isEmpty() || pincode.isEmpty() || state.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Fill all the fields");
	            } else {
	                pst = con.prepareStatement("INSERT INTO signup (form_no, name, father_name, DOB, gender, email, marital_status, address, city, pincode, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	                pst.setString(1, formNo);
	                pst.setString(2, name);
	                pst.setString(3, fname);
	                pst.setString(4, dob);
	                pst.setString(5, gender);
	                pst.setString(6, email);
	                pst.setString(7, marital);
	                pst.setString(8, address);
	                pst.setString(9, city);
	                pst.setString(10, pincode);
	                pst.setString(11, state);
	                
	                pst.executeUpdate();
	                JOptionPane.showMessageDialog(null, "Sign Up Successful Fill More Details ");
	                new SignUp2(first); // because we want form no in next page also
	                setVisible(false);
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error in saving data");
	            e.printStackTrace();
	        }
	    }

	public static void main(String[] args) {
		new SignUp();
	}

}
