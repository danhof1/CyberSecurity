import java.io.IOException;
import java.util.ArrayList;
/*
 * Legend:
 * 0-Start Clamd
 * 1-QuickScan
 * 2-Full Scan
 * 3-File Selector
 * 4-Custom Scan
 * 5-WhiteList
 * 6-Remove From Whitelist
 * 7-Scheduler
 * */
public class MainClass {
    public static void main(String[] args) throws IOException, InterruptedException {   
    	//Need to create some wait loop so action branch can 
    	//continously take in commands from the front-end
    	actionBranch action = new actionBranch();
        action.actionMethod(0);
    }
}
