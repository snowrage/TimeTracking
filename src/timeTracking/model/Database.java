package timeTracking.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import timeTracking.main.Controller;
import timeTracking.main.UserException;

public class Database {
	public static final String processTableName = "processes";

	public static class processColoumnNames {
		public static final String id = "id";
		public static final String name = "name";
		public static final String description = "description";
		public static final String startDate = "startDate";
		public static final String endDate = "endDate";
		public static final String startPause = "startPause";
		public static final String endPause = "endPause";
		public static final String userID = "userID";
	}

	public static final String userTableName = "users";

	public static class usersColoumnsNames {
		public static final String id = "user_id";
		public static final String vorname = "vorname";
		public static final String nachname = "nachname";
		public static final String username = "username";
		public static final String password = "passwort";
	}

	// mySQL
	private final String mySqlUrl = "jdbc:mysql://";
	private final String mySqlServerName = "localhost";
	private final int mySqlPortNumber = 3306;
	private final String mySqlDatabaseName = "timetracking";
	private final String mySqldriver = "com.mysql.jdbc.Driver";

	// MS-SQL
	private Connection conn = null;
	private final String url = "jdbc:sqlserver://";
	private final String serverName = "192.168.100.99";
	private final int portNumber = 1433;
	private final String databaseName = "TimeTracking";

	private enum databaseType {
		MSSQL, MYSQL
	}

	public Database(String type, String server, String port, String databaseName) throws UserException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String driver;
		String extra;

		if (type.equals(databaseType.MSSQL)) {
			driver = "jdbc:sqlserver://";
			extra = "integratedSecurity=true;";
		} else if (type.equals(databaseType.MYSQL)) {
			driver = "jdbc:mysql://";
			extra = "";
		} else {
			throw new UserException("Unbekannter Datenbanktyp");
		}

		String connectionString = driver + server + ":" + port + ";" + databaseName
				+ ";" + extra;

		System.out.println("Verbinde ...");
		conn = DriverManager.getConnection(connectionString);
		System.out.println("Verbindung erfolgreich!");
	}


	public void close() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Verbindung geschlossen");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ResultSet runSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		System.out.println(sql);
		return sta.executeQuery(sql);
	}

	public void execute(String sqlQuery) throws SQLException {
		Statement sta = conn.createStatement();
		sta.execute(sqlQuery);
	}

	public Timestamp getNow() throws SQLException {
		String sqlQuery = "SELECT NOW()";
		ResultSet result = Controller.db.runSql(sqlQuery);
		result.next();
		Timestamp now = result.getTimestamp(1);
		System.out.println(now);
		return now;
	}
}
