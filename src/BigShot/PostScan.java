package BigShot;

import java.io.*;

public class PostScan
{
	File quar;

	
	public PostScan()
	{
		quar = new File("Quarantine");
	}
	
	/**
	 * Delete one file from quarantine
	 * @param original path to file
	 * @throws IOException
	 * @throws InterruptedException
	 */
    public boolean deleteOne(String path) throws IOException, InterruptedException
    {
    	File rat = new File("Quarantine\\" + path.substring(path.lastIndexOf('\\')));
    	
    	System.out.println("Deleting " + rat.getAbsolutePath());
    	return rat.delete();
    	
    }
    
    /**
     * Delete all quarantined files
     * @param array containing all original filepaths
     * @throws IOException
     * @throws InterruptedException
     */
    /*public void deleteAll(String[] path) throws IOException, InterruptedException
    {
        System.out.println(path);
        for(int i=0;i<path.length;i++) { 
            String[] ratArr = path[i].split("\\\\");
            int length = ratArr.length;
            String command = "cmd.exe /c del " +System.getProperty("user.dir")+"\\Quarantine\\"+ratArr[length-1];
            Process proc = Runtime.getRuntime().exec(command);
            proc.waitFor();
        }

    }*/
    
    /**
     * Deletes all files from quarentine
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean deleteAll() throws IOException, InterruptedException
    {
    	for(File rat : quar.listFiles())
    	{
    		if(!rat.delete())
    			return false;
    	}
    	
    	return quar.list().length == 0;
    }
}