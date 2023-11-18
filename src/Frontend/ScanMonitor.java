package Frontend;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class ScanMonitor 
{	
	private static Text curDir;
	private ProgressBar prog;
	private Text scanPercent;
	private Text filesScanned;
	private Text ratsFound;
	//5: cancelText
	//6: scanTitle
	
	private static String curPath;
	
	public static void setObjs(ArrayList<javafx.scene.Node> objects)
	{
		curDir = (Text)objects.get(0);
	}
	
	public static Text getDir()
	{
		return curDir;
	}
	
	public static String getPath()
	{
		return curPath;
	}
	
	public static void setDir(String path)
	{
		curPath = path;

	}
}
