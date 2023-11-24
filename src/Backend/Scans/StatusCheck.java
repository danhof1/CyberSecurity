package Backend.Scans;

import java.util.HashMap;

public class StatusCheck
{
	public enum Severity
	{
			LOW,
			MEDIUM,
			HIGH,
			SAFE,
			UNKNOWN
	}
	
	public static Severity check(String path)
	{
	
		// Map containing various types of malware and their severity levels
		
		HashMap<String, Severity> vSev = new HashMap<String, Severity>();
		vSev.put("Adware", Severity.LOW);
		vSev.put("Backdoor", Severity.HIGH);
		vSev.put("Coinminer", Severity.LOW); //*
		vSev.put("Countermeasure", Severity.MEDIUM); //*
		vSev.put("Downloader", Severity.HIGH);
		vSev.put("Dropper", Severity.HIGH);
		vSev.put("Exploit", Severity.HIGH);
		vSev.put("File", Severity.MEDIUM);
		vSev.put("Filetype", Severity.MEDIUM);
		vSev.put("Infostealer", Severity.HIGH); 
		vSev.put("Ircbot", Severity.HIGH);
		vSev.put("Joke", Severity.LOW);
		vSev.put("Keylogger", Severity.HIGH);
		vSev.put("Loader", Severity.HIGH);
		vSev.put("Macro", Severity.MEDIUM);
		vSev.put("Malware", Severity.HIGH);
		vSev.put("Packed", Severity.MEDIUM);
		vSev.put("Packer", Severity.MEDIUM);
		vSev.put("Phishing", Severity.HIGH);
		vSev.put("Proxy", Severity.MEDIUM);
		vSev.put("Ransomware", Severity.HIGH);
		vSev.put("Revoked", Severity.MEDIUM);
		vSev.put("Rootkit", Severity.HIGH);
		vSev.put("Spyware", Severity.MEDIUM);
		vSev.put("Test", Severity.LOW);
		
		
		String target = path.substring(path.lastIndexOf(": ")+1); //String containing info
		
		//Safe or empty file
		if(target.contains("OK") || target.contains("EMPTY"))
				return Severity.SAFE;
		
		//Can't read/ recognize file
		else if (!target.contains("FOUND"))
			return Severity.UNKNOWN;
		
		//Threat found
		
		//only threat type
		target = target.substring(target.indexOf('.')+1, target.lastIndexOf('.'));
		
		//return severity from hash map
		Severity sev = vSev.get(target);
		if(sev != null)
			return vSev.get(target);
		else
			return Severity.MEDIUM;
	}
}