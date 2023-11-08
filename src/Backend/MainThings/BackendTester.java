package Backend.MainThings;

import java.io.IOException;

public class BackendTester {

    public static void main(String[] args) throws IOException, InterruptedException {
        /*clamstart cs = new clamstart();

        Thread clamThread = new Thread(() -> {
            try {
                cs.startClam();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread quickScanThread = new Thread(() -> {
            synchronized (cs) {
                try {
                    cs.wait(); // Wait for the readiness signal from clamstart
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000); // Sleep for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Now you can start the scan
                try {
                    actionBranch ab = new actionBranch();
                    ab.actionMethod(1);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        clamThread.start();
        quickScanThread.start();

        clamThread.join();
        quickScanThread.join();*/
    	ratBooter rat = new ratBooter();
    	rat.bootRat();
    }
}
