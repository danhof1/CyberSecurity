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
	public static int unknown;
	public static int[] ratInfo = new int[3]; //low, medium, high
	public static ScanLog log;
	
	public static long total;
	public static long subTotal;
	public static long incBy;
    public static ScanType type;
	public static clamstart cs;
    final private int MAXLENGTH = 75;
	
    private boolean cancelled;
    
    //Log info    
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
        unknown = 0;
        clamstart.unknown = 0;
        clamstart.ratInfo = new int[3];
        ratInfo = new int[3];
        cancelled = false;
        
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
	        	filePaths.add("C:\\");
	        	break;
	        	
	        case TEST:
	        	filePaths.add("C:\\Users\\Ashley\\Pictures");
	        	//filePaths.add("C:\\Users\\Ashley\\Documents\\ScanTests");
	        	break;	        	
        }
        N = filePaths.size();

        reader = new BufferedReader(new InputStreamReader((clamstart.proc).getInputStream()));
    
        //Updates message
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
        	ratInfo = clamstart.ratInfo;
        	
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
    	//Creates log
        log = new ScanLog();
    	StatusInfo.refresh();
    	
    	
    	
        String line;
        for (int i = 0; i < filePaths.size(); i++) 
        {        	
            //Runs clamdscan
            //Process proc = Runtime.getRuntime().exec("C:\\Program Files\\ClamAV\\clamdscan.exe --recursive " + filePaths.get(i));
            Process proc = Runtime.getRuntime().exec("C:\\Program Files\\ClamAV\\clamdscan.exe --move=\"" + System.getProperty("user.dir") + "\\Quarantine\"" + filePaths.get(i));
        	
            //Quits scan
            if(!ScanManager.liveScan())
            {
            	updateMessage("Scan Cancelled");
            	System.out.println("Scan Cancelled");
            	proc.destroy();
            	
            	//Write log
            	int[] stats = {count, unknown, rats, ratInfo[0], ratInfo[1], ratInfo[2]};
                log.writeLog(false, stats);
            	cancelled = true;
                
            	break;
            }
            //Waits until clamscan is done
            proc.waitFor();
        }
        System.out.println("Done!");
        
        //Write log
        if(!cancelled)
        {
            updateMessage("Scan Completed");
        	int[] stats = {count, unknown, rats, ratInfo[0], ratInfo[1], ratInfo[2]};
        	log.writeLog(true, stats);
        }
        
        StatusInfo.refresh();
        ScanManager.killScan();
    }

	@Override
	protected Object call() throws Exception
	{
    	updateProgress(0, 10);

    	ScanManager.startScan();
    	System.out.println("Calling Scan");
    	//Gets total size
		for(int i = 0; i < N; i++)
		{
			total += CountFiles.dirCount(new File(filePaths.get(i)));
		}
		System.out.println("Total: " + total);
    	scanFiles();

		return null;
	}

    
}
