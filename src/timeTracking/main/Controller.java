package timeTracking.main;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

import timeTracking.model.Database;
import timeTracking.model.ProcessFactory;
import timeTracking.model.ProcessManager;
import timeTracking.view.MainWindow;
import timeTracking.model.Process;

public class Controller
{
	public static int userID = 1;	
	public static Database db = new Database();

	private static MainWindow w;
	
	private Controller()
	{}
	
	public static void main(String[] args)
	{	
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					w = new MainWindow();	
					w.setVisibilityOfProcessFrame(false);		// set logonView visible		
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	
	// #########################
	// ## process controlling ##
	// #########################
	public static void startNewProcess()
	{
		ProcessManager.startNewProcess();	
	}
	public static void endProcess()	
	{
		ProcessManager.endProcess();
	}

	public static Timestamp getWorkStart()
	{
		Process p = ProcessManager.getCurrentProcess();
		return p.getStartDate();		
	}
	
	public static Timestamp getWorkEnd()
	{
		Process p = ProcessManager.getCurrentProcess();
		return p.getEndDate();		
	}
	
	public static Process getProcessByDay(Timestamp day)
	{
		HashMap<Integer, Process> pMap = ProcessFactory.getProcessesByDay(day);
		
		return pMap.get(0);
	}
	
	// ######################
	// ## user controlling ##
	// ######################
	public static boolean logon(String userName, String password) throws SQLException, UserException
	{
		ResultSet result = null;
		boolean retValue = false;	
		
		String sqlQuery = "SELECT "+ Database.usersColoumnsNames.password + 
							" From " + Database.userTableName +
							" WHERE " + Database.usersColoumnsNames.username + "='" + userName + "'";		
		result = db.runSql(sqlQuery);
		
		if(result != null && result.last()) //check if result has any rows 
		{			
			int rowCount = result.getRow();
			if(rowCount > 1)
			{
				throw new UserException(UserException.Messages.ToManyUsers);
			}
			
			result.first();					
			String pw = result.getString(Database.usersColoumnsNames.password);
			
			//md5 or some hash over the password needed here
			
			
			if(pw.equals(password))
			{
				sqlQuery = "SELECT " + Database.usersColoumnsNames.id + 
							" From " + Database.userTableName + 
							" WHERE " + Database.usersColoumnsNames.username + "='" + userName + "'";		
				result = db.runSql(sqlQuery);
				
				result.first();
				userID = result.getInt(Database.usersColoumnsNames.id);
				retValue = true;
			}
			else
			{
				throw new UserException(UserException.Messages.WrongPassword);
			}
		}
		else
		{
			throw new UserException(UserException.Messages.NoSuchUser);
		}
		
		return retValue;
	}	
	public static String getUserName() throws SQLException
	{
		String sqlQuery = "SELECT "+ Database.usersColoumnsNames.vorname + ", " +  Database.usersColoumnsNames.nachname + 
				" From " + Database.userTableName +
				" WHERE " + Database.usersColoumnsNames.id + "=" + userID;		
		ResultSet result = db.runSql(sqlQuery);
		
		result.first();
		String vorname = result.getString(Database.usersColoumnsNames.vorname);
		String nachname = result.getString(Database.usersColoumnsNames.nachname);
		
		return vorname + " " + nachname;		
	}

	

}
