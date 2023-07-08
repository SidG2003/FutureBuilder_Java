import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Company {
    private String name;
    private String role;
    private Float packageOffered;
    private Float cgpaCriteria;
    private LocalDateTime registrationDateTime;
    private String status = "u";

    public ArrayList<Student> AppliedStudents = new ArrayList<Student>();

    public ArrayList<Student> SentOfferToStudentList = new ArrayList<Student>();
    public ArrayList<Student> SelectedStudentList = new ArrayList<Student>();
    public String getName(){ return this.name; }
    public String getRole(){ return this.role; }
    public Float getPackageOffered(){
        return this.packageOffered;
    }
    public Float getCgpaCriteria(){
        return this.cgpaCriteria;
    }
    public LocalDateTime getRegistrationDateTime(){
        return this.registrationDateTime;
    }
    public String getStatus(){
        return this.status;
    }
    public void setRole(String role) { this.role = role; }
    public void setPackageOffered(Float pkg){
        this.packageOffered=pkg;
    }
    public void setCgpaCriteria(Float cgpa) { this.cgpaCriteria=cgpa; }
    public void setRegistrationDateTime(){
    }
    public void setStatus(){
        this.status="r";
    }


//    public ArrayList<Company> companyList = new ArrayList<>();

    public void addCompany(String name, String role, Float packageOffered, Float cgpaCriteria) {
        this.name = name;
        this.role = role;
        this.packageOffered = packageOffered;
        this.cgpaCriteria = cgpaCriteria;
//        this.registrationDateTime = registrationDateTime;
    }
    public void registerToInstituteDrive(LocalDateTime dt){
        this.status="r";
        System.out.println(dt);
        System.out.println("Registered!");
    }

    public ArrayList<Student> getSelectedStudents(ArrayList<Student> studentList){
        ArrayList<Student> selectedStudentList = new ArrayList<>();

        for(int i = 0; i < studentList.size(); i++){
            Student s = studentList.get(i);
            if (Objects.equals(s.getCompany(), this.name)){
                selectedStudentList.add(s);
                System.out.println("==========");
                System.out.println("name:"+s.getName());
                System.out.println("Roll_no:"+s.getRollNumber());
                System.out.println("cgpa:"+s.getCgpa());
                System.out.println("branch:"+s.getBranch());
                System.out.println("==========");

            }
        }
        return selectedStudentList;
    }


}
