package com.cms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class EditTutor extends JFrame{

	private JFrame frame;
	private JTextField fullname;
	private JTextField paswd;
	private JTextField email;
	private JTextField mblno;
	private JTextField tutorID;
	private JTextField searchfield;

	/**
	 * Launch the application.
	 */
	public static void edittutor(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditTutor window = new EditTutor();
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
	public EditTutor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EditTutor.class.getResource("/img/RBS.png")));
		frame.setTitle("Course Management System");
		frame.setBounds(100, 100, 701, 448);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(196, 249, 223));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTutorDetails = new JLabel("Tutor Details");
		lblTutorDetails.setFont(new Font("Consolas", Font.BOLD, 30));
		lblTutorDetails.setBounds(218, 0, 237, 36);
		panel.add(lblTutorDetails);
		
		JLabel lblFullname = new JLabel("Fullname");
		lblFullname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblFullname.setBounds(22, 124, 64, 36);
		panel.add(lblFullname);
		
		fullname = new JTextField();
		fullname.setBounds(90, 127, 201, 31);
		panel.add(fullname);
		fullname.setColumns(10);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPassword.setBounds(22, 188, 64, 36);
		panel.add(lblPassword);
		
		paswd = new JTextField();
		paswd.setBounds(93, 188, 180, 33);
		panel.add(paswd);
		paswd.setColumns(10);
		
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblEmail.setBounds(370, 124, 45, 36);
		panel.add(lblEmail);
		
		email = new JTextField();
		email.setBounds(416, 128, 213, 31);
		panel.add(email);
		email.setColumns(10);
		
		JLabel lblMblno = new JLabel("Mobile no.");
		lblMblno.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblMblno.setBounds(22, 253, 78, 36);
		panel.add(lblMblno);
		
		mblno = new JTextField();
		mblno.setBounds(101, 259, 180, 29);
		panel.add(mblno);
		mblno.setColumns(10);
		
		tutorID = new JTextField();
		tutorID.setBounds(436, 193, 131, 29);
		panel.add(tutorID);
		tutorID.setColumns(10);
		
		
		searchfield = new JTextField();
		searchfield.setColumns(10);
		searchfield.setBounds(146, 62, 205, 31);
		panel.add(searchfield);
		
		JButton btnedit = new JButton("Update");
		btnedit.addActionListener(new ActionListener() {
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
		               
		            //update the tutor deatils by tutor name or tutor id
		            
		            	 String query = "UPDATE tutor SET fullname = ? , tutorID = ?, email =?, mblNo = ?, password =? where fullname = ? or tutorID = ? ";
				            
				            PreparedStatement pstate = connect.prepareStatement(query);
				           
				            pstate.setString(1, fullname.getText());
				            pstate.setString(2, tutorID.getText());
				            pstate.setString(3, email.getText());
				            pstate.setString(4, mblno.getText());
				            pstate.setString(5, paswd.getText());
				            pstate.setString(6, searchfield.getText());
				            pstate.setString(7, searchfield.getText());
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
		btnedit.setBackground(SystemColor.info);
		btnedit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnedit.setBounds(500, 327, 120, 36);
		panel.add(btnedit);
		
		JLabel lblCollegeID = new JLabel("TutorID");
		lblCollegeID.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCollegeID.setBounds(368, 197, 64, 21);
		panel.add(lblCollegeID);
		
		
		
		
		
		JButton btnsearch = new JButton("Search");
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
		            
		            //searching the tutor deatils by tutor id or tutor name
		            String query = "SELECT ID,fullname,tutorID,email, mblNo, password FROM tutor WHERE fullname=? or tutorID =?";
		            
		            PreparedStatement pstate = connect.prepareStatement(query);
		            pstate.setString(1, searchfield.getText());
		            pstate.setString(2, searchfield.getText());
		            
		            ResultSet rs = pstate.executeQuery();
		            
		            if( rs.next()) {
		        		 
		            		fullname.setText(rs.getString("fullname"));
		            		tutorID.setText(rs.getString("tutorID"));
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
			        	   		tutorID.setText("");
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
		btnsearch.setBounds(14, 60, 120, 33);
		panel.add(btnsearch);
		
		
		frame.setVisible(true);
		
		
		
		
		
	}
}
