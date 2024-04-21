package com.cms;

import java.awt.CardLayout;
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
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
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

public class Teacher extends JFrame {

	private JFrame frame;
	private JTextField fullname;
	private JTextField email;
	private JTextField mblno;
	private JTable assigneddetails;
	private JTable Student_info;
	private JTextField search;
	private JTable course;
	private JTable table;
	private JTextField searchfield;
	private ArrayList<String> totamodule = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void teacher(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Teacher window = new Teacher();
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
	public Teacher(String emailf) {
		initialize(emailf);
		Showenrollinfo();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String emailf ) {
		
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
		
		JLabel admintext = new JLabel("Teacher");
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
//		===================================================================================================================================
//		Dashboard Panel
		
		JPanel dashboardpanel = new JPanel();
		dashboardpanel.setBackground(new Color(207, 248, 222));
		layeredPane.add(dashboardpanel, "name_233468739105100");
		dashboardpanel.setLayout(null);
		
		JLabel total_course_assigned = new JLabel("Total Assigned Modules");
		total_course_assigned.setHorizontalAlignment(SwingConstants.CENTER);
		total_course_assigned.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		total_course_assigned.setBounds(406, 176, 358, 60);
		dashboardpanel.add(total_course_assigned);
		
		JLabel show_assign_module = new JLabel("");
		show_assign_module.setHorizontalAlignment(SwingConstants.CENTER);
		show_assign_module.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		show_assign_module.setBounds(519, 243, 132, 46);
		dashboardpanel.add(show_assign_module);
		
		JLabel Show_course = new JLabel("");
		Show_course.setHorizontalAlignment(SwingConstants.CENTER);
		Show_course.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		Show_course.setBounds(73, 250, 260, 38);
		dashboardpanel.add(Show_course);
		
		JLabel course_assigned = new JLabel("Course");
		course_assigned.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		course_assigned.setHorizontalAlignment(SwingConstants.CENTER);
		course_assigned.setBounds(94, 179, 218, 38);
		dashboardpanel.add(course_assigned);
		
		JLabel lblNewLabel = new JLabel("Dashboard");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblNewLabel.setBounds(296, 0, 185, 55);
		dashboardpanel.add(lblNewLabel);
		
		course = new JTable();
		course.setBounds(66, 177, 278, 143);
		dashboardpanel.add(course);
		
		table = new JTable();
		table.setBounds(406, 174, 358, 143);
		dashboardpanel.add(table);
		
		//********************************************************************************
		/*
		 * This section is for display the total assigned module to tutor in dashboard according to course.
		 * for this I have used arraylist and iterator
		 */
		
		String url9 = "jdbc:mysql://localhost:3306/course_management_system";
	    String username9 = "root";
	    String password9 = "";
	    
	    try{
	    
		        //load the driver
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        //create the connection
		        Connection connect1 = DriverManager.getConnection(url9,username9,password9);
		        
		        //this query to fetch the tutorID by login eamil
		        String q1 = "select tutorID from tutor where email =?";
		        PreparedStatement pstate = connect1.prepareStatement(q1);
		        pstate.setString(1, emailf);
		        ResultSet rs1 = pstate.executeQuery();
		        if(rs1.next()) {
		        	
		        	String TeacherID = rs1.getString("tutorID"); //getting tutor ID
		        	
		        	//this query is to select the course name by Tutor ID in what course the module is assigned
		        	String qry = "select course_name from module_assigned where tutorID= ?";
			        
			        PreparedStatement pstate1 = connect1.prepareStatement(qry);
			        pstate1.setString(1, TeacherID);
			        
			        ResultSet rs = pstate1.executeQuery();
			        if(rs.next()) {
			        	Show_course.setText(rs.getString("course_name")); //show course name
			        	
			        	//this query is to select the module code by course name
			        	String qry1 = "select module_code from module_assigned where course_name =?";
			        	PreparedStatement pstate2 = connect1.prepareStatement(qry1);
				        pstate2.setString(1, Show_course.getText());
				        ResultSet rs2 = pstate2.executeQuery();
				        
				        while(rs2.next()) {
				        	
				        	totamodule.add(rs2.getString("module_code")); //adding the total module code in arraylist
				        }   
			        }
		        	
		        } 
		        //using iterator
		        java.util.Iterator<String> c = totamodule.iterator();
	            int count = 0; //initializing count
	            while ( c.hasNext()) {
	                String condition = (String) c.next(); //for next value
	                count++;    //count the total number of module code
	            }
	            
	            totamodule.clear(); //clear to fetch totally new
	            show_assign_module.setText(String.valueOf(count)); //show total number module assigned according to course
		        
		        
		        pstate.close();
		        connect1.close();
	    }catch (Exception exp){
	        System.out.println(exp);
	    }	
		
		
//		====================================================================================================================================
//		Course Panel
		JPanel coursepanel = new JPanel();
		coursepanel.setBackground(new Color(207, 248, 222));
		layeredPane.add(coursepanel, "name_233628479463100");
		coursepanel.setLayout(null);
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setBounds(328, 6, 142, 40);
		lblCourses.setFont(new Font("Tahoma", Font.BOLD, 33));
		coursepanel.add(lblCourses);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(63, 144, 657, 385);
		coursepanel.add(scrollPane);
		
		//for displaying module assigned details
		assigneddetails = new JTable();
		assigneddetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"module_code", "module_name", "level", "Semester", "optional", "position"
			}
		));
		assigneddetails.getColumnModel().getColumn(0).setPreferredWidth(79);
		assigneddetails.getColumnModel().getColumn(1).setPreferredWidth(193);
		assigneddetails.getColumnModel().getColumn(5).setPreferredWidth(123);
		scrollPane.setViewportView(assigneddetails);
		
		JLabel lblNewLabel_1 = new JLabel("Module Assigned");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		lblNewLabel_1.setBounds(63, 107, 147, 31);
		coursepanel.add(lblNewLabel_1);
		
		searchfield = new JTextField();
		searchfield.setBounds(133, 59, 199, 25);
		coursepanel.add(searchfield);
		searchfield.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				search(searchfield.getText()); //for search
				
			}
		});
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnNewButton.setBounds(35, 61, 85, 25);
		coursepanel.add(btnNewButton);
		
//		====================================================================================================================================
//		Student Panel
		JPanel studentpanel = new JPanel();
		studentpanel.setBackground(new Color(207, 248, 222));
		studentpanel.setLayout(null);
		layeredPane.add(studentpanel, "name_233749033870100");
		
		JLabel lblStudents = new JLabel("Student");
		lblStudents.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblStudents.setBounds(337, 0, 145, 40);
		studentpanel.add(lblStudents);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setBounds(30, 159, 743, 313);
		studentpanel.add(scrollPane_1);
		
		//for disppalying the enroll student informations
		Student_info = new JTable();
		Student_info.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Course Name", "Student ID", "Student Fullname", "Level"
			}
		));
		Student_info.getColumnModel().getColumn(0).setPreferredWidth(154);
		Student_info.getColumnModel().getColumn(2).setPreferredWidth(139);
		scrollPane_1.setViewportView(Student_info);
		
		JLabel lblNewLabel_1_1 = new JLabel("Student Information");
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(30, 124, 181, 31);
		studentpanel.add(lblNewLabel_1_1);
		
		search = new JTextField();
		search.setToolTipText("Search\r\n");
		search.setBounds(131, 62, 234, 31);
		studentpanel.add(search);
		search.setColumns(10);
		
		JButton btnaddMarks = new JButton("Add Marks");
		btnaddMarks.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(true);
				new Addmarks(emailf); //calling and passing the login email to Addmarks class to add marks in the assigned module of student
			}
		});
		btnaddMarks.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnaddMarks.setBounds(647, 496, 125, 33);
		studentpanel.add(btnaddMarks);
//**********************************************************************		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
//				DefaultTableModel model = (DefaultTableModel)Student_info.getModel();
//				model.setRowCount(0);
				searchenrollstd(search.getText()); //for search
				
			}
		});
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnNewButton_1.setBounds(35, 64, 85, 25);
		studentpanel.add(btnNewButton_1);
		
		
//		====================================================================================================================================
//		Setting Panel
		JPanel settingpanel = new JPanel();
		settingpanel.setBackground(new Color(207, 248, 222));
		settingpanel.setLayout(null);
		layeredPane.add(settingpanel, "name_233785812080600");
		
		JLabel lblSetting = new JLabel("Settings");
		lblSetting.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblSetting.setBounds(300, 0, 178, 40);
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
		text_mbl_no.setBounds(22, 157, 76, 23);
		settingpanel.add(text_mbl_no);
		
		
//*******************************************************************************************************
		
		fullname = new JTextField();
		fullname.setBounds(103, 101, 239, 32);
		settingpanel.add(fullname);
		fullname.setColumns(10);
		
		System.out.println(fullname.getText());
		
		email = new JTextField();
		email.setBounds(469, 100, 239, 32);
		settingpanel.add(email);
		email.setColumns(10);
		
		mblno = new JTextField();
		mblno.setBounds(105, 152, 239, 32);
		settingpanel.add(mblno);
		mblno.setColumns(10);
		
		
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
			            
		            	String query1 = "UPDATE tutor SET fullname=?"
		            			+ ",email=?, mblNo=? WHERE email =?";
			            PreparedStatement pstate1 = connect.prepareStatement(query1);
			            pstate1.setString(1, fullname.getText());
			            pstate1.setString(2, email.getText());
			            pstate1.setString(3, mblno.getText());
			            pstate1.setString(4, emailf);
			            
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
			            
		            	String query1 = "UPDATE tutor SET email=? WHERE email =?";
			            PreparedStatement pstate1 = connect.prepareStatement(query1);
			            
			            pstate1.setString(1, email.getText());
			           
			            pstate1.setString(2, emailf);
			            
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
			            
		            	String query1 = "UPDATE tutor SET mblNo=? WHERE email =?";
			            PreparedStatement pstate1 = connect.prepareStatement(query1);
			            
			            pstate1.setString(1, mblno.getText());
			           
			            pstate1.setString(2, emailf);
			            
			            pstate1.executeUpdate();
			            
			            JOptionPane.showMessageDialog(null, "Mobile Number updated succefully!");
		            	
			            pstate1.close();
			            
			            mblno.setText("");
			            
			            
			            //close the Statement
			            connect.close();
				    
			        }catch (Exception exp){
			            System.out.println(exp);
			        }
				}else if(patternfullname.matcher(fullnamecheck).matches()){ //check fullname pattern
					
					String url = "jdbc:mysql://localhost:3306/course_management_system";
				    String username = "root";
				    String password = "";
				    
				   
				    try{
			            //load the driver
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            //create the connection
			            Connection connect = DriverManager.getConnection(url,username,password);
			            
		            	String query1 = "UPDATE tutor SET fullname=? WHERE email =?";
			            PreparedStatement pstate1 = connect.prepareStatement(query1);
			            
			            pstate1.setString(1, fullname.getText());
			           
			            pstate1.setString(2, emailf);
			            
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
		texthelp.setBounds(24, 274, 50, 23);
		settingpanel.add(texthelp);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(23, 299, 44, 2);
		settingpanel.add(separator_4);
		
		JLabel lblhelp = new JLabel("For any help and support, contact to collage administration");
		lblhelp.setForeground(new Color(128, 64, 64));
		lblhelp.setBackground(new Color(0, 128, 192));
		lblhelp.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblhelp.setBounds(20, 309, 401, 23);
		settingpanel.add(lblhelp);
		
		JLabel lblEmailHeraldcollageadminedunp = new JLabel("Email: RenaissanceboardingAdmin@edu.np");
		lblEmailHeraldcollageadminedunp.setForeground(new Color(0, 0, 0));
		lblEmailHeraldcollageadminedunp.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEmailHeraldcollageadminedunp.setBackground(new Color(0, 128, 192));
		lblEmailHeraldcollageadminedunp.setBounds(56, 344, 300, 23);
		settingpanel.add(lblEmailHeraldcollageadminedunp);
		
		JLabel lblTelNumber = new JLabel("Tel number: 046 540 410");
		lblTelNumber.setForeground(Color.BLACK);
		lblTelNumber.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTelNumber.setBackground(new Color(0, 128, 192));
		lblTelNumber.setBounds(56, 375, 179, 23);
		settingpanel.add(lblTelNumber);
		
		
		
		
		
//	===========================================================================================================================================	
//		Profile Panel
		JPanel profilpanel = new JPanel();
		profilpanel.setBackground(new Color(207, 248, 222));
		profilpanel.setLayout(null);
		layeredPane.add(profilpanel, "name_233715755737700");
		
		JLabel lblTutors = new JLabel("Profile");
		lblTutors.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblTutors.setBounds(292, 0, 123, 40);
		profilpanel.add(lblTutors);
		
		JLabel lblFullname = new JLabel("FullName:");
		lblFullname.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblFullname.setBounds(40, 108, 75, 23);
		profilpanel.add(lblFullname);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblEmail.setBounds(40, 179, 56, 23);
		profilpanel.add(lblEmail);
		
		JLabel lblMobileNo = new JLabel("Mobile no.:");
		lblMobileNo.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblMobileNo.setBounds(40, 221, 92, 23);
		profilpanel.add(lblMobileNo);
		
		JLabel showfullname = new JLabel("");
		showfullname.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		showfullname.setBounds(125, 108, 227, 23);
		profilpanel.add(showfullname);
		
		JLabel showemail = new JLabel("");
		showemail.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		showemail.setBounds(90, 179, 227, 23);
		profilpanel.add(showemail);
		
		JLabel showMbl_no = new JLabel("");
		showMbl_no.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		showMbl_no.setBounds(133, 221, 123, 23);
		profilpanel.add(showMbl_no);
		
		JLabel lbltutorID = new JLabel("TutorID:");
		lbltutorID.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lbltutorID.setBounds(40, 146, 75, 23);
		profilpanel.add(lbltutorID);
		
		JLabel showtutorID = new JLabel("");
		showtutorID.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		showtutorID.setBounds(125, 146, 158, 23);
		profilpanel.add(showtutorID);
		
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
		btnlogout.setBounds(39, 340, 114, 29);
		profilpanel.add(btnlogout);
		
		//**********************************************************************************
		
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
		        
		        String qry ="SELECT fullname, tutorID, email, mblNo  FROM tutor WHERE email = ?";
		        PreparedStatement pstat =  connect4.prepareStatement(qry);
		        pstat.setString(1, emailf);
		        ResultSet rsm = pstat.executeQuery();
		        
		        if(rsm.next()) {
		        	
		        	showfullname.setText(rsm.getString("fullname"));
		        	showtutorID.setText(rsm.getString("tutorID"));
		        	showemail.setText(emailf);
		        	showMbl_no.setText(rsm.getString("mblNo"));
		        	
		        	
		        }
		        
		        
	    }catch (Exception exp){
	        System.out.println(exp);
	    }
		
		
//	===========================================================================================================================================
	    
	    /*
	     * This to show the Student fullname in the menu panel
	     */
		JLabel tutorname = new JLabel(emailf);
		
		String url = "jdbc:mysql://localhost:3306/course_management_system";
	    String username = "root";
	    String password = "";
	    
	    try{
		        //load the driver
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        //create the connection
		        Connection connect = DriverManager.getConnection(url,username,password);
		        
		        String query = "select fullname from tutor  where email = ?";
		        PreparedStatement pstat =  connect.prepareStatement(query);
		        pstat.setString(1, emailf);
	
		        ResultSet rs = pstat.executeQuery();
		        
		        if(rs.next()) {
		        	
		        	String nameprofile = rs.getString("fullname");
		        	tutorname.setText(nameprofile);
		        }else {
		        	
		        }
	        
	        	pstat.close();
	        	//close the Statement
		        connect.close();
		    }catch (Exception exp){
		        System.out.println(exp);
		    }
		
		tutorname.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				layeredPane.removeAll();
				layeredPane.add(profilpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		tutorname.setHorizontalAlignment(SwingConstants.CENTER);
		tutorname.setForeground(new Color(255, 255, 255));
		tutorname.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		tutorname.setBounds(10, 44, 207, 32);
		menu.add(tutorname);
//===========================================================================================================================================		
		
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
		
		JButton btnstudents = new JButton("Students");
		btnstudents.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				//helps to change the layerpanel
				layeredPane.removeAll();
				layeredPane.add(studentpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnstudents.setBackground(new Color(192, 192, 192));
		btnstudents.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnstudents.setBounds(28, 438, 177, 36);
		menu.add(btnstudents);
		
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
		btnsettings.setBounds(28, 497, 177, 36);
		menu.add(btnsettings);
		
		

		
//=========================================================================================================================
		/*
		  * This method is for loading and fetch from the database to show tutor information in the jtable by login email
		  */
		
		String url1 = "jdbc:mysql://localhost:3306/course_management_system";
	    String username1 = "root";
	    String password1 = "";
	    
	    
	    try{
	        //load the driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        //create the connection
	        Connection connect = DriverManager.getConnection(url1,username1,password1);
	        
	        
	        String modulec = "";
	        String modeln = "";
	        String mlevel  = "";
	        String msem = "";
	        String opt = "";
	        String pos = "";
	        
	        String qry ="select tutorID from tutor where email =?";
	        PreparedStatement pstat1 =  connect.prepareStatement(qry);
	        pstat1.setString(1, emailf);
	        ResultSet rs = pstat1.executeQuery();

	        if(rs.next()) {
	        	
	        	String tID = rs.getString("tutorID");
	        	
	        	
	        	 String q = "SELECT module_code , module_name , level , semester , optional, position FROM module_assigned WHERE TutorID = ?"; //query to fetch all information of module assigned
	 	        PreparedStatement pstat =  connect.prepareStatement(q);
	 	        pstat.setString(1, tID);
	 	        ResultSet rsm = pstat.executeQuery();  
	 	        
	 	        
	 	        DefaultTableModel model1 = (DefaultTableModel)assigneddetails.getModel();
	 	        
	 	        
	 	        while(rsm.next()) {
	 	        	
	 	        	//decalared the and initilized the string variables
	 	        	
	 	        	modulec = rsm.getString("module_code");
	 	        	modeln = rsm.getString("module_name");
	 	        	mlevel = rsm.getString("level");
	 	        	msem = rsm.getString("semester");
	 	        	opt = rsm.getString("optional");
	 	        	pos = rsm.getString("position");
	 	        	//storing the variables data in the array
	 	        	String[] row = {modulec, modeln,mlevel,msem,opt,pos};
	 	        	model1.addRow(row);  //adding array as a row in the jscrollpanel 

	 	        }
	 	        	
	 	        pstat.close(); //close the prepared Statement
	        }
	       

	        //close the Statement
	        connect.close();
	    }catch (Exception exp){
	        System.out.println(exp);
	    }
		
		
		frame.setVisible(true);
		
		
		
		
	
	}
//-------------------------------------------------------------------------------------------------------------------------------

public void Showenrollinfo() {
	/*
	 * This method is to show the enrolled student in the jtable
	 */
	
	String url1 = "jdbc:mysql://localhost:3306/course_management_system";
    String username1 = "root";
    String password1 = "";
    
    
    try{
        //load the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //create the connection
        Connection connect = DriverManager.getConnection(url1,username1,password1);
        
        
        String coursename = "";
        String studentId = "";
        String studentName  = "";
        String level = "";
        
        
        
        	
        	
        	 String q = "SELECT course_name ,stdID , std_fullname , level  FROM student_enroll_info "; //query to fetch all information of student enrolled from student_enroll_info table
 	        PreparedStatement pstat =  connect.prepareStatement(q);
 	       
 	        ResultSet rsm = pstat.executeQuery();  
 	        
 	        
 	        DefaultTableModel model1 = (DefaultTableModel)Student_info.getModel();
 	        
 	        
 	        while(rsm.next()) {
 	        	
 	        	//decalared the and initilized the string variables
 	        	
 	        	coursename = rsm.getString("course_name");
 	        	studentId = rsm.getString("stdID");
 	        	studentName = rsm.getString("std_fullname");
 	        	level = rsm.getString("level");
 	        	
 	        	//storing the variables data in the array
 	        	String[] row = {coursename, studentId,studentName,level};
 	        	model1.addRow(row);  //adding array as a row in the jscrollpanel 

 	        }
 	        	
 	        pstat.close(); //close the prepared Statement
        
       
        //close the Statement
        connect.close();
    }catch (Exception exp){
        System.out.println(exp);
    }
}

//------------------------------------------------------------------------------------------------------------------------------

public void search(String str) {
	/*
	 * This method is for searching course information in the jtable
	 */
	
	DefaultTableModel model = (DefaultTableModel)assigneddetails.getModel();
	TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
	assigneddetails.setRowSorter(trs);
	trs.setRowFilter(RowFilter.regexFilter(str));
	
}

//-----------------------------------------------------------------------------------------------------------------------------

public void searchenrollstd(String str) {
	/*
	 * This method is for searching course information in the jtable
	 */
	
	DefaultTableModel model = (DefaultTableModel)Student_info.getModel();
	TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
	Student_info.setRowSorter(trs);
	trs.setRowFilter(RowFilter.regexFilter(str));
	
}
}
