package com.cms;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.mysql.cj.api.jdbc.Statement;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Window.Type;
import java.util.Scanner;
import java.util.regex.Pattern;

import java.sql.*;

public class Login extends JFrame{

	private JFrame frame;
	private JLabel UserMode;
	private JLabel Passwordlabel;
	private JPasswordField passwordtext;
	private JButton login;
	private JLabel login_icon;
	private JLabel lblEmailEmpty;
	private JLabel lblPasswordEmpty;
	private JButton btnNewButton;
	private JTextField emailfield;
	
	/**
	 * Launch the application.
	 */
	public static void login(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

		frame = new JFrame();
		frame.setResizable(false);
		frame.setAutoRequestFocus(false);
		frame.setAlwaysOnTop(true);
		frame.setBackground(java.awt.Color.black);
		
		frame.setBackground(new Color(255, 255, 255));
		frame.setTitle("Course Management System");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/RBS.png")));
		frame.setForeground(new Color(0, 0, 0));
		frame.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		frame.getContentPane().setBackground(new Color(0, 153, 102));
		frame.setBounds(100, 100, 862, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ImageIcon img = new ImageIcon("background.png");
		
		lblPasswordEmpty = new JLabel("Required Password");
		lblPasswordEmpty.setVisible(false);
		
		emailfield = new JTextField();
		emailfield.setBounds(359, 274, 234, 36);
		frame.getContentPane().add(emailfield);
		emailfield.setColumns(10);
		
		
		
		lblPasswordEmpty.setForeground(Color.RED);
		lblPasswordEmpty.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPasswordEmpty.setBounds(359, 396, 213, 25);
		frame.getContentPane().add(lblPasswordEmpty);
		
		lblEmailEmpty = new JLabel("Required Email");
		lblEmailEmpty.setVisible(false);
		lblEmailEmpty.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmailEmpty.setForeground(new Color(255, 0, 0));
		lblEmailEmpty.setBounds(359, 320, 171, 25);
		frame.getContentPane().add(lblEmailEmpty);
		
		login_icon = new JLabel("");
//		login_icon.setIcon(new ImageIcon(Login.class.getResource("/img/log.png")));
		login_icon.setBounds(24, 227, 128, 112);
		frame.getContentPane().add(login_icon);
		
		JLabel hck_logo = new JLabel("");
		hck_logo.setBackground(new Color(255, 255, 255));
		hck_logo.setForeground(new Color(255, 255, 255));
		hck_logo.setIcon(new ImageIcon(Login.class.getResource("/img/RBS.png")));
		hck_logo.setBounds(13, 9, 135, 138);
		frame.getContentPane().add(hck_logo);
		
		passwordtext = new JPasswordField();
		passwordtext.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		passwordtext.setBounds(359, 355, 234, 33);
		frame.getContentPane().add(passwordtext);
		
		
		
		Passwordlabel = new JLabel("Password:");
		Passwordlabel.setForeground(new Color(255, 255, 255));
		Passwordlabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		Passwordlabel.setBounds(213, 360, 100, 20);
		frame.getContentPane().add(Passwordlabel);
		
	    
//        String passwordcheck = password.getPassword().toString();
//        System.out.println(passwordcheck);
        
		JLabel Emaillabel = new JLabel("Email:");
		Emaillabel.setForeground(new Color(255, 255, 255));
		Emaillabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		Emaillabel.setBounds(212, 281, 86, 20);
		frame.getContentPane().add(Emaillabel);
		
		String selectmode[] = {"Admin", "Instructor", "Student"};
		JComboBox mode = new JComboBox(selectmode);
		mode.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		mode.setBounds(359, 175, 150, 29);
		frame.getContentPane().add(mode);
		
		UserMode = new JLabel("User Mode:");
		UserMode.setForeground(new Color(255, 255, 255));
		UserMode.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		UserMode.setBounds(213, 178, 113, 20);
		frame.getContentPane().add(UserMode);
		
		JLabel welcome = new JLabel("Welcome to Login Panel");
		welcome.setForeground(new Color(255, 255, 255));
		welcome.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		welcome.setBounds(272, 43, 304, 43);
		frame.getContentPane().add(welcome);
		
		login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String selectedvalue = mode.getSelectedItem().toString();  // assigned the String variable to selected items
				
				switch(selectedvalue) {
//    ==============================================================================================================================
				// check for admin mode
				case "Admin":
					/*
					 * Checking the email and password text field is empty or not,
					 * if the email and password text field is empty than display the message as error
					 */
					if(emailfield.getText().isEmpty() && passwordtext.getText().isEmpty()) {
						lblEmailEmpty.setVisible(true);
						lblPasswordEmpty.setVisible(true);
					}else if(passwordtext.getText().isEmpty()) {
						lblPasswordEmpty.setVisible(true);
					}else if(emailfield.getText().isEmpty()) {
						lblEmailEmpty.setVisible(true);	
					}else {
						String emailf = emailfield.getText();
						String pws = String.valueOf(passwordtext.getPassword());
						String url = "jdbc:mysql://localhost:3306/course_management_system";
					    String username = "root";
					    String password = "";
					    
					    try{
				            //load the driver
				            Class.forName("com.mysql.cj.jdbc.Driver");
				            //create the connection
				            Connection connect = DriverManager.getConnection(url,username,password);
				               
				          //select query for check the email and password
				            String q = "Select email , password from admin where email= ? and password=?";
				            
				            PreparedStatement psmt = connect.prepareStatement(q);
				            psmt.setString(1, emailfield.getText());
				            psmt.setString(2, pws);
				           
				           ResultSet rs = psmt.executeQuery(); //creating ResultSet object
				           
				        	  if( rs.next()) {
				        		 Admin admin = new Admin(emailf); //calling and passing the email to admin class
				        		 
				        		  frame.setVisible(false);
				        		  dispose();
				        		  emailfield.setText("");
					        	  passwordtext.setText("");
					        	     
					           }else {
					        	   
					        	   frame.setAlwaysOnTop(false);
					        	   emailfield.setText("");
					        	   passwordtext.setText("");
					        	   JOptionPane.showMessageDialog(null, "!! Email or Password is incorrect !!");
					        	   
					        	   
					           }
				         
				           psmt.close(); //close the Statement
				            
				            connect.close(); //connection close
				        }catch (Exception exp){
				            System.out.println(exp);
				        }	
						
					}
					
					
					
					break;
//	======================================================================================================================================
				// check for tutor mode
				case "Instructor":
					/*
					 * Checking the email and password text field is empty or not,
					 * if the email and password text field is empty than display the message as error
					 */
					if(emailfield.getText().isEmpty() && passwordtext.getText().isEmpty()) {
						lblEmailEmpty.setVisible(true);
						lblPasswordEmpty.setVisible(true);
					}else if(passwordtext.getText().isEmpty()) {
						lblPasswordEmpty.setVisible(true);
					}else if(emailfield.getText().isEmpty()) {
						lblEmailEmpty.setVisible(true);	
					}else {
						String emailf1 = emailfield.getText();
						String pws = String.valueOf(passwordtext.getPassword());
						String url = "jdbc:mysql://localhost:3306/course_management_system";
					    String username = "root";
					    String password = "";
						
						try {
							
						    
							//load the driver
				            Class.forName("com.mysql.cj.jdbc.Driver");
				            //create the connection
				            Connection connect = DriverManager.getConnection(url,username,password);
				               
				            //select query to check email and password
				            String q = "Select email , password from tutor where email= ? and password=?";
				            
				            PreparedStatement psmt = connect.prepareStatement(q);
				            psmt.setString(1, emailfield.getText());
				            psmt.setString(2, pws);
				            
				           ResultSet rs = psmt.executeQuery();
				      
				        	  if( rs.next()) {
				        		  
					        	   passwordtext.setText("");
					        	   new Teacher(emailf1); //calling and passing email  Teacher class
					        	   frame.setVisible(false);
					        	   
					           }else {
					        	   frame.setAlwaysOnTop(false);
					        	   emailfield.setText("");
					        	   passwordtext.setText("");
					        	   JOptionPane.showMessageDialog(null, "!! Email or Password is incorrect !!");
					        	      
					           } 
				           psmt.close();//close the Statement
				            
				            connect.close(); 
			        }catch (Exception exp){
			            System.out.println(exp);
			        }	
					}
					
					break;
					
//	============================================================================================================================================
				// check for Student mode
				case "Student":
					/*
					 * Checking the email and password text field is empty or not,
					 * if the email and password text field is empty than display the message as error
					 */
					if(emailfield.getText().isEmpty() && passwordtext.getText().isEmpty()) {
						lblEmailEmpty.setVisible(true);
						lblPasswordEmpty.setVisible(true);
					}else if(passwordtext.getText().isEmpty()) {
						lblPasswordEmpty.setVisible(true);
					}else if(emailfield.getText().isEmpty()) {
						lblEmailEmpty.setVisible(true);	
					}else {
						
						String emailf2 = emailfield.getText();
						String pws = String.valueOf(passwordtext.getPassword());
						String url = "jdbc:mysql://localhost:3306/course_management_system";
					    String username = "root";
					    String password = "";
						
						try {
							
						    
							//load the driver
				            Class.forName("com.mysql.cj.jdbc.Driver");
				            //create the connection
				            Connection connect = DriverManager.getConnection(url,username,password);
				               
				           //select query for checking the email and password
				            String q = "Select email , password from student where email= ? and password=?";
				            
				            PreparedStatement psmt = connect.prepareStatement(q);
				            psmt.setString(1, emailfield.getText());
				            psmt.setString(2, pws);
				            
				           ResultSet rs = psmt.executeQuery();
				        	  if( rs.next()) {
				        		  emailfield.setText("");
					        	   passwordtext.setText("");
					        	   new Student(emailf2); //calling and passing eamil to Student class
					        	   frame.setVisible(false);
					        	     
					           }else {
					        	   
					        	   frame.setAlwaysOnTop(false);
					        	   emailfield.setText("");
					        	   passwordtext.setText("");
					        	   JOptionPane.showMessageDialog(null, "!! Email or Password is incorrect !!");   
					        	   
					           }
				           psmt.close();
				            //close the Statement
				            connect.close();
			        }catch (Exception exp){
			            System.out.println(exp);
			        }
					
					}
					break;
					
				
				}
					

			}
		});
		login.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		login.setBounds(366, 488, 162, 43);
		login.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(login);
		JLabel background = new JLabel("", new ImageIcon(Login.class.getResource("/img/background.png")), JLabel.CENTER);
		background.setBounds(0, 0, 848, 566);
		frame.getContentPane().add(background);
		
		frame.setVisible(true);
	}
}
