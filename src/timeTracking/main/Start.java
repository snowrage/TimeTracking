package timeTracking.main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Start
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{		
		Database db = Controller.db;
		try
		{
			ResultSet result = db.runSql("SELECT * FROM sysadm.ROLE");
			System.out.println(result.toString());

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		db.close();
	}
}
