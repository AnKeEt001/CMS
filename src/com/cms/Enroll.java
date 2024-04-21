package com.cms;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Enroll extends JFrame{

	private JFrame frame;
	private JTable EnrollStdInfo;
	private JTextField stdfullname;
	private JTextField stdID;
	private JTextField search;
	private JTextField namecourse;
	private JTextField leveltext;

	/**
	 * Launch the application.
	 */
	public static void enroll(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Enroll window = new Enroll();
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
	public Enroll() {
		initialize();
		showinfo();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EditTutor.class.getResource("/img/RBS.png")));
		frame.setTitle("Course Management System");
		frame.setBounds(100, 100, 857, 705);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(196, 249, 223));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Enrollment");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(368, 10, 184, 37);
		panel.add(lblNewLabel);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCourseName.setBounds(51, 171, 97, 31);
		panel.add(lblCourseName);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblLevel.setBounds(483, 173, 52, 31);
		panel.add(lblLevel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.control, null, null));
		scrollPane.setBounds(46, 277, 725, 281);
		panel.add(scrollPane);
		
		EnrollStdInfo = new JTable();
		EnrollStdInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		EnrollStdInfo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Course Name", "Student ID", "Student Fullname", "level"
			}
		));
		EnrollStdInfo.getColumnModel().getColumn(0).setPreferredWidth(47);
		EnrollStdInfo.getColumnModel().getColumn(1).setPreferredWidth(165);
		EnrollStdInfo.getColumnModel().getColumn(2).setPreferredWidth(60);
		EnrollStdInfo.getColumnModel().getColumn(3).setPreferredWidth(187);
		EnrollStdInfo.getColumnModel().getColumn(4).setPreferredWidth(50);
		scrollPane.setViewportView(EnrollStdInfo);
		
		JLabel lblModuleInfo = new JLabel("Student Enrolled Information");
		lblModuleInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblModuleInfo.setBounds(50, 237, 376, 31);
		panel.add(lblModuleInfo);
		
//************************************************************************************************************		
		JButton btnEnroll = new JButton("Refresh");
		btnEnroll.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				 //check the search text field is empty or not for search
				if(search.getText().isEmpty()) {
					
					search(search.getText());
					DefaultTableModel model2 = (DefaultTableModel)EnrollStdInfo.getModel(); //set the object of defaulttablemodel
					model2.setRowCount(0); //set the row count 0
					showinfo(); //calling the function for show the data in jtable
					
				}else {
					
					DefaultTableModel model2 = (DefaultTableModel)EnrollStdInfo.getModel(); //set the object of defaulttablemodel
					model2.setRowCount(0); //set the row count 0
					showinfo(); //calling the function for show the data in jtable
				}
			}
		});
		btnEnroll.setToolTipText("");
		btnEnroll.setBackground(Color.LIGHT_GRAY);
		btnEnroll.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnEnroll.setBounds(660, 590, 114, 31);
		panel.add(btnEnroll);
//**************************************************************************************************************		
		stdfullname = new JTextField();
		stdfullname.setBounds(177, 122, 206, 27);
		panel.add(stdfullname);
		stdfullname.setColumns(10);
		
		stdID = new JTextField();
		stdID.setColumns(10);
		stdID.setBounds(553, 119, 116, 27);
		panel.add(stdID);
		
		JLabel lblstdFullname = new JLabel("Student fullname");
		lblstdFullname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblstdFullname.setBounds(46, 119, 128, 31);
		panel.add(lblstdFullname);
		
		JLabel lblstdID = new JLabel("Student ID");
		lblstdID.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblstdID.setBounds(449, 119, 97, 31);
		panel.add(lblstdID);
		
		namecourse = new JTextField();
		namecourse.setColumns(10);
		namecourse.setBounds(158, 175, 225, 27);
		panel.add(namecourse);
		
		leveltext = new JTextField();
		leveltext.setColumns(10);
		leveltext.setBounds(534, 177, 114, 27);
		panel.add(leveltext);
		
//******************************************************************************************************************	
		/*
		 * This block is for search for edit and delete enrolled student
		 */
		search = new JTextField();
		search.setToolTipText("Search by Student ID");
		search.setColumns(10);
		search.setBounds(167, 64, 197, 31);
		panel.add(search);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				search(search.getText());//calling the function to search by passing the String
				
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		            
		            //this query id for displaying the enroll student deatils by student ID
		            String query = "SELECT course_name, stdID,std_fullname, level FROM student_enroll_info WHERE stdID = ? or  std_fullname =?";
		            
		            PreparedStatement pstate = connect.prepareStatement(query);
		            pstate.setString(1, search.getText());
		            pstate.setString(2, search.getText());
		            ResultSet rs = pstate.executeQuery();
		         
		        	  if( rs.next()) {
		        		 
		        		  stdID.setText(rs.getString("stdID"));
		        		  stdfullname.setText(rs.getString("std_fullname"));
		        		  namecourse.setText(rs.getString("course_name"));
		        		  leveltext.setText(rs.getString("level")); 
			           }else {
//			        	   JOptionPane.showMessageDialog(null, "!! Sorry record not found !!");
			        	   
			        	   stdID.setText("");
			        	   stdfullname.setText("");
			        	   namecourse.setText("");
			        	   leveltext.setText("");
			        		  	   
			           }
		          
		           
		        	  pstate.close();
		           
		            //close the Statement
		            connect.close();
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
			}
		});
		
		btnSearch.setToolTipText("Click to get enroll");
		btnSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnSearch.setBackground(Color.LIGHT_GRAY);
		btnSearch.setBounds(51, 65, 97, 31);
		panel.add(btnSearch);
		
//******************************************************************************************************************************
		/*
		 * This section is for edit the enrolled student
		 */
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
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
		               
		            	//update query for enroll to edit by student ID or Student name
		            	 String query = "UPDATE student_enroll_info SET course_name = ? , stdID = ?, std_fullname =?, level = ?"
		            	 		+ "where stdID = ? or std_fullname = ?";
				            
				            PreparedStatement pstate = connect.prepareStatement(query);
				           
				            pstate.setString(1, namecourse.getText());
				            pstate.setString(2, stdID.getText());
				            pstate.setString(3, stdfullname.getText());
				            pstate.setString(4, leveltext.getText());
				            pstate.setString(5, search.getText());
				            pstate.setString(6, search.getText());
				           
				            pstate.executeUpdate();
				            
				            JOptionPane.showMessageDialog(null, "Enrolled updated successfully");
				            DefaultTableModel model2 = (DefaultTableModel)EnrollStdInfo.getModel(); //set the object of defaulttablemodel
							model2.setRowCount(0); //set the row count 0
				            showinfo(); //calling function to refresh the table
				            search(stdID.getText()); //calling the search function after refresh the table to remain the search item with updated
		           
		            //close the Statement
		            connect.close();
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
			}
		});
		btnEdit.setToolTipText("");
		btnEdit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnEdit.setBackground(Color.LIGHT_GRAY);
		btnEdit.setBounds(387, 63, 83, 31);
		panel.add(btnEdit);
//****************************************************************************************************************************************
		/*
		 * This block is for delete the enrolled student infromations 
		 */
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
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
			       
			        //delete query of enrolled student by student ID or student Name
				        String q = "Delete from student_enroll_info where stdID =? or std_fullname = ?";  // query to remove the data
				        PreparedStatement pstat =  connect.prepareStatement(q); // prepared statement
				        pstat.setString(1, search.getText());
				        pstat.setString(2, search.getText());
				        
				        pstat.executeUpdate();  // delete the data in database
				        	
				        JOptionPane.showMessageDialog(null,"Delete Enrolled Student Successfully");
				        pstat.close();
				        
				        namecourse.setText("");
				        stdID.setText("");
				        stdfullname.setText("");
				        leveltext.setText("");
				        search.setText("");
				        DefaultTableModel model = (DefaultTableModel)EnrollStdInfo.getModel();
						model.setRowCount(0); // set the row count 0
						
						showinfo(); // to remove the row after delete data in jtable
			        //close the Statement
			        connect.close();
			    }catch (Exception exp){
			        System.out.println(exp);
			    }
			}
		});
		btnDelete.setToolTipText("");
		btnDelete.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnDelete.setBounds(501, 61, 104, 31);
		panel.add(btnDelete);
		
		
		
		frame.setVisible(true);
	}
	
	
//--------------------------------------------------------------------------------------------------------------------------------------
	
	public void showinfo() {
		
		/*
		  * This is for loading and fetch from the database to show student information in the jtable
		  */
		
		String url = "jdbc:mysql://localhost:3306/course_management_system";
	    String username = "root";
	    String password = "";
	    
	    
	    try{
	        //load the driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        //create the connection
	        Connection connect = DriverManager.getConnection(url,username,password);
	        
	        String ID = "";
	        String course_name = "";
	        String Student_ID = "";
	        String Student_fname  = "";
	        String level = "";
	        
	        
	        String q = "Select * from student_enroll_info"; //query to fetch all information of student enrolled from student_enroll_info table
	        PreparedStatement pstat =  connect.prepareStatement(q);

	        ResultSet rs = pstat.executeQuery();  
	        
	        
	        DefaultTableModel model1 = (DefaultTableModel)EnrollStdInfo.getModel();
	        
	        
	        while(rs.next()) {
	        	
	        	//decalared the and initilized the string variables
	        	ID = rs.getString("ID");
	        	course_name = rs.getString("course_name");
	        	Student_ID = rs.getString("stdID");
	        	Student_fname = rs.getString("std_fullname");
	        	level = rs.getString("level");
	        	
	        	//storing the variables data in the array
	        	String[] row = {ID, course_name, Student_ID,Student_fname,level};
	        	model1.addRow(row);  //adding array as a row in the jscrollpanel 

	        }
	        	
	        pstat.close(); //close the prepared Statement
	        
	        

	        //close the Statement
	        connect.close();
	    }catch (Exception exp){
	        System.out.println(exp);
	    }
		
	}
	
//----------------------------------------------------------------------------------------------------------------------------
	public void search(String str) {
		/*
		 * This method is for searching student enrolled information in the jtable
		 */
		
		DefaultTableModel model = (DefaultTableModel)EnrollStdInfo.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
		EnrollStdInfo.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(str));
		
	}
}
