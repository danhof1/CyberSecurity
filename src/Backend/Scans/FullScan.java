package Backend.Scans;
import java.io.*;

public class FullScan {
    public void scanFiles() throws IOException, InterruptedException {
        Scan myScan = new Scan();
        myScan.addFilePath("C:\\");
        myScan.Scanner();
    }
}
