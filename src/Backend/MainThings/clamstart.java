package Backend.MainThings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class clamstart {

    public void startClam() throws IOException, InterruptedException {
        try {
            Process proc = Runtime.getRuntime().exec("C:\\Program Files\\ClamAV\\clamd.exe");
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            boolean firstLineReceived = false; // Flag to track if the first line has been received
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (!firstLineReceived) {
                    // Send a readiness signal as soon as the first line is received
                    System.out.println("Sending readiness signal...");
                    // Use this signal to indicate that the next thread can start
                    synchronized (this) {
                        this.notify();
                    }
                    firstLineReceived = true;
                    Thread.sleep(1000);
                }
            }
            
            proc.waitFor();
        } catch (Exception e) {
            System.out.println("clamd could not be found.");
        }
    }
}
