package Whitelist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class sqlMethodsWhite
{
	
	//Path to sql
	public String jdbcURL = "jdbc:sqlite:" + System.getProperty("user.dir").replace("\\", "/") + "/Databases/sqlite/ratTrap.db";
	
	
	/**
	 * Add whitelisted file
	 * @param id internal only?
	 * @param filePath path to file to whitelist
	 */
	public void add(int id, String filePath)
	{
		//Connect to sql
        try (Connection connection = DriverManager.getConnection(jdbcURL))
        {
        	//insert to whitelist table
            String sql = "INSERT INTO Whitelist (id,filePath) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.setString(2, filePath);
                statement.executeUpdate();
            }

        } catch (SQLException e)
        {
            System.out.println("Error connecting to SQLite Database");
            e.printStackTrace();
        }
    }

	//testing only
	public void viewAll()
	{
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()) {
	        Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = "Select * From Whitelist";

	        

	        ResultSet result = statement.executeQuery(sql);
	        
	        while (result.next()) {
	            int id = result.getInt("id");
	            String filePath = result.getString("filePath");
	            
	            System.out.println(id + "| "+filePath);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        System.out.println("Error connecting to SQLite Database");
	        e.printStackTrace();
	    }
	}
	
	//returns table as array
	public ArrayList<String[]> toArray()
	{
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()){
	        
			Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = " SELECT * FROM Whitelist ORDER BY id";

	        ResultSet result = statement.executeQuery(sql);
	        
	        //output array
	        ArrayList<String[]> arr = new ArrayList<String[]>();
	        
	        
	        while (result.next())
	        {
	        	int id = result.getInt("id");
	        	String filePath = result.getString("filePath");
	            
	            //System.out.println(id + " | " + date_time + " | " + activity+ " | "+ recurrence +"| "+filePath+" | "+dayOfWeek);
	        
	            String[] temp = new String[2];
	            temp[0] = id + "";
	            temp[1] = filePath;
	            arr.add(temp);
	        }
			return arr;

	        
	    } catch (SQLException | ClassNotFoundException e) {
	        System.out.println("Error connecting to SQLite Database");
	        e.printStackTrace();
	    }
		return null;
		
	}

	/**
	 * 
	 * @return true if tabe is empty
	 */
	public boolean isEmpty()
	{
	    try (Connection connection = DriverManager.getConnection(jdbcURL);
	         Statement statement = connection.createStatement()) {

	        String sql = "SELECT COUNT(*) FROM Whitelist;";
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

	/**
	 * 
	 * @param n id for entry
	 * @return file path on success, null on fail
	 */
	public String getPathbyId(int n)
	{
		try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement()) {

			 	String sql = "SELECT filepath FROM Whitelist WHERE id = " + n;

		       
				ResultSet result = statement.executeQuery(sql);

				return result.getString("filepath");
		    } catch (SQLException e) {
		        System.out.println("Error connecting to SQLite Database");
		        e.printStackTrace();
		    }
		return null;
	}
	
	public int getIdbyPath(String path) {
        try (Connection connection = DriverManager.getConnection(jdbcURL);
                 Statement statement = connection.createStatement()) {

                 String sql = "SELECT id FROM Whitelist WHERE filepath = " + path;

               
                ResultSet result = statement.executeQuery(sql);

                return result.getInt("id");
            } catch (SQLException e) {
                System.out.println("Error connecting to SQLite Database");
                e.printStackTrace();
            }
        return 0;
    }
		


	public int getLastID(){
	    int value = -1; // Initialize value

	    try (Connection connection = DriverManager.getConnection(jdbcURL);
		         Statement statement = connection.createStatement())   {
	        Class.forName("org.sqlite.JDBC"); // Load the JDBC driver
	        
	        String sql = "SELECT id FROM Whitelist ORDER BY id DESC LIMIT 1;";

	       

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
	            String sql = "DELETE FROM Whitelist WHERE id =" + id + ";";

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
			 String sql = "UPDATE Whitelist SET id = id - (CASE WHEN id > " + deleted_id + " THEN 1 ELSE 0 END);";

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
	            String sql = "DELETE FROM Whitelist;" ;

	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.executeUpdate();
	            }

	        } catch (SQLException e) {
	            System.out.println("Error connecting to SQLite Database");
	            e.printStackTrace();
	        }
	}

	
	
}
