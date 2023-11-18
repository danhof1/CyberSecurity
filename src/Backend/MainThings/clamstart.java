package Backend.MainThings;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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
            	  /*if(count % 10 == 0)
            	  {
            		  line = line.substring(0, line.lastIndexOf(' ')) + "FOUND";
            	  }*/
            	  
            	  
            	  updateMessage(line);
            	  count++;
            	  if(line.substring(line.lastIndexOf(':')+1).equals("FOUND"))
            	  {
            		  rats++;
            	  }
    		  }
            }
            proc.waitFor();
        } catch (Exception e) {
            System.out.println("clamd could not be found.");
        }
    }

	@Override
	protected Object call() throws Exception
	{
		startClam();
		return null;
	}
}
