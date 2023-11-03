package Backend.Scans;

public class ScanManager 
{
	private static boolean bool;
	
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
	}
}
