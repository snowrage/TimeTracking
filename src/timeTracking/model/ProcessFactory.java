package timeTracking.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import timeTracking.main.Controller;
import timeTracking.main.Database;

public class ProcessFactory
{
	public static Process createNewProcess()
	{	
		return createNewProcess("", "");	
	}
	public static Process createNewProcess(String name)
	{	
		return createNewProcess(name, "");
	}
	public static Process createNewProcess(String name, String description)
	{
		System.out.println("Erzeuge neuen Prozess.");
		
		Process process = new Process();
		process.setName(name);
		process.setDescription(description);	
			
		// write process into db
		String sqlQuery = "INSERT INTO " + Database.processTableName + " (" +				
				Database.processColoumnNames.name + ", " + 
				Database.processColoumnNames.description + ", " +
				Database.processColoumnNames.userID + ", " +
				Database.processColoumnNames.startDate + ", " +
				Database.processColoumnNames.endDate + ") " +
				"VALUES ('" + name + "', '" + description + "', '" + Controller.userID + "', '" + process.getStartDate() + "', '" + process.getEndDate() + "')";
		System.out.println(sqlQuery);
		
		try
		{
			Controller.db.execute(sqlQuery); // write process into db (auto-increment will generate id)
			
			// get id of new created process			
			sqlQuery = "SELECT id FROM processes WHERE userid='" + Controller.userID + "' ORDER BY id Desc";			
			ResultSet result = Controller.db.runSql(sqlQuery);
			result.next();		
			int id = result.getInt("id");
			process.setId(id); // set id
		}
		
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("Erzeugen des neuen Prozesses fehlgeschlagen.");
			e.printStackTrace();
		}		
		return process;
	}
	

	public static Process getProcessById(int id)
	{ 
		String sql = "SELECT " + 
					Database.processColoumnNames.id + ", " +
					Database.processColoumnNames.name + ", " + 
					Database.processColoumnNames.description + ", " + 
					Database.processColoumnNames.startDate + ", " + 
					Database.processColoumnNames.endDate + " " +
					"FROM " + Database.processTableName +" ";
		sql += "WHERE id='" + id + "'";
		System.out.println(sql);	

		Process result = null;
		HashMap<Integer, Process> processes = readProcesses(sql);
		for(Entry<Integer, Process> entry : processes.entrySet())
		{			
			result = entry.getValue();
			break;
		}
		return result;
	}
	
	
	public static HashMap<Integer, Process> getProcessesByRange(Date startDate, Date endDate)
	{
		String sql = "SELECT " + 
				Database.processColoumnNames.id + ", " +
				Database.processColoumnNames.name + ", " + 
				Database.processColoumnNames.description + ", " + 
				Database.processColoumnNames.startDate + ", " + 
				Database.processColoumnNames.endDate + ", " +
				"FROM " + Database.processTableName + " ";
		sql += "WHERE startDate>='" + startDate +"' AND endDate<='" + endDate +"'";
		
		return readProcesses(sql);	
	}
	
	
	//############
	//## Helper ##
	//############
	private static HashMap<Integer, Process> readProcesses(String sqlQuery)
	{
		HashMap<Integer, Process> processes = new HashMap<Integer, Process>();	
		
		try
		{
			ResultSet result = Controller.db.runSql(sqlQuery);
			while (result.next())
			{
				Process process = new Process();
	          
				process.setId(result.getInt(Database.processColoumnNames.id));
				process.setName(result.getString(Database.processColoumnNames.name));
				process.setDescription(result.getString(Database.processColoumnNames.description));
				process.setStartDate(result.getTimestamp(Database.processColoumnNames.startDate));
	          
				try { process.setEndDate(result.getTimestamp(Database.processColoumnNames.endDate)); }
				catch (Exception e) { e.printStackTrace(); }
	          
				processes.put(process.getId(), process);
	        }		
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return processes;
	}	
}


