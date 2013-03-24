package timeTracking.main;

public class UserException extends Exception
{
	public class Messages
	{
		public static final String WrongPassword = "Falsches Passwort.";
		public static final String NoSuchUser = "Benutzer wurde nicht gefunden";
		public static final String ToManyUsers = "In der Datenbank sind zu viele Benutzer mit dem selben Namen. Konsulitieren Sie den Datenbank-Administrator.";
	}
	
	
	public UserException(String message)
	{
		super(message);
	}
}
