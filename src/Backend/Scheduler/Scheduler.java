package Backend.Scheduler;
import java.io.*;
import java.util.concurrent.Executors;
import java.time.LocalDateTime;
import Backend.Scans.Scan2;
import Backend.Scans.ScanManager;
import javafx.concurrent.Task;
import Backend.Scans.Scan2.ScanType;
import java.util.concurrent.ExecutorService;
public class Scheduler extends Task
{

	public static Scan2 scan = null;

	
	public void checkSchedule() throws FileNotFoundException, IOException, InterruptedException
	{	
	    	//Prototype, can't expand upon this until addToSchedule is finished
	    	//Should choose the next scan in line in an SQL Database as long as it is past the system time
		    	sqlMethods mySQL = new sqlMethods();

		    	//Get next event params
		    	String recentDate = mySQL.MostRecentDT();
		    	String recentAct = mySQL.mostRecentACT();
		    	String recentRec = mySQL.mostRecentRec();
		    	int recentInd = mySQL.mostRecentInd();
		    	String recentFile = mySQL.mostRecentF();
		    	String dayOfWeek = mySQL.MostRecentDayOfWeek();
		    	actionMap myKey = new actionMap(); 
		    	int lastInd=mySQL.getLastID();
		    	//int actionKey = ScanType.valueOf(recentRec);
		    	//gets recurrance value (days)
		    	
		    	if(recentRec == null)
		    	{
		    		updateMessage("NONE");
		    		return;
		    	}
		    	
		    	int recKey = myKey.chooseRecurrence(recentRec);
		    	
		    	//parse current time String --> Time
		    	LocalDateTime savedDate = LocalDateTime.parse(recentDate);
		    	
		    	//Creates the time for the edge-case
		    	LocalDateTime edgeTime = savedDate.plusMinutes(4).plusSeconds(59);
                
		    	//Increment for next recur.
		    	String newTime = savedDate.plusDays(recKey).toString();
		    	System.out.println(LocalDateTime.now());
		    	System.out.println(edgeTime);
		    	System.out.println(LocalDateTime.now().isAfter(edgeTime));
		    	if(LocalDateTime.now().isAfter(edgeTime)){
		    		System.out.println("It is 5+ minutes past scheduled event. The event has been moved or deleted");
		    		mySQL.rmItem(recentInd);
		    		if(!recentRec.equals("Once")) {
			    		mySQL.add(lastInd, newTime, recentAct,recentRec,recentFile,dayOfWeek);
                	}
		    	}
		    	
		    	//Executes scan
		    	ExecutorService executorService = Executors.newFixedThreadPool(3); //number of threads?
		    	
		    	
		    	
		    	//If event time matches current time
		    	if (LocalDateTime.now().isAfter(savedDate))
                {	
		    		if(recentAct.equals("CUSTOM"))
		    				ScanManager.custPath = recentFile;
		    		
		    		updateMessage(recentAct);
		    		
			    	/*if(recentAct.contains("QUICK"))
			    	{
			    		System.out.println("Quick Scan");
			    		//scan = new Scan2(ScanType.QUICK, null);			    		
			    	}
			    	else if(recentAct.contains("CUSTOM"))
			    	{
			    		System.out.println("Custom Scan");
			    		//scan = new Scan2(ScanType.CUSTOM, ScanManager.custPath);			    		
		                
			    	}
			    	else 
			    	{
			    		System.out.println("Full Scan");
			    		//scan = new Scan2(ScanType.FULL, null);	
			    	}
			    	//executorService.execute(scan);
			    	*/
			    	//remove event --> add next event
                	
                	mySQL.rmItem(recentInd);
                	if(!recentRec.equals("Once")) {
                		mySQL.add(lastInd, newTime, recentAct,recentRec,recentFile,dayOfWeek);
                	}
                }
		    	else //no event found
		    	{
		    		updateMessage("NONE");
		    	}
               
	}

	@Override
	protected Object call() throws Exception
	{
		System.out.println("Starting scheduler");
		
		while(true)
		{
			System.out.println("Checking for scheduled events...");
			checkSchedule();
						
			try
			{
				System.out.println("Waiting...");
				Thread.sleep(1000*60); // Sleep for 1 minute before checking again
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			} 
		}
		
		//return null;
	}
}