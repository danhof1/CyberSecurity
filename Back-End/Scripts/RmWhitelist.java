import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class RmWhitelist {
    public static void main(String[] args) {
        String hitList = "C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\hitList.txt";
        String destinationFileName = "C:\\Program Files\\ClamAV\\clamd.conf";

        try {
            BufferedReader hitMan = new BufferedReader(new FileReader(hitList));
            BufferedReader clamReader = new BufferedReader(new FileReader(destinationFileName));
            BufferedWriter clamWriter = new BufferedWriter(new FileWriter(destinationFileName+".tmp"));
            String clamLine;
            String hitLine;
            ArrayList<String> hitArr = new ArrayList<String>();
            while ((hitLine = hitMan.readLine()) != null) {
                hitArr.add("# ExcludePath \"" + hitLine + "\"");
            }
            System.out.println(hitArr);
            while ((clamLine = clamReader.readLine()) != null) {
                if (!hitArr.contains(clamLine)) {
                    clamWriter.write(clamLine + System.getProperty("line.separator"));
                } 
            }
            clamReader.close();
            hitMan.close();
            clamWriter.close();

            System.out.println("Content updated successfully.");
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        Path source = Paths.get(destinationFileName + ".tmp");
        Path target = Paths.get(destinationFileName);

        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File replaced successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
