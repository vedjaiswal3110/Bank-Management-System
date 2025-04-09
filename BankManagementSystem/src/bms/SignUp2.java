package bms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.*;

public class SignUp2 extends JFrame implements ActionListener {

	JLabel lb1, lb2, lb3, lbr, lbc, lbi, lbeq, lbo, lbp, lba, lbs, lbe;
	JComboBox religions, category, income, education, occupation;
	JTextField txt1, txt2;
	JRadioButton r1, r2, r3, r4;
	JButton b1;
	Connection con;
	PreparedStatement pst;

	String formno;

	SignUp2(String first) {
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

		lb1 = new JLabel("Page 2");
		lb1.setFont(new Font("Arial", Font.BOLD, 20));
		lb1.setBounds(200, 45, 600, 30);
		add(lb1);

		lb2 = new JLabel("Additional Details");
		lb2.setFont(new Font("Arial", Font.BOLD, 20));
		lb2.setBounds(200, 80, 600, 30);
		add(lb2);

		lb3 = new JLabel("Form No:" + formno);
		lb3.setFont(new Font("Arial", Font.BOLD, 18));
		lb3.setBounds(620, 10, 600, 30);
		add(lb3);

		// Religion
		lbr = new JLabel("Religion:");
		lbr.setFont(new Font("Arial", Font.BOLD, 18));
		lbr.setBounds(100, 150, 100, 30);
		add(lbr);

		String religion[] = { "Hindu", "Christian", "Muslim", "Sikh", "Others" };
		religions = new JComboBox(religion);
		religions.setFont(new Font("Arial", Font.BOLD, 18));
		religions.setBounds(300, 150, 400, 30);
		add(religions);

		// Category
		lbc = new JLabel("Category:");
		lbc.setFont(new Font("Arial", Font.BOLD, 18));
		lbc.setBounds(100, 190, 100, 30);
		add(lbc);

		String cate[] = { "General", "OBC", "SC", "ST", "Others" };
		category = new JComboBox(cate);
		category.setFont(new Font("Arial", Font.BOLD, 18));
		category.setBounds(300, 190, 400, 30);
		add(category);

		// INcome
		lbi = new JLabel("Income:");
		lbi.setFont(new Font("Arial", Font.BOLD, 18));
		lbi.setBounds(100, 230, 100, 30);
		add(lbi);

		String in[] = { "<1,50,000", "<2,50,000", "5,00,000", "Upto 10,00,000" };
		income = new JComboBox(in);
		income.setFont(new Font("Arial", Font.BOLD, 18));
		income.setBounds(300, 230, 400, 30);
		add(income);

		// Educatonal Qualification
		lbeq = new JLabel("Educational:");
		lbeq.setFont(new Font("Arial", Font.BOLD, 18));
		lbeq.setBounds(100, 270, 150, 30);
		add(lbeq);

		String edu[] = { "Non Graduate", "Graduate", "Post-Graduate", "Doctrate" };
		education = new JComboBox(edu);
		education.setFont(new Font("Arial", Font.BOLD, 18));
		education.setBounds(300, 270, 400, 30);
		add(education);

		// ocupation
		lbo = new JLabel("Occupation:");
		lbo.setFont(new Font("Arial", Font.BOLD, 18));
		lbo.setBounds(100, 310, 150, 30);
		add(lbo);

		String ocu[] = { "Student", "Self-Employed", "Employed", "Business", "Retired" };
		occupation = new JComboBox(ocu);
		occupation.setFont(new Font("Arial", Font.BOLD, 18));
		occupation.setBounds(300, 310, 400, 30);
		add(occupation);

		// Pan
		lbp = new JLabel("Pan Number:");
		lbp.setFont(new Font("Arial", Font.BOLD, 18));
		lbp.setBounds(100, 350, 150, 30);
		add(lbp);

		txt1 = new JTextField(15);
		txt1.setFont(new Font("Arial", Font.BOLD, 18));
		txt1.setBounds(300, 350, 400, 30);
		add(txt1);

		// Aadhar
		lba = new JLabel("Aadhar Number:");
		lba.setFont(new Font("Arial", Font.BOLD, 18));
		lba.setBounds(100, 390, 150, 30);
		add(lba);

		txt2 = new JTextField(15);
		txt2.setFont(new Font("Arial", Font.BOLD, 18));
		txt2.setBounds(300, 390, 400, 30);
		add(txt2);

		// Senior Citizen
		lbs = new JLabel("Senior Citizen:");
		lbs.setFont(new Font("Arial", Font.BOLD, 18));
		lbs.setBounds(100, 430, 150, 30);
		add(lbs);

		r1 = new JRadioButton("Yes");
		r1.setFont(new Font("Arial", Font.BOLD, 18));
		r1.setBackground(new Color(222, 255, 228));
		r1.setBounds(300, 430, 200, 30);
		add(r1);

		r2 = new JRadioButton("No");
		r2.setFont(new Font("Arial", Font.BOLD, 18));
		r2.setBackground(new Color(222, 255, 228));
		r2.setBounds(550, 430, 200, 30);
		add(r2);

		ButtonGroup scGroup = new ButtonGroup();
		scGroup.add(r1);
		scGroup.add(r2);

		// Existin account
		lbe = new JLabel("Existing Account:");
		lbe.setFont(new Font("Arial", Font.BOLD, 18));
		lbe.setBounds(100, 470, 200, 30);
		add(lbe);

		r3 = new JRadioButton("Yes");
		r3.setFont(new Font("Arial", Font.BOLD, 18));
		r3.setBackground(new Color(222, 255, 228));
		r3.setBounds(300, 470, 200, 30);
		add(r3);

		r4 = new JRadioButton("No");
		r4.setFont(new Font("Arial", Font.BOLD, 18));
		r4.setBackground(new Color(222, 255, 228));
		r4.setBounds(550, 470, 200, 30);
		add(r4);

		ButtonGroup eGroup = new ButtonGroup();
		eGroup.add(r3);
		eGroup.add(r4);

		b1 = new JButton("Next");
		b1.setFont(new Font("Arial", Font.BOLD, 18));
		b1.setBounds(610, 590, 80, 30);
		b1.setForeground(Color.BLACK);
		b1.setBackground(Color.WHITE);
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
	    String rel = (String) religions.getSelectedItem();
	    String cat = (String) category.getSelectedItem();
	    String inc = (String) income.getSelectedItem();
	    String edu = (String) education.getSelectedItem();
	    String occ = (String) occupation.getSelectedItem();
	    String pan = txt1.getText().trim();
	    String aadhar = txt2.getText().trim();
	    String seniorCitizen = r1.isSelected() ? "Yes" : "No";
	    String existingAccount = r3.isSelected() ? "Yes" : "No";

	    if (rel.isEmpty() || cat.isEmpty() || inc.isEmpty() || edu.isEmpty() || occ.isEmpty() || pan.isEmpty() || aadhar.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "All fields must be filled!");
	        return;
	    }

	    try {
	        
	        pst = con.prepareStatement("INSERT INTO signup2 (form_no, religion, category, income, education, occupation, pan, aadhar, seniorcitizen, existing_account) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	        pst.setString(1, formno);
	        pst.setString(2, rel);
	        pst.setString(3, cat);
	        pst.setString(4, inc);
	        pst.setString(5, edu);
	        pst.setString(6, occ);
	        pst.setString(7, pan);
	        pst.setString(8, aadhar);
	        pst.setString(9, seniorCitizen);
	        pst.setString(10, existingAccount);
	        pst.executeUpdate();
	        
	        JOptionPane.showMessageDialog(this, "Details Saved Successfully");
	        new SignUp3(formno);
	        setVisible(false);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Error in storing data");
	        e.printStackTrace();
	    }
	}


	public static void main(String[] args) {
		new SignUp2("");
	}

}
