package Backend.Scans;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Frontend.ScanMonitor;
import Frontend.eFXtend.Palette;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ImageInput;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Scan 
{
    private List<String> filePaths;
	private ArrayList<Node> objects;
	
	private int count;
	private int rats;
	private long total;	//scan bar denominator
	private long current;	//scan bar numerator
    private double ratio;
	
	
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
    	//curDir.setText("Starting Scan...");
    	//scanPercent.setText("0%");
    	//filesScanned.setText("0 Files Scanned");
    	//ratsFound.setText("0 Rats Found");
		//prog.setProgress(0);
		
		if(filePaths.size() == 1)
		{
			total = CountFiles.dirSize(new File(filePaths.get(0)));
		}
		
    	//total = totalSize();
    	//System.out.println("Total size: " + total);
    	     	
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
            	
                String path = line.substring(0, line.lastIndexOf(':'));
                System.out.println(line);
                
                
                
                File temp = new File(path);
                
                //curDir.setText(path);	//Updates status text
                ScanMonitor.setDir(path);
                
                count++;
                
        		if(filePaths.size() == 1)
        		{
        			ratio = (int)(((double)current / total)*100);
        			current += temp.length();
        	    	//prog.setProgress((double)current/total);
        	    	//scanPercent.setText(ratio + "%");
        	    	System.out.println(ratio);

        		}
                
                
                //System.out.println(current +"/" + total);
                //filesScanned.setText(count + " Files Scanned");
                

                if(line.substring(line.lastIndexOf(' ')+1).equals("FOUND"))
                {
                	rats++;
                	//ratsFound.setText(rats + " Rats Found");
                }
                
                	
            }
                
            if(!ScanManager.liveScan())
            {
            	kill();
            	break;
            }
            
            //Waits until clamscan is done
            proc.waitFor();
            System.out.println("Done!");
            /*curDir.setText("Done!");
            scanPercent.setText("100%");
            prog.setProgress(1);
            Text back = (Text)objects.get(5);
            back.setText("Back");
            
            Text scanTitle = (Text)objects.get(6);
            Rectangle ratGif = (Rectangle)objects.get(7);
            String ratPath = ((ImageInput)ratGif.getEffect()).getSource().getUrl();
            ratPath = ratPath.substring(0, ratPath.lastIndexOf('/')+1);
            
            if(rats == 0) //no threats
            {
            	scanTitle.setText("You're all good!");
            	ratPath = ratPath + "cheese.jpg";
            	
            }
            else //threats found
            {
            	scanTitle.setText(ratsFound.getText());
            	ratPath = ratPath + "ratCage.png";

            	
            	for(int j = 8; j <= 9; j++)
            	{
            		Rectangle statusIcon = (Rectangle)objects.get(j);
            		String statusPath = ((ImageInput)statusIcon.getEffect()).getSource().getUrl();
            		
            		if(statusPath.contains("Good"))
            		{
            			statusPath = statusPath.replace("Good", "Bad");
                    	Palette.changeImg(statusIcon, statusPath);
            		}
            		
            		else if(statusPath.contains("Ok"))
            		{
                		statusPath = statusPath.replace("Ok", "Bad");
                    	Palette.changeImg(statusIcon, statusPath);
            		}
            		
            		
            		
            	}
            }
            
        	Palette.changeImg(ratGif, ratPath);
			*/
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
    
    private void incProg()
    {
    }
    
}
