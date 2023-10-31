package Backend.Scans;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


import javafx.scene.Node;

public class QuickScan 
{
    private ArrayList<String> quickArray;
    private String username;
    
	private ArrayList<Node> objects;

    
    public QuickScan(ArrayList<Node> objs) 
    {
    	objects = objs;
    	username = System. getProperty("user.name");
        quickArray = new ArrayList<String>(Arrays.asList
        (
        	"C:\\Windows\\System32",
	        "C:\\Windows\\SysWOW64"
	        /*"C:\\Users\\" + username + "\\AppData\\Local\\Temp",
	        "C:\\Users\\" + username + "\\AppData\\Roaming",
	        "C:\\Program Files",
	        "C:\\Program Files (x86)",
	        "C:\\Users\\" + username + "\\Downloads",
	        "C:\\Users\\" + username + "\\Desktop",
	        "C:\\Users\\" + username + "\\Documents",
	        "C:\\Users\\" + username + "\\Pictures",
	        "C:\\Users\\" + username + "\\Music",
	        "C:\\Users\\" + username + "\\Videos",
	        "C:\\Users\\" + username + "\\Favorites",
	        "C:\\Users\\" + username +"\\Contacts",
	        "C:\\Users\\" + username + "\\Links",
	        "C:\\Users\\" + username + "\\Searches",
	        "C:\\Users\\" + username + "\\Saved Games",
	        "C:\\Users\\" + username + "\\OneDrive"*/
        ));
    }

    public void scanFiles() throws IOException, InterruptedException 
    {
        Scan myScan = new Scan(objects);
        myScan.addArray(quickArray);

        myScan.Scanner();
    }
}
