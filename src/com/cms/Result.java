package com.cms;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.Iterator;

import com.mysql.cj.jdbc.PreparedStatement;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


import java.math.RoundingMode;

public class Result extends JFrame{

	private JFrame frame;
	private JTable table;
	private JTextField searchfiled;
	private ArrayList<String> moduleCode1 = new ArrayList<>();
	private ArrayList<String> moduleCode2 = new ArrayList<>();
    private ArrayList<Integer> marksadd = new ArrayList<>();
    
	/**
	 * Launch the application.
	 */
	public static void report(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Result window = new Result();
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
	public Result() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Result.class.getResource("/img/RBS.png")));
		frame.setTitle("Course Management System");
		frame.setBounds(100, 100, 772, 656);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblhcklogo = new JLabel("");
		lblhcklogo.setIcon(new ImageIcon(Result.class.getResource("/img/hck.png")));
		lblhcklogo.setBounds(412, 10, 204, 97);
		panel.add(lblhcklogo);
		
		JLabel lblFullname = new JLabel("Student Name:");
		lblFullname.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblFullname.setBounds(29, 213, 97, 19);
		panel.add(lblFullname);
		
		JLabel showfullname = new JLabel("");
		showfullname.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showfullname.setBounds(128, 214, 248, 19);
		panel.add(showfullname);
		
		JLabel lblCourseName = new JLabel("Course: ");
		lblCourseName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCourseName.setBounds(463, 242, 58, 19);
		panel.add(lblCourseName);
		
		JLabel showCourseName = new JLabel("");
		showCourseName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showCourseName.setBounds(520, 242, 225, 19);
		panel.add(showCourseName);
		
		JLabel lblLevel = new JLabel("Level: ");
		lblLevel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblLevel.setBounds(29, 247, 48, 19);
		panel.add(lblLevel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 276, 684, 235);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Module Code", "Module Name", "marks", "Grade", "Remarks"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(114);
		table.getColumnModel().getColumn(1).setPreferredWidth(192);
		table.getColumnModel().getColumn(2).setPreferredWidth(78);
		table.getColumnModel().getColumn(3).setPreferredWidth(142);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBackground(new Color(255, 255, 255));
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTotal.setBounds(407, 530, 57, 19);
		panel.add(lblTotal);
		
		JLabel lblpercentage = new JLabel("Percentage:");
		lblpercentage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblpercentage.setBounds(409, 556, 82, 19);
		panel.add(lblpercentage);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGrade.setBounds(409, 582, 58, 19);
		panel.add(lblGrade);
		
		JLabel showTotal = new JLabel("");
		showTotal.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showTotal.setBounds(456, 530, 87, 19);
		panel.add(showTotal);
		
		JLabel showPercentage = new JLabel("");
		showPercentage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showPercentage.setBounds(492, 557, 87, 19);
		panel.add(showPercentage);
		
		JLabel showGrade = new JLabel("");
		showGrade.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showGrade.setBounds(462, 582, 255, 19);
		panel.add(showGrade);
		
		JLabel lblNewLabel_1 = new JLabel("Mark Sheet");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(270, 110, 168, 45);
		panel.add(lblNewLabel_1);
		
		JLabel lblwlvLogo = new JLabel("");
		lblwlvLogo.setIcon(new ImageIcon(Result.class.getResource("/img/wlv.png")));
		lblwlvLogo.setBounds(78, 20, 239, 88);
		panel.add(lblwlvLogo);
		
		JLabel showStdID = new JLabel("");
		showStdID.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showStdID.setBounds(535, 216, 105, 19);
		panel.add(showStdID);
		
		searchfiled = new JTextField();
		searchfiled.setBounds(132, 168, 174, 23);
		panel.add(searchfiled);
		searchfiled.setColumns(10);
//***********************************************************************************************************************
		JComboBox showlevel = new JComboBox();
		showlevel.addItemListener(new ItemListener() {
			 @Override
			public void itemStateChanged(ItemEvent e) {
				
				/*
				 * According to selected item change the details of student search by ID  is also changed
				 * display in the table
				 */
				
				String url1 = "jdbc:mysql://localhost:3306/course_management_system";
			    String username1 = "root";
			    String password1 = "";
			  
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect1 = DriverManager.getConnection(url1,username1,password1);
		            
		            String module_code = "";
		            String module_name = "";
		            String marks = "";
		            String grd = "";
		            String rem = "";
		            
		            String qry = " select course_name,Std_Fullname, module_code, module_name, level, marks,grade,remarks from report where level = ? and std_id = ?";
		            PreparedStatement pst = (PreparedStatement) connect1.prepareStatement(qry);
		            pst.setString(1, (String) showlevel.getSelectedItem());
		            pst.setString(2, showStdID.getText());
		            ResultSet rset = pst.executeQuery();
		            
		            DefaultTableModel model = (DefaultTableModel)table.getModel(); //default table object is created
		            
		            while(rset.next()) {
		            	module_code = rset.getString("module_code");
		            	module_name = rset.getString("module_name");
		            	marks = rset.getString("marks");
		            	grd = rset.getString("grade");
		            	rem = rset.getString("remarks");
		            	String[]row = {module_code,module_name,marks,grd,rem}; //storing data in array
		            	model.addRow(row); // adding the array as row in the table with the help created object of default table model

		            }
		            
		            
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
			    DefaultTableModel model1 = (DefaultTableModel)table.getModel();
				model1.setRowCount(0);// for refresh table
			}
		});
		showlevel.setBounds(78, 243, 76, 21);
		panel.add(showlevel);
		
		
//**********************************************************************************************************************		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				 
				 
				
				/*
				 * By searching the Student Result by Student ID, first I have searched the deatils of students and display marks
				 * according to module code, module name, grade and remarks in each modules in the table.
				 * 
				 * Also, check if marks is not added to the all modules than the total marks, percentage and final grade is not display.
				 * 
				 */
				showlevel.removeAllItems();
				DefaultTableModel model1 = (DefaultTableModel)table.getModel();
				model1.setRowCount(0); // set the row count 0
							
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		            
		            
		            //first fetch the student details and display in the jlabel 
		            String qy ="select stdID from student_enroll_info where stdID =?";
		            PreparedStatement st1 = (PreparedStatement) connect.prepareStatement(qy);
		            st1.setString(1, searchfiled.getText());
		            ResultSet rse = st1.executeQuery();
		            
		            if(rse.next()) {
		            	
				            	String qury = " select course_name,Std_Fullname,std_id, module_code, module_name, level, marks,grade,remarks from report where std_id = ?";
					            PreparedStatement st = (PreparedStatement) connect.prepareStatement(qury);
					            st.setString(1, searchfiled.getText());
					            ResultSet rs = st.executeQuery();
					            
					            
					            
					            if(rs.next()) {
					            	
					            	showfullname.setText(rs.getString("Std_Fullname"));
					            	showCourseName.setText(rs.getString("course_name"));
					            	showStdID.setText(rs.getString("std_id"));
					            	
					            	
					            	} 
			//###################################################################################################		            
					            
					            // show the level option in the combobox according to the enrolled for view the results according to level by admin
					            String qry7 = "select level from student_enroll_info where stdID = ?";
				            	PreparedStatement pst7 = (PreparedStatement) connect.prepareStatement(qry7);
				            	pst7.setString(1, searchfiled.getText());
				            	ResultSet rsm7 = pst7.executeQuery();
				            	
				            	while(rsm7.next()) {
				            		
				            		showlevel.addItem(rsm7.getString("level"));
				            	}	
				            	
				            	pst7.close();
					       
			//##########################################################################################################################################				
						    
				           /*
				            * This part is for display the marks details according to modules in the table
				            */
						    String url1 = "jdbc:mysql://localhost:3306/course_management_system";
						    String username1 = "root";
						    String password1 = "";
						  
						    try{
					            //load the driver
					            Class.forName("com.mysql.cj.jdbc.Driver");
					            //create the connection
					            Connection connect1 = DriverManager.getConnection(url1,username1,password1);
					            
					            //creating the empty String variables
					            String module_code = "";
					            String module_name = "";
					            String marks = "";
					            String grd = "";
					            String rem = "";
					            
					            String qry = " select course_name,Std_Fullname, module_code, module_name, level, marks,grade,remarks from report where std_id = ?";
					            PreparedStatement pst = (PreparedStatement) connect1.prepareStatement(qry);
					            pst.setString(1, searchfiled.getText());
					            ResultSet rset = pst.executeQuery();
					            
					            DefaultTableModel model = (DefaultTableModel)table.getModel(); //created default table model
					            
					            while(rset.next()) {
					            	module_code = rset.getString("module_code");
					            	module_name = rset.getString("module_name");
					            	marks = rset.getString("marks");
					            	grd = rset.getString("grade");
					            	rem = rset.getString("remarks");
					            	
					            	String[] row = {module_code,module_name,marks,grd,rem}; //add the fetch data in the array
					            	 model.addRow(row); // add the array as row in the table with the help of obje of default table model
					            }
					            
					            pst.close();
					        }catch (Exception exp){
					            System.out.println(exp);
					        }
						    
						    
						    
//##################################################################################################################################################
						    /*
						     * This block is for diplaying the total marks, calculated percenatge and final grade.
						     * 
						     * Also, Check Whether the marks is added in all subject or not, if marks added in all
						     * subject than it display all otherwise it cannot display total marks, calculate percentage and final grade.
						     * 
						     * for the check, I have used ArrayList and iterator tatics
						     */
						    
						    
						    // first counted the total number modules in the course according to level
						    String qry = "select module_code from course where course_name = ? and level =?";
				            PreparedStatement st6 = (PreparedStatement) connect.prepareStatement(qry);
				            st6.setString(1, showCourseName.getText());
				            st6.setString(2, (String) showlevel.getSelectedItem());
				            ResultSet rs6 = st6.executeQuery();
				            
				            while(rs6.next()) {
				            	
				            	moduleCode1.add(rs6.getString("module_code")); //added the module code in the arraylist variable
				            	
				            }
				            
				            //using iterator
				            java.util.Iterator<String> c = moduleCode1.iterator();
				            int count = 0; //initializing count
				            while ( c.hasNext()) {
				                String condition = (String) c.next(); //for next value
				                count++;    // count total number
				            }
				            
				            moduleCode1.clear(); // clear the module code to fetch totally new
						    
				            
//==============================================================================================================================================
				            
				            // this part count the totl number of module code in the report table according to level and course
				            String quy = "select module_code from report where course_name = ? and level =?";
				            PreparedStatement pstate = (PreparedStatement) connect.prepareStatement(quy);
				            pstate.setString(1, showCourseName.getText());
				            pstate.setString(2, (String) showlevel.getSelectedItem());
				            ResultSet rsm = pstate.executeQuery();
				            while(rsm.next()) {
				            	
				            	moduleCode2.add(rsm.getString("module_code")); //added the module code in the arraylist variable
				            }
				            
				            //using iterator
				            java.util.Iterator<String> c1 = moduleCode2.iterator();
				            int count1 = 0; //initializing count
				            while ( c1.hasNext()) {
				                String condition1 = (String) c1.next();
				                count1++;
				                
				            }
				            
				            moduleCode2.clear(); //clear 
				            pstate.close(); //close statement
				            
				            
				            //check the counted number is equal or not
				            if(count == count1 ) { //if equal than display the total marks, calculated percentage and final grade
					        	 
				            	String q = "select marks from report where std_id = ? and level =?";
				            	PreparedStatement psta = (PreparedStatement) connect.prepareStatement(q);
				            	
				            	psta.setString(1, searchfiled.getText());
				            	psta.setString(2, (String) showlevel.getSelectedItem());
				            	
				            	ResultSet rst = psta.executeQuery();
				            	
				            	while(rst.next()) {
				            		
				            		marksadd.add(rst.getInt("marks")); // marks is added in the arraylist varibale to calculated total marks
				            			
				            	}
				            	
				            	//using iterator
				            	java.util.Iterator<Integer> c2 = marksadd.iterator();
					            int count2 = 0; //initializing count
					            float totalmarks = 0;
					            while ( c2.hasNext()) {
					                Integer condition2 = (Integer) c2.next();
					                totalmarks = totalmarks + condition2; //to caculated the total marks
					                count2++;     // count the total number of module count to calculate the percenatge
					            }
				            	
					            showTotal.setText(String.valueOf(Math.round(totalmarks)));
				            	float total = count2 *100; 
				            	float percentage = (totalmarks)/(total) *100 ; //caculate the percentage
				            	showPercentage.setText(String.valueOf(Math.round(percentage))+"%");
				            	
				            	//checking the final grade according to calculated percentage
				            	 String Grade ="";
						            String remarks = "";
						            if (percentage >= 70) {
						                Grade = "First-Class Honours";
						                
						            } else if (percentage >= 60) {
						                Grade = "Upper Second-Class Honours";
						                
						            } else if (percentage >= 50) {
						                Grade = "Lower Second-Class Honours";
						                
						            } else if (percentage >= 40) {
						                Grade = "Third Class Honours";
						                
						            } else {
						                Grade = "Fail";
						                
						            } 
					        	
						            showGrade.setText(Grade);
					 		}else {
					 			
					 			JOptionPane.showMessageDialog(null, "!! Result is not ready !!");
					 		}
		            	
		            }else {
		            	
		            	JOptionPane.showMessageDialog(null, "Student is not enroll.");
		            }
		            
		            st1.close(); 
		            searchfiled.setText("");
			}catch (Exception exp){
	            System.out.println(exp);
	        }
			    
			}
		});
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnNewButton.setBounds(36, 169, 85, 23);
		panel.add(btnNewButton);
		
//**********************************************************************************************************************************	
		/*
		 * This section is for to give access to admin to published the result or not by searching with student ID.
		 * if the level wise all the modules marks is not added, than it shows the message i.e. result is not ready.
		 */
		JButton btnNewButton_1 = new JButton("Result published");
		btnNewButton_1.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				 
				 /*
				  * To give permission to admin to published the result, I have used level/yearly based, in a single course the 
				  * number of modules register is counted and in the report table the number of marks added to the moudules counted 
				  * without duplicated marks to that modules. Then, If the both number counted is equal then access to published the result
				  * else denied to give permission.
				  * Also check whether the result is published already or not according to level.
				  * for All this logic, I have used ArrayList and Iterator, to print the result as well as to count the numbers.
				  */
				
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		            
		            //This first part I have used to count the register modules in a particular course
		            
		            String qry = "select module_code from course where course_name = ? and level =?";
		            PreparedStatement st = (PreparedStatement) connect.prepareStatement(qry); //prepared Statement
		            st.setString(1, showCourseName.getText());
		            st.setString(2, (String) showlevel.getSelectedItem());
		            ResultSet rs = st.executeQuery();
		            
		            while(rs.next()) {
		            	
		            	moduleCode1.add(rs.getString("module_code")); //added the module code to arraylist varaibe 
		            	
		            }
		            
		            //using Iterator
		            java.util.Iterator<String> c = moduleCode1.iterator();
		            int count = 0; //initializing count
		            while ( c.hasNext()) {
		                String condition = (String) c.next(); //for next value
		                count++;    //count the number of modules register in a particular course
		            }
		            
		            moduleCode1.clear(); //clear the module code to fetch the totally 
		          //################################################################################################## 
		            
		            // Second part counted the number of modules in the report tables according to level 
		            String qury = "select module_code from report where course_name = ? and level =?";
		            PreparedStatement pstate = (PreparedStatement) connect.prepareStatement(qury);
		            pstate.setString(1, showCourseName.getText());
		            pstate.setString(2, (String) showlevel.getSelectedItem());
		            ResultSet rsm = pstate.executeQuery();
		            while(rsm.next()) {
		            	
		            	moduleCode2.add(rsm.getString("module_code")); //added the module code to arraylist varaibe 
		            }
		          //using Iterator
		            java.util.Iterator<String> c1 = moduleCode2.iterator();
		            int count1 = 0; //initializing count
		            while ( c1.hasNext()) {
		                String condition1 = (String) c1.next();
		                count1++;
		                
		            }
		            
		            moduleCode2.clear();
		            pstate.close();
		            st.close(); //close the Statement
		            
		            //Third part checking the counted number equal or not
			         if(count == count1 ) { //if counted number is equal this part execute
			        	 
			        	 
			        	 // This first part is for checking for result is already published or not, if publised it shows message else it allow to publised
			        	 String quryresult = "SELECT * FROM  stdreport  WHERE std_id =?  and level =?";
			        	 PreparedStatement pstatresult = (PreparedStatement) connect.prepareStatement(quryresult);
			        	 pstatresult.setString(1, showStdID.getText());
			        	 pstatresult.setString(2, (String) showlevel.getSelectedItem());
			        	 ResultSet rsResult  = pstatresult.executeQuery();
			        	 
			        	 int coun =0; 
			        	 if(rsResult.next()) {
			        		
			        		coun++; // this count is used for diplay the message one time only
			        		 
			        	 }else {
			        		 
			        		 // this part the result is published that means the result insert to another table for student view according to student ID 
			        		 /*first getting the email address from the enrolled student and insert in the stdreport and other details as well for fetching
			        		  * result for student view according to email they login.
			        		  */
			        		 
			        		 String quryemail = "select std_email from student_enroll_info where stdID =?";
			        		 PreparedStatement pstatemail = (PreparedStatement) connect.prepareStatement(quryemail);
			        		 pstatemail.setString(1, showStdID.getText());
			        		 ResultSet rsemail = pstatemail.executeQuery();
			        		 
			        		 if(rsemail.next()) {
			        			 
			        			 String email = rsemail.getString("std_email");
			        			 
			        			 String qurymarks = "SELECT module_code, module_name, marks, grade, remarks FROM report"
			        			 		+ " WHERE  level =? and std_id =?";
			        			 PreparedStatement pstatmarks = (PreparedStatement) connect.prepareStatement(qurymarks);
			        			 pstatmarks.setString(1, (String) showlevel.getSelectedItem());
			        			 pstatmarks.setString(2, showStdID.getText());
			 		            	ResultSet rsetmarks = pstatmarks.executeQuery();
			 		            	
			 		            		
		 		            	while(rsetmarks.next()) {
		 		            		
		 		            		String module_c = rsetmarks.getString("module_code");
		 		            		String module_n = rsetmarks.getString("module_name");
		 		            		int obtainmarks = rsetmarks.getInt("marks");
		 		            		String grd = rsetmarks.getString("grade");
		 		            		String rmarks = rsetmarks.getString("remarks");
		 		            		
		 		            		
		 		            		String quryinsert = "INSERT INTO stdreport (std_name, std_id, email, "
				        			 		+ "course_name, module_code, module_name, level, marks, grade, remarks)VALUES"
				        			 		+ "(?,?,?,?,?,?,?,?,?,?)";
				        			 PreparedStatement pstatinsert = (PreparedStatement) connect.prepareStatement(quryinsert);
				        			 pstatinsert.setString(1, showfullname.getText());
				        			 pstatinsert.setString(2, showStdID.getText());
				        			 pstatinsert.setString(3, email);
				        			 pstatinsert.setString(4, showCourseName.getText());
				        			 pstatinsert.setString(5, module_c);
				        			 pstatinsert.setString(6, module_n);
				        			 pstatinsert.setString(7, (String) showlevel.getSelectedItem());
				        			 pstatinsert.setInt(8, obtainmarks);
				        			 pstatinsert.setString(9, grd);
				        			 pstatinsert.setString(10, rmarks);
				        			 
				        			 pstatinsert.executeUpdate();
				        			 	 
		 		            		
		 		            	}
		 		            	
		 		            	
		 		            	/*
		 		            	 *  This query is for inserting the total marks, percentage, and final grde into grade table, 
		 		            	 *  to give access to enroll in the level or not
		 		            	 */
		 		            	String qurygrade = "INSERT INTO grade(std_id, std_name, email, grade) VALUES (?,?,?,?)";
		 		            	PreparedStatement pstatgrade = (PreparedStatement) connect.prepareStatement(qurygrade);
		 		            	pstatgrade.setString(1, showStdID.getText());
		 		            	pstatgrade.setString(2, showfullname.getText());
		 		            	pstatgrade.setString(3, email);
		 		            	pstatgrade.setString(4, showGrade.getText());
		 		            	pstatgrade.executeUpdate();
		 		            	
		 		            	pstatgrade.close();
			 		             
			        		 }
			        		 
			        		 JOptionPane.showMessageDialog(null, "!! Result is published!!");	
			        		 
			        	 }
			        	 
			        	 //This is for displaying mesaage one time only 'coun' variable is above
			        	 if(coun ==1) {
		        			 JOptionPane.showMessageDialog(null, "!! Result Already published!!");	
		        		 }
			        	 
			        	 
			        	 
			 		}else {
			 			
			 			JOptionPane.showMessageDialog(null, "!! Result is not ready !!");
			 		}
			        connect.close();
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
			}
		});
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnNewButton_1.setBounds(29, 569, 158, 28);
		panel.add(btnNewButton_1);
		
		JLabel lblstd_id = new JLabel("Student ID:");
		lblstd_id.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblstd_id.setBounds(460, 217, 76, 19);
		panel.add(lblstd_id);
		
		

		
		frame.setVisible(true);
		
	}
}
