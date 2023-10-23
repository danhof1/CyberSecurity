import java.io.IOException;

public class actionBranch {
	public void actionMethod(int n) throws IOException, InterruptedException {
        switch (n) {
	        case 0:
	        	clamstart clam = new clamstart();
	        	clam.startClam();
	        	break;
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
