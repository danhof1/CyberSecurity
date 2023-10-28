import java.io.IOException;
import java.util.ArrayList;
/*
 * Legend:
 * Scan Items
	 * 0-Start Clamd
	 * 1-QuickScan
	 * 2-Full Scan
	 * 3-File Selector
	 * 4-Custom Scan
 * WhiteList Items
	 * (5 and 6 Wont run unless run as administrator, wont be shown in demo)
	 * 5-WhiteList 
	 * 6-Remove From Whitelist
 * Schedule Items
	 * 7-Add to Scheduler
	 * 8-View Schedule
	 * 9-View Sorted Schedule
	 * 10-Remove Item from Schedule
	 * 11-Wipe Schedule
	 * 12-Activate Scheduler
 * Other Items
 * 	 * 13-Log Scan Results
 * 
 * */
public class MainClass {
    public static void main(String[] args) throws IOException, InterruptedException {   
    	//Need to create some wait loop so action branch can 
    	//continously take in commands from the front-end
    	actionBranch action = new actionBranch();
        action.actionMethod(11);
    }
}
