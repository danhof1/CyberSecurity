package Backend.MainThings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.concurrent.Task;

public class freshClam extends Task
{

    public void Fresh() throws IOException, InterruptedException {
        try {
            Process proc = Runtime.getRuntime().exec("C:\\Program Files\\ClamAV\\freshclam.exe");
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            //boolean firstLineReceived = false; // Flag to track if the first line has been received
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
        } catch (Exception e) {
            System.out.println("Freshclam could not be found.");
        }
    }

	@Override
	protected Object call() throws Exception
	{
		Fresh();
		return null;
	}
}
