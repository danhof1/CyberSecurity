package Backend.MainThings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

<<<<<<< HEAD
import javafx.concurrent.Task;

public class freshClam extends Task
{
=======
public class freshClam {
>>>>>>> ab255db1312f2e867005c54eca788cfed00222ca

    public void Fresh() throws IOException, InterruptedException {
        try {
            Process proc = Runtime.getRuntime().exec("C:\\Program Files\\ClamAV\\freshclam.exe");
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            //boolean firstLineReceived = false; // Flag to track if the first line has been received
<<<<<<< HEAD
            String multiLine = new String();
            
            
            while ((line = reader.readLine()) != null) 
            {
                System.out.println("Fresh: " + line);
                updateMessage(line);
               
            }
            
            proc.waitFor();
            
            /**
             * ~Ashley
             */
=======
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
              /*  if (!firstLineReceived) {
                    // Send a readiness signal as soon as the first line is received
                    System.out.println("Sending readiness signal...");
                    // Use this signal to indicate that the next thread can start
                    synchronized (this) {
                        this.notify();
                    }
                    firstLineReceived = true;
                    Thread.sleep(1000);
                }*/
            }
            
            proc.waitFor();
>>>>>>> ab255db1312f2e867005c54eca788cfed00222ca
        } catch (Exception e) {
            System.out.println("Freshclam could not be found.");
        }
    }
<<<<<<< HEAD

	@Override
	protected Object call() throws Exception
	{
		Fresh();
		return null;
	}
=======
>>>>>>> ab255db1312f2e867005c54eca788cfed00222ca
}
