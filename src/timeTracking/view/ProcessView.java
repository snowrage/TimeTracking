package timeTracking.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import timeTracking.main.Controller;
import timeTracking.model.Process;
import timeTracking.model.ProcessManager;

import com.michaelbaranov.microba.calendar.DatePicker;

public class ProcessView extends JFrame
{	
	private MainWindow w;
	
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
	
	private JLabel lblStatus;
	private JPanel panel;
	private JLabel lblArbeitsbeginn;
	private JLabel lblPausenbeginn;
	private JLabel lblPausenende;
	private JLabel lblArbeitsende;
	private JLabel lblWorkStartTime;
	private JLabel lblPauseStartTime;
	private JLabel lblPauseEndTime;
	private JLabel lblWorkEndTime;
	
	public ProcessView(MainWindow w)
	{
		this.w = w;
		initializeProcessFrame();
	}
	
	private void initializeProcessFrame()
	{		
		setBounds(100, 100, 490, 229);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{525, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		panelDateNavigation = new JPanel();
		GridBagConstraints gbc_panelDateNavigation = new GridBagConstraints();
		gbc_panelDateNavigation.insets = new Insets(0, 0, 5, 0);
		gbc_panelDateNavigation.fill = GridBagConstraints.BOTH;
		gbc_panelDateNavigation.gridx = 0;
		gbc_panelDateNavigation.gridy = 0;
		getContentPane().add(panelDateNavigation, gbc_panelDateNavigation);
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
		datePicker.addActionListener(datePickerAction);
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
		gbc_panelProcessControl.insets = new Insets(0, 0, 5, 0);
		gbc_panelProcessControl.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelProcessControl.gridx = 0;
		gbc_panelProcessControl.gridy = 1;
		getContentPane().add(panelProcessControl, gbc_panelProcessControl);
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
		
		
		// set initial state
		btnContinueProcess.setEnabled(false);
		btnPauseProcess.setEnabled(false);
		btnEndProcess.setEnabled(false);		
		
		// status label		
		lblStatus = new JLabel(""); //$NON-NLS-1$
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 10, 5, 5);
		gbc_lblStatus.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 3;
		getContentPane().add(lblStatus, gbc_lblStatus);
		
		// time labels
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 5, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{90, 363, };
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblArbeitsbeginn = new JLabel(Messages.getString("ProcessView.lblArbeitsbeginn.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblArbeitsbeginn = new GridBagConstraints();
		gbc_lblArbeitsbeginn.anchor = GridBagConstraints.EAST;
		gbc_lblArbeitsbeginn.insets = new Insets(0, 5, 5, 5);
		gbc_lblArbeitsbeginn.gridx = 0;
		gbc_lblArbeitsbeginn.gridy = 0;
		panel.add(lblArbeitsbeginn, gbc_lblArbeitsbeginn);
		
		lblWorkStartTime = new JLabel(Messages.getString("ProcessView.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblWorkStartTime = new GridBagConstraints();
		gbc_lblWorkStartTime.anchor = GridBagConstraints.WEST;
		gbc_lblWorkStartTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblWorkStartTime.gridx = 1;
		gbc_lblWorkStartTime.gridy = 0;
		panel.add(lblWorkStartTime, gbc_lblWorkStartTime);
		
		lblPausenbeginn = new JLabel(Messages.getString("ProcessView.lblPausenbeginn.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblPausenbeginn = new GridBagConstraints();
		gbc_lblPausenbeginn.anchor = GridBagConstraints.EAST;
		gbc_lblPausenbeginn.insets = new Insets(0, 5, 5, 5);
		gbc_lblPausenbeginn.gridx = 0;
		gbc_lblPausenbeginn.gridy = 1;
		panel.add(lblPausenbeginn, gbc_lblPausenbeginn);
		
		lblPauseStartTime = new JLabel("-");
		GridBagConstraints gbc_lblPauseStartTime = new GridBagConstraints();
		gbc_lblPauseStartTime.anchor = GridBagConstraints.WEST;
		gbc_lblPauseStartTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblPauseStartTime.gridx = 1;
		gbc_lblPauseStartTime.gridy = 1;
		panel.add(lblPauseStartTime, gbc_lblPauseStartTime);
		
		lblPausenende = new JLabel(Messages.getString("ProcessView.lblPausenende.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblPausenende = new GridBagConstraints();
		gbc_lblPausenende.anchor = GridBagConstraints.EAST;
		gbc_lblPausenende.insets = new Insets(0, 0, 5, 5);
		gbc_lblPausenende.gridx = 0;
		gbc_lblPausenende.gridy = 2;
		panel.add(lblPausenende, gbc_lblPausenende);
		
		lblPauseEndTime = new JLabel("-");
		GridBagConstraints gbc_lblPauseEndTime = new GridBagConstraints();
		gbc_lblPauseEndTime.anchor = GridBagConstraints.WEST;
		gbc_lblPauseEndTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblPauseEndTime.gridx = 1;
		gbc_lblPauseEndTime.gridy = 2;
		panel.add(lblPauseEndTime, gbc_lblPauseEndTime);
		
		lblArbeitsende = new JLabel(Messages.getString("ProcessView.lblArbeitsende.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblArbeitsende = new GridBagConstraints();
		gbc_lblArbeitsende.insets = new Insets(0, 0, 0, 5);
		gbc_lblArbeitsende.anchor = GridBagConstraints.EAST;
		gbc_lblArbeitsende.gridx = 0;
		gbc_lblArbeitsende.gridy = 3;
		panel.add(lblArbeitsende, gbc_lblArbeitsende);
		
		lblWorkEndTime = new JLabel("-");
		GridBagConstraints gbc_lblWorkEndTime = new GridBagConstraints();
		gbc_lblWorkEndTime.anchor = GridBagConstraints.WEST;
		gbc_lblWorkEndTime.insets = new Insets(0, 0, 0, 5);
		gbc_lblWorkEndTime.gridx = 1;
		gbc_lblWorkEndTime.gridy = 3;
		panel.add(lblWorkEndTime, gbc_lblWorkEndTime);
		
		
	}
	
	public void updateStatus()
	{
		String status; 
		try
		{
			 status = "Angemeldet als "  + Controller.getUserName();
			 lblStatus.setText(status);
		}
		catch(SQLException e)
		{
			status = e.getMessage();
		}
	}
	
	
	//####################
	//## ActionListener ##
	//####################
	private ActionListener nextDayAction = new ActionListener()
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
	
	private ActionListener previousDayAction = new ActionListener()
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
	
	private ActionListener startProcessAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			btnStartProcess.setEnabled(false);
			btnPauseProcess.setEnabled(true);
			btnEndProcess.setEnabled(true);
			
			Controller.startNewProcess();			
			Timestamp startDate = Controller.getWorkStart();			
			lblWorkStartTime.setText(timeToString(startDate));
		}
	};
	
	private ActionListener pauseProcessAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			btnContinueProcess.setEnabled(true);
			btnPauseProcess.setEnabled(false);	
			
			Controller.pauseProcess();			
			Timestamp startPause = Controller.getWorkStart();			
			lblWorkStartTime.setText(timeToString(startPause));
		}
	};
	
	private ActionListener continueProcessAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			btnContinueProcess.setEnabled(false);
			btnPauseProcess.setEnabled(true);
			
			Controller.continueProcess();			
			Timestamp endPause = Controller.getWorkStart();			
			lblWorkStartTime.setText(timeToString(endPause));
		}
	};
	
	private ActionListener endProcessAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			btnEndProcess.setEnabled(false);
			btnContinueProcess.setEnabled(false);
			btnPauseProcess.setEnabled(false);
			
			Controller.endProcess();			
			Timestamp endDate = Controller.getWorkEnd();			
			lblWorkEndTime.setText(timeToString(endDate));
		}
	};	
	
	private ActionListener datePickerAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(datePicker.getDate());

			setTimeLabels(cal);	
		}
	};
	
	
	//############
	//## Helper ##
	//############
	private String timeToString(Timestamp stamp) 
	{
		String time = "";
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(stamp.getTime());
		
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		int seconds = cal.get(Calendar.SECOND);
		
		if(Math.floor(hours/10) == 0) time += "0";
		time += hours + ":";
		
		if(Math.floor(minutes/10) == 0) time += "0";
		time += minutes + ":";
			
		if(Math.floor(seconds/10) == 0) time += "0";
		time += seconds;
		
		return time;		
	}
	

	/*
	 * Write the start/end-time into the labels as string 
	 */
	private void setTimeLabels(GregorianCalendar cal)
	{
		
		Process p = Controller.getProcessByDay(new Timestamp(cal.getTimeInMillis()));
		
		if(p != null)
		{
			Timestamp startDate = p.getStartDate();
			
			lblWorkStartTime.setText(timeToString(startDate));
			
			Timestamp endDate = p.getEndDate();
			if(endDate != null)
				lblWorkEndTime.setText(timeToString(endDate));
			else 
				lblWorkEndTime.setText("-");
		}
		else
		{
			lblWorkStartTime.setText("-");
			lblWorkEndTime.setText("-");
		}		
	}
	
}
