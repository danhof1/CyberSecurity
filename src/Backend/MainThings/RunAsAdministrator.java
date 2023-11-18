<<<<<<< HEAD
package Backend.MainThings;

=======
>>>>>>> ab255db1312f2e867005c54eca788cfed00222ca
import java.io.File;
import java.io.IOException;

public class RunAsAdministrator {

    public void runAsAdmin(String Path) throws IOException {
        String command = "C:\\Program Files\\ClamAV\\clamd.exe";

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(new File(Path)); // Set the working directory if necessary

        builder.redirectErrorStream(true); // Redirect error stream to the output stream

        Process process = builder.start();


        try {
            int exitCode = process.waitFor();
            System.out.println("Process exited with code " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
