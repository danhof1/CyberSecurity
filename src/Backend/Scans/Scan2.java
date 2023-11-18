package Backend.Scans;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Backend.MainThings.clamstart;
import Frontend.ScanMonitor;
import Frontend.eFXtend.Palette;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ImageInput;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Scan2 extends Task
{
    private List<String> filePaths;
	private int N;
    
	public static int count;
	public static int rats;
	public static long total;
	public static long subTotal;
	public static long incBy;
    public static ScanType type;
	public static clamstart cs;
    final private int MAXLENGTH = 75;
	
    public static enum ScanType
	{
		QUICK, CUSTOM, FULL, TEST
	}
    
    private BufferedReader reader;
	
	/**
	 * Starts a new scan
	 * @param scan type
	 * @param file path to scan if custom scan, else make NULL
	 */
    public Scan2(ScanType arg0, String file) 
    {
        filePaths = new ArrayList<>();
        count = 0;
        clamstart.count = 0;
        rats = 0;
        clamstart.rats = 0;
        type = arg0;
        total = 0;
        switch(arg0)
        {
	        case QUICK:
	        	filePaths.add("C:\\Windows\\System32");
	        	filePaths.add("\"C:\\\\Windows\\\\SysWOW64\"");
	        	break;
	        case CUSTOM:
	        	filePaths.add(file);
	        	break;
	        case FULL:
	        	//TO ADD
	        	break;
	        	
	        case TEST:
	        	filePaths.add("C:\\Users\\Ashley\\Pictures");
	        	//filePaths.add("C:\\Users\\Ashley\\Documents\\ScanTests");
	        	break;	        	
        }
        N = filePaths.size();

        reader = new BufferedReader(new InputStreamReader((clamstart.proc).getInputStream()));
    
        cs.messageProperty().addListener((observable, oldValue, newValue) ->
    	{
    		int start = newValue.indexOf("C:");
        	int end = newValue.lastIndexOf(":");
        	
        	String line = newValue.substring(start, end);
        	if(line.length() > MAXLENGTH)
        		line = "..." + line.substring(line.length() - MAXLENGTH);
        	
        	updateMessage(line);
        	
        	count = clamstart.count;
        	rats = clamstart.rats;
        	
        	//Update counts            	
        	if(type != ScanType.FULL)
        	{
        		updateProgress(count, total);
        	}
    	});   
    }

    @SuppressWarnings("deprecation")
	public void scanFiles() throws IOException, InterruptedException, java.lang.NullPointerException
    {    
        String line;
        for (int i = 0; i < filePaths.size(); i++) 
        {        	
            //Runs clamdscan
            Process proc = Runtime.getRuntime().exec("C:\\Program Files\\ClamAV\\clamdscan.exe --recursive " + filePaths.get(i));
                    
            //Quits scan
            if(!ScanManager.liveScan())
            {
            	updateMessage("Scan Cancelled");
            	System.out.println("Scan Cancelled");
            	proc.destroy();
            	break;
            }
            //Waits until clamscan is done
            proc.waitFor();
        }
        System.out.println("Done!");
        updateMessage("Scan Completed");
        ScanManager.killScan();
    }

	@Override
	protected Object call() throws Exception
	{
    	ScanManager.startScan();
    	System.out.println("Calling Scan");
    	//Gets total size
    	if(type != ScanType.FULL)
    	{
    		for(int i = 0; i < N; i++)
    		{
    			total += CountFiles.dirCount(new File(filePaths.get(i)));
    		}
    		System.out.println("Total: " + total);
    	}
    	else //TODO: add size of full scan
    	{
    		
    	}
    	scanFiles();

		return null;
	}

    
}
