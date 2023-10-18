import java.io.*;

public class FullScan {
    public static void  main(String[] args) throws IOException, InterruptedException {
        // test one 
    	String line= "C:\\"; 
    		System.out.println(line); 
    		Process proc = Runtime.getRuntime().exec("C:\\Program Files\\ClamAV\\clamscan.exe --recursive " + line);
    		BufferedReader reader= new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line2 = "";
            while((line2 = reader.readLine()) != null) {
                System.out.print(line2 + "\n");
            }
            proc.waitFor();  
    }
}