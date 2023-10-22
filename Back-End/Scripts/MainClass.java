import java.io.IOException;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        int n = 7;
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
            case 5:
            	WhiteList white= new WhiteList();
            	white.AddToWhitelist();
            	break;
            case 6:
            	WhiteList black= new WhiteList();
            	black.RmWhitelist();
            	break;
            case 7:
            	Scheduler time = new Scheduler();
            	time.addToScheulde();
            	time.viewSchedule();
        }
    }
}
