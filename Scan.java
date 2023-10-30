package Backend.Scans;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.text.Text;

public class Scan 
{
    private List<String> filePaths;
	private ArrayList<Node> objects;
	
	private int total;	//scan bar denominator
	private int current;	//scan bar numerator
    private int ratio;
	
	
	private ArrayList<Process> procs;	
	
	//Changed to global variable so the scan can be cancelled
    
    public Scan() 
    {
        this.filePaths = new ArrayList<>();
        current = 0;   
    }

    /**
     * New constructor
     * 
     */
    public Scan(ArrayList<Node> objs)
    {
        this.filePaths = new ArrayList<>();
        objects = objs;
        current = 0;
    }


    public void addFilePath(String path) 
    {
        filePaths.add(path);
        //total += FileCount.count(new File(path));
    }

    public void addArray(List<String> directories) 
    {
        filePaths = directories;
        /*total = 0;
        
        for(int i = 0; i < filePaths.size(); i++)
        {
        	total += FileCount.count(new File(filePaths.get(i)));
        	System.out.println(i);
        }
        System.out.println("done");*/
    }

    public void Scanner() throws IOException, InterruptedException
    {    	
    	ScanManager.startScan();
    	Text statTxt = (Text)objects.get(0);
    	total = totalSize();
    	System.out.println("Total size: " + total);
    	    	
        for (int i = 0; i < filePaths.size(); i++) 
        {
        	//Prints to console
            //System.out.println(filePaths.get(i));
            
            //Runs clamdscan
            Process proc = Runtime.getRuntime().exec("C:\\Program Files\\ClamAV\\clamscan.exe --recursive " + filePaths.get(i));
            
            //Reads the file
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = "";
            
            //Iterates through file
            while ((line = reader.readLine()) != null && ScanManager.liveScan()) //added proc.isAlive so it ends if scan is cancelled
            {
                //System.out.print(line + "\n");
            	
                line =  line.substring(0, line.lastIndexOf(':'));
                statTxt.setText(line);	//Updates status text
                
                //updates current (progress bar numerator) w/ previous file scanned
            	current += (new File(line).length());
            	System.out.println(current + "/" + total);
            }
                
            if(!ScanManager.liveScan())
            {
            	kill();
            	break;
            }
            
            //Waits until clamscan is done
            proc.waitFor();
        }
    }
    
    /**
     * kills the scan
     * ~Ashley
     */
    public void kill()
    {
    	System.out.println("Killing scan...");
    	
		try 
		{
			Runtime.getRuntime().exec("Taskkill /IM clamscan.exe /F");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Used for progress bar denominator
     * ~Ashley
     * @return total size of files being scanned
     */
    private int totalSize()
    {
    	int count = 0;
    	for(int i = 0; i < filePaths.size(); i++)
    	{
    		File temp = new File(filePaths.get(i));
    		count += temp.length();
    	}
    	
    	return count;
    }
    
}
