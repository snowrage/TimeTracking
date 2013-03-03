package timeTracking.model;

import java.sql.Timestamp;
import java.util.HashMap;

import timeTracking.main.Controller;
import timeTracking.main.Database;

public class ProcessManager
{
	private static HashMap<Integer, Process> processMap = new HashMap<Integer, Process>();
	private static Process currentProcess;
	
	public static void startNewProcess()
	{
		Process process = ProcessFactory.createNewProcess();			
		try
		{
			// get time with serverTime (don't use local time)
			Timestamp now = Controller.db.getNow();		
			String sqlQuery = "UPDATE " + Database.processTableName + " " + 
						"SET " + Database.processColoumnNames.startDate + "='" + now + "'" +
						"WHERE " + Database.processColoumnNames.id + "='" + process.getId() + "';";
			Controller.db.execute(sqlQuery);

			// save time to process
			process.setStartDate(now);
			
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		processMap.put(process.getId(), process);		
		currentProcess = process;		
	}
	
	public static void endProcess()
	{	
		try
		{
			// get time with serverTime (don't use local time)
			Timestamp now = Controller.db.getNow();		
			String sqlQuery = "UPDATE " + Database.processTableName + " " + 
						"SET " + Database.processColoumnNames.endDate + "='" + now + "'" +
						"WHERE " + Database.processColoumnNames.id + "='" + currentProcess.getId() + "';";
			Controller.db.execute(sqlQuery);
				
			// save time to process
			currentProcess.setEndDate(now);
			
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}		
}
