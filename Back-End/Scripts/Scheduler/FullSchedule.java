import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class FullSchedule {

    public static void main(String[] args) throws IOException, InterruptedException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\FullScanSchedule.txt"))) {
            String line = reader.readLine();

            if (line != null && !line.isEmpty()) {
                LocalDateTime savedDate = LocalDateTime.parse(line);
                while(true) {
	                if (LocalDateTime.now().isAfter(savedDate)) {
	                    ProcessBuilder compiler = new ProcessBuilder("javac", "C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\FullScan.java");
	                    Process compileProcess = compiler.start();
	                    int compileResult = compileProcess.waitFor();
	
	                    System.out.println("Compilation result: " + compileResult);
	
                        Process runProcess = Runtime.getRuntime().exec("java -cp C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src FullScan");
                        
                        // Read and print output
                        InputStream inputStream = runProcess.getInputStream();
                        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
                        
                        while ((line = inputReader.readLine()) != null) {
                            System.out.println(line);
                        }

                        int runResult = runProcess.waitFor();

                        System.out.println("Run result: " + runResult);
                        break;
                }
                try {
                    Thread.sleep(1000); // Sleep for 1 second before checking again
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
              
        } 
               
        }
        }
        }
    }
}

