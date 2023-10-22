import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlMethods {
	String jdbcURL = "jdbc:sqlite:C:\\Users\\Daniel\\OneDrive\\Desktop\\Work\\School\\Junior Year\\Software Engg\\RAT Trap\\Back-End\\Databases\\Databases\\ratTrap.db"; 
	public void add(String Recurrence, String Date_time, String Activity) {
	    try {
	        Class.forName("org.sqlite.JDBC");
	        Connection connection = DriverManager.getConnection(jdbcURL);
	        
	        String sql = "INSERT INTO Schedule (Date_time, Activity, Recurrence) VALUES (?, ?, ?)";
	        
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, Date_time);
	        statement.setString(2, Activity);
	        statement.setString(3, Recurrence);

	        statement.executeUpdate();
	        
	        statement.close();
	        connection.close();
	    } catch (SQLException | ClassNotFoundException e) {
	        System.out.println("Error connecting to SQLite Database");
	        e.printStackTrace();
	    }
	}

	public void viewAll() {
		try {
	        Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        Connection connection = DriverManager.getConnection(jdbcURL);
	        String sql = "Select * From Schedule";

	        Statement statement = connection.createStatement();

	        ResultSet result = statement.executeQuery(sql);
	        
	        while (result.next()) {
	            String date_time = result.getString("date_time");
	            String activity = result.getString("activity");
	            String recurrence = result.getString("recurrence");
	            
	            System.out.println(date_time + " | " + activity+ " | "+ recurrence);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        System.out.println("Error connecting to SQLite Database");
	        e.printStackTrace();
	    }
	}
}
