package timeTracking.views;


import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import timeTracking.main.Controller;
import timeTracking.model.ProcessManager;

import com.michaelbaranov.microba.calendar.DatePicker;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.table.TableColumn;

public class MainWindow
{
	private JFrame logonFrame;
	private JTextField txtUserName;
	private JPasswordField pwPassword;
	
	private JFrame processFrame;
	
	private JPanel panelDateNavigation;
	private JLabel lblDate;
	private DatePicker datePicker;
	private JButton btnNextDay;	
	private JButton btnPreviousDay;
	
	private JPanel panelProcessControl;
	private JButton btnStartProcess;
	private JButton btnPauseProcess;
	private JButton btnContinueProcess;
	private JButton btnEndProcess;
	
	private JTable table;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainWindow window = new MainWindow();					
					window.logonFrame.setVisible(true);
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow()
	{
		initializeLogOnFrame();
		initializeProcessFrame();
	}

	//########################
	//## initzialze content ##
	//######################## 
	private void initializeLogOnFrame()
	{
		logonFrame = new JFrame();
		logonFrame.setBounds(100, 100, 624, 460);
		logonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setBounds(110, 98, 99, 23);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(110, 132, 73, 23);
		logonFrame.getContentPane().setLayout(null);
		logonFrame.getContentPane().add(lblUserName);
		logonFrame.getContentPane().add(lblPassword);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(180, 99, 86, 20);
		logonFrame.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		pwPassword = new JPasswordField();
		pwPassword.setBounds(180, 133, 86, 20);
		logonFrame.getContentPane().add(pwPassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(submitAction);
		btnSubmit.setBounds(177, 166, 89, 23);
		logonFrame.getContentPane().add(btnSubmit);
	}
	private void initializeProcessFrame()
	{	
		processFrame = new JFrame();		
		processFrame.setBounds(100, 100, 624, 460);
		processFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{525, 131, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		processFrame.getContentPane().setLayout(gridBagLayout);
		
		panelDateNavigation = new JPanel();
		GridBagConstraints gbc_panelDateNavigation = new GridBagConstraints();
		gbc_panelDateNavigation.insets = new Insets(0, 0, 5, 5);
		gbc_panelDateNavigation.fill = GridBagConstraints.BOTH;
		gbc_panelDateNavigation.gridx = 0;
		gbc_panelDateNavigation.gridy = 0;
		processFrame.getContentPane().add(panelDateNavigation, gbc_panelDateNavigation);
		GridBagLayout gbl_panelDateNavigation = new GridBagLayout();
		gbl_panelDateNavigation.columnWidths = new int[]{0, 115, 0, 0, 0};
		gbl_panelDateNavigation.rowHeights = new int[]{0, 0};
		gbl_panelDateNavigation.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDateNavigation.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelDateNavigation.setLayout(gbl_panelDateNavigation);
		
		lblDate = new JLabel(Messages.getString("MainWindow.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.insets = new Insets(0, 5, 0, 5);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 0;
		panelDateNavigation.add(lblDate, gbc_lblDate);
		
		datePicker = new DatePicker();
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_datePicker.gridx = 1;
		gbc_datePicker.gridy = 0;
		panelDateNavigation.add(datePicker, gbc_datePicker);
		
		btnPreviousDay = new JButton(Messages.getString("MainWindow.btnPreviousDay.text")); //$NON-NLS-1$
		GridBagConstraints gbc_btnPreviousDay = new GridBagConstraints();
		gbc_btnPreviousDay.insets = new Insets(5, 0, 5, 0);
		gbc_btnPreviousDay.anchor = GridBagConstraints.WEST;
		gbc_btnPreviousDay.gridx = 2;
		gbc_btnPreviousDay.gridy = 0;
		panelDateNavigation.add(btnPreviousDay, gbc_btnPreviousDay);
		btnPreviousDay.addActionListener(previousDayAction);
		
		btnNextDay = new JButton(Messages.getString("MainWindow.btnNextDay.text")); //$NON-NLS-1$
		GridBagConstraints gbc_btnNextDay = new GridBagConstraints();
		gbc_btnNextDay.anchor = GridBagConstraints.WEST;
		gbc_btnNextDay.gridx = 3;
		gbc_btnNextDay.gridy = 0;
		panelDateNavigation.add(btnNextDay, gbc_btnNextDay);
		btnNextDay.addActionListener(nextDayAction);
		
		panelProcessControl = new JPanel();
		GridBagConstraints gbc_panelProcessControl = new GridBagConstraints();
		gbc_panelProcessControl.insets = new Insets(0, 0, 5, 5);
		gbc_panelProcessControl.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelProcessControl.gridx = 0;
		gbc_panelProcessControl.gridy = 1;
		processFrame.getContentPane().add(panelProcessControl, gbc_panelProcessControl);
		GridBagLayout gbl_panelProcessControl = new GridBagLayout();
		gbl_panelProcessControl.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelProcessControl.rowHeights = new int[]{0, 0};
		gbl_panelProcessControl.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelProcessControl.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelProcessControl.setLayout(gbl_panelProcessControl);		
		
		btnStartProcess = new JButton(Messages.getString("MainWindow.btnStartProcess.text"));
		btnStartProcess.addActionListener(startProcessAction);
		GridBagConstraints gbc_btnStartProcess = new GridBagConstraints();
		gbc_btnStartProcess.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStartProcess.insets = new Insets(5, 5, 5, 5);
		gbc_btnStartProcess.gridx = 0;
		gbc_btnStartProcess.gridy = 0;
		panelProcessControl.add(btnStartProcess, gbc_btnStartProcess);
		
		btnContinueProcess = new JButton(Messages.getString("MainWindow.btnContinueProcess.text"));
		btnContinueProcess.addActionListener(continueProcessAction);
		GridBagConstraints gbc_btnContinueProcess = new GridBagConstraints();
		gbc_btnContinueProcess.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnContinueProcess.insets = new Insets(0, 0, 0, 5);
		gbc_btnContinueProcess.gridx = 1;
		gbc_btnContinueProcess.gridy = 0;
		panelProcessControl.add(btnContinueProcess, gbc_btnContinueProcess);
		
		btnPauseProcess = new JButton(Messages.getString("MainWindow.btnPauseProcess1.text"));
		btnPauseProcess.addActionListener(pauseProcessAction);
		GridBagConstraints gbc_btnPauseProcess = new GridBagConstraints();
		gbc_btnPauseProcess.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPauseProcess.insets = new Insets(0, 0, 0, 5);
		gbc_btnPauseProcess.gridx = 2;
		gbc_btnPauseProcess.gridy = 0;
		panelProcessControl.add(btnPauseProcess, gbc_btnPauseProcess);
		
		btnEndProcess = new JButton(Messages.getString("MainWindow.btnEndProcess.text"));
		btnEndProcess.addActionListener(endProcessAction);
		GridBagConstraints gbc_btnEndProcess = new GridBagConstraints();
		gbc_btnEndProcess.insets = new Insets(0, 0, 0, 5);
		gbc_btnEndProcess.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEndProcess.gridx = 3;
		gbc_btnEndProcess.gridy = 0;
		panelProcessControl.add(btnEndProcess, gbc_btnEndProcess);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 2;
		processFrame.getContentPane().add(table, gbc_table);
		
		// set initial state
		btnContinueProcess.setEnabled(false);
		btnPauseProcess.setEnabled(false);
		btnEndProcess.setEnabled(false);
		
	}
	
	//########
	//########
	private void updateView()
	{		
		String[] columnNames = {"Aktion","Zeitpunkt"};
		TableColumn col = new TableColumn();
		table.addColumn(col);
		
		
	}
	
	
	//####################
	//## ActionListener ##
	//####################
	
	ActionListener nextDayAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(datePicker.getDate());
			cal.add(Calendar.DAY_OF_MONTH, 1);
			try
			{
				datePicker.setDate(cal.getTime());
			} catch (PropertyVetoException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
	};
	
	ActionListener previousDayAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{			
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(datePicker.getDate());
			cal.add(Calendar.DAY_OF_MONTH, -1);
			try
			{
				datePicker.setDate(cal.getTime());
			} catch (PropertyVetoException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
	
	ActionListener startProcessAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			btnStartProcess.setEnabled(false);
			btnPauseProcess.setEnabled(true);
			btnEndProcess.setEnabled(true);
			
			ProcessManager.startNewProcess();
			updateView();
		}
	};
	
	ActionListener pauseProcessAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			btnContinueProcess.setEnabled(true);
			btnPauseProcess.setEnabled(false);
		}
	};
	
	ActionListener continueProcessAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			btnContinueProcess.setEnabled(false);
			btnPauseProcess.setEnabled(true);
		}
	};
	
	ActionListener endProcessAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			btnEndProcess.setEnabled(false);
			btnContinueProcess.setEnabled(false);
			btnPauseProcess.setEnabled(false);
			ProcessManager.endProcess();
		}
	};
	
	ActionListener submitAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent arg0) 
		{			
			try
			{
				if(Controller.logon(txtUserName.getText(), String.valueOf(pwPassword.getPassword())))
				{
					processFrame.setVisible(true);
					logonFrame.setVisible(false);
				}
			} 
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	
}
