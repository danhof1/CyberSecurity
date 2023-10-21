import java.io.IOException;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        int n = 4;
        switch (n) {
            case 1:
                QuickScan quick = new QuickScan();
                quick.scanFiles();
                break;
            case 2:
                FullScan full = new FullScan();
                full.scanFiles();
                break; 
            case 3:
            	 SelectDirectories select = new SelectDirectories();
            	 select.Selector();
            	 break;
            case 4:
            	CustomScan cust = new CustomScan();
            	cust.scanFiles(cust.parseTemp());
            	break;
        }
    }
}
