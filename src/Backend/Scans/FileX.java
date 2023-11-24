package Backend.Scans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileX
{
	protected File file;
	protected List<String> content;
	
	protected int length;
	protected boolean mismatch; //true if file needs to be updated to match arraylist
	
	/**
	 * Opens and/or makes a txt file
	 * @param name of file
	 */
	public FileX(String name)
	{
		mismatch = false;
		content = new ArrayList<String>();
		try 
		{
			file = new File(name);
			if(!file.createNewFile()) //Creates new file
			{
				//File exists ==> parse to array
				System.out.println("Opened file: " + name);
				parse();
			}
			else
			{
				System.out.println("Created file: " + name);
			}
		} 
		catch (IOException e)
		{
		      e.printStackTrace();
		}
	}
	
	/**
	 * Only use for inheritance
	 */
	protected FileX()
	{
		mismatch = false;
		content = new ArrayList<String>();
	}
	
	/**
	 * Parse to array
	 * Updates array to match file
	 * @throws FileNotFoundException
	 */
	public void parse() throws FileNotFoundException
	{
		Scanner scan = new Scanner(file);
		content.clear();
		length = 0;
		
		//Add lines to array
		while(scan.hasNextLine())
		{
			String line = scan.nextLine();
			writeLine(line); //write to array	
		}
		scan.close();
		//Sets length
		length = content.size();
		mismatch = false;
	}

	/**
	 * Writes to file
	 * Updates file to match array
	 */
	public void writeFile()
	{
		try
		{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            if(content.isEmpty())
            	writer.write("");
            
            else
            {
	            for(String line : content)
	            {
	            	//System.out.println(file.getName() + " < " + line);
	            	writer.write(line);
	            	writer.newLine();
	            } 
            }
            writer.close();
            mismatch = false;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Copies from a target file
	 * @param target file to copy
	 * @param name of new file
	 * @throws FileNotFoundException 
	 */
	public void copy(File target) throws FileNotFoundException
	{		
		//If target file doesn't exist
		if(!target.exists())
		{
			System.out.println("Error copying " + target.getName() + " to " + this.getName());
			return;
		}
		
		//Creates a new FileX which automatically parses through target file
		FileX targetFile = new FileX(target.getAbsolutePath());
		//Copy over array
		this.content = targetFile.content;
		this.length = targetFile.length;
					
		//Writes to file
		this.writeFile();
		
		mismatch = false;
	}
	
	/**
	 * Reads a line
	 * @param lineNum line number to read from
	 * @return text at line number
	 */
	public String readLine(int lineNum)
	{
		return content.get(lineNum-1);
	}
	
	/**
	 * Reads the last line
	 * @return text at line number
	 */
	public String readLine()
	{
		return content.get(length-1);
	}

	/**
	 * Writes to the specified line number
	 * @param lineNum
	 */
	public void writeLine(String line, int lineNum)
	{
		mismatch = true;
		//Replace element with new line
		
		if(line.contains("\n")) //Multiple lines
		{
			//Split by new line char
			String[] multiLine = line.split("\n");
			content.set(lineNum-1, line);
			
			//Adds all sublines
			for(int i = 0; i < multiLine.length; i++)
			{
				content.add(lineNum + i, multiLine[i]);
			}
		}
		else //Only one line
			content.set(lineNum-1, line);
		
		length = content.size();

	}
	
	/**
	 * Writes to the the end of the file
	 */
	public void writeLine(String line)
	{
		mismatch = true;
		//Add line to end of array
		if(line.contains("\n")) //Multiple lines
		{
			//Split by new line char
			String[] multiLine = line.split("\n");
			
			//Iterate through list, split into multiple lines
			for(String subStr : multiLine)
			{
				content.add(subStr);
			}
		}
		else //only one line
			content.add(line);
		
		length = content.size();

	}

	
	/**
	 * Deletes line at specified line number
	 * @param lineNum
	 */
	public void deleteLine(int lineNum)
	{
		content.remove(lineNum-1);
		length = content.size();
		mismatch = true;
	}
	
	/**
	 * Deletes the first line containing the specified text
	 * @param String line
	 */
	public void deleteLine(String line)
	{
		content.remove(line);
		length = content.size();
		mismatch = true;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public String getName()
	{
		return file.getName();
	}
	
	public String getPath()
	{
		return file.getPath();
	}
	
	public File getFile()
	{
		return file;
	}
	
	public List<String> getContent()
	{
		return content;
	}
	
	public void trim()
	{
		for(String line : content)
		{
			if (line.isBlank())
			{
				content.remove(line);
			}
		}
		length = content.size();
		writeFile();
		
	}
	
	/**
	 * If the file is up to date
	 * If the arraylist and file match
	 * @return true if they match, false if the file is outdated
	 */
	public boolean hasMismatch()
	{
		return mismatch;
	}
}
