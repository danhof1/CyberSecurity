package Backend.Scheduler;
import java.util.HashMap;

import Backend.Scans.Scan2.ScanType;

public class actionMap
{
	//private static HashMap<String, ScanType> matchUps = new HashMap<String, ScanType>();
	private static HashMap<String, Integer> matchUps2 = new HashMap<String, Integer>();

	
	public actionMap()
	{
	    matchUps2.put("Daily", 1);
	    matchUps2.put("Weekly", 7);
	    matchUps2.put("Monthly", 30);
	}
	public int chooseRecurrence(String Recurrence) {
	    
	    return matchUps2.get(Recurrence);
	   //matchUps.put("Backup", ###);
	}
}
