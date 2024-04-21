package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;
import java.awt.event.ActionEvent;

import java.sql.*;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
public class AddModule extends JFrame{

	private JFrame frame;
	private JTextField NoofYear;
	private JTextField ModuleName;
	private JTextField semester;
	private JTextField Level;

	/**
	 * Launch the application.
	 */
	public static void addmodule(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddModule window = new AddModule();
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
	public AddModule() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AddModule.class.getResource("/img/RBS.png")));
		frame.setTitle("Course Management System");
		frame.setBounds(-7, -10, 728, 620);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 247, 226));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
//		===============================================================================
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCourseName.setBounds(31, 55, 97, 28);
		panel.add(lblCourseName);
		
//***************************************************************************************
		
		NoofYear = new JTextField();
		NoofYear.setColumns(10);
		NoofYear.setBounds(571, 56, 59, 31);
		panel.add(NoofYear);
		
		JComboBox coursename = new JComboBox();
		coursename.addItemListener(new ItemListener() {
			 @Override
			public void itemStateChanged(ItemEvent e) {
				
				String url1 = "jdbc:mysql://localhost:3306/course_management_system";
			    String username1 = "root";
			    String password1 = "";
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url1,username1,password1);
		            
		            //according the chnage the state of selected item of course name , change the number of years of that course
		            String q ="select no_of_years from course_info where course_name= ?";
		            PreparedStatement pstate = connect.prepareStatement(q);
		            pstate.setString(1, (String) coursename.getSelectedItem());
		            ResultSet rs = pstate.executeQuery();
		            
		            if(rs.next()) {
		            	NoofYear.setText(rs.getString("no_of_years"));
		            	
		            }
		            pstate.close();
		            connect.close();
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
			}
		});
		coursename.setBounds(135, 59, 231, 23);
		panel.add(coursename);
		
		String url = "jdbc:mysql://localhost:3306/course_management_system";
	    String username = "root";
	    String password = "";
	    
	    try{
            //load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create the connection
            Connection connect = DriverManager.getConnection(url,username,password);
            
            //this query is for dispay the course name in the combobox 
            String query= "select course_name from course_info ";
            Statement pstate = connect.createStatement();
            ResultSet rs = pstate.executeQuery(query);
            while(rs.next()) {
            	String namecourse = rs.getString("course_name");
            	coursename.addItem(namecourse);
            }
            connect.close();
        }catch (Exception exp){
            System.out.println(exp);
        }
		
//****************************************************************************************		
		JLabel lblNo_of_year = new JLabel("No. of years");
		lblNo_of_year.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNo_of_year.setBounds(478, 55, 97, 28);
		panel.add(lblNo_of_year);
	
		
		
		
//*****************************************************************************************		
		semester = new JTextField();
		semester.setColumns(10);
		semester.setBounds(109, 124, 59, 31);
		panel.add(semester);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblLevel.setBounds(456, 123, 49, 28);
		panel.add(lblLevel);
		
		Level = new JTextField();
		Level.setColumns(10);
		Level.setBounds(510, 123, 144, 31);
		panel.add(Level);
		
//		================================================================================================================
		
		JLabel lblSemester1 = new JLabel("Semester");
		lblSemester1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSemester1.setBounds(31, 123, 71, 28);
		panel.add(lblSemester1);
		
		JLabel lblModuleinfo = new JLabel("Module Information");
		lblModuleinfo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblModuleinfo.setBounds(32, 221, 150, 28);
		panel.add(lblModuleinfo);
		
		JLabel lblModuleName1 = new JLabel("Module Name");
		lblModuleName1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblModuleName1.setBounds(31, 332, 97, 28);
		panel.add(lblModuleName1);
		
		ModuleName = new JTextField();
		ModuleName.setColumns(10);
		ModuleName.setBounds(135, 332, 231, 31);
		panel.add(ModuleName);
		
		JCheckBox optional = new JCheckBox("Optional");
		optional.setSelected(false);
		optional.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		optional.setBounds(31, 277, 93, 21);
		panel.add(optional);
		
		JLabel lblNooFEmpty = new JLabel("Required No. of years");
		lblNooFEmpty.setVisible(false);
		lblNooFEmpty.setForeground(Color.RED);
		lblNooFEmpty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNooFEmpty.setBounds(488, 91, 188, 22);
		panel.add(lblNooFEmpty);
		
		JLabel lblSemesterEmpty = new JLabel("Required Semester");
		lblSemesterEmpty.setVisible(false);
		lblSemesterEmpty.setForeground(Color.RED);
		lblSemesterEmpty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSemesterEmpty.setBounds(178, 127, 171, 22);
		panel.add(lblSemesterEmpty);
		
		JLabel lblLevelEmpty = new JLabel("Required Level");
		lblLevelEmpty.setVisible(false);
		lblLevelEmpty.setForeground(Color.RED);
		lblLevelEmpty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLevelEmpty.setBounds(510, 158, 144, 22);
		panel.add(lblLevelEmpty);
//		===================================================================================
		JButton btnNewButton = new JButton("Add Course");
		btnNewButton.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * Checking the course name text field, no of year text field, semester text field and level text field
				 *  is empty or not for add course,
				 * if any of the fields are empty than display the message as error
				 */
				
				//first checking by all fields
				if( NoofYear.getText().isEmpty() && semester.getText().isEmpty() && Level.getText().isEmpty()) {
					
					lblNooFEmpty.setVisible(true);
					lblSemesterEmpty.setVisible(true);
					lblLevelEmpty.setVisible(true);
					
				}
				//checking two fields
				else if( semester.getText().isEmpty() && Level.getText().isEmpty()) {
					
					lblSemesterEmpty.setVisible(true);
					lblLevelEmpty.setVisible(true);
					
				}else if(NoofYear.getText().isEmpty() && Level.getText().isEmpty()){
					
					lblNooFEmpty.setVisible(true);
					lblLevelEmpty.setVisible(true);
					
				}else if(NoofYear.getText().isEmpty() && semester.getText().isEmpty()) {
					
					lblNooFEmpty.setVisible(true);
					lblSemesterEmpty.setVisible(true);
					
				}
				
				//checking by one fields
				else if(NoofYear.getText().isEmpty()) {
					lblNooFEmpty.setVisible(true);
					
				}else if(semester.getText().isEmpty()) {
					lblSemesterEmpty.setVisible(true);
					
				}else if(Level.getText().isEmpty()) {
					lblLevelEmpty.setVisible(true);
				}else {
					String url2 = "jdbc:mysql://localhost:3306/course_management_system";
				    String username2 = "root";
				    String password2 = "";
				    
				    try{
				    	String codemodule = codemodule();
			            //load the driver
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            //create the connection
			            Connection connect2 = DriverManager.getConnection(url2,username2,password2);
			            
			            //check whether the module is already register or not
			            String qry = "select module_code, module_name from course where module_code =? and module_name =?";
			            PreparedStatement pstate2 = connect2.prepareStatement(qry);
			            pstate2.setString(1, codemodule);
			            pstate2.setString(2,ModuleName.getText());
			            ResultSet rs = pstate2.executeQuery();
					
						if(rs.next()) {
							
							JOptionPane.showMessageDialog(null, "Module already register!");
							
						}else {
							
							//if module is not register before than allow to register
							String url = "jdbc:mysql://localhost:3306/course_management_system";
						    String username = "root";
						    String password = "";
						    
						    String ModuleCode = codemodule();
						    
						    try{
					            //load the driver
					            Class.forName("com.mysql.cj.jdbc.Driver");
					            //create the connection
					            Connection connect = DriverManager.getConnection(url,username,password);
					               
					           
					            //if the module is optional than insert as true in the optional field in database otherwise false
					            if(optional.isSelected()) {
					            	
					            	 String query = "INSERT INTO course (course_name, no_of_years, level, semester, module_code, module_name, optional) VALUES (?,?,?,?,?,?,?)";
							            
					            	 
							            PreparedStatement pstate = connect.prepareStatement(query);
							            pstate.setString(1, (String) coursename.getSelectedItem());
							            pstate.setString(2, NoofYear.getText());
							            pstate.setString(3, Level.getText());
							            pstate.setString(4, semester.getText());
							            pstate.setString(5, ModuleCode);
							            pstate.setString(6, ModuleName.getText());
							            pstate.setString(7, "true"); //manually set true
							            pstate.executeUpdate();
							            
							            JOptionPane.showMessageDialog(null, "Course add succefuly!");
							            frame.dispose();
					            }else {
					            	String query = "INSERT INTO course (course_name, no_of_years, level, semester, module_code, module_name, optional) VALUES (?,?,?,?,?,?,?)";
					            	 		
						            	PreparedStatement pstate = connect.prepareStatement(query);
							            pstate.setString(1, (String) coursename.getSelectedItem());
							            pstate.setString(2, NoofYear.getText());
							            pstate.setString(3, Level.getText());
							            pstate.setString(4, semester.getText());
							            pstate.setString(5, ModuleCode);
							            pstate.setString(6, ModuleName.getText());
							            pstate.setString(7, "false"); //manually false
							            pstate.executeUpdate();
							            
							            JOptionPane.showMessageDialog(null, "Course add succefuly!");
							            frame.dispose();
					            }
					            //close the Statement
					            connect.close();
					        }catch (Exception exp){
					            System.out.println(exp);
					        }
							
						}
					
				     //close the Statement
		            connect2.close();
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
				}
			}
		});
		btnNewButton.setBackground(new Color(0, 193, 193));
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnNewButton.setBounds(571, 500, 120, 28);
		panel.add(btnNewButton);
		
		JLabel lblmoduledetails = new JLabel("Module Details");
		lblmoduledetails.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblmoduledetails.setBounds(238, 3, 231, 31);
		panel.add(lblmoduledetails);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 197, 658, 14);
		panel.add(separator);
		
		JTextPane txtpnNoteModuleCode = new JTextPane();
		txtpnNoteModuleCode.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtpnNoteModuleCode.setText("Note:  Module code is auto-genetrated by the system.");
		txtpnNoteModuleCode.setBackground(new Color(204, 247, 226));
		txtpnNoteModuleCode.setBounds(31, 408, 438, 66);
		panel.add(txtpnNoteModuleCode);
//*******************************************************************************************************************
		
		
		frame.setVisible(true);
	}
// ============================================================================================================================
    //this method creates code for module
    public String codemodule() {
        String insertcode = null;
        try {
            insertcode = alphaNumeric(); // here the alphanumeric method is called to get a random code
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/course_management_system",
                    "root", "password");
            PreparedStatement st = connection.prepareStatement("Select module_code from course where module_code=?");
            st.setString(1, insertcode);
            ResultSet rs = st.executeQuery();
            //if there is already a module name with the code name then it 'code()' method calls itself as recursion
            if (rs.next()) {
                codemodule();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        //if code doesn't exist then it returns code when it is called
        return insertcode;
    }
    
//    =============================================================================================================================================
    
    //this method alphaNumeric() just creates a new code that has length of 4 and mixed with numbers and letters
    public String alphaNumeric() {
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String alphaNumeric = upperAlphabet + numbers;
        StringBuilder sb = new StringBuilder();
        // create an object of Random class
        Random random = new Random();
        // specify length of random string
        for (int i = 0; i < 4; i++) {
            // generate random index number
            int index = random.nextInt(alphaNumeric.length());
            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);
            // append the character to string builder
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }
}
