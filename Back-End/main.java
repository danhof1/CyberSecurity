import java.io.IOException;

public class main {
	public static void main(String[] args) throws IOException, InterruptedException {
		QuickScan quick = new QuickScan();
		quick.scanFiles();
		
		FullScan full = new FullScan();
		full.scanFiles();
		
		
	}

}
