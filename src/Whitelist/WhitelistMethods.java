package Whitelist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class WhitelistMethods
{
	public static sqlMethodsWhite sqlW;
	private String destinationFileName; 
	public HashSet<String> hashSet;
	
	public WhitelistMethods()
	{
		destinationFileName = "C:\\Program Files\\ClamAV\\clamd.conf";
		sqlW = new sqlMethodsWhite();
		hashSet = new HashSet<>();
		hashSet.add("Windows");
		hashSet.add("Program Files");
		hashSet.add("$Recycle.Bin");
		hashSet.add("Program Files(x86)");
		hashSet.add("Program Data");
		hashSet.add("Recovery");
		hashSet.add("tmp");
		hashSet.add("ys");
		hashSet.add("PerfLogs");
		hashSet.add("Drivers");
	}
	
	public boolean containsForbidden(String filePath) {
		if(filePath.equals("C:\\"))
			return true;
		
		String[] splitArr = filePath.split("\\\\");
		if (hashSet.contains(splitArr[1]))
		{
			System.out.println("You cannot whitlist this path");
			return true;
		}
		return false;
		
	}
	/**
	 * Adds whitelisted file
	 * Called by driver
	 * @param filePath path to file
	 */
    public void AddToWhitelist(String filePath)
    {
    	 
		 if(sqlW.isEmpty()) {
			sqlW.add(1,filePath);
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFileName, true))) {
				writer.write("ExcludePath \"" + filePath + "\"");
			}
			catch(IOException e2) {
				e2.printStackTrace();
			}
			
		 }
		 
		 else {
			 	try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(destinationFileName, true))) {
					writer2.write("\nExcludePath \"" + filePath + "\"");
				}
				catch(IOException e2) {
					e2.printStackTrace();
				}
			 sqlW.add(sqlW.getLastID()+1,filePath);
		 }
        
    }

    private void replaceLine(String filePath) throws IOException {
    	File inputFile = new File(destinationFileName);
        File tempFile = new File(destinationFileName+".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String lineToReplace = filePath;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Write the new line instead of the line to replace
                if (currentLine.equals("ExcludePath "+"\""+lineToReplace+"\"")) {
                	System.out.println(currentLine);
                    writer.write("");
                } 
                else {
                    writer.write(currentLine);
                }
                writer.newLine();
            }
            reader.close();
            writer.flush();
            writer.close();
        }

        // Replace the original file with the temp file
        if (!inputFile.delete()) {
            System.out.println("Could not delete original file");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temp file to original file name");
        }
    }
        
    
    public void rmWhitelist(int n) throws IOException
    {
		//sqlMethodsWhite sqlW = new sqlMethodsWhite();
		String filePath = sqlW.getPathbyId(n);
		System.out.println(filePath);
		replaceLine(filePath);
		sqlW.rmItem(n);
		sqlW.fixID(n);
	}
	public void wipeWhitelist() throws IOException {
		for(int i=1;i<=sqlW.getLastID();i++) {
			rmWhitelist(i);
		}
		//sqlMethodsWhite sqlW = new sqlMethodsWhite();
		sqlW.wipeTable();
		
	}
	public void viewWhitelist(){
		//sqlMethodsWhite sqlW = new sqlMethodsWhite(); 
		sqlW.viewAll();
	}


}
