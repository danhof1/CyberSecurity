import java.io.*;

// clamscan C:\\Users\\chemi\\Downloads\\Arcade - Street Fighter III 3rd Strike - Sound Effects

public class QuickScan {
    public static void  main(String[] args) throws IOException, InterruptedException {
        // test one 
    	BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\QuickScan.txt"));
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
    }
}