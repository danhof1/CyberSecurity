package Backend.Scans;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import Backend.Scans.StatusCheck.Severity;

public class StatusInfo
{
	public enum Actions
	{
		PENDING,
		ISOLATED,
		REMOVED,
		WHITELISTED,
	}

	private static final ArrayList<String> categories = new ArrayList<String>(Arrays.asList(
			"Adware",
			"Backdoor",
			"Coinminer",
			"Countermeasure",
			"Downloader",
			"Dropper",
			"Exploit",
			"File",
			"Filetype",
			"Infostealer",
			"Ircbot",
			"Joke",
			"Keylogger",
			"Loader",
			"Macro",
			"Malware",
			"Packed",
			"Packer",
			"Phishing",
			"Proxy",
			"Ransomware",
			"Revoked",
			"Rootkit",
			"Spyware",
			"Test"
			));
	
	private static final ArrayList<String> descriptions = new ArrayList<String>(Arrays.asList(
			"Adware is a Rat that downloads advertizements to your computer. This can cause problems such as slowing down or even crashing your computer.",
			"A backdoor is a Rat that can potentially allow bad actors to bypass security and gain access to your computer.",
			"A coinminer is a Rat used by some third party to use your computer's processes to mine for 'cryptocurrency'. It's O.K. if you don't know what that is. This can slow down your computer they're making money at your expense.",
			"A countermeasure Rat attempts to weaken your computer's antivirus processes, but don't worry we found it!",
			"A downloader is a Rat that is used to install other malicious Rats onto your computer.",
			"A dropper is a Rat that is used to install other malicious Rats onto your computer.",
			"An exploit is a Rat that takes advantage of your computer's vulnerabilities with ill-intent",
			"N/A",
			"N/A",
			"An infostealer is a Rat that sends personal information, such as login credentials or credit card numbers, to a malicious third party. We highly recommended that you change your login credentials after we take care of this Rat.",
			"An ircbot is a Rat that may allow bad actors remote access or control to your computer.",
			"There's a chance this Rat is harmless, but we still recommend taking care of it just in case. Better safe than sorry!",
			"A keylogger is a Rat that catalogs and sends text you've typed on your computer to a malicious third party. This can contain information such as login credentials or credit card numbers, to a malicious third party. We highly recommended that you change your login credentials after we take care of this Rat.",
			"A loader is a Rat that is used to install other malicious Rats onto your computer.",
			"A macro is a Rat used to perform various actions automatically on your computer. If this is a file you personally created, this may be a false postive. Otherwise, we recommend taking care of it.",
			"N/A",
			"This file shows some signs of being a Rat. While we don't know specific details; if it walks like a Rat, and talks like a Rat...",
			"This program may be used to disguise Rats to be harder for us to find.",
			"A phishing Rat may allow bad actors remote access to your computer or personal information. We highly recommended that you change your login credentials after we take care of this Rat.",
			"A proxy is a Rat that may be used to hide and disguise malicious activities on your computer. If this is a file you personally created, this may be a false postive. Otherwise, we recommend taking care of it.",
			"Ransomware is a particularly nasty Rat that locks away your files and demands a ransom in return. We should take care of this one ASAP.",
			"A revoked Rat is a program that has had it's certification revoked, possibly due to criminal activity.",
			"A rootkit is a Rat that may allow bad actors remote access or control to your computer.",
			"A spyware Rat monitors your actions and may steal or sell personal information. We highly recommended that you change your login credentials after we take care of this Rat.",
			"This is likely a safe file disguised as a Rat to test that your antivirus is working, and we are! We still recommend taking care of it just in case, though."
			));

	
	private static ArrayList<Rat> rats = new ArrayList<Rat>(); //array containing rat info
	//private static File logs = new File("ScanLogs"); //ScanLogs folder
	private static FileX info = new FileX("Resources/StatusInfo.txt"); //file containing info
	
	public static void addRat(String str) //string separated by \t
	{
		Rat temp = new Rat(str);
		if(rats.contains(temp) || info.getContent().contains(str) || str.isEmpty())
			return;
		
		info.writeLine(str);
		rats.add(new Rat(str));
	}
	
	public static void removeRat(String str)
	{
		info.deleteLine(str);
		rats.remove(new Rat(str));
	}
	
	public static void clearRats()
	{
		for(String str : info.getContent())
		{
			if(!str.isBlank() && !str.startsWith("//"))
				removeRat(str);
		}
	}
	
	public static void refresh()
	{
		//Update text file
		info.trim();
		info.writeFile();
		
		//Update rat array
		rats.clear();
		
		for(String line : info.getContent())
		{
			if(line.startsWith("//") || line.isBlank() || line == null)
					continue;
			else
			{
				rats.add(new Rat(line));
			}
		}
	}
	
	public static Rat getRat(int index)
	{
		return rats.get(index);
	}
	
	public static ArrayList<Rat> getRats()
	{
		return rats;
	}
	
	public static int getTotalRats()
	{
		return info.getLength()-1;
	}

	public static String getDescription (String rat)
	{
		String desc = new String();
		int index = categories.indexOf(rat);
		if(index == -1)
			return "ERR";
		
		desc = descriptions.get(index);
		return desc;
	}
}
