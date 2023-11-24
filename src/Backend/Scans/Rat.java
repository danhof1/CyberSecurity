package Backend.Scans;

import Backend.Scans.StatusCheck.Severity;
import Backend.Scans.StatusInfo.Actions;

public class Rat
{
	private String filePath;
	private String info;
	private String category;
	private Severity sev;
	private String id;
	
	public Rat()
	{
		
	}
	
	public void setRat(String str)
	{
		id = str;
		String[] temp = str.split("\t");
		filePath = temp[0];
		info = temp[1];
		category = info.substring(info.indexOf('.')+1, info.lastIndexOf('.'));
		sev = Severity.valueOf(temp[2]);
	}
	
	public Rat(String str) //separated by \t
	{
		id = str;
		String[] temp = str.split("\t");
		filePath = temp[0];
		info = temp[1];
		category = info.substring(info.indexOf('.')+1, info.lastIndexOf('.'));
		sev = Severity.valueOf(temp[2]);
	}
	
	public Rat(String fp, String in, String cat, Severity s)
	{
		id = fp + "\t" + in + "\t" + s;
		filePath = fp;
		info = in;
		category = cat;
		sev = s;
	}
	
	public String getFilePath()
	{
		return filePath;
	}
	
	public String getFileName()
	{
		return filePath.substring(filePath.indexOf('\\')+1);
	}
	
	public String getInfo()
	{
		return info;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public Severity getSeverity()
	{
		return sev;
	}
	
	public String getId()
	{
		return id;
	}
}
