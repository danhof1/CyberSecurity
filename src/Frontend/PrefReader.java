package Frontend;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import Backend.Scans.FileX;
import Backend.Scans.StatusCheck.Severity;

public class PrefReader
{
	private static FileX conf = new FileX("config/prefConf.txt"); //file containing info
	private static HashMap<String, Object> prefs = new HashMap<String, Object>();
	
	public static void parse() throws FileNotFoundException
	{
		conf.parse(); //parse file
		
		for(String line : conf.getContent())
		{
			if(line.startsWith("//") || line.isBlank()) //skips comments
					continue;
			
			String[] pair = line.split(":\t"); //splits line into key value
			prefs.put(pair[0], pair[1]);
		}
	}
	
	public static void updatePref(String key, Object value)
	{
		prefs.put(key, value);
		refresh();
	}
	
	public static void refresh()
	{
		String temp = conf.readLine(1);
		
		conf.getContent().clear();
		conf.writeLine(temp);
		
		for(String key : prefs.keySet())
		{
			conf.writeLine(key + ":\t" + prefs.get(key));
		}
		conf.writeFile();
	}
	
	public static Object getPref(String key)
	{
		Object val = prefs.get(key);
		
		if(val.equals("TRUE"))
				return true;
		else if (val.equals("FALSE"))
				return false;
		
		else return val;
	}
	
}
