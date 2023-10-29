package Backend.Scheduler;
import java.util.HashMap;

public class actionMap {
	public int chooseAction(String Action) {
		HashMap<String, Integer> matchUps = new HashMap<String, Integer>();
	    matchUps.put("Quick", 1);
	    matchUps.put("Full", 2);
	    matchUps.put("Custom", 4);
	    return matchUps.get(Action);
	   //matchUps.put("Backup", ###);
	}
	public int chooseRecurrence(String Recurrence) {
		HashMap<String, Integer> matchUps = new HashMap<String, Integer>();
	    matchUps.put("Daily", 1);
	    matchUps.put("Weekly", 7);
	    matchUps.put("Monthly", 30);
	    return matchUps.get(Recurrence);
	   //matchUps.put("Backup", ###);
	}
}
