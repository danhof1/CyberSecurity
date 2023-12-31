package Backend.MainThings;

import java.io.IOException;
import java.util.ArrayList;

import Backend.Scans.*;
import Backend.Scheduler.*;
import Backend.Pickers.*;

import javafx.scene.Node;

public class actionBranch 
{	
	private String path;
	
	public actionBranch()
	{
		
	}
	
	public void setPath(String p)
	{
		path = p;
	}
	
   
	public Object actionMethod(int n) throws IOException, InterruptedException 
	{
        switch (n) {
        	case 0:
        		clamstart clam = new clamstart();
	        	clam.startClam();
	        	break;
        	case 1:
                QuickScan quick = new QuickScan();
                quick.scanFiles();
                break;
            case 2:
                FullScan full = new FullScan();
                full.scanFiles();
                break; 
            case 3:
            	 SelectDirectories select = new SelectDirectories();
            	 select.Selector();
            	 break;
            case 4:
            	CustomScan cust = new CustomScan(path);
            	cust.scanFiles();
            	//cust.scanFiles(cust.parseTemp());
            	break;
            case 5:
            	WhiteList white= new WhiteList();
            	white.AddToWhitelist();
            	break;
            case 6:
            	WhiteList black= new WhiteList();
            	black.RmWhitelist();
            	break;
            case 7:
            	ScheduleMethods time = new ScheduleMethods();
            	time.addToScheulde();
            	time.viewSchedule();
            	break;
            case 8:
            	ScheduleMethods time2 = new ScheduleMethods();
            	time2.viewSchedule();
            	break;
            case 9:
            	ScheduleMethods time3 = new ScheduleMethods();
            	time3.printSortedSchedule();
            	break;
           /* case 10:
            	ScheduleMethods time4 = new ScheduleMethods();
            	time4.rmSchedule();
            	time4.viewSchedule();
            	break;*/
            case 11:
            	ScheduleMethods time5 = new ScheduleMethods();
            	time5.wipeSchedule();
            	time5.viewSchedule();
            	break;
            case 12:
            	Scheduler time6 = new Scheduler();
            	ScheduleMethods time7 = new ScheduleMethods();
            	time6.Schedule();
            	time7.viewSchedule();
            	break;
            case 13:
            	freshClam myFresh = new freshClam();
            	myFresh.Fresh();
        }
        
        return null;
	}
}
