package Backend.Scans;
import java.io.*;
import java.util.ArrayList;

import javafx.scene.Node;

public class FullScan 
{
	private ArrayList<Node> objects;

	
	public FullScan(ArrayList<Node> objs) 
	{
    	objects = objs;
	}
	
    public void scanFiles() throws IOException, InterruptedException 
    {
        Scan myScan = new Scan(objects);
        myScan.addFilePath("C:\\");
        myScan.Scanner();
    }
}
