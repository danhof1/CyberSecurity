package Backend.Scans;
import java.io.*;
import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.scene.Node;

public class FullScan extends Task
{
	
    public void scanFiles() throws IOException, InterruptedException 
    {
        Scan myScan = new Scan();
        myScan.addFilePath("C:\\");
        myScan.Scanner();
    }

	@Override
	protected Object call() throws Exception
	{
		scanFiles();
		return null;
	}
}
