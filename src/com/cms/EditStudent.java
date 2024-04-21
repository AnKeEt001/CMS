package com.cms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class EditStudent extends JFrame{

	private JFrame frame;
	private JTextField fullname;
	private JTextField paswd;
	private JTextField email;
	private JTextField mblno;
	private JTextField stdID;
	private JTextField searchField;

	/**
	 * Launch the application.
	 */
	public static void editstd(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStudent window = new EditStudent();
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
	public EditStudent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EditTutor.class.getResource("/img/RBS.png")));
		frame.setTitle("Course Management System");
		frame.setBounds(100, 100, 701, 468);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(196, 249, 223));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTutorDetails = new JLabel("Student Details");
		lblTutorDetails.setFont(new Font("Consolas", Font.BOLD, 30));
		lblTutorDetails.setBounds(204, 1, 279, 36);
		panel.add(lblTutorDetails);
		
		JLabel lblFullname = new JLabel("Fullname");
		lblFullname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblFullname.setBounds(22, 112, 64, 36);
		panel.add(lblFullname);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPassword.setBounds(22, 176, 64, 36);
		panel.add(lblPassword);
		
	
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblEmail.setBounds(402, 116, 45, 36);
		panel.add(lblEmail);
		
		
		JLabel lblMblno = new JLabel("Mobile no.");
		lblMblno.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblMblno.setBounds(22, 241, 78, 36);
		panel.add(lblMblno);
		
		
		fullname = new JTextField();
		fullname.setBounds(90, 119, 180, 25);
		panel.add(fullname);
		fullname.setColumns(10);
		
		paswd = new JTextField();
		paswd.setBounds(93, 182, 180, 25);
		panel.add(paswd);
		paswd.setColumns(10);
		
		email = new JTextField();
		email.setBounds(448, 121, 180, 25);
		panel.add(email);
		email.setColumns(10);
		
		mblno = new JTextField();
		mblno.setBounds(101, 247, 180, 25);
		panel.add(mblno);
		mblno.setColumns(10);
		
		JLabel lblCollegeID = new JLabel("StudentID");
		lblCollegeID.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCollegeID.setBounds(365, 185, 78, 21);
		panel.add(lblCollegeID);
		
		stdID = new JTextField();
		stdID.setColumns(10);
		stdID.setBounds(450, 181, 180, 25);
		panel.add(stdID);
		
		searchField = new JTextField();
		searchField.setColumns(10);
		searchField.setBounds(148, 53, 237, 31);
		panel.add(searchField);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		               
		            //update the student with the help of student full name and student ID
		            	 String query = "UPDATE student SET fullname = ? , studentID = ?, email =?, mblNo = ?, password =? where fullname = ? or studentID = ? ";
				            
				            PreparedStatement pstate = connect.prepareStatement(query);
				           
				            pstate.setString(1, fullname.getText());
				            pstate.setString(2, stdID.getText());
				            pstate.setString(3, email.getText());
				            pstate.setString(4, mblno.getText());
				            pstate.setString(5, paswd.getText());
				            pstate.setString(6, searchField.getText());
				            pstate.setString(7, searchField.getText());
				            pstate.executeUpdate();
				            
				            JOptionPane.showMessageDialog(null, "Tutor updated successfully");
				            frame.dispose();
		            
		            //close the Statement
		            connect.close();
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
				
				
			}
		});
		btnUpdate.setBackground(SystemColor.info);
		btnUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnUpdate.setBounds(506, 338, 120, 36);
		panel.add(btnUpdate);
		
		
		
		JButton btnsearch = new JButton("search");
		btnsearch.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		            
		            //search the student details by stuednt ID or student name
		            String query = "SELECT ID,fullname,studentID,email, mblNo, password FROM student WHERE fullname=? or studentID =?";
		            
		            PreparedStatement pstate = connect.prepareStatement(query);
		            pstate.setString(1, searchField.getText());
		            pstate.setString(2, searchField.getText());
		            
		            ResultSet rs = pstate.executeQuery();
		            
		            if( rs.next()) {
		        		 
		            		fullname.setText(rs.getString("fullname"));
		            		stdID.setText(rs.getString("studentID"));
		            		email.setText(rs.getString("email"));
		        		  
		        		  	paswd.setText(rs.getString("password"));
		        		  
		                  
		        		  String optmbl = rs.getString("mblNo");
		        		  if(optmbl.equals("N/A")) {
		        			  
		        			  mblno.setText("N/A");
		        			  
		        		  }else {
		        			  
		        			  mblno.setText(rs.getString("mblNo"));
		        		  }
			        	   
			        	   
			           }else {
			        	   JOptionPane.showMessageDialog(null, "!! Sorry record not found !!");
			        	   
			        	   		fullname.setText("");
			        	   		stdID.setText("");
			        	   		email.setText("");
			        	   		paswd.setText("");
			        	   		mblno.setText("");   
			           }
		          
		           
		        	  pstate.close();
		            
		            
		            
		            //close the Statement
		            connect.close();
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
			}
		});
		btnsearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnsearch.setBackground(SystemColor.info);
		btnsearch.setBounds(10, 53, 120, 30);
		panel.add(btnsearch);
		
		frame.setVisible(true);
		
	}

}
