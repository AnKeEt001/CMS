package com.cms;
import java.lang.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.print.DocFlavor.STRING;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class Admin extends JFrame{

	protected static final String String = null;
	private JFrame frame;
	private JTable totalTeacher;
	private JTable totalStudent;
	private JTable totalCourse;
	private JTextField searchtext;
	private JTable Coursedetails_table;
	
	private JTextField tutorSearch;
	private JTextField searchStudent;
	private JTextField fullname;
	private JTextField email;
	private JTextField mbl_no;
	private JTextField newpassword;
	private String  emailfield;
	
	private JLabel adminname;
	private JTable detailstutor;
	private JTable detailsStudent;
	
	private ArrayList<String> totacourse = new ArrayList<>();
	private ArrayList<String> totaltutor = new ArrayList<>();
    private ArrayList<String> totalstudent = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void admin(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					

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
	public Admin(String emailfield) {
		

		initialize(emailfield);
		showinfo();
		showinfoTutor();
		showinfoStudent();
	}

	


	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(String emailfield) {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Admin.class.getResource("/img/RBS.png")));
		frame.setBounds(100, 100, 1147, 721);
		frame.setTitle("Course Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(99, 153, 163));
		menu.setBounds(0, 0, 227, 684);
		frame.getContentPane().add(menu);
		menu.setLayout(null);
		
		JLabel admintext = new JLabel("Admin ");
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
		
		Content.setBounds(225, 0, 908, 684);
		frame.getContentPane().add(Content);
		Content.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 908, 684);
		Content.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
//	===========================================================================================================================================
//		Dashboard Panel
		JPanel dashboardpanel = new JPanel();
		dashboardpanel.setBackground(new Color(207, 248, 222));
		layeredPane.add(dashboardpanel, "name_233468739105100");
		dashboardpanel.setLayout(null);
		
		JLabel countStudent = new JLabel("");
		countStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		countStudent.setBounds(504, 148, 132, 61);
		dashboardpanel.add(countStudent);
		
		JLabel countStudent_dis = new JLabel("  Total Student");
		countStudent_dis.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		countStudent_dis.setBounds(444, 77, 252, 61);
		dashboardpanel.add(countStudent_dis);
		
		JLabel countteacher = new JLabel("");
		countteacher.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		countteacher.setBounds(336, 397, 132, 61);
		dashboardpanel.add(countteacher);
		
		JLabel countTeacher_dis = new JLabel("  Total Tutor");
		countTeacher_dis.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		countTeacher_dis.setBounds(271, 326, 252, 61);
		dashboardpanel.add(countTeacher_dis);
		
		JLabel lblNewLabel = new JLabel("Dashboard");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblNewLabel.setBounds(279, 0, 185, 55);
		dashboardpanel.add(lblNewLabel);
		
		totalTeacher = new JTable();
		totalTeacher.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, SystemColor.info, null));
		totalTeacher.setBounds(271, 326, 252, 151);
		dashboardpanel.add(totalTeacher);
		
		totalStudent = new JTable();
		totalStudent.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, SystemColor.info, null));
		totalStudent.setBounds(444, 77, 252, 151);
		dashboardpanel.add(totalStudent);
		
		JLabel countCourse = new JLabel("");
		countCourse.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		countCourse.setBounds(77, 148, 132, 61);
		dashboardpanel.add(countCourse);
		
		JLabel countCourse_dis = new JLabel("  Total Course");
		countCourse_dis.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		countCourse_dis.setBounds(12, 77, 252, 61);
		dashboardpanel.add(countCourse_dis);
		
		totalCourse = new JTable();
		totalCourse.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, SystemColor.info, null));
		totalCourse.setBounds(12, 77, 252, 151);
		dashboardpanel.add(totalCourse);
		//***********************************************************************************************
		//This section is for displaying the total number of course, student and tutor by using arralist and iterator 
		String url3 = "jdbc:mysql://localhost:3306/course_management_system";
	    String username3 = "root";
	    String password3 = "";
	    
	    
	    try{
	        //load the driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        //create the connection
	        Connection connect = DriverManager.getConnection(url3,username3,password3);
	        //this query is for fetch course name
	        String q ="SELECT course_name FROM `course_info`";
	        Statement pstat3 =  connect.createStatement();
	        ResultSet r3 = pstat3.executeQuery(q);
	        
	        while(r3.next()){
	        	
	        	totacourse.add(r3.getString("course_name")); //added course name in arraylist variable
	        }
	        
	        //using iterator
	        java.util.Iterator<String> c = totacourse.iterator();
            int count = 0; //initializing count
            while ( c.hasNext()) {
                String condition = (String) c.next();
                count++;   //count the total number
            }
            
            totacourse.clear(); //clear for totally new to fetch
            countCourse.setText(String.valueOf(count)); //set the counted value in the jlabel
            
            //*************************************************************************************************
            
            //this query is for fetch tutorID for counting total tutor
            String q1 ="SELECT tutorID FROM tutor";
	        Statement p1 =  connect.createStatement();
	        ResultSet r1 = p1.executeQuery(q1);
	        
	        while(r1.next()){
	        	
	        	totaltutor.add(r1.getString("tutorID"));
	        }
	        
	        
	        java.util.Iterator<String> c1 = totaltutor.iterator();
            int count1 = 0; //initializing count
            while ( c1.hasNext()) {
                String condition1 = (String) c1.next();
                count1++;   
            }
            
            totaltutor.clear();
            countteacher.setText(String.valueOf(count1));
            
            //********************************************************************
            //this query is for fetch StudentId for counting the total number of student
            String q2 ="SELECT studentID FROM student";
	        Statement p2 =  connect.createStatement();
	        ResultSet r2 = p2.executeQuery(q2);
	        
	        while(r2.next()){
	        	
	        	totalstudent.add(r2.getString("studentID"));
	        }
	        
	        
	        java.util.Iterator<String> c2 = totalstudent.iterator();
            int count2 = 0; //initializing count
            while ( c2.hasNext()) {
                String condition2 = (String) c2.next();
                count2++;   
            }
            
            totalstudent.clear();
            countStudent.setText(String.valueOf(count2));
            
            

	        //close the Statement
	        connect.close();
	    }catch (Exception exp){
	        System.out.println(exp);
	    }
		
		
//		==============================================================================================================================================
//		Course Panel
		
		JPanel coursepanel = new JPanel();
		coursepanel.setBackground(new Color(207, 248, 222));
		layeredPane.add(coursepanel, "name_233628479463100");
		coursepanel.setLayout(null);
		
		JLabel lblCourses = new JLabel("Modules Details");
		lblCourses.setBounds(300, 6, 286, 40);
		lblCourses.setFont(new Font("Tahoma", Font.BOLD, 33));
		coursepanel.add(lblCourses);
		
//		******************************************************************************************************************************
		/*
		 * This section is for search field
		 */
		searchtext = new JTextField();
		
		searchtext.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		searchtext.setToolTipText("search by module name");
		searchtext.setBounds(145, 62, 305, 34);
		coursepanel.add(searchtext);
		searchtext.setColumns(10);
		
		
//	*******************************************************************************************************************************
		/*
		 * This section is for Course adding, which redirect to the AddCourse class
		 */
		JButton btnaddCourse = new JButton("Add Module");
		btnaddCourse.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				new AddModule();
			}
		});
		btnaddCourse.setForeground(new Color(0, 0, 0));
		btnaddCourse.setBackground(new Color(126, 194, 166));
		btnaddCourse.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnaddCourse.setBounds(468, 61, 118, 34);
		coursepanel.add(btnaddCourse);
		
//	********************************************************************************************************************************
		/*
		 * This section is for editing course, which redirect to the EditCourse class
		 */
		
		JButton btneditCourse = new JButton("Edit Module");
		btneditCourse.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				new EditModule();
			}
		});
		btneditCourse.setBackground(SystemColor.info);
		btneditCourse.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btneditCourse.setBounds(606, 60, 118, 36);
		coursepanel.add(btneditCourse);
		
//	********************************************************************************************************************************
		/*
		 * This section is for delete the course by module name from the database, module name coming from the searchtext.
		 */
		JButton btndeleteCourse = new JButton("Delete Module");
		btndeleteCourse.addActionListener(new ActionListener() {
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
			       
			        
				        String q = "Delete from course where module_name =?";  // query to remove the data
				        PreparedStatement pstat =  connect.prepareStatement(q); // prepared statement
				        pstat.setString(1, searchtext.getText());
				        
				        pstat.executeUpdate();  // delete the data in database
				        	
				        JOptionPane.showMessageDialog(null,"Delete Course Successfully");
				        pstat.close();
				        
				        searchtext.setText("");
				        DefaultTableModel model = (DefaultTableModel)Coursedetails_table.getModel();
						model.setRowCount(0); // set the row count 0
						search(searchtext.getText()); // to remove the row after delete data in jtable
				        
			        //close the Statement
			        connect.close();
			    }catch (Exception exp){
			        System.out.println(exp);
			    }
			}
		});
		btndeleteCourse.setForeground(new Color(0, 0, 0));
		btndeleteCourse.setBackground(new Color(255, 151, 151));
		btndeleteCourse.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btndeleteCourse.setBounds(742, 59, 141, 34);
		coursepanel.add(btndeleteCourse);
		
//	*********************************************************************************************************************************
		
		/*
		 * This section for Jscrollpanel where the data is display
		 */
		JScrollPane detailsCourse = new JScrollPane();
		detailsCourse.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.control, null, null));
		detailsCourse.setBounds(30, 170, 853, 430);
		coursepanel.add(detailsCourse);
		
		Coursedetails_table = new JTable();
		Coursedetails_table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Coursedetails_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Course Name", "No of Year", "Level", "Semester", "Module Code", "Module Name", "Optional" //set the heading column name
			}
		));
		Coursedetails_table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.control, null, null));
		detailsCourse.setViewportView(Coursedetails_table);
		
		JLabel lblNewLabel_1 = new JLabel("Course Details");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblNewLabel_1.setBounds(31, 135, 124, 34);
		coursepanel.add(lblNewLabel_1);
		
//		*************************************************************************************************************************************
		/*
		 * This section is used for refresh the course details table
		 */
		JButton btnrtefresh = new JButton("Refresh");
		btnrtefresh.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				if(searchtext.getText().isEmpty()) {
					
					search(searchtext.getText());
					DefaultTableModel model = (DefaultTableModel)Coursedetails_table.getModel(); //set the object of defaulttablemodel
					model.setRowCount(0); //set the row count 0
					showinfo(); //calling the function for show the data in jtable
				}else {
					
					DefaultTableModel model = (DefaultTableModel)Coursedetails_table.getModel(); //set the object of defaulttablemodel
					model.setRowCount(0); //set the row count 0
					showinfo(); //calling the function for show the data in jtable
				}
		
				
				
			}
		});
		btnrtefresh.setForeground(Color.BLACK);
		btnrtefresh.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnrtefresh.setBackground(SystemColor.controlHighlight);
		btnrtefresh.setBounds(698, 640, 118, 34);
		coursepanel.add(btnrtefresh);
		
		
//		**************************************************************************************************************************************
		/*
		 * this block is for search the course infromation in the jtable
		 */
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * This section help to search the course in the jtable by key released in search text field
				 */
				
				String Stext = searchtext.getText();
				search(Stext); // calling the search() method for search and passing the string for search
			}
		});
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnSearch.setBackground(SystemColor.inactiveCaption);
		btnSearch.setBounds(14, 62, 118, 34);
		coursepanel.add(btnSearch);
		
		JButton btnaddmodule = new JButton("Add Course");
		btnaddmodule.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				new AddCourse();
			}
		});
		btnaddmodule.setForeground(Color.BLACK);
		btnaddmodule.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnaddmodule.setBackground(SystemColor.controlHighlight);
		btnaddmodule.setBounds(32, 637, 118, 34);
		coursepanel.add(btnaddmodule);
		
//		==============================================================================================================================================
//		Tutor Panel
		JPanel tutorpanel = new JPanel();
		tutorpanel.setBackground(new Color(207, 248, 222));
		tutorpanel.setLayout(null);
		layeredPane.add(tutorpanel, "name_233715755737700");
		
		JLabel lblTutors = new JLabel("Tutors");
		lblTutors.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblTutors.setBounds(325, 0, 119, 40);
		tutorpanel.add(lblTutors);
		
		tutorSearch = new JTextField();
		tutorSearch.setToolTipText("Search");
		tutorSearch.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tutorSearch.setColumns(10);
		tutorSearch.setBounds(117, 64, 269, 34);
		tutorpanel.add(tutorSearch);
		
		JButton btndddTeacher = new JButton("Add Tutor");
		btndddTeacher.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				new AddTutor();
			}
		});
		btndddTeacher.setForeground(Color.BLACK);
		btndddTeacher.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btndddTeacher.setBackground(new Color(126, 194, 166));
		btndddTeacher.setBounds(399, 63, 128, 34);
		tutorpanel.add(btndddTeacher);
		
		JButton btneditTeacher = new JButton("Edit Tutor");
		btneditTeacher.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				new EditTutor();
			}
		});
		btneditTeacher.setBackground(SystemColor.info);
		btneditTeacher.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btneditTeacher.setBounds(552, 62, 126, 36);
		tutorpanel.add(btneditTeacher);
//*******************************************************************************************************************************************
		/*
		 * This section for deleting tutor
		 */
		JButton btndeleteTeacher = new JButton("Delete Teacher");
		btndeleteTeacher.addActionListener(new ActionListener() {
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
				       
				        
					        String q = "Delete from tutor where tutorID =?";  // query to remove the data
					        PreparedStatement pstat =  connect.prepareStatement(q); // prepared statement
					        pstat.setString(1, tutorSearch.getText());
					        
					        pstat.executeUpdate();  // delete the data in database
					        	
					        JOptionPane.showMessageDialog(null,"Delete Tutor Successfully");
					        pstat.close();
					        
					        tutorSearch.setText("");
					        DefaultTableModel model = (DefaultTableModel)detailstutor.getModel();
							model.setRowCount(0); // set the row count 0
							
							searchtutor(tutorSearch.getText()); // to remove the row after delete data in jtable
				        //close the Statement
				        connect.close();
				    }catch (Exception exp){
				        System.out.println(exp);
				    }
			}
		});
		btndeleteTeacher.setForeground(Color.BLACK);
		btndeleteTeacher.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btndeleteTeacher.setBackground(new Color(255, 151, 151));
		btndeleteTeacher.setBounds(707, 61, 143, 34);
		tutorpanel.add(btndeleteTeacher);
		
//*****************************************************************************************************************************		
		JLabel lblNewLabel_1_1 = new JLabel("Tutor Details");
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(27, 137, 124, 34);
		tutorpanel.add(lblNewLabel_1_1);
		
		JButton btnassignModule = new JButton("Assign Module");
		btnassignModule.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				 frame.setVisible(true);
				new ModuleAssign();
				
				
			}
		});
		btnassignModule.setForeground(Color.BLACK);
		btnassignModule.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnassignModule.setBackground(new Color(126, 194, 166));
		btnassignModule.setBounds(674, 628, 176, 34);
		tutorpanel.add(btnassignModule);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setBounds(27, 181, 826, 415);
		tutorpanel.add(scrollPane_1);
		
		detailstutor = new JTable();
		detailstutor.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "fullname", "tutorID", "email", "Mobile Number", "password"
			}
		));
		detailstutor.getColumnModel().getColumn(0).setPreferredWidth(56);
		detailstutor.getColumnModel().getColumn(1).setPreferredWidth(167);
		detailstutor.getColumnModel().getColumn(2).setPreferredWidth(54);
		detailstutor.getColumnModel().getColumn(3).setPreferredWidth(115);
		detailstutor.getColumnModel().getColumn(4).setPreferredWidth(90);
		detailstutor.getColumnModel().getColumn(5).setPreferredWidth(104);
		scrollPane_1.setViewportView(detailstutor);
		
//*************************************************************************************************************************		
		JButton btnsearch = new JButton("Search");
		btnsearch.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * This section help to search the course in the jtable by key released in search text field
				 */
				
				String Stext1 = tutorSearch.getText();
				searchtutor(Stext1); // calling the search() method for search and passing the string for search
			}
		});
		btnsearch.setForeground(Color.BLACK);
		btnsearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnsearch.setBackground(new Color(126, 194, 166));
		btnsearch.setBounds(10, 65, 97, 34);
		tutorpanel.add(btnsearch);
		
//***************************************************************************************************************************************
		/*
		 * This block for refresh the tutor jtable refresh
		 */
		JButton btnrefresh = new JButton("Refresh");
		btnrefresh.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				if(tutorSearch.getText().isEmpty()) {
					
					searchtutor(tutorSearch.getText());
					DefaultTableModel model2 = (DefaultTableModel)detailstutor.getModel(); //set the object of defaulttablemodel
					model2.setRowCount(0); //set the row count 0
					showinfoTutor(); //calling the function for show the data in jtable
				}else {
					
					DefaultTableModel model2 = (DefaultTableModel)detailstutor.getModel(); //set the object of defaulttablemodel
					model2.setRowCount(0); //set the row count 0
					showinfoTutor(); //calling the function for show the data in jtable
				}
				
					
				
				
				
			}
		});
		btnrefresh.setForeground(Color.BLACK);
		btnrefresh.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnrefresh.setBackground(SystemColor.menu);
		btnrefresh.setBounds(27, 626, 124, 34);
		tutorpanel.add(btnrefresh);
		
//		=========================================================================================================================================
//		Student Panel
		
		JPanel studentpanel = new JPanel();
		studentpanel.setBackground(new Color(207, 248, 222));
		studentpanel.setLayout(null);
		layeredPane.add(studentpanel, "name_233749033870100");
		
		JLabel lblStudents = new JLabel("Students");
		lblStudents.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblStudents.setBounds(320, 0, 157, 40);
		studentpanel.add(lblStudents);
		
		searchStudent = new JTextField();
		searchStudent.setToolTipText("Search");
		searchStudent.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		searchStudent.setColumns(10);
		searchStudent.setBounds(141, 64, 269, 34);
		studentpanel.add(searchStudent);
		
		JButton btnaddStudent = new JButton("Add Student");
		btnaddStudent.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				new AddStudent();
			}
		});
		btnaddStudent.setForeground(Color.BLACK);
		btnaddStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnaddStudent.setBackground(new Color(126, 194, 166));
		btnaddStudent.setBounds(440, 63, 128, 34);
		studentpanel.add(btnaddStudent);
		
		JButton btneditStudent = new JButton("Edit Student");
		btneditStudent.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				new EditStudent();
			}
		});
		btneditStudent.setBackground(SystemColor.info);
		btneditStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btneditStudent.setBounds(578, 62, 126, 36);
		studentpanel.add(btneditStudent);
//******************************************************************************************************************
		/*
		 * This section delete the student
		 */
		JButton btndeleteStudent = new JButton("Delete Student");
		btndeleteStudent.addActionListener(new ActionListener() {
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
			       
			        
				        String q = "Delete from student where studentID =?";  // query to remove the data
				        PreparedStatement pstat =  connect.prepareStatement(q); // prepared statement
				        pstat.setString(1, searchStudent.getText());
				        
				        pstat.executeUpdate();  // delete the data in database
				        	
				        JOptionPane.showMessageDialog(null,"Delete Student Successfully");
				        pstat.close();
				        
				        searchStudent.setText("");
				        DefaultTableModel model = (DefaultTableModel)detailsStudent.getModel();
						model.setRowCount(0); // set the row count 0
						
						searchstudent(searchStudent.getText()); // to remove the row after delete data in jtable
			        //close the Statement
			        connect.close();
			    }catch (Exception exp){
			        System.out.println(exp);
			    }
			}
		});
		btndeleteStudent.setForeground(Color.BLACK);
		btndeleteStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btndeleteStudent.setBackground(new Color(255, 151, 151));
		btndeleteStudent.setBounds(714, 61, 143, 34);
		studentpanel.add(btndeleteStudent);
		
// ********************************************************************************************************************		
		
		JLabel Student_details = new JLabel("Student Details");
		Student_details.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		Student_details.setBounds(28, 137, 135, 34);
		studentpanel.add(Student_details);
		
		JButton btnverifyReport = new JButton("View Report");
		btnverifyReport.setBackground(SystemColor.activeCaption);
		btnverifyReport.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				new Result();
				
				
			}
		});
		btnverifyReport.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnverifyReport.setBounds(718, 573, 143, 36);
		studentpanel.add(btnverifyReport);	
		
//********************************************************************************************************************************		
		JButton btnvVewReport = new JButton("Veiw Enrolled student");
		btnvVewReport.setBackground(SystemColor.inactiveCaptionBorder);
		btnvVewReport.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				new Enroll();
				
			}
		});
		btnvVewReport.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnvVewReport.setBounds(25, 568, 198, 36);
		studentpanel.add(btnvVewReport);
		
		//Jscrollpanel to display the student informations
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_2.setBounds(25, 181, 836, 328);
		studentpanel.add(scrollPane_2);
		
		detailsStudent = new JTable();
		detailsStudent.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "fullname", "studentID", "email", "mobile number", "password"
			}
		));
		detailsStudent.getColumnModel().getColumn(0).setPreferredWidth(63);
		detailsStudent.getColumnModel().getColumn(1).setPreferredWidth(179);
		detailsStudent.getColumnModel().getColumn(2).setPreferredWidth(61);
		detailsStudent.getColumnModel().getColumn(3).setPreferredWidth(142);
		detailsStudent.getColumnModel().getColumn(4).setPreferredWidth(102);
		detailsStudent.getColumnModel().getColumn(5).setPreferredWidth(90);
		scrollPane_2.setViewportView(detailsStudent);
		
//******************************************************************************************************************************	
		/*
		 * This section help to search the student
		 */
		JButton btnsearchStudent = new JButton("Search");
		btnsearchStudent.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * This section help to search the course in the jtable by key released in search text field
				 */
				
				String Stext1 = searchStudent.getText();
				searchstudent(Stext1); // calling the search() method for search and passing the string for search
			}
		});
		btnsearchStudent.setForeground(Color.BLACK);
		btnsearchStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnsearchStudent.setBackground(new Color(126, 194, 166));
		btnsearchStudent.setBounds(25, 63, 106, 34);
		studentpanel.add(btnsearchStudent);
		
//*****************************************************************************************************************************	
		/*
		 * This section help to refresh the student details jtable
		 */
		JButton btnrefreshstudent = new JButton("Refresh");
		btnrefreshstudent.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				if(searchStudent.getText().isEmpty()) {
					
					searchstudent(searchStudent.getText());
					DefaultTableModel model2 = (DefaultTableModel)detailsStudent.getModel(); //set the object of defaulttablemodel
					model2.setRowCount(0); //set the row count 0
					showinfoStudent(); //calling the function for show the data in jtable
					
				}else {
					
					DefaultTableModel model2 = (DefaultTableModel)detailsStudent.getModel(); //set the object of defaulttablemodel
					model2.setRowCount(0); //set the row count 0
					showinfoStudent(); //calling the function for show the data in jtable
				}
				
				DefaultTableModel model2 = (DefaultTableModel)detailsStudent.getModel(); //set the object of defaulttablemodel
				model2.setRowCount(0); //set the row count 0
				showinfoStudent(); //calling the function for show the data in jtable
			
			}
		});
		btnrefreshstudent.setForeground(Color.BLACK);
		btnrefreshstudent.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnrefreshstudent.setBackground(new Color(126, 194, 166));
		btnrefreshstudent.setBounds(407, 573, 128, 34);
		studentpanel.add(btnrefreshstudent);
		
//		======================================================================================================================================
//		Setting Panel
		
		JPanel settingpanel = new JPanel();
		settingpanel.setBackground(new Color(207, 248, 222));
		settingpanel.setLayout(null);
		layeredPane.add(settingpanel, "name_233785812080600");
		
		JLabel lblSetting = new JLabel("Settings");
		lblSetting.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblSetting.setBounds(299, 1, 147, 40);
		settingpanel.add(lblSetting);
		
		JLabel textgeneralProfile = new JLabel("General Profile");
		textgeneralProfile.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		textgeneralProfile.setBounds(23, 58, 134, 23);
		settingpanel.add(textgeneralProfile);
		
		JLabel textfullname = new JLabel("FullName");
		textfullname.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		textfullname.setBounds(29, 105, 67, 23);
		settingpanel.add(textfullname);
		
		fullname = new JTextField();
		fullname.setBounds(103, 101, 239, 32);
		settingpanel.add(fullname);
		fullname.setColumns(10);
		
		JLabel text_email = new JLabel("Email");
		text_email.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		text_email.setBounds(423, 104, 44, 23);
		settingpanel.add(text_email);
		
		JLabel text_mbl_no = new JLabel("Mobile no.");
		text_mbl_no.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		text_mbl_no.setBounds(24, 157, 76, 23);
		settingpanel.add(text_mbl_no);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(469, 100, 239, 32);
		settingpanel.add(email);
		
		mbl_no = new JTextField();
		mbl_no.setColumns(10);
		mbl_no.setBounds(107, 152, 239, 32);
		settingpanel.add(mbl_no);
//***********************************************************************************************		
		JButton btn_edit_profile = new JButton("Edit Profile");
		btn_edit_profile.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				//this for edit the profile
				
				String fullnamecheck = fullname.getText();
				
				String Mblnocheck = mbl_no.getText();
				String emailcheck = email.getText();
				
				
				String fullnameRegex = "([a-zA-Z]+)\s([a-zA-Z]+){4,30}";
				
				
				
				//Regex for mobile number check i.e. exactly 10 digits number
				String mblnoregex = "\\d{10}";
				
				//Regex for email format check, Somthings before @ and somethings after @
				String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
						"[a-zA-Z0-9+&*-]+)*@"+
						"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
				
				Pattern patternfullname = Pattern.compile(fullnameRegex);
				
				Pattern patternmblno = Pattern.compile(mblnoregex);
				Pattern patternemail = Pattern.compile(emailRegex);
				
				//according to the pattern of any than it allow to update or it should be matches all the patterh to update else don't allow to update
				if(patternfullname.matcher(fullnamecheck).matches() && patternmblno.matcher(Mblnocheck).matches() && patternemail.matcher(emailcheck).matches() ) {
					
					String url = "jdbc:mysql://localhost:3306/course_management_system";
				    String username = "root";
				    String password = "";
				    
				   
				    try{
			            //load the driver
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            //create the connection
			            Connection connect = DriverManager.getConnection(url,username,password);
			            
		            	String query1 = "UPDATE admin SET fullname=?"
		            			+ ",email=?, mblno=? WHERE email =?";
			            PreparedStatement pstate1 = connect.prepareStatement(query1);
			            pstate1.setString(1, fullname.getText());
			            pstate1.setString(2, email.getText());
			            pstate1.setString(3, mbl_no.getText());
			            pstate1.setString(4, emailfield);
			            
			            pstate1.executeUpdate();
			            
			            JOptionPane.showMessageDialog(null, "updated succefully!");
		            	
			            pstate1.close();
			            fullname.setText("");
			            email.setText("");
			            mbl_no.setText("");
			            
			            //close the Statement
			            connect.close();
				    
			        }catch (Exception exp){
			            System.out.println(exp);
			        }
				}else if(patternemail.matcher(emailcheck).matches()) {
					
					String url = "jdbc:mysql://localhost:3306/course_management_system";
				    String username = "root";
				    String password = "";
				    
				   
				    try{
			            //load the driver
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            //create the connection
			            Connection connect = DriverManager.getConnection(url,username,password);
			            
		            	String query1 = "UPDATE admin SET email=? WHERE email =?";
			            PreparedStatement pstate1 = connect.prepareStatement(query1);
			            
			            pstate1.setString(1, email.getText());
			           
			            pstate1.setString(2, emailfield);
			            
			            pstate1.executeUpdate();
			            
			            JOptionPane.showMessageDialog(null, "Email updated succefully!");
		            	
			            pstate1.close();
			            
			            email.setText("");
			            
			            
			            //close the Statement
			            connect.close();
				    
			        }catch (Exception exp){
			            System.out.println(exp);
			        }
				}else if(patternmblno.matcher(Mblnocheck).matches()) {
					
					String url = "jdbc:mysql://localhost:3306/course_management_system";
				    String username = "root";
				    String password = "";
				    
				   
				    try{
			            //load the driver
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            //create the connection
			            Connection connect = DriverManager.getConnection(url,username,password);
			            
		            	String query1 = "UPDATE admin SET mblno=? WHERE email =?";
			            PreparedStatement pstate1 = connect.prepareStatement(query1);
			            
			            pstate1.setString(1, mbl_no.getText());
			           
			            pstate1.setString(2, emailfield);
			            
			            pstate1.executeUpdate();
			            
			            JOptionPane.showMessageDialog(null, "Mobile Number updated succefully!");
		            	
			            pstate1.close();
			            
			            mbl_no.setText("");
			            
			            
			            //close the Statement
			            connect.close();
				    
			        }catch (Exception exp){
			            System.out.println(exp);
			        }
				}else if(patternfullname.matcher(fullnamecheck).matches()){
					
					String url = "jdbc:mysql://localhost:3306/course_management_system";
				    String username = "root";
				    String password = "";
				    
				   
				    try{
			            //load the driver
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            //create the connection
			            Connection connect = DriverManager.getConnection(url,username,password);
			            
		            	String query1 = "UPDATE admin SET fullname=? WHERE email =?";
			            PreparedStatement pstate1 = connect.prepareStatement(query1);
			            
			            pstate1.setString(1, fullname.getText());
			           
			            pstate1.setString(2, emailfield);
			            
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
					
					JOptionPane.showMessageDialog(null, "Please, Check fullname(firstname and lastname required),"
							+ "mobile number and email format is not match\"");
				}
				
			}
		});
		btn_edit_profile.setBackground(SystemColor.info);
		btn_edit_profile.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btn_edit_profile.setBounds(23, 213, 121, 32);
		settingpanel.add(btn_edit_profile);
//************************************************************************************************		
		JLabel textSecurity = new JLabel("Security & Login");
		textSecurity.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		textSecurity.setBounds(23, 274, 140, 23);
		settingpanel.add(textSecurity);
		
		newpassword = new JTextField();
		newpassword.setColumns(10);
		newpassword.setBounds(164, 329, 239, 32);
		settingpanel.add(newpassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewPassword.setBounds(53, 333, 112, 23);
		settingpanel.add(lblNewPassword);
		
		JLabel lblNewPasswordEmpty = new JLabel("Required New Password");
		lblNewPasswordEmpty.setVisible(false);
		lblNewPasswordEmpty.setForeground(Color.RED);
		lblNewPasswordEmpty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewPasswordEmpty.setBounds(412, 332, 215, 23);
		settingpanel.add(lblNewPasswordEmpty);
		
		
		JButton changePassword = new JButton("Change Password");
		changePassword.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * New Password text field is empty or not for change the password,
				 * if any of the fields are empty than display the message as error
				 */
				
				 if(newpassword.getText().isEmpty()) {
					lblNewPasswordEmpty.setVisible(true);
				}else {
					
					String  passwordcheck = newpassword.getText();
					//Regex for password format check, exactly one special character but exclude +- charater
					String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?!.*[+-])"
							+ "(?=[^!@#$*%_]*[!@#*$%_][^!@#*$%_]*$)[a-zA-Z0-9!@#*$%_]{8,}$";
					
					Pattern patternpassword = Pattern.compile(passwordRegex);
					
					if(patternpassword.matcher(passwordcheck).matches()) {
						
						String url = "jdbc:mysql://localhost:3306/course_management_system";
					    String username = "root";
					    String password = "";
					    
					   
					    try{
				            //load the driver
				            Class.forName("com.mysql.cj.jdbc.Driver");
				            //create the connection
				            Connection connect = DriverManager.getConnection(url,username,password);
				            
				            //for update the password of admin
			            	String query1 = "UPDATE admin SET password=? WHERE email =?";
				            PreparedStatement pstate1 = connect.prepareStatement(query1);
				            pstate1.setString(1, newpassword.getText());
				            pstate1.setString(2, emailfield);
				          
				            pstate1.executeUpdate();
				            
				            JOptionPane.showMessageDialog(null, "password updated!");
			            	
				            pstate1.close();
				            newpassword.setText("");
				            
				            //close the Statement
				            connect.close();
					    
				        }catch (Exception exp){
				            System.out.println(exp);
				        }
					}else {
						JOptionPane.showMessageDialog(null, "password is not match, include at least one Capital Letter"
								+ "one special character exculde '+-' up to 12 character");
					}
				}
			}
		});
		changePassword.setBackground(new Color(22, 209, 112));
		changePassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		changePassword.setBounds(23, 393, 158, 32);
		settingpanel.add(changePassword);
//*******************************************************************************************************		
		JSeparator separator = new JSeparator();
		separator.setBounds(23, 262, 685, 2);
		settingpanel.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(23, 83, 124, 2);
		settingpanel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(23, 301, 134, 2);
		settingpanel.add(separator_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(23, 478, 685, 2);
		settingpanel.add(separator_1);
		
		JLabel texthelp = new JLabel("Help");
		texthelp.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		texthelp.setBounds(24, 494, 50, 23);
		settingpanel.add(texthelp);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(23, 519, 44, 2);
		settingpanel.add(separator_4);
		
		JLabel lblhelp = new JLabel("For any help and support, contact to collage administration");
		lblhelp.setForeground(new Color(128, 64, 64));
		lblhelp.setBackground(new Color(0, 128, 192));
		lblhelp.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblhelp.setBounds(20, 529, 401, 23);
		settingpanel.add(lblhelp);
		
		JLabel lblEmailHeraldcollageadminedunp = new JLabel("Email: RenaissanceboardingAdmin@edu.np");
		lblEmailHeraldcollageadminedunp.setForeground(new Color(0, 0, 0));
		lblEmailHeraldcollageadminedunp.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEmailHeraldcollageadminedunp.setBackground(new Color(0, 128, 192));
		lblEmailHeraldcollageadminedunp.setBounds(56, 564, 300, 23);
		settingpanel.add(lblEmailHeraldcollageadminedunp);
		
		JLabel lblTelNumber = new JLabel("Tel number: 046 540 410");
		lblTelNumber.setForeground(Color.BLACK);
		lblTelNumber.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTelNumber.setBackground(new Color(0, 128, 192));
		lblTelNumber.setBounds(56, 595, 179, 23);
		settingpanel.add(lblTelNumber);
		
		
//		========================================================================================================================================
//		Profile Panel
		JPanel profilepanel = new JPanel();
		profilepanel.setLayout(null);
		profilepanel.setBackground(new Color(207, 248, 222));
		layeredPane.add(profilepanel, "name_235492695497500");
		
		JLabel lblProfile = new JLabel("Profile");
		lblProfile.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblProfile.setBounds(297, 10, 123, 40);
		profilepanel.add(lblProfile);
		
		JLabel lblFullname = new JLabel("FullName:");
		lblFullname.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblFullname.setBounds(40, 108, 75, 23);
		profilepanel.add(lblFullname);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblEmail.setBounds(40, 147, 56, 23);
		profilepanel.add(lblEmail);
		
		JLabel lblMobileNo = new JLabel("Mobile no.:");
		lblMobileNo.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblMobileNo.setBounds(40, 201, 92, 23);
		profilepanel.add(lblMobileNo);
		
		JLabel showfullname = new JLabel("");
		showfullname.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		showfullname.setBounds(125, 108, 227, 23);
		profilepanel.add(showfullname);
		
		JLabel showemail = new JLabel("");
		showemail.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		showemail.setBounds(90, 147, 227, 23);
		profilepanel.add(showemail);
		
		JLabel showMbl_no = new JLabel("");
		showMbl_no.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		showMbl_no.setBounds(133, 201, 114, 23);
		profilepanel.add(showMbl_no);
		
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
		btnlogout.setBounds(39, 263, 95, 27);
		profilepanel.add(btnlogout);
		
//		*************************************************************************************************************************
		//This part is for diplaying the full name in the menu panel
		JLabel adminname = new JLabel();
		
		String url = "jdbc:mysql://localhost:3306/course_management_system";
	    String username = "root";
	    String password = "";
	    
	    try{
		        //load the driver
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        //create the connection
		        Connection connect = DriverManager.getConnection(url,username,password);
		        
		        String query = "select fullname from admin  where email = ?";
		        PreparedStatement pstat =  connect.prepareStatement(query);
		        pstat.setString(1, emailfield);
	
		        ResultSet rs = pstat.executeQuery();
		        
		        if(rs.next()) {
		        	
		        	String nameprofile = rs.getString("fullname");
		        	adminname.setText(nameprofile);
		        }else {
		        	
		        }
	        	pstat.close();
	        	//close the Statement
		        connect.close();
		    }catch (Exception exp){
		        System.out.println(exp);
		    }
		adminname.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//helps to changes the layed panel
				layeredPane.removeAll();
				layeredPane.add(profilepanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
//		*****************************************************************************************************************************
		
		adminname.setHorizontalAlignment(SwingConstants.CENTER);
		adminname.setForeground(new Color(255, 255, 255));
		adminname.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		adminname.setBounds(10, 44, 207, 32);
		menu.add(adminname);
		
		
		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				//helps to changes the layed panel
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
				//helps to changes the layed panel
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
				//helps to changes the layed panel
				layeredPane.removeAll();
				layeredPane.add(tutorpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btntutors.setBackground(new Color(192, 192, 192));
		btntutors.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btntutors.setBounds(28, 444, 177, 36);
		menu.add(btntutors);
		
		JButton btnstudents = new JButton("Students");
		btnstudents.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				//helps to changes the layed panel
				layeredPane.removeAll();
				layeredPane.add(studentpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnstudents.setBackground(new Color(192, 192, 192));
		btnstudents.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnstudents.setBounds(28, 504, 177, 36);
		menu.add(btnstudents);
		
		JButton btnsettings = new JButton("Settings");
		btnsettings.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				//helps to changes the layed panel
				layeredPane.removeAll();
				layeredPane.add(settingpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnsettings.setBackground(new Color(192, 192, 192));
		btnsettings.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnsettings.setBounds(28, 563, 177, 36);
		menu.add(btnsettings);
		
		
		//****************************************************************************************
		
		// this for displaying the details in the profile pansel as profile
		String url4 = "jdbc:mysql://localhost:3306/course_management_system";
	    String username4 = "root";
	    String password4 = "";
	    
	    try{
		        //load the driver
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        //create the connection
		        Connection connect4 = DriverManager.getConnection(url4,username4,password4);
		        
		        String qry ="SELECT fullname, email, mblno  FROM admin WHERE email = ?";
		        PreparedStatement pstat =  connect4.prepareStatement(qry);
		        pstat.setString(1, emailfield);
		        ResultSet rsm = pstat.executeQuery();
		        
		        if(rsm.next()) {
		        	
		        	showfullname.setText(rsm.getString("fullname"));
		        	showemail.setText(emailfield);
		        	showMbl_no.setText(rsm.getString("mblno"));	
		        }
		        
		        
	    }catch (Exception exp){
	        System.out.println(exp);
	    }
		
		
		
		frame.setVisible(true);
	
		
		
	}

//---------------------------------------------------------------------------------------------------
 /*
  * This is for loading and fetch from the database to show course information in the jtable
  */

public void showinfo() {

	String url = "jdbc:mysql://localhost:3306/course_management_system";
    String username = "root";
    String password = "";
    
    
    try{
        //load the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //create the connection
        Connection connect = DriverManager.getConnection(url,username,password);
        
        //decalared the and initilized the string variables
        String ID = "";
        String course_name = "";
        String no_of_years = "";
        String level  = "";
        String semester = "";
        String module_code = "";
        String module_name = "";
        String optional = "";
        
        String q = "Select * from course"; //query to fetch all information of course from course table
        PreparedStatement pstat =  connect.prepareStatement(q); //prepared Statement

        ResultSet rs = pstat.executeQuery();   
        
        
        DefaultTableModel model = (DefaultTableModel)Coursedetails_table.getModel(); //creating object the defaulttablemodel
        
        
        while(rs.next()) {
        	
        	//storing the fetched data in the variables
        	ID = rs.getString("ID");
        	course_name = rs.getString("course_name");
        	no_of_years = rs.getString("no_of_years");
        	level = rs.getString("level");
        	semester = rs.getString("semester");
        	module_code = rs.getString("module_code");
        	module_name = rs.getString("module_name");
        	optional = rs.getString("optional");
        	
        	//storing the variables data in the array
        	String[] row = {ID, course_name, no_of_years,level,semester, module_code, module_name,optional};
        	model.addRow(row); //adding array as a row in the jscrollpanel 

        }
        	
        pstat.close(); //close the parepared statement
       

        //close the Statement
        connect.close();
    }catch (Exception exp){
        System.out.println(exp);
    }   
}

// ----------------------------------------------------------------------------------------------------------------------------------
public void showinfoTutor() {
	
	/*
	  * This is for loading and fetch from the database to show tutor information in the jtable
	  */
	
	String url = "jdbc:mysql://localhost:3306/course_management_system";
    String username = "root";
    String password = "";
    
    
    try{
        //load the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //create the connection
        Connection connect = DriverManager.getConnection(url,username,password);
        
      //decalared the and initilized the string variables
        String ID = "";
        String full_name = "";
        String tutor_ID = "";
        String email  = "";
        String mbl_no = "";
        String passsword = "";
        
        String q = "Select * from tutor"; //query to fetch all information of tutor from tutor table
        PreparedStatement pstat =  connect.prepareStatement(q); //prepared Statement

        ResultSet rs = pstat.executeQuery();  
        
        
        DefaultTableModel model1 = (DefaultTableModel)detailstutor.getModel();  //creating object the defaulttablemodel
        
        
        while(rs.next()) {
        	
        	//storing the fetched data in the variables
        	ID = rs.getString("ID");
        	full_name = rs.getString("fullname");
        	tutor_ID = rs.getString("tutorID");
        	email = rs.getString("email");
        	mbl_no = rs.getString("mblNo");
        	passsword = rs.getString("password");
        	
        	//storing the variables data in the array
        	String[] row = {ID, full_name, tutor_ID,email,mbl_no, passsword};
        	model1.addRow(row); //adding array as a row in the jscrollpanel 

        }
        	
        pstat.close();
        
        

        //close the Statement
        connect.close();
    }catch (Exception exp){
        System.out.println(exp);
    }
	
}

//----------------------------------------------------------------------------------------------------------------------

public void showinfoStudent() {
	
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
        String full_name = "";
        String Student_ID = "";
        String email  = "";
        String mbl_no = "";
        String passsword = "";
        
        String q = "Select * from student"; //query to fetch all information of student from student table
        PreparedStatement pstat =  connect.prepareStatement(q);

        ResultSet rs = pstat.executeQuery();  
        
        
        DefaultTableModel model1 = (DefaultTableModel)detailsStudent.getModel();
        
        
        while(rs.next()) {
        	
        	//decalared the and initilized the string variables
        	ID = rs.getString("ID");
        	full_name = rs.getString("fullname");
        	Student_ID = rs.getString("studentID");
        	email = rs.getString("email");
        	mbl_no = rs.getString("mblNo");
        	passsword = rs.getString("password");
        	
        	//storing the variables data in the array
        	String[] row = {ID, full_name, Student_ID,email,mbl_no, passsword};
        	model1.addRow(row);  //adding array as a row in the jscrollpanel 

        }
        	
        pstat.close(); //close the prepared Statement
        
        

        //close the Statement
        connect.close();
    }catch (Exception exp){
        System.out.println(exp);
    }
	
}

//-----------------------------------------------------------------------------------------------------------------------
public void search(String str) {
	/*
	 * This method is for searching course information in the jtable
	 */
	
	DefaultTableModel model = (DefaultTableModel)Coursedetails_table.getModel();
	TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
	Coursedetails_table.setRowSorter(trs);
	trs.setRowFilter(RowFilter.regexFilter(str));
	
}

//----------------------------------------------------------------------------------------------------------------------

public void searchtutor(String str) {
	/*
	 * This method is for searching tutor information in the jtable
	 */
	
	DefaultTableModel model = (DefaultTableModel)detailstutor.getModel();
	TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
	detailstutor.setRowSorter(trs);
	trs.setRowFilter(RowFilter.regexFilter(str));
	
}

//-----------------------------------------------------------------------------------------------------------------------
public void searchstudent(String str) {
	/*
	 * This method is for searching student information in the jtable
	 */
	
	DefaultTableModel model = (DefaultTableModel)detailsStudent.getModel();
	TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
	detailsStudent.setRowSorter(trs);
	trs.setRowFilter(RowFilter.regexFilter(str));
	
}


}
