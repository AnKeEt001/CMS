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
import javax.swing.JSeparator;
import javax.swing.JTextField;

import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
public class EditModule extends JFrame{

	private JFrame frame;
	private JTextField CourseName;
	private JTextField NoofYear;
	private JTextField Semester;
	private JTextField Level;
	private JTextField ModuleName;
	private JTextField ModuleCode;
	private JTextField search;

	/**
	 * Launch the application.
	 */
	public static void editmodule(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditModule window = new EditModule();
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
	public EditModule() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EditModule.class.getResource("/img/RBS.png")));
		frame.setTitle("Course Management System");
		frame.setBounds(-7, -10, 728, 638);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 247, 226));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCourseName.setBounds(31, 119, 97, 28);
		panel.add(lblCourseName);
		
		
		
		JLabel lblNo_of_year = new JLabel("No. of years");
		lblNo_of_year.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNo_of_year.setBounds(478, 119, 97, 28);
		panel.add(lblNo_of_year);
		
		
		
		JLabel lblSemester1 = new JLabel("Semester");
		lblSemester1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSemester1.setBounds(31, 187, 71, 28);
		panel.add(lblSemester1);
		
		JLabel lblModuleinfo = new JLabel("Module Information");
		lblModuleinfo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblModuleinfo.setBounds(32, 285, 150, 28);
		panel.add(lblModuleinfo);
		
		JLabel lblModuleName1 = new JLabel("Module Name");
		lblModuleName1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblModuleName1.setBounds(31, 396, 97, 28);
		panel.add(lblModuleName1);
		
		
		JLabel lblModuleCode1 = new JLabel("Module Code");
		lblModuleCode1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblModuleCode1.setBounds(450, 396, 97, 28);
		panel.add(lblModuleCode1);
		
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblLevel.setBounds(456, 187, 49, 28);
		panel.add(lblLevel);
		
		
		JCheckBox optional = new JCheckBox("Optional");
		optional.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		optional.setBounds(31, 341, 93, 21);
		panel.add(optional);
		
		JButton btnedit = new JButton("Update");
		btnedit.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			    LocalDateTime now = LocalDateTime.now();  
			   
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		               
		           
		            //for edit module also check the module is optional or not
		            if(optional.isSelected()) {
		            	
		            	//if module is optional than execute this query for update by module name
		            	 String query = "UPDATE course SET course_name = ? , no_of_years = ?, level =?, semester = ?, module_code =?, module_name = ?, optional = ? where module_name = ?";
				            
				            PreparedStatement pstate = connect.prepareStatement(query);
				           
				            pstate.setString(1, CourseName.getText());
				            pstate.setString(2, NoofYear.getText());
				            pstate.setString(3, Level.getText());
				            pstate.setString(4, Semester.getText());
				            pstate.setString(5, ModuleCode.getText());
				            pstate.setString(6, ModuleName.getText());
				            pstate.setString(7, "true");
				            pstate.setString(8,search.getText());
				            pstate.executeUpdate();
				            
				            JOptionPane.showMessageDialog(null, "Course updated successfully");
				            frame.dispose();
		            }else {
		            	
		            	//if module is not optional than execute this query for update by module name
		            	String query = "UPDATE course SET course_name = ? , no_of_years = ?, level =?, semester = ?, module_code =?, module_name = ?, optional = ? where module_name = ?";
		            	 		
			            	PreparedStatement pstate = connect.prepareStatement(query);
			            	
				            pstate.setString(1, CourseName.getText());
				            pstate.setString(2, NoofYear.getText());
				            pstate.setString(3, Level.getText());
				            pstate.setString(4, Semester.getText());
				            pstate.setString(5, ModuleCode.getText());
				            pstate.setString(6, ModuleName.getText());
				            pstate.setString(7, "false");
				            
				            pstate.setString(8,search.getText());
				            pstate.executeUpdate();
				            
				            JOptionPane.showMessageDialog(null, "Course updated successfully");
				            frame.dispose();
		            }
		            //close the Statement
		            connect.close();
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
				
				
			}
		});
		btnedit.setBackground(SystemColor.info);
		btnedit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnedit.setBounds(571, 481, 120, 28);
		panel.add(btnedit);
		
		JLabel lblNewLabel = new JLabel("Course Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(238, 3, 231, 31);
		panel.add(lblNewLabel);
		
		CourseName = new JTextField();
		CourseName.setBounds(126, 119, 216, 31);
		panel.add(CourseName);
		CourseName.setColumns(10);
		
		NoofYear = new JTextField();
		NoofYear.setBounds(571, 120, 59, 31);
		panel.add(NoofYear);
		NoofYear.setColumns(10);
		
		Semester = new JTextField();
		Semester.setBounds(109, 188, 59, 31);
		panel.add(Semester);
		Semester.setColumns(10);
		
		Level = new JTextField();
		Level.setBounds(510, 187, 144, 31);
		panel.add(Level);
		Level.setColumns(10);
		
		ModuleName = new JTextField();
		ModuleName.setBounds(135, 396, 231, 31);
		panel.add(ModuleName);
		ModuleName.setColumns(10);
		
		ModuleCode = new JTextField();
		ModuleCode.setBounds(541, 394, 144, 31);
		panel.add(ModuleCode);
		ModuleCode.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 261, 658, 14);
		panel.add(separator);
		
		
		JLabel lblsearch = new JLabel("Search");
		lblsearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblsearch.setBounds(31, 56, 59, 28);
		panel.add(lblsearch);
		
		search = new JTextField();
		search.setToolTipText("Search by module name or module code");
		search.setColumns(10);
		search.setBounds(99, 56, 216, 31);
		panel.add(search);
		
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
		             //search the module details by module name or module code  		            
		            String query = "SELECT ID,course_name,no_of_years,level, semester, module_code, module_name, optional FROM course WHERE module_name = ? or  module_code =?";
		            
		            PreparedStatement pstate = connect.prepareStatement(query);
		            pstate.setString(1, search.getText());
		            pstate.setString(2, search.getText());
		            ResultSet rs = pstate.executeQuery();
		         
		        	  if( rs.next()) {
		        		 
		        		  CourseName.setText(rs.getString("course_name"));
		        		  NoofYear.setText(rs.getString("no_of_years"));
		        		  Semester.setText(rs.getString("semester"));
		        		  Level.setText(rs.getString("level"));
		        		  ModuleName.setText(rs.getString("module_name"));
		        		  ModuleCode.setText(rs.getString("module_code"));
		                  
		        		  String opt = rs.getString("optional");
		        		  if(opt.equals("true")) {
		        			  
		        			  optional.setSelected(true);
		        			  
		        		  }else {
		        			  
		        			  optional.setSelected(false);
		        		  }
			        	   
			        	   
			           }else {
			        	   JOptionPane.showMessageDialog(null, "!! Sorry record not found !!");
			        	   
			        	   CourseName.setText("");
			        		  NoofYear.setText("");
			        		  Semester.setText("");
			        		  Level.setText("");
			        		  ModuleName.setText("");
			        		  ModuleCode.setText("");
			        		  search.requestFocus();
			        	  
			        	   
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
		btnsearch.setBounds(349, 56, 120, 28);
		panel.add(btnsearch);
		
		
		
		frame.setVisible(true);
		
	}
}
