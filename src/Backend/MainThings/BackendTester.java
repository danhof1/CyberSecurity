package Backend.MainThings;

import java.io.IOException;

import Backend.Scheduler.ScheduleMethods;
import Backend.Scheduler.sqlMethods;

public class BackendTester {

    public static void main(String[] args) throws IOException, InterruptedException {
       actionBranch ab = new actionBranch();
       //ab.actionMethod(8);
       //ScheduleMethods sm = new ScheduleMethods();
       //sm.rmSchedule(3);
       sqlMethods sm = new sqlMethods();
       sm.fixID(3);
       ab.actionMethod(8);
       //ab.actionMethod(9);
       
    }
}
