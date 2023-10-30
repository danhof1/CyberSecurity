package Backend.Scans;
import java.io.*;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.text.Text;

public class CustomScan 
{
    private ArrayList<String> custArray; //= new ArrayList<String>();
    private String destinationFileName;//="C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\Custom.tmp";
    
    private File file; //= new File(destinationFileName); //directory to scan i think?
    
	private ArrayList<Node> objects;

    
    public CustomScan()
    {
    	custArray = new ArrayList<String>();
    }
    
    /**
     * ~Ashley
     * @param objs stuff from frontend
     */
    public CustomScan(ArrayList<Node> objs) 
    {
    	objects = objs;
    	
    	custArray = new ArrayList<String>();
    	destinationFileName = ((Text)objects.get(0)).getText(); //sets path
    	custArray.add(destinationFileName); //adds path to array
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
            myScan.addArray(selectedDirectories);
            myScan.Scanner();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ~Ashley
     */
    public Scan scanFiles()
    {
    	
    	if(custArray.isEmpty() || destinationFileName == null)
    		return null;
    	
        try 
        {
            Scan myScan = new Scan(objects);
            myScan.addArray(custArray);
            myScan.Scanner();
            return myScan;

        } catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
