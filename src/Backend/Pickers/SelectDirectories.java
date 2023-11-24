package Backend.Pickers;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class SelectDirectories {

   public void Selector(){
        JFrame frame = new JFrame("Multi-Directory Selector Example");
        JButton button = new JButton("Select Directories");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fileChooser.setMultiSelectionEnabled(true);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                	try {
                		PrintStream out = new PrintStream(new FileOutputStream("C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\Cache.tmp"));
                        
                		for (java.io.File file : fileChooser.getSelectedFiles()) {
                            out.println(file);
                        }
                        out.close();
                        
                        String sourceFileName = "C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\Cache.tmp";
                        String destinationFileName ="C:\\Users\\Daniel\\eclipse-workspace\\Rat_Trap\\src\\Custom.tmp";
                        File myFile = new File(sourceFileName);		
                        
                        try {
                            // Open the source file for reading
                            BufferedReader reader = new BufferedReader(new FileReader(sourceFileName));
                            BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFileName));
                            String line;

                            // Read each line from the source file and append it to the destination file
                            while ((line = reader.readLine()) != null) {
                               writer.write(line);
                               writer.newLine();
                            }

                            // Close the reader and writer
                            reader.close();
                            writer.close();
                            myFile.delete();
                            
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