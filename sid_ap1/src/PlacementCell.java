import java.text.ParseException;
import java.time.ZoneId;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;
public class PlacementCell {
    private static LocalDateTime companyStartDateTime;
    private static LocalDateTime companyEndDateTime;
    private static LocalDateTime studentStartDateTime;
    private static LocalDateTime studentEndDateTime;
    private static boolean companyRegStarted=false;
    private static boolean studentRegStarted=false;
    public LocalDateTime getCompanyStartDateTime(){ return companyStartDateTime; }
    public LocalDateTime getCompanyEndDateTime(){ return companyEndDateTime; }
    public LocalDateTime getStudentStartDateTime(){ return studentStartDateTime; }
    public LocalDateTime getStudentEndDateTime(){ return studentEndDateTime; }
    public boolean getCompanyRegStarted(){ return companyRegStarted; }
    public boolean getStudentRegStarted(){ return studentRegStarted; }
    public void updateCgpa(Student s,Float new_cgpa){s.setCgpa(new_cgpa);}

    public void convertStringToDate(String dt){

    }
    public void openStudentRegistrations(LocalDateTime start_dt,LocalDateTime end_dt) {
        studentStartDateTime= start_dt;
        studentEndDateTime= end_dt;
        studentRegStarted=true;
    }
    public void openCompanyRegistrations(String start_dt,String end_dt) throws ParseException {
        companyStartDateTime= stringToDate(start_dt);
        companyEndDateTime= stringToDate(end_dt);
        companyRegStarted=true;
    }
    public int getNumberOfRegisteredStudents(){
        return 0;
    }
    public int getNumberOfRegisteredCompanies(){
        return  0;
    }
    public LocalDateTime stringToDate(String dt) throws ParseException {
        SimpleDateFormat formatter1=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date date1=formatter1.parse(dt);
        LocalDateTime dt1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return dt1;
    }

}
