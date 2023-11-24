package Backend.MainThings;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import Backend.Scans.StatusCheck;
import Backend.Scans.StatusCheck.Severity;

import Backend.Scans.ScanManager;
import Frontend.ScanMonitor;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import Backend.Scans.CountFiles;
import Backend.Scans.Scan;
import Backend.Scans.Scan2;

public class clamstart extends Task
{
	
	public static int count;
	public static int rats;
	public static int[] ratInfo = new int[3];
	public static int unknown;
	/*public static long total;
	public static long subTotal;
	public static long incBy;*/
	public static Process proc;
	
    public void startClam() throws IOException, InterruptedException {
    	
    	try {
            proc = Runtime.getRuntime().exec("C:\\Program Files\\ClamAV\\clamd.exe");
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            boolean firstLineReceived = false; // Flag to track if the first line has been received
            updateValue("Loading...");
            while ((line = reader.readLine()) != null)
            {
              System.out.println(line);
              
              //Startup
              if (!firstLineReceived)
              {
                    // Send a readiness signal as soon as the first line is received
                    System.out.println("Sending readiness signal...");
                    updateValue("All ready!");

                    // Use this signal to indicate that the next thread can start
                    synchronized (this)
                    {
                        this.notify();
                    }
                    firstLineReceived = true;
                    Thread.sleep(1000);
                }
              if(line.contains("C:"))
    		  {
            	  //TEMP RAT TESTER
            	 /*if(line.contains("evilRat.txt"))
            	  {	
            		  System.out.println("SEVERITY TEST:");
            		  line = line.replace("OK", "T3st.Malware.Agent-123 FOUND");
            		  System.out.println(line);
            	  }*/
            	  
            	  	count++;
            	  	Severity sev = StatusCheck.check(line);
            	  	
            	  	//Increments rats and ratInfo based on severity level
            	  	switch (sev)
            	  	{
            	  		case SAFE:
            	  			break;
            	  			
            	  		case UNKNOWN:
            	  			unknown++;
            	  			break;
            	  		
            	  		default:
            	  			rats++;
            	  			Scan2.log.writeRat(line.substring(line.indexOf("C:")));
            	  			switch(sev)
            	  			{
            	  				case LOW:
            	  					ratInfo[0]++;
            	  					break;
            	  				case MEDIUM:
            	  					ratInfo[1]++;
            	  					break;
            	  				case HIGH:
            	  					ratInfo[2]++;
            	  					break;
            	  			}
            	  		}
            	  
            	  updateMessage(line);
    		  }
            }
            proc.waitFor();
        } catch (Exception e) {
            System.out.println("clamd could not be found.");
            updateMessage("An error has occured, please hit \"Cancel\"");
        }
    }

	@Override
	protected Object call() throws Exception
	{
		startClam();
		return null;
	}
}
