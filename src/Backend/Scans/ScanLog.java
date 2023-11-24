package Backend.Scans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Backend.Scans.StatusCheck.Severity;

public class ScanLog extends FileX
{
	protected String timeStamp;
	private boolean completed;
	private int[] stats;
		//0: files scanned
		//1: unknown files
		//2: Rats Found 
		//3: Low Severity
		//4: Medium Severity
		//5: High Severity
	private ArrayList<String[]> rats; 
		//filepath: platform.category.name-signatureId-revision FOUND	PENDING/ISOLATED/REMOVED/WHITELISTED
		//0: filepath
		//1: platform.category.name-signatureId-revision
		//2: category
	
	private final int timeStampLine = 1;
	private final int completedLine = 3;
	private final int statsLine = 5; // + index
	private final int ratsLine = 13;
	
	/**
	 * Creates new log
	 * @throws IOException 
	 */
	public ScanLog() throws IOException
	{
		super(); //instantiates variables
		
		//Gets current time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yy-HH-mm");
		LocalDateTime now = LocalDateTime.now();  
		timeStamp = (now.format(dtf));
		
		//Name format --> ScanLog MM-dd-yy-HH-mm
		String name = "ScanLogs/ScanLog " + timeStamp + ".txt";
		
		//Create file
		file = new File(name);
		file.createNewFile();
		
		//Copy log template
		copy(new File("Resources/ScanTemplate.txt"));
		
		//Initalize variables
		stats = new int[6];
		rats = new ArrayList<String[]>();
		
		//Write to array
		writeLine(readLine(timeStampLine) + timeStamp, timeStampLine);

	}
	
	private String getStat(int lineNum)
	{
		String line = readLine(lineNum);
		line.substring(line.indexOf(": ")+1);
		return line;
	}
	
	/**
	 * Parse to array
	 * Updates array to match file
	 * Also updates scan variables
	 * @throws FileNotFoundException
	 */
	public void parse() throws FileNotFoundException
	{
		super.parse();
		
		//sets time stamp
		timeStamp = getStat(timeStampLine);
		try
			{
				//sets unresolved
				//unresolved = Integer.parseInt(getStat((unresolvedLine)));
				
				//sets completed
				String line = getStat(completedLine);
				if(line.equals("TRUE"))
					completed = true;
				else
					completed = false;
				
				//sets stats
				for(int i = 0; i < stats.length; i++)
				{
					stats[i] = Integer.parseInt(getStat(statsLine + i));
				}
			}
		catch (Exception e)
		{
			System.out.println("Error retrieving information from " + file.getName());
			return;
		}
		
		if(stats[2] == 0)
			return;
		
		//sets rats
		String line = new String();
		rats.clear();
		
		for(int i = 0; i+ratsLine < this.length; i++)
		{
			line = readLine(ratsLine+i);
			String[] ratRay = new String[3];
			
			//filepath
			ratRay[0] = line.substring(0, line.lastIndexOf(":"));
			line = line.substring(line.lastIndexOf(": "+1));
			
			//extended name
			ratRay[1] = line.substring(0, line.indexOf(" FOUND"));

			//category
			ratRay[2] = line.substring(line.indexOf('.')+1, line.lastIndexOf('.'));
			
			//action
			//ratRay[3] = line.substring(line.indexOf('\t')+1);
			
			rats.set(i, ratRay);
		}
	}

	/**
	 * adds a new rat profile
	 * @param str (filepath: platform.category.name-signatureId-revision FOUND)
	 */
	public void writeRat(String str)
	{
		//writes to end of array
		writeLine(str);
		
		//updates rats array
		String[] ray = new String[3];
		ray[0] = str.substring(0, str.lastIndexOf(':')); //filepath
		
		str = str.substring(str.lastIndexOf(": ")+1);
		ray[1] = str.substring(0, str.indexOf(" FOUND"));
		ray[2] = ray[1].substring(ray[1].indexOf('.')+1, ray[1].lastIndexOf('.'));
		//ray[3] = Actions.PENDING.toString();
		
		rats.add(ray);
		
		//also add to statusInfo
		Severity sev = StatusCheck.check(str);
		StatusInfo.addRat(ray[0] + "\t" + ray[1] + "\t" + sev.toString());
	}
	
	/**
	 * Fills out the log
	 * @param comp if the scan completed
	 * @param s int array containing {filesScanned, unknown, rats, low, medium, high}
	 */
	public void writeLog(boolean comp, int[] s)
	{
		//Update stats
		completed = comp;
		stats = s;
		//unresolved = stats[2];
		
		//Write lines to array
		//writeLine(readLine(unresolvedLine) + unresolved, unresolvedLine);
		
		if(comp)
			writeLine(readLine(completedLine) + "TRUE", completedLine);
		else
			writeLine(readLine(completedLine) + "FALSE", completedLine);
		
		for(int i = 0; i < stats.length; i++)
		{
			int lineNum = statsLine+i;
			writeLine(readLine(lineNum) + stats[i], lineNum);
		}
		
		//Write to file
		writeFile();
	}
	
	/**
	 * Updates an action taken on a threat
	 * @param index index of rat within rats array
	 * @param arg0 action taken
	 */
	/*public void action(int index, Actions arg0)
	{
		//Updates in rats array
		rats.get(index)[3] = arg0.toString();
		
		//Updates unresolved threat count
		if(rats.get(index)[3].equals(Actions.PENDING.toString()))
		{
			String oldLine = readLine(unresolvedLine);
			oldLine.substring(0, oldLine.indexOf(unresolved)); //chops off old value
			
			unresolved--; //updates value
			writeLine(oldLine + unresolved, unresolvedLine); //writes to array
			
		}
		
		//Write to array
		String oldLine = readLine(ratsLine+index);
		oldLine = oldLine.substring(0, oldLine.indexOf("\t")+1); //chop off old action
		writeLine(oldLine + arg0.toString(), ratsLine+index);
	}*/
	
	public String getTimeStamp()
	{
		return timeStamp;
	}
	
	/*public int getUnresolved()
	{
		return unresolved;
	}*/
	
	public boolean wasCompleted()
	{
		return completed;
	}
	
	public int[] getStats()
	{
		return stats;
	}
	
	public int getFileCount()
	{
		return stats[0];
	}
	
	public int getRatCount()
	{
		return stats[2];
	}
	
	public int getRatCount(Severity sev)
	{
		switch(sev)
		{
			case SAFE:
				return stats[0] - (stats[2] + stats[1]);
		
			case UNKNOWN:
				return stats[1];
				
			case LOW:
				return stats[3];
				
			case MEDIUM:
				return stats[4];
				
			case HIGH:
				return stats[5];
				
			default:
				return stats[2];
		}
	}
	
	public ArrayList<String[]> getRats()
	{
		return rats;
	}
	
	/**
	 * 0: filepath
	 * 1: platform.category.name-signatureId-revision
	 * 2: category
	 * 3: action status
	 * @param index in rats array
	 * @return
	 */
	public String[] getRat(int index)
	{
		return rats.get(index);
	}
	
}
