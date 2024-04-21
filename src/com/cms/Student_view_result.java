package com.cms;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.PreparedStatement;

public class Student_view_result extends JFrame{

	private JFrame frame;
	private JTable marksheettable;
	 private ArrayList<Integer> marksadd = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void stdview(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Student_view_report window = new Student_view_report();
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
	public Student_view_result(String emailf2) {
		initialize(emailf2);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String emailf2) {
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
		lblCourseName.setBounds(427, 240, 58, 19);
		panel.add(lblCourseName);
		
		JLabel showCourseName = new JLabel("");
		showCourseName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showCourseName.setBounds(484, 240, 225, 19);
		panel.add(showCourseName);
		
		JLabel lblLevel = new JLabel("Level: ");
		lblLevel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblLevel.setBounds(29, 247, 48, 19);
		panel.add(lblLevel);
		
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTotal.setBounds(437, 530, 57, 19);
		panel.add(lblTotal);
		
		JLabel lblpercentage = new JLabel("Percentage:");
		lblpercentage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblpercentage.setBounds(439, 556, 82, 19);
		panel.add(lblpercentage);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGrade.setBounds(439, 582, 58, 19);
		panel.add(lblGrade);
		
		JLabel showTotal = new JLabel("");
		showTotal.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showTotal.setBounds(486, 530, 87, 19);
		panel.add(showTotal);
		
		JLabel showPercentage = new JLabel("");
		showPercentage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showPercentage.setBounds(522, 557, 87, 19);
		panel.add(showPercentage);
		
		JLabel showGrade = new JLabel("");
		showGrade.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showGrade.setBounds(492, 582, 217, 19);
		panel.add(showGrade);
		
		JLabel lblNewLabel_1 = new JLabel("Mark Sheet");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(270, 110, 168, 45);
		panel.add(lblNewLabel_1);
		
		JLabel lblwlvLogo = new JLabel("");
		lblwlvLogo.setIcon(new ImageIcon(Result.class.getResource("/img/wlv.png")));
		lblwlvLogo.setBounds(78, 20, 239, 88);
		panel.add(lblwlvLogo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 276, 684, 235);
		panel.add(scrollPane);
		
		marksheettable = new JTable();
		scrollPane.setViewportView(marksheettable);
		
		//for displaying the marks details in the table
		marksheettable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Module Code", "Module Name","Level", "marks", "Grade", "Remarks"
				}
			));
			marksheettable.getColumnModel().getColumn(0).setPreferredWidth(114);
			marksheettable.getColumnModel().getColumn(1).setPreferredWidth(192);
			marksheettable.getColumnModel().getColumn(2).setPreferredWidth(78);
			marksheettable.getColumnModel().getColumn(3).setPreferredWidth(142);
			marksheettable.getColumnModel().getColumn(4).setPreferredWidth(110);
			marksheettable.setFont(new Font("Tahoma", Font.PLAIN, 14));
			marksheettable.setBackground(new Color(255, 255, 255));
			
			
		
		JLabel lblStudentId = new JLabel("Student ID: ");
		lblStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblStudentId.setBounds(427, 217, 82, 19);
		panel.add(lblStudentId);
		
		JLabel showStdID = new JLabel("");
		showStdID.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showStdID.setBounds(509, 215, 114, 19);
		panel.add(showStdID);
		
		JLabel showlevel = new JLabel("");
		showlevel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showlevel.setBounds(72, 247, 74, 19);
		panel.add(showlevel);
//---------------------------------------------------------------------------------------------------------------------------------------------
		
		/*
		 * To calculate the marks I have used arraylist and iterator
		 */
		DefaultTableModel model1 = (DefaultTableModel)marksheettable.getModel();
		model1.setRowCount(0); // set the row count 0
					
		String url = "jdbc:mysql://localhost:3306/course_management_system";
	    String username = "root";
	    String password = "";
	    
	    
	    try{
            //load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create the connection
            Connection connect = DriverManager.getConnection(url,username,password);
            
            /*First display the Students details in the jlabel by email and display the marks in the table with 
             * module code, module name, level, marks, garde, and remarks.
             * if the student is not enroll in any level they cannot view even marksheet format 
             * In the down side calcuted total marks, percenatge and final is also display 
             */
            
            //first query is for fetch the student information according to login email
            String qury = "select std_fullname,stdID,course_name,level from student_enroll_info where std_email =? ";
            PreparedStatement pstat =  (PreparedStatement) connect.prepareStatement(qury);
            pstat.setString(1, emailf2);
            ResultSet rs = pstat.executeQuery();
            
            if(rs.next()) {
            	
            	showfullname.setText(rs.getString("std_fullname"));
            	showStdID.setText(rs.getString("stdID"));
            	showlevel.setText(rs.getString("level"));
            	String lev = rs.getString("level");
            	
            	switch(lev) {
            	case"4":
            		showlevel.setText("5");
            		break;
            	case"5":
            		showlevel.setText("6");
            		break;
            	case"6":
            		showlevel.setText("Graduated");
            	}
            	showCourseName.setText(rs.getString("course_name"));
            	
            	
            	String qury1 = "SELECT module_code ,module_name, level, marks," //this query for display marks, module code, module name, level, and grade, remarks in the table
            			+ " grade, remarks FROM stdreport WHERE email =?";
                PreparedStatement pstat1 =  (PreparedStatement) connect.prepareStatement(qury1);
                pstat1.setString(1, emailf2);                        
                ResultSet rs1 = pstat1.executeQuery();
                
                DefaultTableModel model = (DefaultTableModel)marksheettable.getModel(); //object create of default table model
                while(rs1.next()) {
                	
                	String modec = rs1.getString("module_code");
                	String moduname = rs1.getString("module_name");
                	String stdlevel = rs1.getString("level");
                	String modmarks = rs1.getString("marks");
                	String modgrd  = rs1.getString("grade");
                	String modremarks =  rs1.getString("remarks");
                	String[] row = {modec,moduname,stdlevel,modmarks,modgrd,modremarks}; // added the fetch data in the array
                	  model.addRow(row); //add array in the default table model as a row
                	  
                	  marksadd.add(rs1.getInt("marks")); // also added the marks in the arraylist variable to calculate total marks and percentage
                }
            	
                frame.setVisible(true);
            	
            }else {
            	
            	//if the student is not enroll cannot view the marksheet format and display message
            	JOptionPane.showMessageDialog(null, "!! You are not enrolled yet!!");
            	frame.setVisible(false);
            }
            
            //using iterator
            java.util.Iterator<Integer> c2 = marksadd.iterator();
            int count2 = 0; //initializing count
            float totalmarks = 0;
            while ( c2.hasNext()) {
                Integer condition2 = (Integer) c2.next();
                totalmarks = totalmarks + condition2; //calculate total marks
                count2++;    //count the total number of marks to calculate the percentage
            }
        	
            
            showTotal.setText(String.valueOf(Math.round(totalmarks)));
        	float total = count2 *100;
        	float percentage = (totalmarks)/(total) *100 ; //calculate the total percentage
        	showPercentage.setText(String.valueOf(Math.round(percentage))+"%");
        	
        	 String Grade ="";
	            //show the final grade according to caculated percentage
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
		
            
		
	    }catch (Exception exp){
            System.out.println(exp);
        }
	    
	}
	
	
}


