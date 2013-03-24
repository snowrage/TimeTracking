package timeTracking.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import timeTracking.main.Controller;
import timeTracking.main.UserException;

public class LogonView extends JFrame
{
	private MainWindow w;
	private JFrame view;
	private static JTextField txtUserName;
	private static JPasswordField pwPassword;	
	private static JButton btnSubmit;
	
	public LogonView(MainWindow w)
	{
		this.w = w;
		initializeLogOnFrame();
		view = this;
	}
	
	private void initializeLogOnFrame()
	{
		setBounds(100, 100, 624, 460);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setBounds(110, 98, 99, 23);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(110, 132, 73, 23);
		getContentPane().setLayout(null);
		getContentPane().add(lblUserName);
		getContentPane().add(lblPassword);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(180, 99, 86, 20);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		pwPassword = new JPasswordField();
		pwPassword.setBounds(180, 133, 86, 20);
		getContentPane().add(pwPassword);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(submitAction);
		btnSubmit.setBounds(177, 166, 89, 23);
		getContentPane().add(btnSubmit);
	}
	
	private ActionListener submitAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent arg0) 
		{			
			try
			{
				if(Controller.logon(txtUserName.getText(), String.valueOf(pwPassword.getPassword())))
				{
					w.setVisibilityOfProcessFrame(true);
				}
			} 
			catch (UserException e)
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view, e.getMessage(),"Fehler beim Anmelden",  JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	};
}
