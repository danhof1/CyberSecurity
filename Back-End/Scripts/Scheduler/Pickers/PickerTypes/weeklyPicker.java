import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class weeklyPicker {

    public String wPicker() {
        JFrame frame = new JFrame("Weekly Date Time Picker Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        JComboBox<String> dayOfWeekComboBox = new JComboBox<>(daysOfWeek);

        SpinnerDateModel timeModel = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "hh:mm a");
        timeSpinner.setEditor(timeEditor);

        JButton confirmButton = new JButton("Confirm");

        StringBuilder selectedDateTime = new StringBuilder();

        confirmButton.addActionListener(e -> {
            String selectedDayOfWeek = (String) dayOfWeekComboBox.getSelectedItem();
            Date selectedTime = (Date) timeModel.getValue();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, getDayOfWeek(selectedDayOfWeek));
            calendar.set(Calendar.HOUR_OF_DAY, getHour(selectedTime));
            calendar.set(Calendar.MINUTE, getMinute(selectedTime));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss");
            selectedDateTime.append(dateFormat.format(calendar.getTime()));
        });

        JPanel panel = new JPanel();
        panel.add(dayOfWeekComboBox);
        panel.add(timeSpinner);
        panel.add(confirmButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        while (selectedDateTime.length() == 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return selectedDateTime.toString();
    }

    private static int getDayOfWeek(String day) {
        switch (day) {
            case "Sunday":
                return Calendar.SUNDAY;
            case "Monday":
                return Calendar.MONDAY;
            case "Tuesday":
                return Calendar.TUESDAY;
            case "Wednesday":
                return Calendar.WEDNESDAY;
            case "Thursday":
                return Calendar.THURSDAY;
            case "Friday":
                return Calendar.FRIDAY;
            case "Saturday":
                return Calendar.SATURDAY;
            default:
                return -1; // Invalid day
        }
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

    public static void main(String[] args) {
        weeklyPicker picker = new weeklyPicker();
        String selectedDateTime = picker.wPicker();
        System.out.println("Selected Date/Time: " + selectedDateTime);
    }
}
