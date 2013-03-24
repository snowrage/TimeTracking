package timeTracking.view;


import javax.swing.JFrame;

import timeTracking.main.Controller;

public class MainWindow
{
	public final JFrame logonView;	
	public final JFrame processView;

	public MainWindow()
	{
		this.logonView = new LogonView(this);
		this.processView = new ProcessView(this);		
	}
	
	public void setVisibilityOfProcessFrame(boolean visibility)
	{
		processView.setVisible(visibility);
		logonView.setVisible(!visibility);
	}	
}
