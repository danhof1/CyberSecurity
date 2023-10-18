import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class SelectDirectories {

    public static void main(String[] args) {
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
                        String destinationFileName = "C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\CustomScan.txt";
                        try {
                            // Open the source file for reading
                            BufferedReader reader = new BufferedReader(new FileReader(sourceFileName));

                            // Open the destination file for appending
                            BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFileName, true));

                            String line;

                            // Read each line from the source file and append it to the destination file
                            while ((line = reader.readLine()) != null) {
                                writer.write(line);
                                writer.newLine();
                            }

                            // Close the reader and writer
                            reader.close();
                            writer.close();

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
}
