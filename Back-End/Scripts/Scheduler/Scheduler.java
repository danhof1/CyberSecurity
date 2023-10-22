import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Scheduler {
	public void addToScheulde() {
		
		 Picker myPicker = new Picker();
		 sqlMethods mySql = new sqlMethods(); 
		 
		 String recurrence = myPicker.rType();
		 String dateTime = myPicker.datePicker(recurrence); 
		 String activity = myPicker.action();
		 mySql.add(dateTime,activity,recurrence); 
		
		
	}
	
	
	/*public void rmSchedule() {
		//Interface to choose Scheduled events and remove them from the arrayList
	}
	*/
	
	public void viewSchedule(){
		sqlMethods mySql = new sqlMethods(); 
		mySql.viewAll();
	}
	/*
    public void parseSchedule(String FilePath) throws FileNotFoundException, IOException, InterruptedException {
    	//Prototype, can't expand upon this until addToSchedule is finished
    	//Should choose the next scan in line in an SQL Database as long as it is past the system time
    	//
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\Scheduler.txt"))) {
            String line = reader.readLine();

            if (line != null && !line.isEmpty()) {
                LocalDateTime savedDate = LocalDateTime.parse(line);
                while(true) {
	                if (LocalDateTime.now().isAfter(savedDate)) {
	                    ProcessBuilder compiler = new ProcessBuilder("javac", "C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\QuickScan.java");
	                    Process compileProcess = compiler.start();
	                    int compileResult = compileProcess.waitFor();
	
	                    System.out.println("Compilation result: " + compileResult);
	
                        Process runProcess = Runtime.getRuntime().exec("java -cp C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src QuickScan");
                        
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
                    Thread.sleep(1000*60*30); // Sleep for 30 minutes before checking again
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
              
        } 
               
        }
        }
        }
    }*/
}

