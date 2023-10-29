package Backend.Scans;
import java.io.*;
import java.util.ArrayList;

public class CustomScan {
    private ArrayList<String> custArray = new ArrayList<String>();
    private String destinationFileName="C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\Custom.tmp";
    private File file = new File(destinationFileName);
    public ArrayList<String> parseTemp() throws IOException {
        try (BufferedReader tempReader = new BufferedReader(new FileReader(destinationFileName))) {
			String tempLine;
			while ((tempLine = tempReader.readLine()) != null) {
			    custArray.add(tempLine);
			}
		}
        file.delete();
        return custArray;
    	
    }

    public void scanFiles(ArrayList<String> selectedDirectories) {
        try {
            Scan myScan = new Scan();
            myScan.addArray(selectedDirectories);
            myScan.Scanner();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
