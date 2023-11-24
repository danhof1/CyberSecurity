package Backend.Pickers;
import javax.swing.*;
import java.awt.Component; 

public class recurrencePicker {

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

    String[] recurrences = { "Daily", "Weekly", "Monthly","Once"};

    JComboBox<String> rComboBox = new JComboBox<>(recurrences);

    rComboBox.setMaximumSize(rComboBox.getPreferredSize()); // added code
    rComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
    //rComboBox.setVisible(true); // Not needed
    panel.add(rComboBox);

    JButton btn = new JButton("OK");
    btn.setAlignmentX(Component.CENTER_ALIGNMENT); // added code
    panel.add(btn);

    frame.setVisible(true); // added code
    btn.addActionListener(e -> {
        
    	String selectedr = (String) rComboBox.getSelectedItem();
    	System.out.println(selectedr);
        
    });
    
    }
}