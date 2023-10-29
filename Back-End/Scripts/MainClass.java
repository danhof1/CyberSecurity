import java.net.*;
import java.io.*;
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
        /*ServerSocket serverSocket = new ServerSocket(9999); // Choose a port
        System.out.println("Server is running and waiting for connections...");

        while (true) {
            Socket socket = serverSocket.accept(); // Wait for a connection
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int actionNumber = Integer.parseInt(in.readLine());
            
            actionBranch action = new actionBranch();
            action.actionMethod(actionNumber);
            
            socket.close(); // Close the connection after processing
        }*/
    	actionBranch action = new actionBranch();
        action.actionMethod(11);
    }
}

