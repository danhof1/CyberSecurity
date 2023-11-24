package Backend.Scans;

import java.io.IOException;

public class ScanManager 
{
	private static boolean bool;
	public static String custPath;
	
	public static boolean liveScan()
	{
		return bool;
	}
	
	public static void startScan()
	{
		bool = true;
	}
	
	public static void killScan()
	{
		bool = false;
		System.out.println("Killing scan...");
		try 
		{
			Runtime.getRuntime().exec("Taskkill /IM clamdscan.exe /F");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
