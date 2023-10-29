package Backend.Scheduler;
import java.io.*;
import java.time.LocalDateTime;
import Backend.MainThings.*;
public class Scheduler {

	public void Schedule() throws FileNotFoundException, IOException, InterruptedException {
	    	//Prototype, can't expand upon this until addToSchedule is finished
	    	//Should choose the next scan in line in an SQL Database as long as it is past the system time
		    	sqlMethods mySQL = new sqlMethods();
		    	actionBranch myAction = new actionBranch();

		    	
		    	String recentDate = mySQL.MostRecentDT();
		    	String recentAct = mySQL.mostRecentACT();
		    	String recentRec = mySQL.mostRecentRec();
		    	int recentInd = mySQL.mostRecentInd();
		    	
		    	
		    	
		    	
		    	actionMap myKey = new actionMap();
		    	
		    	int actionKey = myKey.chooseAction(recentAct);
		    	int recKey = myKey.chooseRecurrence(recentRec);
		    	
		    	LocalDateTime savedDate = LocalDateTime.parse(recentDate);
                
		    	String newTime = savedDate.plusDays(recKey).toString();
		    	
		    	
		    	while(true) {
	                if (LocalDateTime.now().isAfter(savedDate)) {
	                	myAction.actionMethod(actionKey);
	                	int lastInd=mySQL.getLastID();
	                	mySQL.rmItem(recentInd);
	                	if(!recentRec.equals("Once")) {
	                		mySQL.add(lastInd, newTime, recentAct,recentRec );
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
    
