package Backend.Pickers;
import javax.swing.*;
import java.awt.Component; 

public class scanPicker {

public static void main(String[] args) {

    JFrame frame = new JFrame("A Simple GUI");
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 150);
    frame.setLocation(430, 100);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // added code

    frame.add(panel);

    JLabel lbl = new JLabel("Select your scan");
    lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    //lbl.setVisible(true); // Not needed

    panel.add(lbl);

    String[] Scans = { "Quick", "Full", "Targetted"};

    JComboBox<String> scanComboBox = new JComboBox<>(Scans);

    scanComboBox.setMaximumSize(scanComboBox.getPreferredSize()); // added code
    scanComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
    //scanComboBox.setVisible(true); // Not needed
    panel.add(scanComboBox);

    JButton btn = new JButton("OK");
    btn.setAlignmentX(Component.CENTER_ALIGNMENT); // added code
    panel.add(btn);

    frame.setVisible(true); // added code
    btn.addActionListener(e -> {
        
    	String selectedr = (String) scanComboBox.getSelectedItem();
    	System.out.println(selectedr);
        
    });
    
    }
}