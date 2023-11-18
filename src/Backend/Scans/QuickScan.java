package Backend.Scans;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.concurrent.Task;
import javafx.scene.Node;

public class QuickScan extends Task
{
    private ArrayList<String> quickArray;
    
    public QuickScan() 
    {
    	updateMessage("ERR");
        quickArray = new ArrayList<String>(Arrays.asList
        (
         "C:\\Windows\\System32",
	     "C:\\Windows\\SysWOW64"
        ));
    }

    protected Integer call() throws Exception 
    {
    	scanFiles();
    	return 0;
    }
    
    public void scanFiles() throws IOException, InterruptedException 
    {
        Scan myScan = new Scan();
        myScan.addArray(quickArray);
        myScan.Scanner();
    }
}
