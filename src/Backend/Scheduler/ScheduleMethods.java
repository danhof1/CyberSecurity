package Backend.Scheduler;

import Backend.Pickers.*;

public class ScheduleMethods {
	
	public static sqlMethods mySql = new sqlMethods(); 

	
	public void addToScheulde(String[] funnyArr) {
		
		 String recurrence = funnyArr[3];
		 String filePath = funnyArr[2];;
		 String day= funnyArr[4]; 
		 String time= funnyArr[5];
		 String activity = funnyArr[1];
		 String newDateTime ="";
		 String dayOfWeek = funnyArr[4];
		 Transform tr = new Transform(); 
		 
		 if(recurrence.equals("Daily")){
			 newDateTime = tr.DailyT(time);
		 }
		 
		 else{
			 newDateTime = tr.RegularT(day,time);
		 }
		 
		 if(mySql.isEmpty()) {
			mySql.add(1,newDateTime,activity,recurrence,filePath,dayOfWeek);
		 }
		 
		 else {
			 mySql.add(mySql.getLastID()+1,newDateTime,activity,recurrence,filePath,dayOfWeek);
		 }
			
	}

	public void rmSchedule(int n) {
		sqlMethods mySql = new sqlMethods();
		mySql.rmItem(n);
		mySql.fixID(n);
	}
	public void wipeSchedule() {
		sqlMethods mySql = new sqlMethods();
		mySql.wipeTable();
	}
	public void viewSchedule(){
		sqlMethods mySql = new sqlMethods(); 
		mySql.viewAll();
	}
	public void printSortedSchedule() {
		sqlMethods mySql = new sqlMethods();
		mySql.printSortedTablebyDate();
	}
}