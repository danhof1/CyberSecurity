package Backend.Scans;
import java.io.*;
import java.util.ArrayList;

import javafx.scene.Node;

public class FullScan 
{
	private ArrayList<Node> objects;
	
    public void scanFiles() throws IOException, InterruptedException 
    {
        Scan myScan = new Scan();
        myScan.addFilePath("C:\\");
        myScan.Scanner();
    }
}
