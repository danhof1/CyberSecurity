package Backend.Scans;
import java.io.*;
import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class CustomScan extends Task
{
    private static ArrayList<String> custArray = new ArrayList<String>();
    private static String destinationFileName;//="C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\Custom.tmp";
    
    private File file; //= new File(destinationFileName); //directory to scan i think?
    
	private ArrayList<Node> objects;

    
    public CustomScan()
    {
    	
    }
    
    public CustomScan(String path)
    {
    	
    }
    
    public static void setPath(String path)
    {
    	custArray = new ArrayList<String>();

    	destinationFileName = path;
    	custArray.add(path);
    }
    
    /**
     * I don't THINK this is needed?
     * File choosing is handled by frontend
     * ~Ashley
     * @return
     * @throws IOException
     */
    public ArrayList<String> parseTemp() throws IOException
    {
        try (BufferedReader tempReader = new BufferedReader(new FileReader(destinationFileName))) {
			String tempLine;
			while ((tempLine = tempReader.readLine()) != null) {
			    custArray.add(tempLine);
			}
		}
        file.delete();
        return custArray;
    	
    }

    public void scanFiles(ArrayList<String> selectedDirectories)
    {
        try 
        {
            Scan myScan = new Scan();
            
            if((!new File(destinationFileName).exists()))
            {
            	System.out.println("ERR: File does not exist");
            	return;
            }
            
            myScan.addArray(selectedDirectories);
            myScan.Scanner();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ~Ashley
     */
    public void scanFiles()
    {    	    	
    	if(custArray.isEmpty() || destinationFileName == null)
    	{
    		System.out.println("Cannot find file");
    		return;
    	}
    	
        try 
        {
            Scan myScan = new Scan();
            myScan.addArray(custArray);
            myScan.Scanner();
            
        } 
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }

	@Override
	protected Object call() throws Exception
	{
    	scanFiles();
		return null;
	}
}
