package openClamd;
import java.io.*;

// start up clamd
// clamdscan cant be used if clamd isn't running

public class clamstart {
    public static void main(String[] args) throws IOException, InterruptedException {
    	// the cmd "net start clamd" only works if clamd is installed as a windows service using "clamd --install-service"
    	// using the path to the .exe is fine but it should be in a place that wont change
    	// check if clamd is properly running by testing clamdscan or checking eclipse under task manager
    	
    	try {
    		Process proc = Runtime.getRuntime().exec("C:\\clamav-1.2.0.win.x64\\clamd");
    		proc.waitFor();
    	} catch(Exception e) {
    		System.out.println("clamd could not be found.");
    	}
    }
}