package timeTracking.main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller
{
	public static int userID = 1;	
	public static Database db = new Database();

	public static boolean logon(String userName, String password) throws SQLException
	{
		ResultSet result = null;
		boolean retValue = false;
		
		String sqlQuery = "SELECT password From users WHERE login='" + userName + "'";		
		result = db.runSql(sqlQuery);
		
		if(result != null)
		{
			result.next();			
			String pw = result.getString("password");
			
			//md5 or some hash over the password needed here
			
			
			if(pw.equals(password))
			{
				sqlQuery = "SELECT ID From users WHERE login='" + userName + "'";
				result = db.runSql(sqlQuery);
				result.next();
				userID = result.getInt("ID");
				retValue = true;
			}			
		}
		
		return retValue;
	}	
}
