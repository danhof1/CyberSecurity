package Backend.MainThings;

import java.io.IOException;

public class ratBooter {
	public void bootRat() throws IOException, InterruptedException {
		actionBranch ab = new actionBranch();
		ab.actionMethod(13);
		clamstart cs = new clamstart();
		  Thread clamThread = new Thread(() -> {
	          try {
	              cs.startClam();
	          } catch (IOException | InterruptedException e) {
	              e.printStackTrace();
	          }
	      });
		  clamThread.start();
		  clamThread.join();
	}
 
}
