package Backend.Pickers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transform {
 public String RegularT(String Day, String Time) {
	 StringBuilder selectedDateTime = new StringBuilder();
	 String selectedDayOfWeek = Day;
	 String[] parts = Time.split("-");
     String Hour = parts[0];
     String Minute = parts[1];
	 
     Calendar calendar = Calendar.getInstance();
     calendar.set(Calendar.DAY_OF_WEEK, getDayOfWeek(selectedDayOfWeek));
     calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(Hour));
     calendar.set(Calendar.MINUTE, Integer.parseInt(Minute));

     SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss");
     selectedDateTime.append(dateFormat.format(calendar.getTime()));
     
     return selectedDateTime.toString();
 }
 public String DailyT(String Time) {
	 StringBuilder selectedDateTime = new StringBuilder();
	 String[] parts = Time.split("-");
     String Hour = parts[0];
     String Minute = parts[1];
	 
	 Calendar calendar = Calendar.getInstance();
	 calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(Hour));
     calendar.set(Calendar.MINUTE, Integer.parseInt(Minute));
     
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss");
     selectedDateTime.append(dateFormat.format(calendar.getTime()));
     return selectedDateTime.toString();
 }
 private static int getDayOfWeek(String day) {
	    switch (day) {
	        case "S":
	            return Calendar.SUNDAY;
	        case "M":
	            return Calendar.MONDAY;
	        case "T":
	            return Calendar.TUESDAY;
	        case "W":
	            return Calendar.WEDNESDAY;
	        case "R":
	            return Calendar.THURSDAY;
	        case "F":
	            return Calendar.FRIDAY;
	        case "U":
	            return Calendar.SATURDAY;
	        default:
	            return -1; // Invalid day
	    }
	}
}
