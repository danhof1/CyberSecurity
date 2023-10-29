package Backend.Scans;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.text.Text;

public class Scan 
{
    private List<String> filePaths;
	private ArrayList<Node> objects;
	private int total;
	private int current;    
    
    
    public Scan() 
    {
        this.filePaths = new ArrayList<>();
        current = 0;
    }

    /**
     * New constructor
     * 
     */
    public Scan(ArrayList<Node> objs)
    {
        this.filePaths = new ArrayList<>();
        objects = objs;
        current = 0;
    }


    public void addFilePath(String path) 
    {
        filePaths.add(path);
        total++;
    }

    public void addArray(List<String> directories) 
    {
        filePaths = directories;
        total = filePaths.size();
    }

    public void Scanner() throws IOException, InterruptedException
    {
    	Text statTxt = (Text)objects.get(0);
    	
        for (int i = 0; i < filePaths.size(); i++) 
        {
        	//Prints to console
            System.out.println(filePaths.get(i));
            
            //Runs clamscan
            Process proc = Runtime.getRuntime().exec("C:\\Program Files\\ClamAV\\clamscan.exe --recursive " + filePaths.get(i));
            
            //Reads the file
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = "";
            
            //Iterates through file
            while ((line = reader.readLine()) != null) 
            {
                System.out.print(line + "\n");
                current++;
                String str = new String(line + "\n" + current + "/" + total);
            }
            
            //Waits until clamscan is done
            proc.waitFor();
        }
    }
}
