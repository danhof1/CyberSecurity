import java.io.*;
public class CustomScan {
    public static void  main(String[] args) throws IOException, InterruptedException {
        // test one 
    	BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\CustomScan.txt"));
    	String line= ""; 
    	while((line = reader.readLine()) != null) {
    		System.out.println(line); 
    		Process proc = Runtime.getRuntime().exec("C:\\Program Files/ClamAV/clamscan.exe --recursive " + line);
    		BufferedReader reader2= new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line2 = "";
            while((line2 = reader2.readLine()) != null) {
                System.out.print(line2 + "\n");
            }
            proc.waitFor();  
        }
       reader.close();
       String filePath = "C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\CustomScan.txt"; // Replace with the actual path to your file
       
       try {
           FileWriter fileWriter = new FileWriter(filePath);
           fileWriter.close();
       } catch (IOException e) {
           System.out.println("An error occurred: " + e.getMessage());
       }
    }
}