package Backend.Scheduler;
import java.io.*;
import java.util.concurrent.Executors;
import java.time.LocalDateTime;
import Backend.Scans.Scan2;
import Backend.Scans.ScanManager;
import Backend.Scans.Scan2.ScanType;
import java.util.concurrent.ExecutorService;
public class Scheduler {

	public void Schedule() throws FileNotFoundException, IOException, InterruptedException
	{
	    	//Prototype, can't expand upon this until addToSchedule is finished
	    	//Should choose the next scan in line in an SQL Database as long as it is past the system time
		    	sqlMethods mySQL = new sqlMethods();

		    	
		    	String recentDate = mySQL.MostRecentDT();
		    	String recentAct = mySQL.mostRecentACT();
		    	String recentRec = mySQL.mostRecentRec();
		    	int recentInd = mySQL.mostRecentInd();
		    	String recentFile = mySQL.mostRecentF();
		    	String dayOfWeek = mySQL.MostRecentDayOfWeek();
		    	actionMap myKey = new actionMap(); 
		    	
		    	//int actionKey = ScanType.valueOf(recentRec);
		    	int recKey = myKey.chooseRecurrence(recentRec);
		    	
		    	LocalDateTime savedDate = LocalDateTime.parse(recentDate);
                
		    	String newTime = savedDate.plusDays(recKey).toString();
		    	Scan2 scan = null;
		    	ExecutorService executorService = Executors.newFixedThreadPool(3); //number of threads?
		    	while(true) {
	                if (LocalDateTime.now().isAfter(savedDate)) {

				    	if(recentAct.contains("QUICK"))
				    	{
				    		scan = new Scan2(ScanType.QUICK, null);			    		
				    		System.out.println("Quick Scan");
			                executorService.execute(scan);
				    	}
				    	else if(recentAct.contains("CUSTOM"))
				    	{
				    		scan = new Scan2(ScanType.CUSTOM, ScanManager.custPath);			    		
				    		System.out.println("Custom Scan");
			                executorService.execute(scan);
			                
				    	}
				    	else 
				    	{
				    		System.out.println("FULL SCAN");
				    		scan = new Scan2(ScanType.FULL, null);			    		
				    	}
	                	int lastInd=mySQL.getLastID();
	                	mySQL.rmItem(recentInd);
	                	if(!recentRec.equals("Once")) {
	                		mySQL.add(lastInd, newTime, recentAct,recentRec,recentFile,dayOfWeek);
	                	}
	                	
                        break;
                }
                try {
                    System.out.println("Waiting...");
                    Thread.sleep(1000*60); // Sleep for 1 minute before checking again
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
              
                } 
               
        }
	}
}