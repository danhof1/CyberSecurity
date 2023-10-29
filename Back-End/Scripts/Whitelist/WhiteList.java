import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
public class WhiteList {

    public void AddToWhitelist(){
        JFrame frame = new JFrame("Multi-Directory Selector Example");
        JButton button = new JButton("Select Directories");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setMultiSelectionEnabled(true);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                	try {
                        PrintStream out = new PrintStream(new FileOutputStream("C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\Cache.txt"));
                        System.setOut(out);
                        for (java.io.File file : fileChooser.getSelectedFiles()) {
                            System.out.println(file);
                        }
                        out.close();
                        String sourceFileName = "C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\Cache.txt";
                        String destinationFileName = "C:\\Program Files\\ClamAV\\clamd.conf";
                        String secondDestination = "C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\Whitelist.txt";
                        try {
                            // Open the source file for reading
                            BufferedReader reader = new BufferedReader(new FileReader(sourceFileName));

                            // Open the destination file for appending
                            BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFileName, true));
                            BufferedWriter writer2 = new BufferedWriter(new FileWriter(secondDestination,true));
                            String line;

                            // Read each line from the source file and append it to the destination file
                            while ((line = reader.readLine()) != null) {
                            	writer.write("# ExcludePath \"" + line + "\"");
                                writer2.write(line);
                                writer.newLine();
                                writer2.newLine();
                            }

                            // Close the reader and writer
                            reader.close();
                            writer.close();
                            writer2.close();

                            System.out.println("Content appended successfully.");
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    } 
                	catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }   
                	
                }
            }
        });

        frame.add(button);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
                
    }
   
    public void RmWhitelist() {
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
