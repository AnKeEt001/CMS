package com.cms;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.cj.api.jdbc.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.RowFilter;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Student extends JFrame {

	private JFrame frame;
	private JTextField fullname;
	private JTextField email;
	private JTextField mblno;
	private JTable coursedetails;
	private JTable tutordetails;
	private JTextField searchtutor;
	private JTable enrolled_course;
	private JTable table;
	private ArrayList<String> totamodule = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void student(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Student window = new Student();
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
	public Student(String emailf2) {
		initialize(emailf2);
		showinfo();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String emailf2) {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Admin.class.getResource("/img/RBS.png")));
		frame.setBounds(100, 100, 1052, 721);
		frame.setTitle("Course Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(99, 153, 163));
		menu.setBounds(0, 0, 227, 684);
		frame.getContentPane().add(menu);
		menu.setLayout(null);
		
		JLabel admintext = new JLabel("Student");
		admintext.setForeground(new Color(255, 255, 255));
		admintext.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		admintext.setBounds(70, 10, 91, 32);
		menu.add(admintext);
		
		
		
		JLabel courseimg = new JLabel("");
		courseimg.setIcon(new ImageIcon(Admin.class.getResource("/img/course.png")));
		courseimg.setBounds(39, 119, 137, 108);
		menu.add(courseimg);
		
		JLabel cmstext = new JLabel("Course Management System");
		cmstext.setForeground(Color.WHITE);
		cmstext.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		cmstext.setBounds(10, 236, 217, 32);
		menu.add(cmstext);
		
		
		JPanel Content = new JPanel();
		Content.setBackground(new Color(223, 247, 228));
		
		Content.setBounds(225, 0, 813, 684);
		frame.getContentPane().add(Content);
		Content.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 813, 684);
		Content.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
//		=====================================================================================================================================
//		Dashboard Panel
		JPanel dashboardpanel = new JPanel();
		dashboardpanel.setBackground(new Color(207, 248, 222));
		layeredPane.add(dashboardpanel, "name_233468739105100");
		dashboardpanel.setLayout(null);
		
		JLabel CourseName = new JLabel("");
		CourseName.setHorizontalAlignment(SwingConstants.CENTER);
		CourseName.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		CourseName.setBounds(64, 267, 252, 55);
		dashboardpanel.add(CourseName);
		
		JLabel enrollCourse = new JLabel("Enrolled Course");
		enrollCourse.setHorizontalAlignment(SwingConstants.CENTER);
		enrollCourse.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		enrollCourse.setBounds(61, 204, 262, 54);
		dashboardpanel.add(enrollCourse);
		
		JLabel lblNewLabel = new JLabel("Dashboard");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblNewLabel.setBounds(316, 0, 185, 55);
		dashboardpanel.add(lblNewLabel);
		
		enrolled_course = new JTable();
		enrolled_course.setBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.control, null, null));
		enrolled_course.setBounds(55, 202, 268, 131);
		dashboardpanel.add(enrolled_course);
		
		JLabel TotalModule = new JLabel("Total Modules");
		TotalModule.setHorizontalAlignment(SwingConstants.CENTER);
		TotalModule.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		TotalModule.setBounds(465, 200, 268, 55);
		dashboardpanel.add(TotalModule);
		
		JLabel totalModule = new JLabel("");
		totalModule.setHorizontalAlignment(SwingConstants.CENTER);
		totalModule.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		totalModule.setBounds(563, 252, 73, 55);
		dashboardpanel.add(totalModule);
		
		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBounds(465, 201, 268, 131);
		dashboardpanel.add(table);
		
		
		//*********************************************************************************************
		
		/*
		 * This section is for display the enrolled course name and the total number of modules in that enrolled course
		 * in the dashboard 
		 * for this, arraylist and iterator is used to count total number of modules of enrolled course
		 */
		String url9 = "jdbc:mysql://localhost:3306/course_management_system";
	    String username9 = "root";
	    String password9 = "";
	    
	    try{
	    
		        //load the driver
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        //create the connection
		        Connection connect1 = DriverManager.getConnection(url9,username9,password9);
		        
		        //query to fetch the enrolled course by the student 
		        String qry = "select course_name from student_enroll_info where std_email= ?";
		        
		        PreparedStatement pstate = connect1.prepareStatement(qry);
		        pstate.setString(1, emailf2);
		        
		        ResultSet rs1 = pstate.executeQuery();
		        if(rs1.next()) {
		        	CourseName.setText(rs1.getString("course_name")); //show the enrolled course name
		        	
		        	//query to count the total modules 
		        	String qry1 = "select module_code from course where course_name =?";
		        	PreparedStatement pstate1 = connect1.prepareStatement(qry1);
			        pstate1.setString(1, CourseName.getText());
			        ResultSet rs2 = pstate1.executeQuery();
			        
			        while(rs2.next()) {
			        	
			        	totamodule.add(rs2.getString("module_code")); //adding the modules code in the arraylist variable 
			        }
			           
		        }
		        
		        //using iterator to count total number of modules
		        java.util.Iterator<String> c = totamodule.iterator();
	            int count = 0; //initializing count
	            while ( c.hasNext()) {
	                String condition = (String) c.next(); // for next value
	                count++;    //count the number
	            }
	            
	            totamodule.clear(); //clear to fetch totally new data
	            totalModule.setText(String.valueOf(count)); //show total number of modules of the enrolled  course 
		        
		        
		        pstate.close();
		        connect1.close();
	    }catch (Exception exp){
	        System.out.println(exp);
	    }	
		
		
		
//		=====================================================================================================================================
//		Course Panel
		
		JPanel coursepanel = new JPanel();
		coursepanel.setBackground(new Color(207, 248, 222));
		layeredPane.add(coursepanel, "name_233628479463100");
		coursepanel.setLayout(null);
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setBounds(308, 0, 145, 40);
		lblCourses.setFont(new Font("Tahoma", Font.BOLD, 33));
		coursepanel.add(lblCourses);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.control, null, null));
		scrollPane.setBounds(54, 167, 706, 312);
		coursepanel.add(scrollPane);
		
		//for displaying course details
		coursedetails = new JTable();
		coursedetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Module Code", "Module Name", "Level", "Semester", "Optional"
			}
		));
		coursedetails.getColumnModel().getColumn(0).setPreferredWidth(49);
		coursedetails.getColumnModel().getColumn(1).setPreferredWidth(79);
		coursedetails.getColumnModel().getColumn(2).setPreferredWidth(225);
		scrollPane.setViewportView(coursedetails);
		
		JLabel course_details_text = new JLabel("Course Details");
		course_details_text.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		course_details_text.setBounds(105, 134, 133, 31);
		coursepanel.add(course_details_text);
		
		JLabel nameCourse = new JLabel("Course Name");
		nameCourse.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		nameCourse.setBounds(49, 79, 102, 23);
		coursepanel.add(nameCourse);

//***************************************************************************************************************
		
		
		JLabel optionalmodule = new JLabel("optionalModule");
		optionalmodule.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		optionalmodule.setBounds(335, 77, 103, 23);
		coursepanel.add(optionalmodule);
		
		JComboBox optionalModule = new JComboBox();
		optionalModule.setBounds(444, 73, 168, 31);
		coursepanel.add(optionalModule);
//***************************************************************************************************************		
		/*
		 * This for choosing the level option to get enroll add manually in the combobox
		 */
		
		String[] levelopt = {"4","5","6"};
		JComboBox comboBoxLevel = new JComboBox(levelopt);
		comboBoxLevel.addItemListener(new ItemListener() {
			 @Override
			public void itemStateChanged(ItemEvent e) {
			
				String namecourse = (String) comboBoxLevel.getSelectedItem();
		        search(namecourse);
			}
		});
		comboBoxLevel.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBoxLevel.setBounds(683, 73, 67, 31);
		coursepanel.add(comboBoxLevel);
		
		JLabel Level = new JLabel("Level");
		Level.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		Level.setBounds(636, 77, 43, 24);
		coursepanel.add(Level);
//***************************************************************************************************************************	
		/*
		 * This block is for selecting the course to get enroll fetching from the database and adding in the combobox
		 */
		JComboBox comboCourseName = new JComboBox();
		comboCourseName.addItemListener(new ItemListener() {
			 @Override
			public void itemStateChanged(ItemEvent e) {
				
				 
				
				String url1 = "jdbc:mysql://localhost:3306/course_management_system";
			    String username1 = "root";
			    String password1 = "";
			    
			    try{
			    	optionalModule.removeAllItems();
			    	
			    	DefaultTableModel model2 = (DefaultTableModel)coursedetails.getModel(); //set the object of defaulttablemodel
					model2.setRowCount(0); //for refresh
			    	
			    	//String variables
			    		String  id = "";
			    		String modulecode ="";
			    		String modulename ="";
			    		String level ="";
			    		String semester ="";
			    		String optional = "";
				        //load the driver
				        Class.forName("com.mysql.cj.jdbc.Driver");
				        //create the connection
				        Connection connect1 = DriverManager.getConnection(url1,username1,password1);
				        
				        //this query is to fetch all the module information  according to course and display in the table
				        String qry = "select ID, module_code, module_name,level, semester, optional from course where course_name = ? and level = ?";
				        
				        PreparedStatement pstate = connect1.prepareStatement(qry);
				        pstate.setString(1, (String) comboCourseName.getSelectedItem());
				        pstate.setString(2, (String) comboBoxLevel.getSelectedItem());
				        ResultSet rs = pstate.executeQuery();
				        
				        DefaultTableModel model = (DefaultTableModel)coursedetails.getModel(); //default table model object created
				        
				        while(rs.next()) {
				        	id = rs.getString("ID");
				        	modulecode = rs.getString("module_code");
				        	modulename = rs.getString("module_name");
				        	level = rs.getString("level");
				        	semester = rs.getString("semester");
				        	optional = rs.getString("optional");
				        	String[] row = {id,modulecode,modulename,level,semester,optional}; //adding the fetch data in the array
				        	model.addRow(row); //adding the array as row in table
				        	
				        	/*
				        	 * If the module is optional in the selected course according to the level, than display only optional 
				        	 * module to get enroll by choosing optional module
				        	 */
				        	if(optional.equals("true")) {
				        		
				        		/*
				        		 * if true, run this query to display optional modules according to level and course selected
				        		 */
				        		 String qry3 = "SELECT module_name   FROM course WHERE optional = ?";
							        PreparedStatement pstate3 = connect1.prepareStatement(qry3);
							        pstate3.setString(1, optional);
							        
							        ResultSet rsm3 = pstate3.executeQuery();
							        
							        while(rsm3.next()) {
							        	
							        	
							        	optionalModule.addItem(rsm3.getString("module_name")); //adding the combobox of optional modules
							        }
				        		
				        	}
				        	
				        }
				        String namecourse = (String) comboBoxLevel.getSelectedItem();
				        search(namecourse);
				        
				        pstate.close();
				        connect1.close();
			    }catch (Exception exp){
			        System.out.println(exp);
			    }				
			}
		});
		comboCourseName.setBounds(153, 75, 168, 31);
		coursepanel.add(comboCourseName);
		
		//***************************************************************************************************
		/*
		 * This for displaying all the course in combobox for student to get enroll register by admin 
		 */
		String url1 = "jdbc:mysql://localhost:3306/course_management_system";
	    String username1 = "root";
	    String password1 = "";
	    
	    try{
		        //load the driver
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        //create the connection
		        Connection connect1 = DriverManager.getConnection(url1,username1,password1);
		        
		        //query to display course name
		        String qry = "select course_name from course_info";
		        
		        Statement pstat =  (Statement) connect1.createStatement();
		        ResultSet rs = ((java.sql.Statement) pstat).executeQuery(qry);
		        
		        while(rs.next()) {
		        	
		        	comboCourseName.addItem(rs.getString("course_name")); //adding the course name in the combobox
		        	
		        }
		        
		        ((Connection) pstat).close();
		        connect1.close();
	    }catch (Exception exp){
	        System.out.println(exp);
	    }
	    
	    
//******************************************************************************************************************
	  		/*
	  		 * This block is used to get enroll for student.
	  		 * 
	  		 * To get enroll if the selected level is '4' than it allow to enroll as a fresher and also check wether the 
	  		 * student is enrolled already or not in level '4'.
	  		 * But if the Selected level other than '4' , the system will check whether the student is passed in perivous level or not. 
	  		 * If student is not passed in the perivous level it cannot access to get enroll, If the student is passed in the perivous level
	  		 * than it allow to get enroll also check whether the student get enroll alreadly or not in the same level, If not than only give 
	  		 * access to get enroll in the next level
	  		 */
	  		JButton btnEnroll = new JButton("Enroll");
	  		btnEnroll.addActionListener(new ActionListener() {
	  			 @Override
	  			public void actionPerformed(ActionEvent e) {
	  				
	  				String url1 = "jdbc:mysql://localhost:3306/course_management_system";
	  			    String username1 = "root";
	  			    String password1 = "";
	  			    
	  			    try{
	  			    	//load the driver
	  			        Class.forName("com.mysql.cj.jdbc.Driver");
	  			        //create the connection
	  			        Connection connect1 = DriverManager.getConnection(url1,username1,password1);
	  			        
	  			        //this query is to fetch all information of student with the help of login email
	  			        String query = "select fullname, studentID from student where email = ?";
	  			        PreparedStatement pstat =  connect1.prepareStatement(query);
	  			        pstat.setString(1, emailf2);
	  			    	ResultSet rs = pstat.executeQuery();
	  			    	
	  			    	if(rs.next()) {
	  			    		
	  			    		String fname = rs.getString("fullname");
	  			    		String stdID = rs.getString("studentID");
	  			    		String combolevel = (String) comboBoxLevel.getSelectedItem();
	  			    		
	  			    		//check wether the student selected level is '4' or not
	  			    		if(combolevel.equals("4")) {
	  			    			
	  			    			/*
	  			    			 * if level '4' than execute this part.
	  			    			 * first check wether the student is already get enroll in level '4' or not.
	  			    			 */
	  			    			String query4 = "SELECT level FROM student_enroll_info WHERE std_email =? and level =?";
	  			    			PreparedStatement pstate4 =  connect1.prepareStatement(query4);
	  			    			pstate4.setString(1, emailf2);
	  			    			pstate4.setString(2, combolevel);
	  			    			ResultSet rs4 = pstate4.executeQuery();
	  			    			if(rs4.next()) {
	  			    				
	  			    				/*
	  			    				 * If the student is already enroll in the level, system run this part
	  			    				 * and show ,message
	  			    				 */
	  			    				JOptionPane.showMessageDialog(null," !! you already enrolled this level. !!");
	  			    			}else {
	  			    				
	  			    				//else access to get enroll as fresher
	  			    				String query1 = "INSERT INTO student_enroll_info (course_name, stdID, std_fullname,std_email, level) VALUES (?,?,?,?,?)";
			  				        PreparedStatement pstate =  connect1.prepareStatement(query1);
			  				        pstate.setString(1, (String) comboCourseName.getSelectedItem());
			  				        pstate.setString(2, stdID);
			  				        pstate.setString(3, fname);
			  				        pstate.setString(4, emailf2);
			  				        pstate.setString(5, (String) comboBoxLevel.getSelectedItem());
			  				    	pstate.executeUpdate();
			  				    	
			  				    	pstate.close();
			  				    	JOptionPane.showMessageDialog(null,"you enrolled successfully.");
	  			    			}
	  			    			pstate4.close();	
	  			    			
	  			    		}else {
	  			    			
	  			    			//this query is to check wether the student is passed in the perivous level or not
	  			    			String query1 = "select grade from grade where email = ?";
	  		  			        PreparedStatement pstat1 =  connect1.prepareStatement(query1);
	  		  			        pstat1.setString(1, emailf2);
	  		  			    	ResultSet rs1 = pstat1.executeQuery();
	  		  			    	
	  		  			    	int count =0;
	  		  			    	while(rs1.next()) {
	  		  			    		String checkfinalgrade = rs1.getString("grade"); //assigned the final grade in the String variable
	  		  			    		
	  		  			    		if(checkfinalgrade.equals("fail")) {
	  		  			    			// if fail, run this part
	  		  			    			count++; //count only one to display message
	  		  			    		
	  		  			    			break;
	  		  			    		}else{
	  		  			    			
	  		  			    		    count = 0; //assigned '0' to display message
	  		  			    		}	
	  		  			    	}
	  		  			    	if(count >0) { //count is greater than '0', it will show the message one time only
	  		  			    		/*
	  		  			    		 * if the student is not passed in the perivous level than show this message
	  		  			    		 */
	  		  			    	JOptionPane.showMessageDialog(null," !!Sorry you haven't passed the perivous level.!!");
	  		  			    	}else {
	  		  			    		/*
	  		  			    		 * If student passed this part execute, to get enroll in the next level
	  		  			    		 */
	  		  			    		
	  		  			    		//query to check wether the student is already get enroll in the next level or not 
		  		  			    	String query2 = "SELECT level FROM student_enroll_info WHERE std_email =? and level =?";
		  			    			PreparedStatement pstate2 =  connect1.prepareStatement(query2);
		  			    			pstate2.setString(1, emailf2);
		  			    			pstate2.setString(2, combolevel);
		  			    			ResultSet rs2 = pstate2.executeQuery();
	  		  			    		
		  			    			if(rs2.next()) {
		  			    				//if already get enroll show this message
		  			    				JOptionPane.showMessageDialog(null," !! you already enrolled this level. !!");
		  			    				
		  			    			}else {
		  			    				
		  			    				//if student is not enrolled already, than allow to get enroll in the next level
		  			    				String query3 = "INSERT INTO student_enroll_info (course_name, stdID, std_fullname,std_email, level) VALUES (?,?,?,?,?)";
				  				        PreparedStatement pstate3 =  connect1.prepareStatement(query3);
				  				        pstate3.setString(1, (String) comboCourseName.getSelectedItem());
				  				        pstate3.setString(2, stdID);
				  				        pstate3.setString(3, fname);
				  				        pstate3.setString(4, emailf2);
				  				        pstate3.setString(5, (String) comboBoxLevel.getSelectedItem());
				  				    	pstate3.executeUpdate();
				  				    	
				  				    	pstate3.close();
				  				    	JOptionPane.showMessageDialog(null,"you enrolled successfully.");
		  			    			}
		  		  			    	
		  			    			pstate2.close();
	  		  			    	}
	  		  			    
	  			    		}
	  			    		
	  			    	}else {
	  			    		
	  			    		//if search student is not found than show this message
	  			    		JOptionPane.showMessageDialog(null,"Student fullname and studentID not found");
	  			    		
	  			    	}
	  			    	
	  			    	pstat.close();
	  			    	connect1.close();
	  			    }catch (Exception exp){
	  			        System.out.println(exp);
	  			    }
	  			}
	  		});
	  		btnEnroll.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
	  		btnEnroll.setBounds(631, 523, 129, 40);
	  		coursepanel.add(btnEnroll);
		
//******************************************************************************************************************************8
	    
		JTextPane txtpnNotePleaseCheck = new JTextPane();
		txtpnNotePleaseCheck.setBackground(new Color(207, 248, 222));
		txtpnNotePleaseCheck.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtpnNotePleaseCheck.setText("Note: Please, check and click to enroll button to get enroll. Once you submit, cannot be able to edit and delete.");
		txtpnNotePleaseCheck.setBounds(56, 522, 432, 61);
		coursepanel.add(txtpnNotePleaseCheck);
		
		
		
//		====================================================================================================================================
//		Tutor Panel
		
		JPanel tutorpanel = new JPanel();
		tutorpanel.setBackground(new Color(207, 248, 222));
		tutorpanel.setLayout(null);
		layeredPane.add(tutorpanel, "name_233715755737700");
		
		JLabel lblTutors = new JLabel("Tutors");
		lblTutors.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblTutors.setBounds(353, 0, 117, 40);
		tutorpanel.add(lblTutors);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.control, null, null));
		scrollPane_1.setBounds(34, 190, 621, 345);
		tutorpanel.add(scrollPane_1);
		
		tutordetails = new JTable();
		tutordetails.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tutordetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tutor Name", "Email", "Mobile Number"
			}
		));
		tutordetails.getColumnModel().getColumn(0).setPreferredWidth(176);
		tutordetails.getColumnModel().getColumn(1).setPreferredWidth(161);
		tutordetails.getColumnModel().getColumn(2).setPreferredWidth(106);
		scrollPane_1.setViewportView(tutordetails);
		
		JLabel tutorDetailstext = new JLabel("Tutor Details");
		tutorDetailstext.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		tutorDetailstext.setBounds(36, 157, 133, 30);
		tutorpanel.add(tutorDetailstext);
//***********************************************************************************************************************	
		
		/*
		 * This block is for search the tutor infromations
		 */
		searchtutor = new JTextField();
		searchtutor.setToolTipText("Search");
		searchtutor.setBounds(195, 82, 234, 33);
		tutorpanel.add(searchtutor);
		searchtutor.setColumns(10);
	
		JButton btnSearch = new JButton("Search/refresh");
		btnSearch.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				searchtutor(searchtutor.getText());
			}
		});
		btnSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnSearch.setBounds(40, 84, 145, 29);
		tutorpanel.add(btnSearch);
		
		
		
//		=======================================================================================================================================
//		Setting Panel
		
		JPanel settingpanel = new JPanel();
		settingpanel.setBackground(new Color(207, 248, 222));
		settingpanel.setLayout(null);
		layeredPane.add(settingpanel, "name_233785812080600");
		
		JLabel lblSetting = new JLabel("Settings");
		lblSetting.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblSetting.setBounds(331, 0, 152, 40);
		settingpanel.add(lblSetting);
		
		JLabel textgeneralProfile = new JLabel("General Profile");
		textgeneralProfile.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		textgeneralProfile.setBounds(23, 58, 134, 23);
		settingpanel.add(textgeneralProfile);
		
		JLabel textfullname = new JLabel("FullName");
		textfullname.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		textfullname.setBounds(29, 105, 67, 23);
		settingpanel.add(textfullname);
		
		
		
		JLabel text_email = new JLabel("Email");
		text_email.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		text_email.setBounds(423, 104, 44, 23);
		settingpanel.add(text_email);
		
		JLabel text_mbl_no = new JLabel("Mobile no.");
		text_mbl_no.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		text_mbl_no.setBounds(20, 157, 76, 23);
		settingpanel.add(text_mbl_no);
		
		
		fullname = new JTextField();
		fullname.setBounds(103, 101, 239, 32);
		settingpanel.add(fullname);
		fullname.setColumns(10);
		
		email = new JTextField();
		email.setBounds(469, 100, 239, 32);
		settingpanel.add(email);
		email.setColumns(10);
		
		mblno = new JTextField();
		mblno.setBounds(103, 152, 239, 32);
		settingpanel.add(mblno);
		mblno.setColumns(10);
		
	//**************************************************************************************************************************	
		JButton btn_edit_profile = new JButton("Edit Profile");
		btn_edit_profile.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				 /*
				  * To edit the profile information, Regex is used
				  */
				 
				 //variable to String from text fields
				String fullnamecheck = fullname.getText();
				
				String Mblnocheck = mblno.getText();
				String emailcheck = email.getText();
				
				
				String fullnameRegex = "([a-zA-Z]+)\s([a-zA-Z]+){4,30}";
				
				
				
				//Regex for mobile number check i.e. exactly 10 digits number
				String mblnoregex = "\\d{10}";
				
				//Regex for email format check, Somthings before @ and somethings after @
				String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
						"[a-zA-Z0-9+&*-]+)*@"+
						"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
				
				//initilized the pattern
				Pattern patternfullname = Pattern.compile(fullnameRegex);
				
				Pattern patternmblno = Pattern.compile(mblnoregex);
				Pattern patternemail = Pattern.compile(emailRegex);
				
				/*
				 * if all the patttern matches, than update as the enter information is updated,
				 * if any one of the enter information is matches than system only updated that information only
				 * else any of these enter information doesn't matches the pattern than it will show message and don't allow to update
				 */
				//check all patterns
				if(patternfullname.matcher(fullnamecheck).matches() && patternmblno.matcher(Mblnocheck).matches() && patternemail.matcher(emailcheck).matches() ) {
					
					String url = "jdbc:mysql://localhost:3306/course_management_system";
				    String username = "root";
				    String password = "";
				    
				   
				    try{
			            //load the driver
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            //create the connection
			            Connection connect = DriverManager.getConnection(url,username,password);
			            
		            	String query1 = "UPDATE student SET fullname=?"
		            			+ ",email=?, mblNo=? WHERE email =?";
			            PreparedStatement pstate1 = connect.prepareStatement(query1);
			            pstate1.setString(1, fullname.getText());
			            pstate1.setString(2, email.getText());
			            pstate1.setString(3, mblno.getText());
			            pstate1.setString(4, emailf2);
			            
			            pstate1.executeUpdate();
			            
			            JOptionPane.showMessageDialog(null, "updated succefully!");
		            	
			            pstate1.close();
			            fullname.setText("");
			            email.setText("");
			            mblno.setText("");
			            
			            //close the Statement
			            connect.close();
				    
			        }catch (Exception exp){
			            System.out.println(exp);
			        }
				}else if(patternemail.matcher(emailcheck).matches()) { //check email pattern
					
					String url = "jdbc:mysql://localhost:3306/course_management_system";
				    String username = "root";
				    String password = "";
				    
				   
				    try{
			            //load the driver
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            //create the connection
			            Connection connect = DriverManager.getConnection(url,username,password);
			            
		            	String query1 = "UPDATE student SET email=? WHERE email =?";
			            PreparedStatement pstate1 = connect.prepareStatement(query1);
			            
			            pstate1.setString(1, email.getText());
			           
			            pstate1.setString(2, emailf2);
			            
			            pstate1.executeUpdate();
			            
			            JOptionPane.showMessageDialog(null, "Email updated succefully!");
		            	
			            pstate1.close();
			            
			            email.setText("");
			            
			            
			            //close the Statement
			            connect.close();
				    
			        }catch (Exception exp){
			            System.out.println(exp);
			        }
				}else if(patternmblno.matcher(Mblnocheck).matches()) { //check mobile number pattern
					
					String url = "jdbc:mysql://localhost:3306/course_management_system";
				    String username = "root";
				    String password = "";
				    
				   
				    try{
			            //load the driver
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            //create the connection
			            Connection connect = DriverManager.getConnection(url,username,password);
			            
		            	String query1 = "UPDATE student SET mblNo=? WHERE email =?";
			            PreparedStatement pstate1 = connect.prepareStatement(query1);
			            
			            pstate1.setString(1, mblno.getText());
			           
			            pstate1.setString(2, emailf2);
			            
			            pstate1.executeUpdate();
			            
			            JOptionPane.showMessageDialog(null, "Mobile Number updated succefully!");
		            	
			            pstate1.close();
			            
			            mblno.setText("");
			            
			            
			            //close the Statement
			            connect.close();
				    
			        }catch (Exception exp){
			            System.out.println(exp);
			        }
				}else if(patternfullname.matcher(fullnamecheck).matches()){ //check full name pattern
					
					String url = "jdbc:mysql://localhost:3306/course_management_system";
				    String username = "root";
				    String password = "";
				    
				   
				    try{
			            //load the driver
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            //create the connection
			            Connection connect = DriverManager.getConnection(url,username,password);
			            
		            	String query1 = "UPDATE student SET fullname=? WHERE email =?";
			            PreparedStatement pstate1 = connect.prepareStatement(query1);
			            
			            pstate1.setString(1, fullname.getText());
			           
			            pstate1.setString(2, emailf2);
			            
			            pstate1.executeUpdate();
			            
			            JOptionPane.showMessageDialog(null, "Name updated succefully!");
		            	
			            pstate1.close();
			            
			            fullname.setText("");
			            
			            
			            //close the Statement
			            connect.close();
				    
			        }catch (Exception exp){
			            System.out.println(exp);
			        }
				}
				else {
					
					//else show this message
					JOptionPane.showMessageDialog(null, "Please, Check fullname(firstname and lastname required),"
							+ "mobile number and email format is not match\"");
				}
			}
		});
		btn_edit_profile.setBackground(SystemColor.info);
		btn_edit_profile.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btn_edit_profile.setBounds(23, 213, 121, 32);
		settingpanel.add(btn_edit_profile);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(23, 262, 685, 2);
		settingpanel.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(23, 83, 124, 2);
		settingpanel.add(separator_2);
		
		JLabel texthelp = new JLabel("Help");
		texthelp.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		texthelp.setBounds(24, 306, 50, 23);
		settingpanel.add(texthelp);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(23, 332, 44, 2);
		settingpanel.add(separator_4);
		
		JLabel lblhelp = new JLabel("For any help and support, contact to collage administration");
		lblhelp.setForeground(new Color(128, 64, 64));
		lblhelp.setBackground(new Color(0, 128, 192));
		lblhelp.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblhelp.setBounds(20, 341, 401, 23);
		settingpanel.add(lblhelp);
		
		JLabel lblEmailHeraldcollageadminedunp = new JLabel("Email: RenaissanceboardingAdmin@edu.np");
		lblEmailHeraldcollageadminedunp.setForeground(new Color(0, 0, 0));
		lblEmailHeraldcollageadminedunp.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEmailHeraldcollageadminedunp.setBackground(new Color(0, 128, 192));
		lblEmailHeraldcollageadminedunp.setBounds(56, 376, 300, 23);
		settingpanel.add(lblEmailHeraldcollageadminedunp);
		
		JLabel lblTelNumber = new JLabel("Tel number: 046 540 410");
		lblTelNumber.setForeground(Color.BLACK);
		lblTelNumber.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTelNumber.setBackground(new Color(0, 128, 192));
		lblTelNumber.setBounds(56, 407, 179, 23);
		settingpanel.add(lblTelNumber);
		
		
		
		
		
//		====================================================================================================================================
//		Profile Panel
		
		JPanel profilpanel = new JPanel();
		profilpanel.setBackground(new Color(207, 248, 222));
		profilpanel.setLayout(null);
		layeredPane.add(profilpanel, "name_233749033870100");
		
		JLabel lblStudents = new JLabel("Profile");
		lblStudents.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblStudents.setBounds(341, 0, 122, 40);
		profilpanel.add(lblStudents);
		
		JLabel lblFullname = new JLabel("FullName:");
		lblFullname.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblFullname.setBounds(40, 108, 75, 23);
		profilpanel.add(lblFullname);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblEmail.setBounds(40, 189, 56, 23);
		profilpanel.add(lblEmail);
		
		JLabel lblMobileNo = new JLabel("Mobile no.:");
		lblMobileNo.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblMobileNo.setBounds(40, 243, 92, 23);
		profilpanel.add(lblMobileNo);
		
		JLabel showfullname = new JLabel("");
		showfullname.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		showfullname.setBounds(125, 108, 227, 23);
		profilpanel.add(showfullname);
		
		JLabel showemail = new JLabel("");
		showemail.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		showemail.setBounds(90, 189, 227, 23);
		profilpanel.add(showemail);
		
		JLabel showMbl_no = new JLabel("");
		showMbl_no.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		showMbl_no.setBounds(133, 243, 123, 23);
		profilpanel.add(showMbl_no);
		
		JLabel lblCourse = new JLabel("Course: ");
		lblCourse.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblCourse.setBounds(40, 288, 62, 23);
		profilpanel.add(lblCourse);
		
		JLabel showCourse = new JLabel("");
		showCourse.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		showCourse.setBounds(100, 288, 252, 23);
		profilpanel.add(showCourse);
		
		JLabel lblStdID = new JLabel("StudentID:");
		lblStdID.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblStdID.setBounds(40, 150, 92, 23);
		profilpanel.add(lblStdID);
		
		JLabel showstdID = new JLabel("");
		showstdID.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		showstdID.setBounds(132, 150, 138, 23);
		profilpanel.add(showstdID);
		
		JButton btnlogout = new JButton("Logout");
		btnlogout.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				//after logout by the user call the login class
				new Login();
				frame.setVisible(false);
			}
		});
		btnlogout.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnlogout.setBounds(38, 335, 87, 28);
		profilpanel.add(btnlogout);
		
		//*********************************************************************************************************************
		
		/*
		 * This is to display the information of login user as profile
		 */
		String url4 = "jdbc:mysql://localhost:3306/course_management_system";
	    String username4 = "root";
	    String password4 = "";
	    
	    try{
		        //load the driver
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        //create the connection
		        Connection connect4 = DriverManager.getConnection(url4,username4,password4);
		        
		        //query to fetch selected informtaion of student
		        String qry ="SELECT fullname, studentID, email, mblNo  FROM student WHERE email = ?";
		        PreparedStatement pstat =  connect4.prepareStatement(qry);
		        pstat.setString(1, emailf2);
		        ResultSet rsm = pstat.executeQuery();
		        
		        if(rsm.next()) {
		        	
		        	showfullname.setText(rsm.getString("fullname"));
		        	showstdID.setText(rsm.getString("studentID"));
		        	showemail.setText(emailf2);
		        	showMbl_no.setText(rsm.getString("mblNo"));
		        	
		        	//query to show enrolled cours name
		        	String qry6 ="SELECT course_name  FROM student_enroll_info WHERE stdID = ?";
			        PreparedStatement pstat6 =  connect4.prepareStatement(qry6);
			        pstat6.setString(1, showstdID.getText());
			        ResultSet rsm6 = pstat6.executeQuery();
			        
			        if(rsm6.next()) {
			        	
			        	showCourse.setText(rsm6.getString("course_name"));
			        	
			        }
		        }
		        
		        
	    }catch (Exception exp){
	        System.out.println(exp);
	    }
		
//		==============================================================================================================================
	    /*
	     * This to show the Student fullname in the menu panel
	     */
		JLabel studentname = new JLabel(emailf2);
		
		String url = "jdbc:mysql://localhost:3306/course_management_system";
	    String username = "root";
	    String password = "";
	    
	    try{
		        //load the driver
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        //create the connection
		        Connection connect = DriverManager.getConnection(url,username,password);
		        
		        //query to fetch the fullname by login email
		        String query = "select fullname from student  where email = ?";
		        PreparedStatement pstat =  connect.prepareStatement(query);
		        pstat.setString(1, emailf2);
	
		        ResultSet rs = pstat.executeQuery();
		        
		        if(rs.next()) {
		        	
		        	String nameprofile = rs.getString("fullname"); 
		        	studentname.setText(nameprofile);//show fullname
		        }else {
		        	
		        }
	        
	        	pstat.close();
	        	//close the Statement
		        connect.close();
		    }catch (Exception exp){
		        System.out.println(exp);
		    }
		
		studentname.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				//helps to change the layerpanel
				layeredPane.removeAll();
				layeredPane.add(profilpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		studentname.setHorizontalAlignment(SwingConstants.CENTER);
		studentname.setForeground(new Color(255, 255, 255));
		studentname.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		studentname.setBounds(10, 44, 207, 32);
		menu.add(studentname);
		
//=========================================================================================================================================		
		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				//helps to change the layerpanel
				layeredPane.removeAll();
				layeredPane.add(dashboardpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnDashboard.setBackground(new Color(221, 249, 228));
		btnDashboard.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnDashboard.setBounds(28, 304, 177, 36);
		menu.add(btnDashboard);
		
		JButton btncourses = new JButton("  Courses");
		btncourses.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				//helps to change the layerpanel
				layeredPane.removeAll();
				layeredPane.add(coursepanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btncourses.setBackground(new Color(192, 192, 192));
		btncourses.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btncourses.setBounds(28, 381, 177, 36);
		menu.add(btncourses);
		
		JButton btntutors = new JButton("  Tutors");
		btntutors.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				//helps to change the layerpanel
				layeredPane.removeAll();
				layeredPane.add(tutorpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btntutors.setBackground(new Color(192, 192, 192));
		btntutors.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btntutors.setBounds(28, 440, 177, 36);
		menu.add(btntutors);
		
		JButton btnsettings = new JButton("Settings");
		btnsettings.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				//helps to change the layerpanel
				layeredPane.removeAll();
				layeredPane.add(settingpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnsettings.setBackground(new Color(192, 192, 192));
		btnsettings.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnsettings.setBounds(30, 549, 177, 36);
		menu.add(btnsettings);
		
		JButton btnViewReport = new JButton("Report");
		btnViewReport.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				new Student_view_result(emailf2); //calling and passing the login email to view the report 
				
				
			}
		});
		btnViewReport.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnViewReport.setBackground(Color.LIGHT_GRAY);
		btnViewReport.setBounds(30, 496, 177, 36);
		menu.add(btnViewReport);
		

		
		frame.setVisible(true);
		
		
	
	}
	
	
//-------------------------------------------------------------------------------------------------------
	
public void showinfo() {
		
		/*
		  * This method is for loading and fetch from the database to show tutor information in the jtable
		  */
		
		String url = "jdbc:mysql://localhost:3306/course_management_system";
	    String username = "root";
	    String password = "";
	    
	    
	    try{
	        //load the driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        //create the connection
	        Connection connect = DriverManager.getConnection(url,username,password);
	        
	        
	        String tutor_name = "";
	        String tutor_email = "";
	        String tutor_mobl  = "";
	        
	        
	        
	        String q = "Select * from tutor"; //query to fetch all information of student enrolled from student_enroll_info table
	        PreparedStatement pstat =  connect.prepareStatement(q);

	        ResultSet rs = pstat.executeQuery();  
	        
	        
	        DefaultTableModel model1 = (DefaultTableModel)tutordetails.getModel();
	        
	        
	        while(rs.next()) {
	        	
	        	//decalared the and initilized the string variables
	        	
	        	tutor_name = rs.getString("fullname");
	        	tutor_email = rs.getString("email");
	        	tutor_mobl = rs.getString("mblNo");
	        	
	        	
	        	//storing the variables data in the array
	        	String[] row = {tutor_name, tutor_email,tutor_mobl};
	        	model1.addRow(row);  //adding array as a row in the jscrollpanel 

	        }
	        	
	        pstat.close(); //close the prepared Statement
	        
	        

	        //close the Statement
	        connect.close();
	    }catch (Exception exp){
	        System.out.println(exp);
	    }
		
	}
	
//--------------------------------------------------------------------------------------------------------

	public void search(String str) {
		/*
		 * This method is for searching course information in the jtable
		 */
		
		DefaultTableModel model = (DefaultTableModel)coursedetails.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
		coursedetails.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(str));
		
	}
//-----------------------------------------------------------------------------------------------------------
	
	public void searchtutor(String str) {
		/*
		 * This method is for searching tutor information in the jtable
		 */
		
		DefaultTableModel model = (DefaultTableModel)tutordetails.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
		tutordetails.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(str));
		
	}
}
