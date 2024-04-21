package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
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
import javax.swing.JTextPane;

public class AddTutor extends JFrame{

	private JFrame frame;
	private JTextField fullname;
	private JTextField paswd;
	private JTextField email;
	private JTextField mblno;

	/**
	 * Launch the application.
	 */
	public static void addtutor(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTutor window = new AddTutor();
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
	public AddTutor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AddTutor.class.getResource("/img/RBS.png")));
		frame.setTitle("Course Management System");
		frame.setBounds(100, 100, 701, 429);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(196, 249, 223));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTutorDetails = new JLabel("Tutor Details");
		lblTutorDetails.setFont(new Font("Consolas", Font.BOLD, 30));
		lblTutorDetails.setBounds(225, 0, 237, 36);
		panel.add(lblTutorDetails);
		
//		========================================================================================================================================
		
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
		paswd.setColumns(10);
		paswd.setBounds(93, 134, 180, 25);
		panel.add(paswd);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblEmail.setBounds(402, 65, 45, 36);
		panel.add(lblEmail);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(448, 70, 180, 25);
		panel.add(email);
		
		JLabel lblMblno = new JLabel("Mobile no.");
		lblMblno.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblMblno.setBounds(376, 130, 78, 36);
		panel.add(lblMblno);
		
		mblno = new JTextField();
		mblno.setColumns(10);
		mblno.setBounds(455, 136, 180, 25);
		panel.add(mblno);
		
		
//		============================================================================================================================================
		JLabel lblfullnameEmpty = new JLabel("Required Full Name");
		lblfullnameEmpty.setVisible(false);
		lblfullnameEmpty.setForeground(new Color(255, 0, 0));
		lblfullnameEmpty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblfullnameEmpty.setBounds(90, 102, 180, 28);
		panel.add(lblfullnameEmpty);
		
		JLabel lblPasswordEmpty = new JLabel("Required Password");
		lblPasswordEmpty.setVisible(false);
		lblPasswordEmpty.setForeground(Color.RED);
		lblPasswordEmpty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPasswordEmpty.setBounds(94, 161, 171, 28);
		panel.add(lblPasswordEmpty);
		
		JLabel lblEmailEmpty = new JLabel("Required Email");
		lblEmailEmpty.setVisible(false);
		lblEmailEmpty.setForeground(Color.RED);
		lblEmailEmpty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmailEmpty.setBounds(463, 96, 171, 28);
		panel.add(lblEmailEmpty);
		
//		==================================================================================================================================
		JButton btnregister = new JButton("Register");
		btnregister.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				
				lblfullnameEmpty.setVisible(false);
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
					lblfullnameEmpty.setVisible(true);
					lblPasswordEmpty.setVisible(true);
					lblEmailEmpty.setVisible(true);
					
					
				}
				//check by three field
				else if(paswd.getText().isEmpty()&& email.getText().isEmpty()) {
					lblPasswordEmpty.setVisible(true);
					lblEmailEmpty.setVisible(true);
					
					
				}else if(fullname.getText().isEmpty() && email.getText().isEmpty() ) {
					lblfullnameEmpty.setVisible(true);
					lblEmailEmpty.setVisible(true);
					
					
				}else if(fullname.getText().isEmpty() && paswd.getText().isEmpty()) {
					lblfullnameEmpty.setVisible(true);
					lblPasswordEmpty.setVisible(true);
					
					
				}
				//check by one field
				else if(fullname.getText().isEmpty()) {
					lblfullnameEmpty.setVisible(true);
					
				}else if(paswd.getText().isEmpty()) {
					lblPasswordEmpty.setVisible(true);
					
				}else if(email.getText().isEmpty()) {
					lblEmailEmpty.setVisible(true);
					
				}else {
					
					//check the mobile number text field empty or not
					if(mblno.getText().isEmpty()) {
						
						String fullnamecheck = fullname.getText();
						String passwordcheck = paswd.getText();
						
						String emailcheck = email.getText();
						
						
						String fullnameRegex = "([a-zA-Z]+)\s([a-zA-Z]+){4,30}"; //regex for full name
						
						//Regex for password format check, exactly one special character but exclude +- charater
						String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?!.*[+-])"
								+ "(?=[^!@#$*%_]*[!@#*$%_][^!@#*$%_]*$)[a-zA-Z0-9!@#*$%_]{8,}$";
						
						
						//Regex for email format check, Somthings before @ and somethings after @
						String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
								"[a-zA-Z0-9+&*-]+)*@"+
								"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
						
						//initilized the pattern
						Pattern patternfullname = Pattern.compile(fullnameRegex);
						Pattern patternpassword = Pattern.compile(passwordRegex);
						
						Pattern patternemail = Pattern.compile(emailRegex);
						
						// if all pattern is match, than allow to add the tutor
						if(patternfullname.matcher(fullnamecheck).matches() && patternpassword.matcher(passwordcheck).matches() && 
								 patternemail.matcher(emailcheck).matches() ) {
							
							
								String url = "jdbc:mysql://localhost:3306/course_management_system";
							    String username = "root";
							    String password = "";
							    
							    String IDtutor1 = codetutor();
							    try{
						            //load the driver
						            Class.forName("com.mysql.cj.jdbc.Driver");
						            //create the connection
						            Connection connect = DriverManager.getConnection(url,username,password);
						            
						            //check the tutor is already register of not
						            String query = "select fullname, tutorID from tutor  where fullname = ? and tutorID =?";
						            
						            PreparedStatement pstate = connect.prepareStatement(query);
						            pstate.setString(1, fullname.getText());
						            pstate.setString(2, IDtutor1);
						            ResultSet rs = pstate.executeQuery();
						            
						            if(rs.next()) {
						            	JOptionPane.showMessageDialog(null, "Already registred tutor");
						            	
						            }else {
						            	String IDtutor2 = codetutor(); //to generate tutor ID calling function
						            	
						            	//if mobile number text field is empty than 'N/A' is inserted in the database field
						            	String query1 = "insert into tutor (fullname, tutorID, email,mblNo, password ) values (?,?,?,?,?)";
						            	
						            	
							            PreparedStatement pstate1 = connect.prepareStatement(query1);
							            pstate1.setString(1, fullname.getText());
							            pstate1.setString(2, IDtutor2);
							            pstate1.setString(3, email.getText());
							            pstate1.setString(4, "N/A"); //manually set
							            pstate1.setString(5, paswd.getText());
							            
							            pstate1.executeUpdate();
							            
							            JOptionPane.showMessageDialog(null, "Tutor added succefully!");
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
							    
							    String IDtutor3 = codetutor();
							    try{
						            //load the driver
						            Class.forName("com.mysql.cj.jdbc.Driver");
						            //create the connection
						            Connection connect = DriverManager.getConnection(url,username,password);
						            
						            // check wether the tutor is already register or not
						            String query = "select fullname, tutorID from tutor  where fullname = ? and tutorID =?";
						            
						            PreparedStatement pstate = connect.prepareStatement(query);
						            pstate.setString(1, fullname.getText());
						            pstate.setString(2, IDtutor3);
						            ResultSet rs = pstate.executeQuery();
						            
						            if(rs.next()) {
						            	JOptionPane.showMessageDialog(null, "Already registred tutor");
						            	
						            }else {
						            	String IDtutor4 = codetutor();
						            	
						            	//insert the tutor details to register with mobile number 
						            	String query1 = "insert into tutor (fullname, tutorID, email, mblNo, password ) values (?,?,?,?,?)";
						            	
						            	
							            PreparedStatement pstate1 = connect.prepareStatement(query1);
							            pstate1.setString(1, fullname.getText());
							            pstate1.setString(2, IDtutor4);
							            pstate1.setString(3, email.getText());
							            pstate1.setString(4, mblno.getText());
							            pstate1.setString(5, paswd.getText());
							            
							            pstate1.executeUpdate();
							            
							            JOptionPane.showMessageDialog(null, "Tutor added succefully!");
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
				
				
//				===============================================================================================================================================================
			}
		});
		btnregister.setBackground(new Color(0, 196, 196));
		btnregister.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnregister.setBounds(501, 332, 120, 36);
		panel.add(btnregister);
		
		JTextPane txtpnNotePasswordMust = new JTextPane();
		txtpnNotePasswordMust.setBackground(new Color(196, 249, 223));
		txtpnNotePasswordMust.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtpnNotePasswordMust.setText("Note: password must be exactly one special character eclude(+-)  and at least a Capital Letter up to 12 charatcters");
		txtpnNotePasswordMust.setBounds(22, 244, 611, 62);
		panel.add(txtpnNotePasswordMust);
		
		frame.setVisible(true);
	}
	
	// ============================================================================================================================
    //this method creates code for tutor i.e. TutorID
    public String codetutor() {
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
                codetutor();
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
