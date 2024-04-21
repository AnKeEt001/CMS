package com.cms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class AddStudent extends JFrame{

	private JFrame frame;
	private JTextField fullname;
	private JTextField paswd;
	private JTextField email;
	private JTextField mblno;

	/**
	 * Launch the application.
	 */
	public static void addstudent(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent window = new AddStudent();
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
	public AddStudent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EditTutor.class.getResource("/img/RBS.png")));
		frame.setTitle("Course Management System");
		frame.setBounds(100, 100, 701, 468);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(196, 249, 223));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTutorDetails = new JLabel("Student Details");
		lblTutorDetails.setFont(new Font("Consolas", Font.BOLD, 30));
		lblTutorDetails.setBounds(204, 1, 279, 36);
		panel.add(lblTutorDetails);
		
//		=========================================================================================================================
		
		JLabel lblFullname = new JLabel("Fullname");
		lblFullname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblFullname.setBounds(22, 64, 64, 36);
		panel.add(lblFullname);
		
		fullname = new JTextField();
		fullname.setBounds(90, 71, 180, 25);
		panel.add(fullname);
		fullname.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPassword.setBounds(22, 128, 64, 36);
		panel.add(lblPassword);
		
		paswd = new JTextField();
		paswd.setBounds(93, 134, 180, 25);
		panel.add(paswd);
		paswd.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblEmail.setBounds(402, 65, 45, 36);
		panel.add(lblEmail);
		
		email = new JTextField();
		email.setBounds(448, 70, 180, 25);
		panel.add(email);
		email.setColumns(10);
		
		
		JLabel lblMblno = new JLabel("Mobile no.");
		lblMblno.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblMblno.setBounds(384, 137, 78, 36);
		panel.add(lblMblno);
		
		mblno = new JTextField();
		mblno.setBounds(463, 143, 180, 25);
		panel.add(mblno);
		mblno.setColumns(10);
		
		
		JTextPane txtpnNotePasswordMust = new JTextPane();
		txtpnNotePasswordMust.setBackground(new Color(196, 249, 223));
		txtpnNotePasswordMust.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtpnNotePasswordMust.setText("Note: password must be exactly one special character eclude(+-)  and at least a Capital Letter up to 12 charatcters");
		txtpnNotePasswordMust.setBounds(22, 244, 611, 62);
		panel.add(txtpnNotePasswordMust);
		
//		========================================================================================================================
		
		JLabel lblFullNameEmpty = new JLabel("Required Full name");
		lblFullNameEmpty.setVisible(false);
		lblFullNameEmpty.setForeground(new Color(255, 0, 0));
		lblFullNameEmpty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFullNameEmpty.setBounds(90, 99, 175, 21);
		panel.add(lblFullNameEmpty);
		
		JLabel lblPasswordEmpty = new JLabel("Required Password");
		lblPasswordEmpty.setVisible(false);
		lblPasswordEmpty.setForeground(Color.RED);
		lblPasswordEmpty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPasswordEmpty.setBounds(96, 160, 167, 21);
		panel.add(lblPasswordEmpty);
		
		JLabel lblEmailEmpty = new JLabel("Required Email");
		lblEmailEmpty.setVisible(false);
		lblEmailEmpty.setForeground(Color.RED);
		lblEmailEmpty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmailEmpty.setBounds(450, 98, 167, 21);
		panel.add(lblEmailEmpty);
		
//	==================================================================================================	
		JButton btnregister = new JButton("Register");
		btnregister.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
//				===============================================================================================================================================================================================
				/*
				 * Checking the fullname name text field, password text field, email text field, mblile no. text field and Student ID text field
 				 *  is empty or not for add Student,
				 * if any of the fields are empty than display the message as error
				 */ 
				
				//empty field check
				
				lblFullNameEmpty.setVisible(false);
				lblPasswordEmpty.setVisible(false);
				lblEmailEmpty.setVisible(false);
				
//				===================================================================================================================================================================================
				/*
				 * Checking the fullname name text field, password text field and email text field
 				 *  is empty or not for add Tutor,
				 * if any of the fields are empty than display the message as error
				 */ 
				
				//empty field check
				if(fullname.getText().isEmpty() && paswd.getText().isEmpty()&& email.getText().isEmpty()) {
					lblFullNameEmpty.setVisible(true);
					lblPasswordEmpty.setVisible(true);
					lblEmailEmpty.setVisible(true);
					
					
				}
				//check by three field
				else if(paswd.getText().isEmpty()&& email.getText().isEmpty()) {
					lblPasswordEmpty.setVisible(true);
					lblEmailEmpty.setVisible(true);
					
					
				}else if(fullname.getText().isEmpty() && email.getText().isEmpty() ) {
					lblFullNameEmpty.setVisible(true);
					lblEmailEmpty.setVisible(true);
					
					
				}else if(fullname.getText().isEmpty() && paswd.getText().isEmpty()) {
					lblFullNameEmpty.setVisible(true);
					lblPasswordEmpty.setVisible(true);
					
					
				}
				//check by one field
				else if(fullname.getText().isEmpty()) {
					lblFullNameEmpty.setVisible(true);
					
				}else if(paswd.getText().isEmpty()) {
					lblPasswordEmpty.setVisible(true);
					
				}else if(email.getText().isEmpty()) {
					lblEmailEmpty.setVisible(true);
					
				}else {
					
					
					//check whether the mobile number text field is empty or not
					if(mblno.getText().isEmpty()) {
						
						//variables
						String fullnamecheck = fullname.getText();
						String passwordcheck = paswd.getText();
						
						String emailcheck = email.getText();
						
						
						String fullnameRegex = "([a-zA-Z]+)\s([a-zA-Z]+){4,30}"; //regex for fullname
						
						//Regex for password format check, exactly one special character but exclude +- charater
						String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?!.*[+-])"
								+ "(?=[^!@#$*%_]*[!@#*$%_][^!@#*$%_]*$)[a-zA-Z0-9!@#*$%_]{8,}$";
						
						
						//Regex for email format check, Somthings before @ and somethings after @
						String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
								"[a-zA-Z0-9+&*-]+)*@"+
								"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
						
						//initilized the patterns
						Pattern patternfullname = Pattern.compile(fullnameRegex);
						Pattern patternpassword = Pattern.compile(passwordRegex);
						
						Pattern patternemail = Pattern.compile(emailRegex);
						
						//check all the patterns of all fields than it allow to add student other it don't allow
						if(patternfullname.matcher(fullnamecheck).matches() && patternpassword.matcher(passwordcheck).matches() && 
								 patternemail.matcher(emailcheck).matches() ) {
							
							
								String url = "jdbc:mysql://localhost:3306/course_management_system";
							    String username = "root";
							    String password = "";
							    
							    String IDtutor1 = codestudent();
							    try{
						            //load the driver
						            Class.forName("com.mysql.cj.jdbc.Driver");
						            //create the connection
						            Connection connect = DriverManager.getConnection(url,username,password);
						            
						            //check whether the student is already register or not
						            String query = "select fullname, studentID from student  where fullname = ? and studentID =?";
						            
						            PreparedStatement pstate = connect.prepareStatement(query);
						            pstate.setString(1, fullname.getText());
						            pstate.setString(2, IDtutor1);
						            ResultSet rs = pstate.executeQuery();
						            
						            if(rs.next()) {
						            	JOptionPane.showMessageDialog(null, "Already registred Student");
						            	
						            }else {
						            	String IDtutor2 = codestudent(); // to generate the stdudent ID calling function
						            	
						            	//if the mobile number is not enter than 'N/A' is inserted in the datsbase
						            	String query1 = "insert into student (fullname, studentID, email,mblNo, password ) values (?,?,?,?,?)";
						            	
						            	
							            PreparedStatement pstate1 = connect.prepareStatement(query1);
							            pstate1.setString(1, fullname.getText());
							            pstate1.setString(2, IDtutor2);
							            pstate1.setString(3, email.getText());
							            pstate1.setString(4, "N/A"); //manually set
							            pstate1.setString(5, paswd.getText());
							            
							            pstate1.executeUpdate();
							            
							            JOptionPane.showMessageDialog(null, "Student added succefully!");
							            frame.dispose();
						            	
							            pstate1.close();
						            }
						            
						            pstate.close();
						            
						            //close the Statement
						            connect.close();
							    
						        }catch (Exception exp){
						            System.out.println(exp);
						        }
							 
						}else {
							
							JOptionPane.showMessageDialog(null, "Please, Check fullname(firstname and lastname required), "
									+ "password, mobile number and email format is not match");
							
						}
						
						
					}else {
						String fullnamecheck = fullname.getText();
						String passwordcheck = paswd.getText();
						String Mblnocheck = mblno.getText();
						String emailcheck = email.getText();
						
						
						String fullnameRegex = "([a-zA-Z]+)\s([a-zA-Z]+){4,30}";
						
						//Regex for password format check, exactly one special character but exclude +- charater
						String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?!.*[+-])"
								+ "(?=[^!@#$*%_]*[!@#*$%_][^!@#*$%_]*$)[a-zA-Z0-9!@#*$%_]{8,}$";
						
						//Regex for mobile number check i.e. exactly 10 digits number
						String mblnoregex = "\\d{10}";
						
						//Regex for email format check, Somthings before @ and somethings after @
						String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
								"[a-zA-Z0-9+&*-]+)*@"+
								"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
						
						Pattern patternfullname = Pattern.compile(fullnameRegex);
						Pattern patternpassword = Pattern.compile(passwordRegex);
						Pattern patternmblno = Pattern.compile(mblnoregex);
						Pattern patternemail = Pattern.compile(emailRegex);
						
						
						if(patternfullname.matcher(fullnamecheck).matches() && patternpassword.matcher(passwordcheck).matches() && 
								patternmblno.matcher(Mblnocheck).matches() && patternemail.matcher(emailcheck).matches() ) {
							
							
								String url = "jdbc:mysql://localhost:3306/course_management_system";
							    String username = "root";
							    String password = "";
							    
							    String IDtutor3 = codestudent();
							    try{
						            //load the driver
						            Class.forName("com.mysql.cj.jdbc.Driver");
						            //create the connection
						            Connection connect = DriverManager.getConnection(url,username,password);
						            
						            String query = "select fullname, studentID from student  where fullname = ? and studentID =?";
						            
						            PreparedStatement pstate = connect.prepareStatement(query);
						            pstate.setString(1, fullname.getText());
						            pstate.setString(2, IDtutor3);
						            ResultSet rs = pstate.executeQuery();
						            
						            if(rs.next()) {
						            	JOptionPane.showMessageDialog(null, "Already registred Student");
						            	
						            }else {
						            	String IDtutor4 = codestudent(); //generate student ID
						            	
						            	// if mobile number text field is not empty, execute this query
						            	String query1 = "insert into student (fullname, studentID, email, mblNo, password ) values (?,?,?,?,?)";
						            	
						            	
							            PreparedStatement pstate1 = connect.prepareStatement(query1);
							            pstate1.setString(1, fullname.getText());
							            pstate1.setString(2, IDtutor4);
							            pstate1.setString(3, email.getText());
							            pstate1.setString(4, mblno.getText());
							            pstate1.setString(5, paswd.getText());
							            
							            pstate1.executeUpdate();
							            
							            JOptionPane.showMessageDialog(null, "Student added succefully!");
							            frame.dispose();
						            	
							            pstate1.close();
							              
						            }
						            
						            pstate.close();
						            mblno.setText("");
						            
						            //close the Statement
						            connect.close();
							    
						        }catch (Exception exp){
						            System.out.println(exp);
						        }
							 
						}else {
							
							JOptionPane.showMessageDialog(null, "Please, Check fullname(firstname and lastname required), "
									+ "password, mobile number and email format is not match");
							
						}
						
					}
					
				}
			

			}
				
			
		});
		btnregister.setBackground(new Color(0, 196, 196));
		btnregister.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnregister.setBounds(508, 358, 120, 36);
		panel.add(btnregister);
		
		
	
		
		frame.setVisible(true);
		
	}
	
//======================================================================================================================================================
	
	 //this method creates code for tutor i.e. TutorID
    public String codestudent() {
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
                codestudent();
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
