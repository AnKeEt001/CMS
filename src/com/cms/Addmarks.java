package com.cms;

import java.awt.EventQueue;



import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

import com.Customized_Exception_Handling.MarksException;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
public class Addmarks extends JFrame {

	private JFrame frame;
	private JTextField search;
	private JTextField marks;
	private JButton btnAddmarks;
    private int modmarks;
    
    protected ArrayList<String> Count = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void addmarks(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Addmarks window = new Addmarks();
//					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Addmarks(String emailf) {
		initialize(emailf);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String emailf) {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EditTutor.class.getResource("/img/RBS.png")));
		frame.setTitle("Course Management System");
		frame.setBounds(100, 100, 821, 502);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(196, 249, 223));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTutorDetails = new JLabel("Marks Sheet");
		lblTutorDetails.setFont(new Font("Consolas", Font.BOLD, 30));
		lblTutorDetails.setBounds(306, 5, 198, 36);
		panel.add(lblTutorDetails);
		
		search = new JTextField();
		search.setToolTipText("Search by Stduent ID or Name");
		search.setBounds(124, 64, 232, 25);
		panel.add(search);
		search.setColumns(10);
		
		JLabel lblFullName = new JLabel("Student Name:");
		lblFullName.setToolTipText("");
		lblFullName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblFullName.setBounds(22, 123, 109, 19);
		panel.add(lblFullName);
		
		JLabel showfullname = new JLabel("");
		showfullname.setToolTipText("");
		showfullname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		showfullname.setBounds(134, 123, 232, 19);
		panel.add(showfullname);
		
		JLabel lblStudentID = new JLabel("Student ID:");
		lblStudentID.setToolTipText("");
		lblStudentID.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblStudentID.setBounds(22, 154, 85, 19);
		panel.add(lblStudentID);
		
		JLabel showstdID = new JLabel("");
		showstdID.setToolTipText("");
		showstdID.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		showstdID.setBounds(113, 154, 129, 19);
		panel.add(showstdID);
		
		JLabel lblCourseName = new JLabel("Course Name:");
		lblCourseName.setToolTipText("");
		lblCourseName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCourseName.setBounds(433, 119, 99, 19);
		panel.add(lblCourseName);
		
		JLabel showCourseName = new JLabel("");
		showCourseName.setToolTipText("");
		showCourseName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		showCourseName.setBounds(533, 120, 232, 19);
		panel.add(showCourseName);
		
//*********************************************************************************************************************		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setToolTipText("");
		lblLevel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblLevel.setBounds(434, 154, 47, 19);
		panel.add(lblLevel);

		String[] optlevel = {"4","5","6"};
		JComboBox level = new JComboBox(optlevel);
		level.setFont(new Font("Tahoma", Font.BOLD, 14));
		level.setBounds(480, 153, 63, 21);
		panel.add(level);
		
//***********************************************************************************************************************
		
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setToolTipText("");
		lblSemester.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSemester.setBounds(22, 194, 77, 19);
		panel.add(lblSemester);
		
		String[] optsem = {"First","Second","Third","Fourth","Fifth","Sixth"};
		JComboBox sem = new JComboBox(optsem);
		sem.setFont(new Font("Tahoma", Font.BOLD, 14));
		sem.setBounds(102, 193, 85, 21);
		panel.add(sem);

//***********************************************************************************************************************
		JLabel lblModuleName = new JLabel("Module Name");
		lblModuleName.setToolTipText("");
		lblModuleName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblModuleName.setBounds(434, 205, 109, 19);
		panel.add(lblModuleName);
		
		JLabel lblModuleCode = new JLabel("Module Code:");
		lblModuleCode.setToolTipText("");
		lblModuleCode.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblModuleCode.setBounds(22, 241, 99, 19);
		panel.add(lblModuleCode);
		
		JLabel showModuleCode = new JLabel("");
		showModuleCode.setToolTipText("");
		showModuleCode.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		showModuleCode.setBounds(118, 241, 118, 19);
		panel.add(showModuleCode);
		
		

//***********************************************************************************************************************
		JComboBox module_name = new JComboBox();
		module_name.addItemListener(new ItemListener() {
			 @Override
			public void itemStateChanged(ItemEvent e) {
				
				
			 	/*THis part,
				 * According to the change of selected items of combobox of module name 
				 * display the module code of that module name and marks in the jlabel
				 */
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		            
		            String qry = "select module_code from module_assigned where module_name = ? and course_name =?";
		            
		            PreparedStatement pstate = connect.prepareStatement(qry);
		            pstate.setString(1, (String) module_name.getSelectedItem());
		            pstate.setString(2, showCourseName.getText());
		            ResultSet rs = pstate.executeQuery();
		            
		            if(rs.next()) {
		            	
		            	showModuleCode.setText(rs.getString("module_code"));
		            	
		            	String qury = "select marks from report where std_id =? and module_code =?";
			            PreparedStatement pstate5 = connect.prepareStatement(qury);
			            pstate5.setString(1, search.getText());
			            pstate5.setString(2, showModuleCode.getText());
			            ResultSet rsm = pstate5.executeQuery();
			            if(rsm.next()) {
			            	marks.setText(rsm.getString("marks"));
			            }
			            pstate5.close();
		            }
		            pstate.close();
		            
		            
		            connect.close();
		            
		           
				    
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
			       
			}
		});
		module_name.setBounds(535, 204, 191, 21);
		panel.add(module_name);
	//------------------------------------------------------------------------------------------------	
		
		
		String url = "jdbc:mysql://localhost:3306/course_management_system";
	    String username = "root";
	    String password = "";
	    
	    
	    try{
            //load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create the connection
            Connection connect = DriverManager.getConnection(url,username,password);
            
            String query = "select std_fullname,stdID, course_name from student_enroll_info where stdID =?";
            PreparedStatement pstate1 = connect.prepareStatement(query);
            pstate1.setString(1, search.getText());
            ResultSet rs = pstate1.executeQuery();
            
            if(rs.next()) {
            	
            	String stdname = rs.getString("std_fullname");
            	String stdID = rs.getString("stdID");
            	String coursename = rs.getString("course_name");
            	
            	showfullname.setText(stdname);
            	showstdID.setText(stdID);
            	showCourseName.setText(coursename);
            	
            	
            	
            	 	String qury = "SELECT  module_code  FROM optional WHERE std_id =? and module_code =?";
		            
		            PreparedStatement pstate4 = connect.prepareStatement(qury);
		            
		            pstate4.setString(1, showstdID.getText());
		            pstate4.setString(2, showModuleCode.getText());
		            
		            ResultSet rsm = pstate4.executeQuery();
		            
		            if(rsm.next()) {
		            	JOptionPane.showMessageDialog(null, "!! You have choose optional subject !!");
		            }
//            	String query2 = "select tutorID from tutor where email =?";
//	            PreparedStatement pstate2 = connect.prepareStatement(query2);
//	            pstate2.setString(1, emailf);
//	            ResultSet rs2 = pstate2.executeQuery();
//	            String tutorID;
//	            if(rs2.next()) {
//	            	tutorID = rs2.getString("tutorID");
//	            	
//	            	String query3 = "select module_name  from module_assigned where course_name =? and TutorID =?";
//		            PreparedStatement pstate3 = connect.prepareStatement(query3);
//		            pstate3.setString(1, coursename);
//		            pstate3.setString(2, tutorID);
//		            ResultSet rs3 = pstate3.executeQuery();
//		            while(rs3.next()) {
//		            	module_name.addItem(rs3.getString("module_name")); 
//		            }
//	            	
//		            pstate3.close();	
//	            }
	            
//	            pstate2.close();
            	
            	
            }else {
            	
            	
            }
            pstate1.close();
            search.setText("");
            //close the Statement
            connect.close();
	    
        }catch (Exception exp){
            System.out.println(exp);
        }
			
		
//***********************************************************************************************************************		
		JLabel lblMarks = new JLabel("Marks");
		lblMarks.setToolTipText("");
		lblMarks.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblMarks.setBounds(22, 276, 69, 19);
		panel.add(lblMarks);
		
		marks = new JTextField();
		marks.setToolTipText("");
		marks.setColumns(10);
		marks.setBounds(77, 273, 97, 25);
		panel.add(marks);
		
//************************************************************************************************************************************	
		/*
		 * This section is for adding the marks, first it check the field is empty or not, if empty it dispaly the message as error 
		 * else if run further and check by cutomized handling for adding the marks whether the marks is greater than 0 or not.
		 */
		JLabel lblMarksEmpty = new JLabel("Please, enter the marks");
		lblMarksEmpty.setVisible(false);
		lblMarksEmpty.setForeground(new Color(255, 0, 0));
		lblMarksEmpty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMarksEmpty.setBounds(62, 302, 207, 25);
		panel.add(lblMarksEmpty);
		
		btnAddmarks = new JButton("Add Marks");
		btnAddmarks.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				if(marks.getText().isEmpty()) {
					lblMarksEmpty.setVisible(true);
				}else {
					int mod_marks = Integer.parseInt(marks.getText()); //converting the string value to integer
					//Surrounded with try catch block for customized Exception handling
					try {
						validateMarks(mod_marks); // calling the method for checking whether marks is grater than 0 or not
						
						String url = "jdbc:mysql://localhost:3306/course_management_system";
					    String username = "root";
					    String password = "";
					    
					    
					    try{
				            //load the driver
				            Class.forName("com.mysql.cj.jdbc.Driver");
				            //create the connection
				            Connection connect = DriverManager.getConnection(url,username,password);
				            
				            //According to marks the grade and remarks is insert into report table
				            String Grade ="";
				            String remarks = "";
				            if (mod_marks >= 70) {
				                Grade = "First-Class Honours";
				                remarks ="Excellent";
				            } else if (mod_marks >= 60) {
				                Grade = "Upper Second-Class Honours";
				                remarks = "Very Good";
				            } else if (mod_marks >= 50) {
				                Grade = "Lower Second-Class Honours";
				                remarks = "Good";
				            } else if (mod_marks >= 40) {
				                Grade = "Third Class Honours";
				                remarks = "Benchmark";
				            } else {
				                Grade = "Fail";
				                remarks = "Under Benchmark";
				            }
				            
				            /*checks whether the student is taken optional module or not, if true {the marks of optional is insered},
				             * if not the mandotary module marks is insert in the database
				             */
				            String qury = "SELECT  module_code  FROM optional WHERE std_id =? and module_code =?";
				            
				            PreparedStatement pstate4 = connect.prepareStatement(qury);
				            
				            pstate4.setString(1, showstdID.getText());
				            pstate4.setString(2, showModuleCode.getText());
				            
				            ResultSet rs = pstate4.executeQuery();
				            
				            if(rs.next()) {
				            	
				            	//this part insert the optional module marks and modules informations
				            	JOptionPane.showMessageDialog(null, "OPtinal subject marks is adding.");
				            	String qry = "INSERT INTO report (course_name,std_id,Std_Fullname,module_code,module_name,level,semester,"
					            		+ "marks,grade,remarks) VALUES (?,?,?,?,?,?,?,?,?,?) "; 
					            
					            PreparedStatement pstate = connect.prepareStatement(qry);
					            
					            pstate.setString(1, showCourseName.getText());
					            pstate.setString(2, showstdID.getText());
					            pstate.setString(3, showfullname.getText());
					            pstate.setString(4, showModuleCode.getText());
					            pstate.setString(5, (String) module_name.getSelectedItem());
					            pstate.setString(6, (String) level.getSelectedItem());
					            pstate.setString(7, (String) sem.getSelectedItem());
					            pstate.setInt(8, mod_marks);
					            pstate.setString(9, Grade);
					            pstate.setString(10, remarks);
					            pstate.executeUpdate();
					            
					            connect.close();
							    
					            JOptionPane.showMessageDialog(null, "Marks is added Successfully");
					            marks.setText("");
					            showModuleCode.setText("");
					            showCourseName.setText("");
					            showstdID.setText("");
					            showfullname.setText("");
					            module_name.removeAllItems();
				            	
				            }else {
				            	
				            	//this part insert the mandatory module marks and modules informationls
						            	String qry = "INSERT INTO report (course_name,std_id,Std_Fullname,module_code,module_name,level,semester,"
							            		+ "marks,grade,remarks) VALUES (?,?,?,?,?,?,?,?,?,?) "; 
							            
							            PreparedStatement pstate = connect.prepareStatement(qry);
							            
							            pstate.setString(1, showCourseName.getText());
							            pstate.setString(2, showstdID.getText());
							            pstate.setString(3, showfullname.getText());
							            pstate.setString(4, showModuleCode.getText());
							            pstate.setString(5, (String) module_name.getSelectedItem());
							            pstate.setString(6, (String) level.getSelectedItem());
							            pstate.setString(7, (String) sem.getSelectedItem());
							            pstate.setInt(8, mod_marks);
							            pstate.setString(9, Grade);
							            pstate.setString(10, remarks);
							            pstate.executeUpdate();
							            
							            connect.close();
									    
							            JOptionPane.showMessageDialog(null, "Marks is added Successfully");
							            marks.setText("");
							            showModuleCode.setText("");
							            showCourseName.setText("");
							            showstdID.setText("");
							            showfullname.setText("");
							            module_name.removeAllItems();
						            
						            
						            String check = showModuleCode.getText();	 
				            }
				                
				        }catch (Exception exp){
				            System.out.println(exp);
				        }
						
						
					}catch(Exception message){
						JOptionPane.showMessageDialog(null, "Marks Should be greater than 0.");
						
					}
					
				}
			}
		});
		btnAddmarks.setBackground(new Color(0, 196, 196));
		btnAddmarks.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnAddmarks.setBounds(26, 361, 138, 36);
		panel.add(btnAddmarks);
//****************************************************************************************************************	
		/*
		 * This section is for edit the marks by student ID and Module Code
		 */
		JButton btnEditMarks = new JButton("Edit Marks");
		btnEditMarks.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				 //first check the marks textfield is empty or not
				if(marks.getText().isEmpty()) {
					lblMarksEmpty.setVisible(true);
				}else {
					int mod_marks = Integer.parseInt(marks.getText());
					//Surrounded with try catch block for customized Exception handling
					try {
						validateMarks(mod_marks); // calling the method for checking whether marks is grater than 0 or not
						
						String url = "jdbc:mysql://localhost:3306/course_management_system";
					    String username = "root";
					    String password = "";
					    
					    
					    try{
				            //load the driver
				            Class.forName("com.mysql.cj.jdbc.Driver");
				            //create the connection
				            Connection connect = DriverManager.getConnection(url,username,password);
				            
				            String Grade ="";
				            String remarks = "";
				            if (mod_marks >= 70) {
				                Grade = "First-Class Honours";
				                remarks ="Excellent";
				            } else if (mod_marks >= 60) {
				                Grade = "Upper Second-Class Honours";
				                remarks = "Very Good";
				            } else if (mod_marks >= 50) {
				                Grade = "Lower Second-Class Honours";
				                remarks = "Good";
				            } else if (mod_marks >= 40) {
				                Grade = "Third Class Honours";
				                remarks = "Benchmark";
				            } else {
				                Grade = "Fail";
				                remarks = "Under Benchmark";
				            }
				            
				            //marks update query
				            String qry = "UPDATE report SET module_code =?,module_name =?,level=?,semester =?,"
				            		+ "marks =?,grade=?,remarks=? WHERE  std_id =? and module_code =?"; 
				            
				            PreparedStatement pstate = connect.prepareStatement(qry);
				            
				            pstate.setString(1, showModuleCode.getText());
				            pstate.setString(2, (String) module_name.getSelectedItem());
				            pstate.setString(3, (String) level.getSelectedItem());
				            pstate.setString(4, (String) sem.getSelectedItem());
				            pstate.setInt(5, mod_marks);
				            pstate.setString(6, Grade);
				            pstate.setString(7, remarks);
				            pstate.setString(8, showstdID.getText());
				            pstate.setString(9, showModuleCode.getText());
				            pstate.executeUpdate();
				            
				            connect.close();
						    
				            JOptionPane.showMessageDialog(null, "Marks is Updated Successfully");
				            
				            marks.setText("");
				            showModuleCode.setText("");
				            showCourseName.setText("");
				            showstdID.setText("");
				            showfullname.setText("");
				            module_name.removeAllItems();
				        }catch (Exception exp){
				            System.out.println(exp);
				        }
						
						
					}catch(Exception message){
						JOptionPane.showMessageDialog(null, "Marks Should be greater than 0.");
						
					}
					
				}
				
			}
		});
		btnEditMarks.setBackground(SystemColor.info);
		btnEditMarks.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnEditMarks.setBounds(335, 361, 138, 36);
		panel.add(btnEditMarks);
//****************************************************************************************************************
		
		JButton btnDeleteMarks = new JButton("Delete Marks");
		btnDeleteMarks.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * This part is for deleting the marks according to Stduent ID and selected module name 
				 */
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		            
		            String qry = "DELETE FROM report WHERE  std_id =? and module_code =?"; 
		            
		            PreparedStatement pstate = connect.prepareStatement(qry);
		            
		            pstate.setString(1, showstdID.getText());
		            pstate.setString(2, showModuleCode.getText());
		            pstate.executeUpdate();
		            
		            connect.close();
				    
		            JOptionPane.showMessageDialog(null, "Marks Deleted Successfully");
		            
		            marks.setText("");
		            showModuleCode.setText("");
		            showCourseName.setText("");
		            showstdID.setText("");
		            showfullname.setText("");
		            module_name.removeAllItems();
		            
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
				
			}
		});
		btnDeleteMarks.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnDeleteMarks.setBackground(new Color(255, 151, 151));
		btnDeleteMarks.setBounds(623, 360, 138, 36);
		panel.add(btnDeleteMarks);

//*************************************************************************************************************
		/*
		 * This section is for search the student to add the marks
		 */
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				module_name.removeAllItems();
				
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		            
		            /* According to student enroll and tutor assigned module of the course the module name is diplay
		             * 
		             */
		            
		            //first query is for display the student information 
		            String query = "select std_fullname,stdID, course_name from student_enroll_info where stdID =?";
		            PreparedStatement pstate1 = connect.prepareStatement(query);
		            pstate1.setString(1, search.getText());
		            ResultSet rs = pstate1.executeQuery();
		            
		            if(rs.next()) {
		            	
		            	String stdname = rs.getString("std_fullname");
		            	String stdID = rs.getString("stdID");
		            	String coursename = rs.getString("course_name");
		            	
		            	showfullname.setText(stdname);
		            	showstdID.setText(stdID);
		            	showCourseName.setText(coursename);
		            	
		            	String query2 = "select tutorID from tutor where email =?";
			            PreparedStatement pstate2 = connect.prepareStatement(query2);
			            pstate2.setString(1, emailf);
			            ResultSet rs2 = pstate2.executeQuery();
			            String tutorID;
			            if(rs2.next()) {
			            	tutorID = rs2.getString("tutorID");
			            	
			            	//according to tutor id check the module assigned of the student emroll course and show only those in the combobox
			            	String query3 = "select module_name  from module_assigned where course_name =? and TutorID =?";
				            PreparedStatement pstate3 = connect.prepareStatement(query3);
				            pstate3.setString(1, coursename);
				            pstate3.setString(2, tutorID);
				            ResultSet rs3 = pstate3.executeQuery();
				            while(rs3.next()) {
				            	
				            	module_name.addItem(rs3.getString("module_name")); //adding combobox for selecting module name and module code
				            }
			            	
				            pstate3.close();	
			            }
			            pstate2.close();
		            	
		            	
		            }else {
		            	
		            	JOptionPane.showMessageDialog(null, "!! Sorry Student is not enrolled in any course!!");
		            }
		            pstate1.close();
		            search.setText("");
		            //close the Statement
		            connect.close();
			    
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
			}
		});
		btnSearch.setBackground(SystemColor.inactiveCaption);
		btnSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnSearch.setBounds(22, 64, 85, 25);
		panel.add(btnSearch);
		
		
		
		
		frame.setVisible(true);
		
	}
	
//---------------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is for customized Exception handeling for marks should be grater or equal to 0, not less than 0.
	 */
	public static void validateMarks(int modmarks)throws MarksException {
		
		if(modmarks <0) {
			throw new MarksException("");
			
		}else {
			
		}
		
	}
}
