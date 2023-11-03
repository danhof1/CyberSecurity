package Frontend;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class ScanMonitor 
{
	public static ReentrantLock lock = new ReentrantLock();
	
	private static Text curDir;
	private ProgressBar prog;
	private Text scanPercent;
	private Text filesScanned;
	private Text ratsFound;
	//5: cancelText
	//6: scanTitle
	
	public static String curPath;
	
	public static void setObjs(ArrayList<javafx.scene.Node> objects)
	{
		curDir = (Text)objects.get(0);
	}
	
	public static Text getDir()
	{
		return curDir;
	}
	
	public static void setDir(String path)
	{
		/*System.out.println("setting dir");
		try
		{
			lock.lock();
			curDir.setText(path);
			
		}
		finally
		{
			lock.unlock();
		}
		System.out.println("dir set");*/
		curPath = path;

	}
}
