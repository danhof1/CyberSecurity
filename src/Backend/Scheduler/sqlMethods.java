package Backend.Scheduler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class sqlMethods
{
	//String jdbcURL = "jdbc:sqlite:C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\Backend\\Databases\\sqlite\\ratTrap.db"; 
	public String jdbcURL = "jdbc:sqlite:" + System.getProperty("user.dir").replace("\\", "/") + "/src/Backend/Databases/sqlite/ratTrap.db";
	//String jdbcURL = new String("C:\\Users\\Ashley\\RatTrap\\RatTrap 1.5\\src\\Backend\\Databases\\sqlite");
	
	public void add(int id, String Recurrence, String Date_time, String Activity,String filePath,String dayOfWeek) {
        try (Connection connection = DriverManager.getConnection(jdbcURL)) {
            String sql = "INSERT INTO Schedule (id,date_time, activity, recurrence,filePath,dayOfWeek) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.setString(3, Date_time);
                statement.setString(4, Activity);
                statement.setString(2, Recurrence);
                statement.setString(5, filePath);
                statement.setString(6,dayOfWeek);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite Database");
            e.printStackTrace();
        }
    }

	public void viewAll() {
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()) {
	        Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = "Select * From Schedule";

	        

	        ResultSet result = statement.executeQuery(sql);
	        
	        while (result.next()) {
	            String date_time = result.getString("date_time");
	            String activity = result.getString("activity");
	            String recurrence = result.getString("recurrence");
	            String filePath = result.getString("filePath");
	            String dayOfWeek = result.getString("dayOfWeek");
	            int id = result.getInt("id");
	            
	            System.out.println(id + " | " + date_time + " | " + activity+ " | "+ recurrence +"| "+filePath+" | "+dayOfWeek);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        System.out.println("Error connecting to SQLite Database");
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<String[]> toArray()
	{
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()){
	        
			Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = " SELECT * FROM Schedule ORDER BY date_time";

	        ResultSet result = statement.executeQuery(sql);
	        
	        //output array
	        ArrayList<String[]> arr = new ArrayList<String[]>();
	        
	        
	        while (result.next())
	        {
	            String date_time = result.getString("date_time");
	            String activity = result.getString("activity");
	            String recurrence = result.getString("recurrence");
	            String filePath = result.getString("filePath");
	            String dayOfWeek = result.getString("dayOfWeek");
	            int id = result.getInt("id");
	            
	            //System.out.println(id + " | " + date_time + " | " + activity+ " | "+ recurrence +"| "+filePath+" | "+dayOfWeek);
	        
	            String[] temp = new String[6];
	            temp[0] = id + "";
	            temp[1] = date_time;
	            temp[2] = activity;
	            temp[3] = recurrence;
	            temp[4] = filePath;
	            temp[5] = dayOfWeek;
	            arr.add(temp);
	        }
			return arr;

	        
	    } catch (SQLException | ClassNotFoundException e) {
	        System.out.println("Error connecting to SQLite Database");
	        e.printStackTrace();
	    }
		return null;
		
	}
	
	public void printSortedTablebyDate() {
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()){
	        
			Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = " SELECT * FROM Schedule ORDER BY date_time";

	        ResultSet result = statement.executeQuery(sql);
	        
	        while (result.next()) {
	            String date_time = result.getString("date_time");
	            String activity = result.getString("activity");
	            String recurrence = result.getString("recurrence");
	            String filePath = result.getString("filePath");
	            String dayOfWeek = result.getString("dayOfWeek");
	            int id = result.getInt("id");
	            
	            System.out.println(id + " | " + date_time + " | " + activity+ " | "+ recurrence +"| "+filePath+" | "+dayOfWeek);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        System.out.println("Error connecting to SQLite Database");
	        e.printStackTrace();
	    }
	}
	public String MostRecentDT() {
		String date_time="";
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()){
	        
			Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = " SELECT * FROM Schedule ORDER BY date_time";

	        ResultSet result = statement.executeQuery(sql);
	        
	        result.next();
	       date_time = result.getString("date_time");
		}
	        catch (SQLException | ClassNotFoundException e) {
		        System.out.println("Error connecting to SQLite Database");
		        e.printStackTrace();
		    }
	        return date_time;
	        
	}
	
	public String MostRecentDayOfWeek() {
		String date_time="";
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()){
	        
			Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = " SELECT * FROM Schedule ORDER BY date_time";

	        ResultSet result = statement.executeQuery(sql);
	        
	        result.next();
	       date_time = result.getString("dayOfWeek");
		}
	        catch (SQLException | ClassNotFoundException e) {
		        System.out.println("Error connecting to SQLite Database");
		        e.printStackTrace();
		    }
	        return date_time;
	        
	}
	
	public String mostRecentACT() {
		String Activity="";
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()){
	        
			Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = " SELECT * FROM Schedule ORDER BY date_time";

	        ResultSet result = statement.executeQuery(sql);
	        
	        result.next();
	       Activity = result.getString("activity");
		}
	        catch (SQLException | ClassNotFoundException e) {
		        System.out.println("Error connecting to SQLite Database");
		        e.printStackTrace();
		    }
	        return Activity;
	}
	public String mostRecentRec() {
		String recurrence="";
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()){
	        
			Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = " SELECT * FROM Schedule ORDER BY date_time";

	        ResultSet result = statement.executeQuery(sql);
	        
	       result.next();
	       recurrence = result.getString("recurrence");
		}
	        catch (SQLException | ClassNotFoundException e) {
		        System.out.println("Error connecting to SQLite Database");
		        e.printStackTrace();
		    }
	        return recurrence;
	}
	public String mostRecentF() {
		String recurrence="";
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()){
	        
			Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = " SELECT * FROM Schedule ORDER BY date_time";

	        ResultSet result = statement.executeQuery(sql);
	        
	       result.next();
	       recurrence = result.getString("filePath");
		}
	        catch (SQLException | ClassNotFoundException e) {
		        System.out.println("Error connecting to SQLite Database");
		        e.printStackTrace();
		    }
	        return recurrence;
	}
	public int mostRecentInd() {
		int id= -1;
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()){
	        
			Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = " SELECT * FROM Schedule ORDER BY date_time";

	        ResultSet result = statement.executeQuery(sql);
	        
	       result.next();
	       id = result.getInt("id");
		}
	        catch (SQLException | ClassNotFoundException e) {
		        System.out.println("Error connecting to SQLite Database");
		        e.printStackTrace();
		    }
	        return id;
	}
	
	public void printSortedTablebyID() {
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()) {
	        Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	     
	        String sql = " SELECT * FROM Schedule ORDER BY id";

	       

	        ResultSet result = statement.executeQuery(sql);
	        
	        while (result.next()) {
	            String date_time = result.getString("date_time");
	            String activity = result.getString("activity");
	            String recurrence = result.getString("recurrence");
	            String filePath = result.getString("filePath");
	            String dayOfWeek = result.getString("dayOfWeek");
	            int id = result.getInt("id");
	            
	            System.out.println(id + " | " + date_time + " | " + activity+ " | "+ recurrence +"| "+filePath+" | "+dayOfWeek);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        System.out.println("Error connecting to SQLite Database");
	        e.printStackTrace();
	    }
	}
	public boolean isEmpty() {
	    try (Connection connection = DriverManager.getConnection(jdbcURL);
	         Statement statement = connection.createStatement()) {

	        String sql = "SELECT COUNT(*) FROM Schedule;";
	        ResultSet result = statement.executeQuery(sql);

	        if (result.next()) {
	            int count = result.getInt(1);
	            return count == 0;
	        }

	    } catch (SQLException e) {
	        System.out.println("Error connecting to SQLite Database");
	        e.printStackTrace();
	    }
	    return false;
	}

	// Repeat this pattern for other methods that access the database


	public int getLastID(){
	    int value = -1; // Initialize value

	    try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement())   {
	        Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = "SELECT id FROM Schedule ORDER BY id DESC LIMIT 1;";

	       

	        ResultSet result = statement.executeQuery(sql);
	        
	        if (result.next()) {
	            value = result.getInt(1); // Get the value of the first column
	        }

	    } catch (SQLException | ClassNotFoundException e) {
	        System.out.println("Error connecting to SQLite Database");
	        e.printStackTrace();
	    }
	    
	    return value;
	}
	public void rmItem(int id) {
		 try (Connection connection = DriverManager.getConnection(jdbcURL)) {
	            String sql = "DELETE FROM Schedule WHERE id =" + id + ";";

	            try (PreparedStatement statement = connection.prepareStatement(sql)) {

	                statement.executeUpdate();
	            }

	        } catch (SQLException e) {
	            System.out.println("Error connecting to SQLite Database");
	            e.printStackTrace();
	        }
	}
	public void fixID(int deleted_id) {
		 try (Connection connection = DriverManager.getConnection(jdbcURL)) {
			 String sql = "UPDATE Schedule SET id = id - (CASE WHEN id > " + deleted_id + " THEN 1 ELSE 0 END);";

	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.executeUpdate();
	            }

	        } catch (SQLException e) {
	            System.out.println("Error connecting to SQLite Database");
	            e.printStackTrace();
	        }
	}
	public void wipeTable() {
		 try (Connection connection = DriverManager.getConnection(jdbcURL)) {
	            String sql = "DELETE FROM Schedule;" ;

	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.executeUpdate();
	            }

	        } catch (SQLException e) {
	            System.out.println("Error connecting to SQLite Database");
	            e.printStackTrace();
	        }
	}
	/*public String parseDate() {
		
	}
	public String parseAction() {
		
	}
	public String parseRecurrence() {
		
	}*/
	
	
}
