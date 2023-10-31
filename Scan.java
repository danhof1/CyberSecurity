package Backend.Scans;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class Scan 
{
    private List<String> filePaths;
	private ArrayList<Node> objects;
	
	private int count;
	private int rats;
	private int total;	//scan bar denominator
	private int current;	//scan bar numerator
    private int ratio;
	
	
	private ArrayList<Process> procs;	
	private Text curDir;
	private ProgressBar prog;
	private Text scanPercent;
	private Text filesScanned;
	private Text ratsFound;
	
	//Changed to global variable so the scan can be cancelled
    
    public Scan() 
    {
        this.filePaths = new ArrayList<>();
        current = 0;
        count = 0;
        rats = 0;
    }

    /**
     * New constructor
     * 0: curDir
	 * 1: prog
	 * 2: scanPercent
	 * 3: filesScanned
	 * 4: ratsFound
     */
    public Scan(ArrayList<Node> objs)
    {
        this.filePaths = new ArrayList<>();
        objects = objs;
        current = 0;
        count = 0;
        rats = 0;
        
    	curDir = (Text)objects.get(0);
    	prog = (ProgressBar)objects.get(1);
    	scanPercent = (Text)objects.get(2);
    	filesScanned = (Text)objects.get(3);
    	ratsFound = (Text)objects.get(4);
        //System.out.println("Constructor");

    }


    public void addFilePath(String path) 
    {
        filePaths.add(path);
        //total += FileCount.count(new File(path));
    }

    public void addArray(List<String> directories) 
    {
        filePaths = directories;
        total = 0;
    }

    public void Scanner() throws IOException, InterruptedException, java.lang.NullPointerException
    {    	   	 	
    	ScanManager.startScan();
    	curDir.setText("Starting Scan...");
    	scanPercent.setText("0%");
    	filesScanned.setText("0 Files Scanned");
    	ratsFound.setText("0 Files Scanned");
    	
    	//total = totalSize();
    	//System.out.println("Total size: " + total);
    	 
    	Random rand = new Random(); //temp
    	
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
            	if(line.equals("----------- SCAN SUMMARY -----------") || line.isEmpty())
            	{
            		break;
            	}
            	
            	
            	/*TEMPORARY: DO NOT LEAVE IN
            	 * This will change the string as if a threat is found for testing purposes
            	 * ~Ashley
            	 */
            	
            	if(rand.nextInt(10) == 0)
            	{
            		System.out.println("Simulating threat");
            		line = line.replace("OK", "FOUND");
            	}
            	System.out.println(line);
            	
            	//END TEMP
            	
                String path = line.substring(0, line.lastIndexOf(':'));
                
                File temp = new File(path);
                
                System.out.println(path);
                curDir.setText(path);	//Updates status text
                
                System.out.println(count);
                count++;
                filesScanned.setText(count + " Files Scanned");
                

                if(!(line.substring(line.lastIndexOf(' ')+1).equals("OK")))
                {
                	rats++;
                	ratsFound.setText(rats + " Rats Found");
                }
                
                	
                //updates current (progress bar numerator) w/ previous file scanned
            	//current += (new File(line).length());
            	//System.out.println(current + "/" + total);
            }
                
            if(!ScanManager.liveScan())
            {
            	kill();
            	break;
            }
            
            //Waits until clamscan is done
            proc.waitFor();
            curDir.setText("Done!");
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
    		count += temp.getTotalSpace();
    	}
    	
    	return count;
    }
    
}
