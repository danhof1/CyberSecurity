import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.time.*;
public class monthlyPicker {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Date Time Picker Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        SpinnerModel dayModel = new SpinnerNumberModel(1, 1, 31, 1);
        JSpinner daySpinner = new JSpinner(dayModel);

     

        SpinnerDateModel timeModel = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "hh:mm a");
        timeSpinner.setEditor(timeEditor);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            
        	int selectedDay = (int) dayModel.getValue();
            
            Date selectedTime = (Date) timeModel.getValue();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, selectedDay);
            calendar.set(Calendar.HOUR_OF_DAY, getHour(selectedTime));
            calendar.set(Calendar.MINUTE, getMinute(selectedTime));
            

            int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            if(selectedDay<LocalDate.now().getDayOfMonth()) {
            	calendar.add(Calendar.DATE, daysInMonth);
            }
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String formattedDateTime = dateFormat.format(calendar.getTime());

            System.out.println(formattedDateTime);
        });

        JPanel panel = new JPanel();
        panel.add(daySpinner);
        panel.add(timeSpinner);
        panel.add(confirmButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        
    }
    private static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    private static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }
}
