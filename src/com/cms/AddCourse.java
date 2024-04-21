package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.RowFilter;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class AddCourse extends JFrame {

	private JFrame frame;
	private JTextField searchfield;
	private JTextField coursename;
	private JTextField no_of_year;
	private JTable coursedetails;

	/**
	 * Launch the application.
	 */
	public static void addcourse(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourse window = new AddCourse();
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
	public AddCourse() {
		initialize();
		showinfocourse();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AddCourse.class.getResource("/img/RBS.png")));
		frame.setTitle("Course Management System");
		frame.setBounds(100, 100, 742, 612);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(196, 249, 223));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCoursedetails = new JLabel("Course Details");
		lblCoursedetails.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblCoursedetails.setBounds(256, 2, 224, 42);
		panel.add(lblCoursedetails);
		
		searchfield = new JTextField();
		searchfield.setToolTipText("Search by Course Name");
		searchfield.setBounds(183, 64, 202, 28);
		panel.add(searchfield);
		searchfield.setColumns(10);
		
		coursename = new JTextField();
		coursename.setColumns(10);
		coursename.setBounds(188, 125, 201, 28);
		panel.add(coursename);
		
		no_of_year = new JTextField();
		no_of_year.setColumns(10);
		no_of_year.setBounds(562, 123, 88, 28);
		panel.add(no_of_year);
//*****************************************************************************************************************************************
		/*
		 * This section is for search
		 */
		JButton btnsearch = new JButton("Search");
		btnsearch.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				search(searchfield.getText()); //for search method is calling
				
				//display the course details in the tet fields according to search by course name
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		            
		          
		            String query1 = "select course_name , no_of_years from course_info where course_name = ?";
		            
		            PreparedStatement pstate1 = connect.prepareStatement(query1);
		            pstate1.setString(1, searchfield.getText());
		  
		            ResultSet rs = pstate1.executeQuery();
		            
		            if(rs.next()) {
		            	
		            	coursename.setText(rs.getString("course_name"));
		            	no_of_year.setText(rs.getString("no_of_years"));
		            }
		           
		            
		            pstate1.close();
		            //close the Statement
		            connect.close(); //close connection
		           
			    
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
				
			}
		});
		btnsearch.setBackground(SystemColor.inactiveCaption);
		btnsearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnsearch.setBounds(84, 64, 85, 28);
		panel.add(btnsearch);
		
//****************************************************************************************************************************************	
		/*
		 * This section is for register the course in the database
		 */
		JButton btnregister = new JButton("Register");
		btnregister.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				 //first check the textfields are  empty or not, if empty diplay the message else add the course
				if(coursename.getText().isEmpty() && no_of_year.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "!! Course Name and No of Years required !!");
					
				}else if(coursename.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "!! Course Name required !!");
					
				}else if(no_of_year.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "!! No of Years required !!");
				}else {
					
					String url = "jdbc:mysql://localhost:3306/course_management_system";
				    String username = "root";
				    String password = "";
				    
				    try{
			            //load the driver
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            //create the connection
			            Connection connect = DriverManager.getConnection(url,username,password);
			            
			            //first check the course is already register or not 
			            String query = "select course_name, no_of_years from course_info where course_name =? and no_of_years =?";
			            PreparedStatement pstate = connect.prepareStatement(query);
			            pstate.setString(1, coursename.getText());
			            pstate.setString(2, no_of_year.getText());
			            
			            ResultSet rs1 = pstate.executeQuery();
			            
			            if(rs1.next()) {
							
			            	JOptionPane.showMessageDialog(null, "Course Already exist.");
						}else {
							
							//allow to insert the course in database
							String url1 = "jdbc:mysql://localhost:3306/course_management_system";
						    String username1 = "root";
						    String password1 = "";
						    
						    try{
					            //load the driver
					            Class.forName("com.mysql.cj.jdbc.Driver");
					            //create the connection
					            Connection connect1 = DriverManager.getConnection(url1,username1,password1);
					            
					            String query1 = "insert into course_info (course_name, no_of_years) values (?,?)";
					            
					            PreparedStatement pstate1 = connect1.prepareStatement(query1);
					            pstate1.setString(1, coursename.getText());
					            pstate1.setString(2, no_of_year.getText());
					            
					            pstate1.executeUpdate();
					            JOptionPane.showMessageDialog(null, "Course added succefully!");
					            
					            pstate1.close();
					            //close the Statement
					            connect1.close();
					            
					            //after adding the course than in the text fields is empty
					            coursename.setText("");
					            no_of_year.setText("");
						    
					        }catch (Exception exp){
					            System.out.println(exp);
					        }
							
						}

			            
			            connect.close();
			            
				    }catch (Exception exp){
			            System.out.println(exp);
			        }	
					
				}
			}
		});
		btnregister.setBackground(SystemColor.control);
		btnregister.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnregister.setBounds(481, 195, 113, 28);
		panel.add(btnregister);
		
//****************************************************************************************************************************************	
		/*
		 * This block is for edit the course and update in the database
		 */
		JButton btnedit = new JButton("edit");
		btnedit.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				//to edit also first check the textfields are  empty or not, if empty diplay the message else add the course
				if(coursename.getText().isEmpty() && no_of_year.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "!! Course Name and No of Years required !!");
					
				}else if(coursename.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "!! Course Name required !!");
					
				}else if(no_of_year.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "!! No of Years required !!");
				}else {
					
							String url1 = "jdbc:mysql://localhost:3306/course_management_system";
						    String username1 = "root";
						    String password1 = "";
						    
						    try{
					            //load the driver
					            Class.forName("com.mysql.cj.jdbc.Driver");
					            //create the connection
					            Connection connect1 = DriverManager.getConnection(url1,username1,password1);
					            
					            //update query for edit
					            String query1 = "update course_info set course_name = ?, no_of_years = ? where course_name = ?";
					            
					            PreparedStatement pstate1 = connect1.prepareStatement(query1);
					            pstate1.setString(1, coursename.getText());
					            pstate1.setString(2, no_of_year.getText());
					            pstate1.setString(3, coursename.getText());
					            
					            pstate1.executeUpdate();
					            
					            
					            pstate1.close();
					            //close the Statement
					            connect1.close();
					            
					            JOptionPane.showMessageDialog(null, "Course update succefully!");
					            coursename.setText("");
					            no_of_year.setText("");
						    
					        }catch (Exception exp){
					            System.out.println(exp);
					        }
							
						}
	
			}
		});
		btnedit.setBackground(SystemColor.info);
		btnedit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnedit.setBounds(129, 194, 85, 28);
		panel.add(btnedit);
//***************************************************************************************************************************************
		/*
		 * This section is for delete the course.
		 * if the course will delete than all the record of related course will be deleted such as module information of related course,
		 * module assigned of related course to tutor and student enrolled to the related course.
		 */
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				if(searchfield.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Search to Delete Course.");
					
				}else {
					String url5 = "jdbc:mysql://localhost:3306/course_management_system";
				    String username5 = "root";
				    String password5 = "";
				    
				    try{
			            //load the driver
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            //create the connection
			            Connection connect5 = DriverManager.getConnection(url5,username5,password5);
			            
			            String qu = "SELECT course_name, no_of_years FROM course_info WHERE  course_name = ?";
			            PreparedStatement pstate5 = connect5.prepareStatement(qu);
			            pstate5.setString(1, searchfield.getText());
			            ResultSet rs = pstate5.executeQuery();
					
						if(rs.next()) {
							
							JOptionPane.showConfirmDialog(btndelete, "Do you want to delete the course(deleting "
									+ "course will delete all the information of related to it such as module information of related course,"
									+ "module assigned of related course to tutor and student enrolled to the related course)?");
							
							String url1 = "jdbc:mysql://localhost:3306/course_management_system";
						    String username1 = "root";
						    String password1 = "";
							
						    try{
					            //load the driver
					            Class.forName("com.mysql.cj.jdbc.Driver");
					            //create the connection
					            Connection connect1 = DriverManager.getConnection(url1,username1,password1);
					            
					            String delcourse = "DELETE FROM course_info WHERE course_name = ?";
					            PreparedStatement pstate1 = connect1.prepareStatement(delcourse);
					            pstate1.setString(1, searchfield.getText());
					            pstate1.executeUpdate();
					            pstate1.close();  
					            
					        
					            connect1.close();
					            
					            JOptionPane.showMessageDialog(null, "Delete Course Successfully.");
					        }catch (Exception exp){
					            System.out.println(exp);
					        }
						    
			//######################################################################################################################			    
						    
						    String urlmodule = "jdbc:mysql://localhost:3306/course_management_system";
						    String usernamemodule = "root";
						    String passwordmodule = "";
							
						    try{
					            //load the driver
					            Class.forName("com.mysql.cj.jdbc.Driver");
					            //create the connection
					            Connection connectmodule = DriverManager.getConnection(urlmodule,usernamemodule,passwordmodule);
					            
					            String delmodule = "DELETE FROM course WHERE course_name =? ";
					            PreparedStatement pstate2 = connectmodule.prepareStatement(delmodule);
					            pstate2.setString(1, searchfield.getText());
					            pstate2.executeUpdate();
					            pstate2.close();
					            
					            connectmodule.close();
					            
						    }catch (Exception exp){
					            System.out.println(exp);
					        }
			//#######################################################################################################################
						    
						    String urlassignmodule = "jdbc:mysql://localhost:3306/course_management_system";
						    String usernameassignmodule = "root";
						    String passwordassignmodule = "";
							
						    try{
					            //load the driver
					            Class.forName("com.mysql.cj.jdbc.Driver");
					            //create the connection
					            Connection connectassignmodule = DriverManager.getConnection(urlassignmodule,usernameassignmodule,passwordassignmodule);
					            
					            String delassignmodule = "DELETE FROM module_assigned WHERE course_name =? ";
					            PreparedStatement pstate3 = connectassignmodule.prepareStatement(delassignmodule);
					            pstate3.setString(1, searchfield.getText());
					            pstate3.executeUpdate();
					            pstate3.close();
					            
					            connectassignmodule.close();
					            
						    }catch (Exception exp){
					            System.out.println(exp);
					        }
			//##############################################################################################################################3			    
						    
						    String urlenrollstd = "jdbc:mysql://localhost:3306/course_management_system";
						    String usernameenrollstd = "root";
						    String passwordenrollstd = "";
							
						    try{
					            //load the driver
					            Class.forName("com.mysql.cj.jdbc.Driver");
					            //create the connection
					            Connection connectenrollstd = DriverManager.getConnection(urlenrollstd,usernameenrollstd,passwordenrollstd);
					            
					            String delenrollstd = "DELETE FROM student_enroll_info WHERE course_name =?";
					            PreparedStatement pstate4 = connectenrollstd.prepareStatement(delenrollstd);
					            pstate4.setString(1, searchfield.getText());
					            pstate4.executeUpdate();
					            pstate4.close();
					            
					            connectenrollstd.close();
					            
						    }catch (Exception exp){
					            System.out.println(exp);
					        }
						
						}else {
						
							JOptionPane.showMessageDialog(null, "Course record not found.");
						}
						
						connect5.close();
						
						coursename.setText("");
						no_of_year.setText("");
						searchfield.setText("");
						DefaultTableModel model = (DefaultTableModel)coursedetails.getModel();
						model.setRowCount(0); // set the row count 0
						
						showinfocourse();
						search(searchfield.getText());
					
				 }catch (Exception exp){
			            System.out.println(exp);
			        }
				    
					
				}
			}
		});
		btndelete.setBackground(new Color(241, 138, 120));
		btndelete.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btndelete.setBounds(421, 62, 85, 28);
		panel.add(btndelete);
		
//***************************************************************************************************************************************
		/*
		 * This section is for refresh the jtable
		 */
		JButton btnrefresh = new JButton("Refresh");
		btnrefresh.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				if(searchfield.getText().isEmpty()) {
					
					search(searchfield.getText());
					DefaultTableModel model = (DefaultTableModel)coursedetails.getModel(); //set the object of defaulttablemodel
					model.setRowCount(0); //set the row count 0
					showinfocourse(); //calling the function for show the data in jtable
					coursename.setText("");
					no_of_year.setText("");
				}else {
					DefaultTableModel model = (DefaultTableModel)coursedetails.getModel(); //set the object of defaulttablemodel
					model.setRowCount(0); //set the row count 0
					showinfocourse(); //calling the function for show the data in jtable
					coursename.setText("");
					no_of_year.setText("");
				}
				
			}
		});
		btnrefresh.setBackground(SystemColor.activeCaption);
		btnrefresh.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnrefresh.setBounds(513, 522, 102, 28);
		panel.add(btnrefresh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(88, 296, 527, 204);
		panel.add(scrollPane);
		
		coursedetails = new JTable();
		coursedetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Course Name", "No_of_Year"
			}
		));
		coursedetails.getColumnModel().getColumn(1).setPreferredWidth(191);
		scrollPane.setViewportView(coursedetails);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(38, 246, 628, 10);
		panel.add(separator);
		
		JLabel lblCoursename = new JLabel("Course Name");
		lblCoursename.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCoursename.setBounds(84, 126, 102, 22);
		panel.add(lblCoursename);
		
		JLabel lblNoofyear = new JLabel("No of Year");
		lblNoofyear.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNoofyear.setBounds(469, 126, 86, 22);
		panel.add(lblNoofyear);
		
		JLabel lblCourseinfo = new JLabel("Course Information");
		lblCourseinfo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblCourseinfo.setBounds(90, 266, 210, 22);
		panel.add(lblCourseinfo);
		
		frame.setVisible(true);
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------
	public void showinfocourse() {
		
		String url = "jdbc:mysql://localhost:3306/course_management_system";
	    String username = "root";
	    String password = "";
	    
	    try{
            //load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create the connection
            Connection connect = DriverManager.getConnection(url,username,password);
            
            String id = "";
            String course_name = "";
            String no_years = "";
            
            String query1 = "select * from course_info ";
            
            PreparedStatement pstate1 = connect.prepareStatement(query1);
  
            ResultSet rs = pstate1.executeQuery();
            
            DefaultTableModel model1 = (DefaultTableModel)coursedetails.getModel();  //creating object the defaulttablemodel
            
            while(rs.next()) {
            	
            	id = rs.getString("ID");
            	course_name = rs.getString("course_name");
            	no_years = rs.getString("no_of_years");
            	
            	//storing the variables data in the array
            	String[] row = {id, course_name, no_years};
            	model1.addRow(row); //adding array as a row in the jscrollpanel 
            	
            }
            
            
            pstate1.close();
            //close the Statement
            connect.close();
            
            
	    
        }catch (Exception exp){
            System.out.println(exp);
        }
		
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void search(String str) {
		/*
		 * This method is for searching course information in the jtable
		 */
		
		DefaultTableModel model = (DefaultTableModel)coursedetails.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
		coursedetails.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(str));
		
	}
	
}
