package Backend.Pickers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dailyPicker {
    public String dPicker() {
        JFrame frame = new JFrame("Time Picker Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "hh:mm a");
        spinner.setEditor(editor);

        JButton confirmButton = new JButton("Confirm");

        // Create a string to store the selected time
        StringBuilder selectedTime = new StringBuilder();

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date selectedTimeDate = (Date) spinner.getValue();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss");
                selectedTime.append(dateFormat.format(selectedTimeDate)); // Update the selected time
            }
        });

        JPanel panel = new JPanel();
        panel.add(spinner);
        panel.add(confirmButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Wait for the user to confirm
        while (selectedTime.length() == 0) {
            try {
                Thread.sleep(100); // Sleep for a short while
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return selectedTime.toString();
    }

    public static void main(String[] args) {
        dailyPicker picker = new dailyPicker();
        String selectedTime = picker.dPicker();
        System.out.println("Selected Time: " + selectedTime);
    }
}
