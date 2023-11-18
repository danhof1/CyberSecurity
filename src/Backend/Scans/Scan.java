package Backend.Scans;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Backend.MainThings.clamstart;
import Frontend.ScanMonitor;
import Frontend.eFXtend.Palette;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ImageInput;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Scan //extends Thread
{
    private List<String> filePaths;
	private ArrayList<Node> objects;
	
	private int count;
	private int rats;
	private long total;	//scan bar denominator
	//private long current;	//scan bar numerator
    private double ratio;
	
	
	private ArrayList<Process> procs;	
	private Text curDir;
	private ProgressBar prog;
	private Text scanPercent;
	private Text filesScanned;
	private Text ratsFound;
	private boolean firstLineReceived = false;
	
	//Changed to global variable so the scan can be cancelled
    
    public Scan() 
    {
        this.filePaths = new ArrayList<>();
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
    /*public void run() {
    	try {
			Scanner();
		} catch (NullPointerException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
    public void Scanner() throws IOException, InterruptedException, java.lang.NullPointerException
    {    
    	ScanManager.startScan();
    	total = filePaths.size();
    	//clamstart.total = filePaths.size();
    	//clamstart.count = 0;
    	//clamstart.rats = 0;
    	   
    	//System.out.println("Total: " + clamstart.total);
        for (int i = 0; i < filePaths.size(); i++) 
        {
        	System.out.println("TEST: " + filePaths.get(i));
        	//files in current dir:
        	long subTotal = CountFiles.dirCount(new File(filePaths.get(i)));

        	//clamstart.subTotal = subTotal;
        	//System.out.println("Subtotal: " + clamstart.subTotal);
        	
        	//Prints to console                    	
            //Runs clamdscan
            Process proc = Runtime.getRuntime().exec("C:\\Program Files\\ClamAV\\clamdscan.exe --recursive " + filePaths.get(i));
            
            if(!ScanManager.liveScan())
            {
            	System.out.println("Dead");
            	proc.destroy();
            	break;
            }
            //Waits until clamscan is done
            proc.waitFor();
            System.out.println("test");
            //clamstart.count = ;      
        }
        System.out.println("Done!");
        ScanManager.killScan();
    }
}
