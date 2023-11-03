package Backend.Pickers;

//import com.toedter.calendar.JDateChooser;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class oncePicker {
    private String selectedDateTime;

    public String oPicker() {
        JFrame parentFrame = new JFrame();
        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentFrame.setSize(300, 150);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");

        SpinnerDateModel timeModel = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "hh:mm a");
        timeSpinner.setEditor(timeEditor);

        JButton confirmButton = new JButton("Confirm");

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = dateChooser.getDate();
                Date selectedTime = (Date) timeSpinner.getValue();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(selectedDate);

                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String formattedTime = timeFormat.format(selectedTime);

                selectedDateTime = formattedDate + " " + formattedTime;

                parentFrame.dispose(); // Close the dialog
            }
        });

        JPanel panel = new JPanel();
        panel.add(dateChooser);
        panel.add(timeSpinner);
        panel.add(confirmButton);

        parentFrame.getContentPane().add(panel);
        parentFrame.setVisible(true);

        // Block until user makes a selection and closes the dialog
        while (selectedDateTime == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return selectedDateTime;
    }

    public static void main(String[] args) {
        oncePicker picker = new oncePicker();
        String selectedDateTime = picker.oPicker();
        System.out.println("Selected Date/Time: " + selectedDateTime);
    }
}
