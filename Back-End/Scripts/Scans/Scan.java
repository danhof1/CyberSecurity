import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Scan {
    private List<String> filePaths;

    public Scan() {
        this.filePaths = new ArrayList<>();
    }

    public void addFilePath(String path) {
        filePaths.add(path);
    }

    public void addArray(List<String> directories) {
        filePaths = directories;
    }

    public void Scanner() throws IOException, InterruptedException {
        for (int i = 0; i < filePaths.size(); i++) {
            System.out.println(filePaths.get(i));
            Process proc = Runtime.getRuntime().exec("C:\\Program Files\\ClamAV\\clamscan.exe --recursive " + filePaths.get(i));
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }
            proc.waitFor();
        }
    }
}
