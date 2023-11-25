package Backend.Scheduler;
import java.util.HashMap;

import Backend.Scans.Scan2.ScanType;

public class actionMap
{
	//private static HashMap<String, ScanType> matchUps = new HashMap<String, ScanType>();
	private static HashMap<String, Integer> matchUps2 = new HashMap<String, Integer>();

	
	public actionMap()
	{	
	    matchUps2.put("ONCE", 0);
	    matchUps2.put("DAILY", 1);
	    matchUps2.put("WEEKLY", 7);
	    matchUps2.put("MONTHLY", 30);
	}
	public int chooseRecurrence(String Recurrence)
	{			
		Integer num = matchUps2.get(Recurrence);
		
	    return num;
	   //matchUps.put("Backup", ###);
	}
}
