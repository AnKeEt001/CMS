package com.cms;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import com.mysql.cj.api.jdbc.Statement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ModuleAssign extends JFrame{

	private JFrame frame;
	private JTextField tutorID;
	private JTextField tutorName;
	private JTextField searchtext;
	private JTable detailstable;

	/**
	 * Launch the application.
	 */
	public static void moduleassign(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModuleAssign window = new ModuleAssign();
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
	public ModuleAssign() {
		initialize();
		showinfo();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ModuleAssign.class.getResource("/img/RBS.png")));
		frame.setTitle("Course Management System");
		frame.getContentPane().setBackground(new Color(223, 247, 228));
		frame.setBounds(100, 100, 912, 759);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(223, 247, 228));
		panel.setBounds(2, 0, 898, 722);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
//		*************************************************************************************************************************************************
		/*
		 * This section is for showing the register module name in the jcombobox by fetching from the database
		 */
		
		JComboBox<String> moduleName = new JComboBox<String>();
		moduleName.setBounds(234, 184, 193, 21);
		panel.add(moduleName);
		
		String url = "jdbc:mysql://localhost:3306/course_management_system";
	    String username = "root";
	    String password = "";
	    
	    try{
            //load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create the connection
            Connection connect = DriverManager.getConnection(url,username,password);
               
            String query = "select module_name from course"; //query to get all the module name from course
            Statement state = (Statement) connect.createStatement();
            ResultSet rs = state.executeQuery(query);
            
           while(rs.next()) {
        	   String NameModule = rs.getString("module_name"); //getting module name from database
        	   moduleName.addItem(NameModule); // adding the fetching module name in jcombobox
           }

            state.close();
            //close the Statement
            connect.close();
        }catch (Exception exp){
            System.out.println(exp);
        }
	    
	    
//		***********************************************************************************************************************************************************
	     /* 
	      * This block is for radion butttons
	      */
		JRadioButton rdbtnModuleLeader = new JRadioButton("Module Leader");
		rdbtnModuleLeader.setBackground(new Color(223, 247, 228));
		rdbtnModuleLeader.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		rdbtnModuleLeader.setBounds(187, 252, 120, 21);
		panel.add(rdbtnModuleLeader);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPosition.setBounds(122, 244, 64, 36);
		panel.add(lblPosition);
		
		JRadioButton rdbtnAsistantModuleLeader = new JRadioButton("Asistant Module Leader");
		rdbtnAsistantModuleLeader.setBackground(new Color(223, 247, 228));
		rdbtnAsistantModuleLeader.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		rdbtnAsistantModuleLeader.setBounds(319, 250, 163, 21);
		panel.add(rdbtnAsistantModuleLeader);
		
		JRadioButton rdbtnTutor1 = new JRadioButton("Tutor1");
		rdbtnTutor1.setBackground(new Color(223, 247, 228));
		rdbtnTutor1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		rdbtnTutor1.setBounds(500, 249, 65, 21);
		panel.add(rdbtnTutor1);
		
		JRadioButton rdbtnTutor2 = new JRadioButton("Tutor2");
		rdbtnTutor2.setBackground(new Color(223, 247, 228));
		rdbtnTutor2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		rdbtnTutor2.setBounds(585, 248, 65, 21);
		panel.add(rdbtnTutor2);
		
		JRadioButton rdbtnTutor3 = new JRadioButton("Tutor3");
		rdbtnTutor3.setBackground(new Color(223, 247, 228));
		rdbtnTutor3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		rdbtnTutor3.setBounds(668, 247, 65, 21);
		panel.add(rdbtnTutor3);
		
	//***************************************************************************************************************************
		
		JLabel lblModuleNamel = new JLabel("Module Name");
		lblModuleNamel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblModuleNamel.setBounds(122, 174, 102, 36);
		panel.add(lblModuleNamel);
		
		JLabel lblModuleAssigned = new JLabel("Module Assigned");
		lblModuleAssigned.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblModuleAssigned.setBounds(307, 5, 211, 36);
		panel.add(lblModuleAssigned);
		
		JLabel lblTutorID = new JLabel("Tutor ID");
		lblTutorID.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblTutorID.setBounds(122, 114, 81, 36);
		panel.add(lblTutorID);
		
		tutorID = new JTextField();
		tutorID.setColumns(10);
		tutorID.setBounds(199, 118, 152, 30);
		panel.add(tutorID);
		
		JLabel lblTutorName = new JLabel("Tutor Name");
		lblTutorName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblTutorName.setBounds(405, 114, 102, 36);
		panel.add(lblTutorName);
		
		tutorName = new JTextField();
		tutorName.setColumns(10);
		tutorName.setBounds(506, 118, 211, 30);
		panel.add(tutorName);
		
//=============================================================================================================================================================================
		/*
		 * This Section is for inserting the data into database i.e. module assigned regiter
		 */
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(new Color(165, 245, 199));
		btnSubmit.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * To insert, first check the tutor Name and tutor ID field is empty or not if empty it will display the massage else next step,
				 *  it will check the register tutor :if not it will display the message else it will check the radio button to select one only 
				 *  if not it will display the message else then it allow to upadte in the database
				 */
				
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		               
		            String query = "select fullname, tutorID  from tutor  where fullname =? and tutorID =? ";  //query to check whether the tutor is register or not
		            PreparedStatement pstate = connect.prepareStatement(query);
		            pstate.setString(1, tutorName.getText());
		            pstate.setString(2, tutorID.getText());
		            ResultSet rs = pstate.executeQuery();
		            
		          //check the requird field is empty or not
		            if(tutorName.getText().isEmpty() && tutorID.getText().isEmpty()) {
		            	JOptionPane.showMessageDialog(null, "!! Required Tutor Name and Tutor ID!!");
		            	
		            }else if(tutorName.getText().isEmpty()) {
		            	JOptionPane.showMessageDialog(null, "!! Required Tutor Name !!");
		            	
		            }else if(tutorID.getText().isEmpty()) {
		            	JOptionPane.showMessageDialog(null, "!! Required Tutor ID !!");
		            }else {
		            
		            
		//*******************************************************************************************************************************************************************************************************************************
				           if(rs.next()) {
				        	   
				        	 //if tutor is register than this section will execute
				        	   String qu = "select course_name, level, semester, module_code, optional  from course  where module_name =?"; //query to get the information of course details by user selecting module name
				        	// to insert in the module assign table the above query is executed
					            PreparedStatement pstate1 = connect.prepareStatement(qu); //pepared Statement
					            pstate1.setString(1, (String) moduleName.getSelectedItem()); //setting the string by getting the selected item from the Jcombobox
					            ResultSet rsm = pstate1.executeQuery();
					            
					            if(rsm.next()) {
					            	
					            	// fetching from the database and storing in the variables
					            	String namecourse = rsm.getString("course_name");
					            	String leveltype  = rsm.getString("level");
					            	String semestertype = rsm.getString("semester");
					            	String codemodule = rsm.getString("module_code");
					            	String optionaltype = rsm.getString("optional");
					            	
		//	****************************************************************************************************************************************************************************************************************************   
					            	//check the radio buttons to select one only for assign the tutor position
					            	
					            	if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }
						            
						            //******************************************************************************************************************
						            
						            
						            else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            }
						            
						            //***********************************************************************************************************
						            
						            
						            else if(rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            }
						            
						            //*****************************************************************************************************************
						            
						            else if(rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            }
						            		            
						            
						            
			//***************************************************************************************************************************************
						            
						            else if(rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            }
						            
						            		            
						            
		    //***************************************************************************************************************************************
						            
						            else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            }
			//*****************************************************************************************************************************************************************
						            else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }
						            
						            
						            
		    //****************************************************************************************************************************************
						            else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }
						            
						            
						            
		    //*****************************************************************************************************************************************
						            
						            
						            
						            else if(rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }
						            
						            
						            
						            
		    //******************************************************************************************************************************************
						            else if(rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }
						            
						            
						            
						            
		    //******************************************************************************************************************************************
						            
						            
						            else if(rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }
						            		            
					            	//if the radio button is selected one only than the query for update executes according to selected radio button
					            	
		    //******************************************************************************************************************************************
						           else if(rdbtnModuleLeader.isSelected()) {
							            	String quer = " insert into module_assigned (TutorName, TutorID, course_name, level, semester, "
								            		+ "module_code, module_name, optional, position)"
								            		+ "values (?,?,?,?,?,?,?,?,?)";
								            PreparedStatement pstate2 = connect.prepareStatement(quer);
								            pstate2.setString(1, tutorName.getText());
								            pstate2.setString(2, tutorID.getText());
								            pstate2.setString(3, namecourse);
								            pstate2.setString(4, leveltype);
								            pstate2.setString(5, semestertype);
								            pstate2.setString(6, codemodule);
								            pstate2.setString(7, (String) moduleName.getSelectedItem());
								            pstate2.setString(8, optionaltype);
								            pstate2.setString(9, "Module Leader");
								            
								            pstate2.executeUpdate();
								            
								            JOptionPane.showMessageDialog(null, "Module is assigned Successfully.");
								           
								            tutorName.setText("");
								            tutorID.setText("");
								            rdbtnModuleLeader.setSelected(false);
								            
								         // for refreshing the table
								            DefaultTableModel model = (DefaultTableModel)detailstable.getModel();
											model.setRowCount(0); //set row count 0
											showinfo(); // calling the function
						            
						            }else if(rdbtnAsistantModuleLeader.isSelected()){
						            	
							            	String quer = " insert into module_assigned (TutorName, TutorID, course_name, level, semester, "
								            		+ "module_code, module_name, optional, position)"
								            		+ "values (?,?,?,?,?,?,?,?,?)";
								            PreparedStatement pstate3 = connect.prepareStatement(quer);
								            pstate3.setString(1, tutorName.getText());
								            pstate3.setString(2, tutorID.getText());
								            pstate3.setString(3, namecourse);
								            pstate3.setString(4, leveltype);
								            pstate3.setString(5, semestertype);
								            pstate3.setString(6, codemodule);
								            pstate3.setString(7, (String) moduleName.getSelectedItem());
								            pstate3.setString(8, optionaltype);
								            pstate3.setString(9, "Asistant Module Leader");
								            
								            pstate3.executeUpdate();
								            JOptionPane.showMessageDialog(null, "Module is assigned Successfully.");
								            
								            tutorName.setText("");
								            tutorID.setText("");
								            rdbtnAsistantModuleLeader.setSelected(false);
								            
								         // for refreshing the table
								            DefaultTableModel model = (DefaultTableModel)detailstable.getModel();
											model.setRowCount(0); //set row count 0
											showinfo(); //calling the function
								            
						            }else if(rdbtnTutor1.isSelected()) {
							            	String quer = " insert into module_assigned (TutorName, TutorID, course_name, level, semester, "
								            		+ "module_code, module_name, optional, position)"
								            		+ "values (?,?,?,?,?,?,?,?,?)";
								            PreparedStatement pstate4 = connect.prepareStatement(quer);
								            pstate4.setString(1, tutorName.getText());
								            pstate4.setString(2, tutorID.getText());
								            pstate4.setString(3, namecourse);
								            pstate4.setString(4, leveltype);
								            pstate4.setString(5, semestertype);
								            pstate4.setString(6, codemodule);
								            pstate4.setString(7, (String) moduleName.getSelectedItem());
								            pstate4.setString(8, optionaltype);
								            pstate4.setString(9, "Tutor1");
								            
								            pstate4.executeUpdate();
								            JOptionPane.showMessageDialog(null, "Module is assigned Successfully.");
								            
								            tutorName.setText("");
								            tutorID.setText("");
								            rdbtnTutor1.setSelected(false);
								            
								         // for refreshing the table
								            DefaultTableModel model = (DefaultTableModel)detailstable.getModel();
											model.setRowCount(0);
											showinfo();
						            	
						            }else if(rdbtnTutor2.isSelected()) {
							            	String quer = " insert into module_assigned (TutorName, TutorID, course_name, level, semester, "
								            		+ "module_code, module_name, optional, position)"
								            		+ "values (?,?,?,?,?,?,?,?,?)";
								            PreparedStatement pstate5 = connect.prepareStatement(quer);
								            pstate5.setString(1, tutorName.getText());
								            pstate5.setString(2, tutorID.getText());
								            pstate5.setString(3, namecourse);
								            pstate5.setString(4, leveltype);
								            pstate5.setString(5, semestertype);
								            pstate5.setString(6, codemodule);
								            pstate5.setString(7, (String) moduleName.getSelectedItem());
								            pstate5.setString(8, optionaltype);
								            pstate5.setString(9, "Tutor2");
								            
								            pstate5.executeUpdate();
								            JOptionPane.showMessageDialog(null, "Module is assigned Successfully.");
								            
								            tutorName.setText("");
								            tutorID.setText("");
								            rdbtnTutor2.setSelected(false);
								            
								         // for refreshing the table
								            DefaultTableModel model = (DefaultTableModel)detailstable.getModel();
											model.setRowCount(0);
											showinfo();
						            	
						            }else if(rdbtnTutor3.isSelected()) {
							            	String quer = " insert into module_assigned (TutorName, TutorID, course_name, level, semester, "
								            		+ "module_code, module_name, optional, position)"
								            		+ "values (?,?,?,?,?,?,?,?,?)";
								            PreparedStatement pstate6 = connect.prepareStatement(quer);
								            pstate6.setString(1, tutorName.getText());
								            pstate6.setString(2, tutorID.getText());
								            pstate6.setString(3, namecourse);
								            pstate6.setString(4, leveltype);
								            pstate6.setString(5, semestertype);
								            pstate6.setString(6, codemodule);
								            pstate6.setString(7, (String) moduleName.getSelectedItem());
								            pstate6.setString(8, optionaltype);
								            pstate6.setString(9, "Tutor3");
								            
								            pstate6.executeUpdate();
								            JOptionPane.showMessageDialog(null, "Module is assigned Successfully.");
								           
								            tutorName.setText("");
								            tutorID.setText("");
								            rdbtnTutor3.setSelected(false);
								            
								         // for refreshing the table
								            DefaultTableModel model = (DefaultTableModel)detailstable.getModel();
											model.setRowCount(0);
											showinfo();
						            	
						            }else {
						            	
						            	JOptionPane.showMessageDialog(null, "!! Please, select position !!");
						            	
						            }
		//***************************************************************************************************************************************************************************************************************************************   
					            	
					            }else {
					            	JOptionPane.showMessageDialog(null, "Error to Assigned module!");
					            }
				        	   
					            pstate1.close(); //close the pepared Statement
		
				           }else {
				        	   
				        	   JOptionPane.showMessageDialog(null, "Tutor is not register!");
				        	   
				           }
		           
		            }
		          pstate.close(); //close the pepared Statement
		            //close the Statement
		            connect.close();
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
			    
			       
			    
			}
		});
		btnSubmit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnSubmit.setBounds(611, 323, 93, 30);
		panel.add(btnSubmit);
		
		searchtext = new JTextField();
		searchtext.setToolTipText("Search by Tutor ID");
		searchtext.setBounds(234, 57, 193, 30);
		panel.add(searchtext);
		searchtext.setColumns(10);
		
//===============================================================================================================================================================================		
		/*
		 * This section help to search the module assigned in the jtable by searching ID in search text field
		 */
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				String Stext = searchtext.getText();
				search(Stext); // calling the search() method for search and passing the string for search
				
				//setting all the radio buttons selected false to refresh the radio button
				rdbtnModuleLeader.setSelected(false);
				rdbtnAsistantModuleLeader.setSelected(false);
				rdbtnTutor1.setSelected(false);
				rdbtnTutor2.setSelected(false);
				rdbtnTutor3.setSelected(false);
				
				
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		               
		            String query = "SELECT TutorName, TutorID, module_name, position  FROM  module_assigned  WHERE TutorID = ?"; //search query for edit and delete
		            
		            
		            PreparedStatement pstate = connect.prepareStatement(query); //prepared statement
		            pstate.setString(1, searchtext.getText());
		            ResultSet rs = pstate.executeQuery();
		          
		            if(rs.next()) {
		            	    
		            	tutorName.setText(rs.getString("TutorName"));
		            	tutorID.setText(rs.getString("TutorID"));
		            	moduleName.setSelectedItem(rs.getString("module_name"));
		            	
		            	String pos = rs.getString("position");
		            	
		            	// after fetching from the database, checking the string and according to the string the radio button is selected for editing
		            	if(pos.equals("Module Leader")) {
		            		rdbtnModuleLeader.setSelected(true);
		            		
		            	}else if(pos.equals("Asistant Module Leader")) {
		            		rdbtnAsistantModuleLeader.setSelected(true);
		            		
		            	}else if(pos.equals("Tutor1")) {
		            		rdbtnTutor1.setSelected(true);
		            		
		            		
		            	}else if(pos.equals("Tutor2")) {
		            		rdbtnTutor2.setSelected(true);
		            		
		            	}else if(pos.equals("Tutor3")) {
		            		rdbtnTutor3.setSelected(true);
		            		
		            	}
		            	
		            	
		            	searchtext.requestFocus();
		            }else {
		            	
		            	JOptionPane.showMessageDialog(null, "Record not found!");
     	
		            	tutorName.setText("");
		            	tutorID.setText("");
		            	
		            	
		            	rdbtnModuleLeader.setSelected(false);
						rdbtnAsistantModuleLeader.setSelected(false);
						rdbtnTutor1.setSelected(false);
						rdbtnTutor2.setSelected(false);
						rdbtnTutor3.setSelected(false);
		            	searchtext.requestFocus();
		            	
		            }
 
		        	  pstate.close();
		           
		            //close the Statement
		            connect.close();
		        }catch (Exception exp){
		            System.out.println(exp);
		        }	
			}
			
		});
		btnSearch.setBackground(SystemColor.inactiveCaption);
		btnSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnSearch.setBounds(122, 57, 96, 30);
		panel.add(btnSearch);

//==============================================================================================================================================================================		
		/*
		 * This is section for editing the module assign by searching ID
		 */
		JButton btnedit = new JButton("Edit");
		btnedit.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * To edit, first check the tutor Name and tutor ID field is empty or not if empty it will display the massage else next step,
				 *  it will check the register tutor :if not it will display the message else it will check the radio button to select one only 
				 *  if not it will display the message else then it allow to upadte in the database
				 */
				String url = "jdbc:mysql://localhost:3306/course_management_system";
			    String username = "root";
			    String password = "";
			    
			    try{
		            //load the driver
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            //create the connection
		            Connection connect = DriverManager.getConnection(url,username,password);
		               
		            String query = "select fullname, tutorID  from tutor  where fullname =? and tutorID =? "; //query to check whether the tutor is register or not 
		            PreparedStatement pstate = connect.prepareStatement(query);
		            pstate.setString(1, tutorName.getText());
		            pstate.setString(2, tutorID.getText());
		            ResultSet rs = pstate.executeQuery();
		            
		            //check the requird field is empty or not
		            if(tutorName.getText().isEmpty() && tutorID.getText().isEmpty()) {
		            	JOptionPane.showMessageDialog(null, "!! Required Tutor Name and Tutor ID!!");
		            	
		            }else if(tutorName.getText().isEmpty()) {
		            	JOptionPane.showMessageDialog(null, "!! Required Tutor Name !!");
		            	
		            }else if(tutorID.getText().isEmpty()) {
		            	JOptionPane.showMessageDialog(null, "!! Required Tutor ID !!");
		            }else {
		            	
		            	//*******************************************************************************************************************************************************************************************************************************
				           if(rs.next()) {
				        	   
				        	   //if tutor is register than this section will execute
				        	   String qu = "select course_name, level, semester, module_code, optional  from course  where module_name =?";  //query to get the information of course details by user selecting module name
					            // to insert in the module assign table the above query is executed
				        	   PreparedStatement pstate1 = connect.prepareStatement(qu); //prepared Statement
					            pstate1.setString(1, (String) moduleName.getSelectedItem()); //setting the string by getting the selected item from the Jcombobox
					            ResultSet rsm = pstate1.executeQuery();
					            
					            if(rsm.next()) {
					            	
					            	// fetching from the database and storing in the variables
					            	String namecourse = rsm.getString("course_name");
					            	String leveltype  = rsm.getString("level");
					            	String semestertype = rsm.getString("semester");
					            	String codemodule = rsm.getString("module_code");
					            	String optionaltype = rsm.getString("optional");
					            	
//			****************************************************************************************************************************************************************************************************************************   
						            //checks the radio buttons to select one only for assign the tutor position
					            	
					            	
					            	if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }
						            
						            //******************************************************************************************************************
						            
						            
						            else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            }
						            
						            //***********************************************************************************************************
						            
						            
						            else if(rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            }
						            
						            //*****************************************************************************************************************
						            
						            else if(rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            }
						            		            
						            
						            
			//***************************************************************************************************************************************
						            
						            else if(rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            }
						            
						            		            
						            
		    //***************************************************************************************************************************************
						            
						            else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            }
			//********************************************************************************************************************************************
						            else if(rdbtnModuleLeader.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }
						            
						            
						            
		    //****************************************************************************************************************************************
						            else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnAsistantModuleLeader.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }
						            
						            
						            
		    //*****************************************************************************************************************************************
						            
						            
						            
						            else if(rdbtnTutor1.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor1.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor1.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor1.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }
						            
						            
						            
						            
		    //******************************************************************************************************************************************
						            else if(rdbtnTutor2.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor2.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor2.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor2.isSelected() && rdbtnTutor3.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }
						            
						            
						            
						            
		    //******************************************************************************************************************************************
						            
						            
						            else if(rdbtnTutor3.isSelected() && rdbtnModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor3.isSelected() && rdbtnAsistantModuleLeader.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor3.isSelected() && rdbtnTutor1.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            } else if(rdbtnTutor3.isSelected() && rdbtnTutor2.isSelected()) {
						            	JOptionPane.showMessageDialog(null, "!! Please, select position one only !!");
						            	
						            }
						            
						            
						            
						            //if the radio button is selected one only than the query for update executes according to selected radio button
						            
						            
		    //******************************************************************************************************************************************
						           else if(rdbtnModuleLeader.isSelected()) {
						        	   
							            	String quer = " update module_assigned set TutorName = ?, TutorID =? , course_name = ?, level = ?, semester = ?, "
								            		+ "module_code = ?, module_name = ?, optional = ?, position = ?"
								            		+ "where TutorID = ?";  //query for upfate
							            	
							            	 
								            PreparedStatement pstate2 = connect.prepareStatement(quer);
								            pstate2.setString(1, tutorName.getText());
								            pstate2.setString(2, tutorID.getText());
								            pstate2.setString(3, namecourse);
								            pstate2.setString(4, leveltype);
								            pstate2.setString(5, semestertype);
								            pstate2.setString(6, codemodule);
								            pstate2.setString(7, (String) moduleName.getSelectedItem());
								            pstate2.setString(8, optionaltype);
								            pstate2.setString(9, "Module Leader");
								            pstate2.setString(10, searchtext.getText());
								            
								            pstate2.executeUpdate();
								            
								            JOptionPane.showMessageDialog(null, "Module is assigned Successfully updated.");
								            tutorName.setText("");
								            tutorID.setText("");
								            searchtext.setText("");
								            rdbtnModuleLeader.setSelected(false);
								            
								            // for refreshing the table
								            DefaultTableModel model = (DefaultTableModel)detailstable.getModel();
											model.setRowCount(0); // set the row count 0
											search(searchtext.getText());  
											
											showinfo();
						            
						            }else if(rdbtnAsistantModuleLeader.isSelected()){
						            	
							            	String quer = " update module_assigned set TutorName = ?, TutorID =? , course_name = ?, level = ?, semester = ?, "
								            		+ "module_code = ?, module_name = ?, optional = ?, position = ?"
								            		+ " where TutorID = ?";
							            	
							            	
								            PreparedStatement pstate3 = connect.prepareStatement(quer);
								            pstate3.setString(1, tutorName.getText());
								            pstate3.setString(2, tutorID.getText());
								            pstate3.setString(3, namecourse);
								            pstate3.setString(4, leveltype);
								            pstate3.setString(5, semestertype);
								            pstate3.setString(6, codemodule);
								            pstate3.setString(7, (String) moduleName.getSelectedItem());
								            pstate3.setString(8, optionaltype);
								            pstate3.setString(9, "Asistant Module Leader");
								            pstate3.setString(10, searchtext.getText());
								            
								            pstate3.executeUpdate();
								            JOptionPane.showMessageDialog(null, "Module is assigned Successfully updated.");
								            tutorName.setText("");
								            tutorID.setText("");
								            searchtext.setText("");
								            rdbtnAsistantModuleLeader.setSelected(false);
								            
								         // for refreshing the table
								            DefaultTableModel model = (DefaultTableModel)detailstable.getModel();
											model.setRowCount(0); // set the row count 0
											search(searchtext.getText()); 
											
											showinfo();
								            
						            }else if(rdbtnTutor1.isSelected()) {
							            	String quer = " update module_assigned set TutorName = ?, TutorID =? , course_name = ?, level = ?, semester = ?, "
								            		+ "module_code = ?, module_name = ?, optional = ?, position = ?"
								            		+ "where TutorID = ?";
							            	
							            	
								            PreparedStatement pstate4 = connect.prepareStatement(quer);
								            pstate4.setString(1, tutorName.getText());
								            pstate4.setString(2, tutorID.getText());
								            pstate4.setString(3, namecourse);
								            pstate4.setString(4, leveltype);
								            pstate4.setString(5, semestertype);
								            pstate4.setString(6, codemodule);
								            pstate4.setString(7, (String) moduleName.getSelectedItem());
								            pstate4.setString(8, optionaltype);
								            pstate4.setString(9, "Tutor1");
								            pstate4.setString(10, searchtext.getText());
								            
								            pstate4.executeUpdate();
								            JOptionPane.showMessageDialog(null, "Module is assigned Successfully updated.");
								            tutorName.setText("");
								            tutorID.setText("");
								            searchtext.setText("");
								            rdbtnTutor1.setSelected(false);
								            
								         // for refreshing the table
								            DefaultTableModel model = (DefaultTableModel)detailstable.getModel();
											model.setRowCount(0); // set the row count 0
											search(searchtext.getText()); 
											
											showinfo();
						            	
						            }else if(rdbtnTutor2.isSelected()) {
							            	String quer = " update module_assigned set TutorName = ?, TutorID =? , course_name = ?, level = ?, semester = ?, "
								            		+ "module_code = ?, module_name = ?, optional = ?, position = ?"
								            		+ "where TutorID = ?";
							            	
								            PreparedStatement pstate5 = connect.prepareStatement(quer);
								            pstate5.setString(1, tutorName.getText());
								            pstate5.setString(2, tutorID.getText());
								            pstate5.setString(3, namecourse);
								            pstate5.setString(4, leveltype);
								            pstate5.setString(5, semestertype);
								            pstate5.setString(6, codemodule);
								            pstate5.setString(7, (String) moduleName.getSelectedItem());
								            pstate5.setString(8, optionaltype);
								            pstate5.setString(9, "Tutor2");
								            pstate5.setString(10, searchtext.getText());
								            
								            pstate5.executeUpdate();
								            JOptionPane.showMessageDialog(null, "Module is assigned Successfully updated.");
								            tutorName.setText("");
								            tutorID.setText("");
								            searchtext.setText("");
								            rdbtnTutor2.setSelected(false);
								            
								         // for refreshing the table
								            DefaultTableModel model = (DefaultTableModel)detailstable.getModel();
											model.setRowCount(0); // set the row count 0
											search(searchtext.getText()); 
											
											showinfo();
						            	
						            }else if(rdbtnTutor3.isSelected()) {
							            	String quer = " update module_assigned set TutorName = ?, TutorID =? , course_name = ?, level = ?, semester = ?, "
								            		+ "module_code = ?, module_name = ?, optional = ?, position = ?"
								            		+ "where TutorID = ?";
							            	
							            	
								            PreparedStatement pstate6 = connect.prepareStatement(quer);
								            pstate6.setString(1, tutorName.getText());
								            pstate6.setString(2, tutorID.getText());
								            pstate6.setString(3, namecourse);
								            pstate6.setString(4, leveltype);
								            pstate6.setString(5, semestertype);
								            pstate6.setString(6, codemodule);
								            pstate6.setString(7, (String) moduleName.getSelectedItem());
								            pstate6.setString(8, optionaltype);
								            pstate6.setString(9, "Tutor3");
								            pstate6.setString(10, searchtext.getText());
								            pstate6.executeUpdate();
								            JOptionPane.showMessageDialog(null, "Module is assigned Successfully updated.");
								            tutorName.setText("");
								            tutorID.setText("");
								            searchtext.setText("");
								            rdbtnTutor3.setSelected(false);
								            
								         // for refreshing the table
								            DefaultTableModel model = (DefaultTableModel)detailstable.getModel();
											model.setRowCount(0); // set the row count 0
											search(searchtext.getText()); 
											
											showinfo();
						            	
						            }else {
						            	
						            	JOptionPane.showMessageDialog(null, "!! Please, select position !!");
						            }
		//***************************************************************************************************************************************************************************************************************************************   
					            	
					            }else {
					            	JOptionPane.showMessageDialog(null, "Error to Assigned module!");
					            }
				        	   
					            pstate1.close();   

				           }else {
				        	   JOptionPane.showMessageDialog(null, "Tutor is not register!");
				           }
		            }

		          pstate.close();
		            //close the Statement
		            connect.close();
		        }catch (Exception exp){
		            System.out.println(exp);
		        }
					
				
			}
		});
		btnedit.setBackground(SystemColor.info);
		btnedit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnedit.setBounds(157, 323, 96, 30);
		panel.add(btnedit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 390, 840, 21);
		panel.add(separator);

//====================================================================================================================================================================
		/*
		 * This section is for jscrollpane where the data is shown in table 
		 */
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(192, 192, 192), null, null));
		scrollPane.setBounds(20, 447, 840, 236);
		panel.add(scrollPane);
		
		detailstable = new JTable();
		detailstable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Tutor Name", "Tutor ID", "Course Name", "Level", "Semester", "Module Code", "Module Name", "optional", "position" // setting the heading columns
			}
		));
		detailstable.getColumnModel().getColumn(0).setPreferredWidth(56);
		detailstable.getColumnModel().getColumn(0).setMinWidth(56);
		scrollPane.setViewportView(detailstable);
		
// =====================================================================================================================================================================	
		/*
		 * This section is for delete the Module assign by ID
		 */
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(new ActionListener() {
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
			       
			        
				        String q = "Delete from module_assigned where TutorID =? or ID =?";  // query to remove the data
				        
				        
				        PreparedStatement pstat =  connect.prepareStatement(q); // prepared statement
				        pstat.setString(1, searchtext.getText());
				        int id = Integer.parseInt(searchtext.getText());
				        pstat.setInt(2, id);
				        
				        pstat.executeUpdate();  // delete the data in database
				        	
				        JOptionPane.showMessageDialog(null,"Delete Course Successfully");
				        pstat.close();
				        
				        searchtext.setText("");
				        DefaultTableModel model = (DefaultTableModel)detailstable.getModel();
						model.setRowCount(0); // set the row count 0
						search(searchtext.getText()); // to remove the row after delete data in jtable
						showinfo();
				        
			        //close the Statement
			        connect.close();
			        //after deleting by ID setting setting the text field empty
			        tutorName.setText("");
		            tutorID.setText("");
		            
		            // after deleting by ID setting the selected radio button false
		            rdbtnModuleLeader.setSelected(false);
					rdbtnAsistantModuleLeader.setSelected(false);
					rdbtnTutor1.setSelected(false);
					rdbtnTutor2.setSelected(false);
					rdbtnTutor3.setSelected(false);
			        
			    }catch (Exception exp){
			        System.out.println(exp);
			    }
			}
		});
		btndelete.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btndelete.setBackground(new Color(240, 128, 128));
		btndelete.setBounds(481, 57, 96, 30);
		panel.add(btndelete);
		
		JLabel lblNewLabel = new JLabel("Module Assigned Details");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblNewLabel.setBounds(22, 405, 256, 32);
		panel.add(lblNewLabel);
		
		

		frame.setVisible(true);
		
	}
	
	
	
//=========================================================================================================================================================================
	public void showinfo() {
		/* 
		 * This method is for fetching all the required information of module assigned and showing the information in the jscrollpanel 
		 */

		String url = "jdbc:mysql://localhost:3306/course_management_system";
	    String username = "root";
	    String password = "";
	    
	    
	    try{
	        //load the driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        //create the connection
	        Connection connect = DriverManager.getConnection(url,username,password);
	        
	        // declared variables for storing the data in the array
	        String ID = "";
	        String Tutor_Name = "";
	        String TutorID = "";
	        String course_name  = "";
	        String level = "";
	        String semester = "";
	        String module_code = "";
	        String module_name = "";
	        String optional = "";
	        String positiontype = "";
	        
	        String q = "Select * from  module_assigned"; //query to select all the information of module assigned details
	        PreparedStatement pstat =  connect.prepareStatement(q);

	        ResultSet rs = pstat.executeQuery();  
	        
	        
	        DefaultTableModel model = (DefaultTableModel)detailstable.getModel(); //creating default table model object
	        
	        
	        while(rs.next()) {
	        	
	        	//storing the fetched data in the variables
	        	ID = rs.getString("ID");
	        	Tutor_Name = rs.getString("TutorName");
	        	TutorID = rs.getString("TutorID");
	        	course_name = rs.getString("course_name");
	        	level = rs.getString("level");
	        	semester = rs.getString("semester");
	        	module_code = rs.getString("module_code");
	        	module_name = rs.getString("module_name");
	        	optional = rs.getString("optional");
	        	positiontype = rs.getString("position");
	        	String[] row = {ID, Tutor_Name,  TutorID,course_name, level,semester, module_code, module_name, optional, positiontype}; // Storing  data in the array
	        	model.addRow(row); // adding the array as a row in the default table model 

	        }
	        	
	        pstat.close();
	       

	        //close the Statement
	        connect.close();
	    }catch (Exception exp){
	        System.out.println(exp);
	    }
	    
	    
	    
	}

	
	
//=========================================================================================================================================================================
	public void search(String str) {
		/*
		 * This method is for searching in the jtable
		 */
		
		DefaultTableModel model = (DefaultTableModel)detailstable.getModel(); //creating default table model object
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model); //creating tablerowsorter object
		detailstable.setRowSorter(trs); 
		trs.setRowFilter(RowFilter.regexFilter(str)); //string to filter in the table
		
	}
}
