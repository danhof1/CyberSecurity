package Backend.Scans;

import java.io.File;

public class CountFiles 
{
	public static long dirSize(File dir) {

	      long length = 0;
	      File[] files = dir.listFiles();
	      if (files != null) {
	          for (File file : files) {
	              if (file.isFile())
	                  length += file.length();
	              else
	                  length += dirSize(file);
	          }
	      }
	      return length;

	  }
}
