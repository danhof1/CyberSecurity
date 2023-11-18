package Backend.Scheduler;

import Backend.Pickers.*;

public class ScheduleMethods {

	public void addToScheulde() {
		
		 Picker myPicker = new Picker();
		 sqlMethods mySql = new sqlMethods(); 
		 String recurrence = myPicker.rType();
		 String dateTime = myPicker.datePicker(recurrence); 
		 String activity = myPicker.action();
		
		 if(mySql.isEmpty()) {
			mySql.add(1,dateTime,activity,recurrence);
		 }
		 else {
			 mySql.add(mySql.getLastID()+1,dateTime,activity,recurrence);
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