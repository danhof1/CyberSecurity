import javax.swing.*;
import java.awt.Component; 

public class Picker {
    private String selectedValue; // Field to store selected value
    public String rType() {
        JFrame frame = new JFrame("A Simple GUI");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 150);
        frame.setLocation(430, 100);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
        frame.add(panel);

        JLabel lbl = new JLabel("Select your scan");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lbl);

        String[] recurrences = { "Daily", "Weekly", "Monthly","Once"};
        JComboBox<String> rComboBox = new JComboBox<>(recurrences);

        rComboBox.setMaximumSize(rComboBox.getPreferredSize());
        rComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(rComboBox);

        JButton btn = new JButton("OK");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btn);

        frame.setVisible(true);

        btn.addActionListener(e -> {
            selectedValue = (String) rComboBox.getSelectedItem();
        });
        
        while (selectedValue == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        return selectedValue;
    }
    
    public String datePicker(String rType){
		String date_time=" "; 
    	switch(rType) { 
	    	case "Once":
				 oncePicker once = new oncePicker();
				 date_time = once.oPicker();
				 break;
			 case "Daily":
				 dailyPicker daily = new dailyPicker();
				 date_time = daily.dPicker();
				 break;
			 case "Weekly":
				 weeklyPicker weekly = new weeklyPicker();
				 date_time = weekly.wPicker(); 
				 break;
			 case "Monthly":
				 monthlyPicker monthly = new monthlyPicker();
			 	 date_time = monthly.mPicker();
				 break;
    	}
    	return date_time;
    }
    
    public String action() {
    	selectedValue= null;
    	JFrame frame = new JFrame("A Simple GUI");
         frame.setVisible(true);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(200, 150);
         frame.setLocation(430, 100);

         JPanel panel = new JPanel();
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
         frame.add(panel);

         JLabel lbl = new JLabel("Select your scan");
         lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
         panel.add(lbl);

         String[] recurrences = { "Quick", "Full", "Targeted"};
         JComboBox<String> rComboBox = new JComboBox<>(recurrences);

         rComboBox.setMaximumSize(rComboBox.getPreferredSize());
         rComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
         panel.add(rComboBox);

         JButton btn = new JButton("OK");
         btn.setAlignmentX(Component.CENTER_ALIGNMENT);
         panel.add(btn);

         frame.setVisible(true);

         btn.addActionListener(e -> {
             selectedValue = (String) rComboBox.getSelectedItem();
         });
         
         while (selectedValue == null) {
             try {
                 Thread.sleep(100);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         
         return selectedValue;
    }
    // You can add a getter method to retrieve the selected value
    public String getSelectedValue() {
        return selectedValue;
    }
}
