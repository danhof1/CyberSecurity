package Backend.MainThings;

import java.io.IOException;

public class ratBooter {
	public void bootRat() throws IOException, InterruptedException {
		actionBranch ab = new actionBranch();
		ab.actionMethod(13);
<<<<<<< HEAD
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
=======
		ab.actionMethod(0);
>>>>>>> ab255db1312f2e867005c54eca788cfed00222ca
	}
 
}
