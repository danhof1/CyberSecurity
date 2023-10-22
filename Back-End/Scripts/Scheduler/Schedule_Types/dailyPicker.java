
    //Simple Time Picker
    
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dailyPicker {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Time Picker Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "hh:mm a");
        spinner.setEditor(editor);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date selectedTime = (Date) spinner.getValue();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedTime = dateFormat.format(selectedTime);
                System.out.println(formattedTime);//Selected Time
            }
        });

        JPanel panel = new JPanel();
        panel.add(spinner);
        panel.add(confirmButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}