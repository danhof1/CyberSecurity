package Backend.MainThings;

import java.io.IOException;
import java.util.ArrayList;

import Backend.Scans.*;
import Backend.Scheduler.*;
import Backend.Pickers.*;

import javafx.scene.Node;

public class startUpapplets
{	
	private String path;
	
	public startUpapplets()
	{
		
	}
	
	public void setPath(String p)
	{
		path = p;
	}
	
	public Object startUpBranch(int n) throws IOException, InterruptedException 
	{
        switch (n) {
	        case 0:
	        	clamstart clam = new clamstart();
	        	clam.startClam();
	        	break;
        }
        
        return null;
	}
}
