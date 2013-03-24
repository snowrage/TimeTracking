package timeTracking.view;


public class MainWindow
{
	public final LogonView logonView;	
	public final ProcessView processView;

	public MainWindow()
	{
		this.logonView = new LogonView(this);
		this.processView = new ProcessView(this);		
	}
	
	public void setVisibilityOfProcessFrame(boolean visibility)
	{
		processView.setVisible(visibility);
		logonView.setVisible(!visibility);
		
		if(visibility)processView.updateStatus();		
	}	
}
