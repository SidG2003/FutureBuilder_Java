import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String rollNumber;
    private Float cgpa;
    private String branch;
    private String company;

    private String status = "Not-applied";
    private String reg_status="u";
    private Float pkg= Float.valueOf(0);
    private boolean flag_got_atleast_one_offer=false;

//    public ArrayList<Student> studentList = new ArrayList<>();

    public String getName(){
        return this.name;
    }
    public String getRollNumber(){
        return this.rollNumber;
    }
    public Float getCgpa(){
        return this.cgpa;
    }
    public String getBranch(){
        return this.branch;
    }
    public String getCompany(){
        return this.company;
    }
    public void setCgpa(Float new_cgpa){this.cgpa=new_cgpa;}
    public void setCompany(String updated_company){
        this.company=updated_company;
    }
    public String getStatus(){
        return this.status;
    }
    public String getReg_status(){
        return this.reg_status;
    }
    public Float getPkg(){
        return this.pkg;
    }
    public boolean getFlag_got_atleast_one_offer(){
        return this.flag_got_atleast_one_offer;
    }
    public void setStatus(String updated_status){
        this.status=updated_status;
    }
    public void setReg_status(){this.reg_status="r";}
    public void setPkg(Float new_pkg){this.pkg=new_pkg;}
    public void setFlag_got_atleast_one_offer(boolean flag){
        this.flag_got_atleast_one_offer=flag;
    }

    public void addStudent(String name, String rollNumber, Float cgpa, String branch) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.cgpa = cgpa;
        this.branch = branch;
    }

    public void registerForCompany(String companyName){
        setStatus("applied");
        setCompany(companyName);
    }

    public void getAllAvailableCompanies(){

    }

    public String getCurrentStatus(){
        return getStatus();
    }

    public void  updateCgpa(Float newCgpa){
        this.cgpa=newCgpa;
    }

    public void acceptOffer(){
        this.status="offered";
    }
    public void rejectOffer(){
    }

//    public static void main(String args[]){
    //        System.out.println("HelloWorld");
    //    }

}
