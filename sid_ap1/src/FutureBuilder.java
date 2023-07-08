import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDateTime; // Import the LocalDateTime class
//import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class FutureBuilder {

    public static ArrayList<Student> studentList = new ArrayList<Student>();
    public static ArrayList<Company> companyList = new ArrayList<Company>();
    public static ArrayList<Student> registeredStudentList = new ArrayList<Student>();
    public static ArrayList<Company> registeredCompanyList = new ArrayList<Company>();
    public static PlacementCell placementCell = new PlacementCell();

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type 'Enter FutureBuilder' to start");
        while(sc.hasNext()) {
            String s = sc.nextLine();
            if(s.equals("Enter FutureBuilder")) {
                System.out.println("Entered");
                beginApplication();
                break;
            }
        }
    }

    public static void beginApplication() throws ParseException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to FutureBuilder:");
        System.out.println("\t1) Enter the Application");
        System.out.println("\t2) Exit the Application");

        String s = sc.nextLine();
        if (s.equals("1")){
            chooseMode();
        }
        else{
            System.out.println("Thanks For Using FutureBuilder!!!!!!");
        }
    }

    public static void chooseMode() throws ParseException {
        System.out.println("Choose The mode you want to Enter in-");
        System.out.println("1) Enter as Student Mode");
        System.out.println("2) Enter as Company Mode");
        System.out.println("3) Enter as Placement Cell Mode");
        System.out.println("4) Return To Main Application");

        Scanner sc = new Scanner(System.in);
        String mode = sc.nextLine();
        if(mode.equals("1")){
            enterStudentMode();
        }
        else if (mode.equals("2")){
            enterCompanyMode();
        }
        else if (mode.equals("3")){
            enterPlacementCellMode();
        }
        else if (mode.equals("4")){
            beginApplication();
        }
    }

    public static void enterStudentMode() throws ParseException {
        System.out.println("Choose the Student Query to perform-");
        System.out.println("\t1) Enter as a Student(Give Student Name, and Roll No.)");
        System.out.println("\t2) Add students");
        System.out.println("\t3) Back");

        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();

        if (query.equals("1")){
            if(studentList.size()==0){
                System.out.println("No students added yet");
            }
            else {
                System.out.println("Enter name: ");
                String naam = sc.nextLine();
                System.out.println("Enter roll no: ");
                String rollno = sc.nextLine();
                boolean flag_stu_present = false;
//            *******************************************************************
                Student stu = null;
                for (int i = 0; i < studentList.size(); i++) {
                    Student s = studentList.get(i);
                    if (s.getRollNumber().equals(rollno)) {
                        flag_stu_present = true;
                        stu = studentList.get(i);
                        break;
                    }
                }
                if (flag_stu_present == false) {
                    System.out.println("Student not added, add student first: ");
                } else {
                    studentFunctionalities(stu);
                }
            }
            enterStudentMode();

        }else if (query.equals("2")){
            System.out.println("Enter the number of students to add: ");
            int no_stu=sc.nextInt();
            for(int i=0;i<no_stu;i++){
                Student new_stu = new Student();
                System.out.println("Enter the name: ");
                String nm=sc.next();
                System.out.println("Enter the roll no: ");
                String rn=sc.next();
                System.out.println("Enter the cgpa");
                Float cg=sc.nextFloat();
                System.out.println("Enter the Branch: ");
                String b=sc.next();
                new_stu.addStudent(nm,rn,cg,b);
                studentList.add(new_stu);
//                for(int j=0;j<studentList.size();j++){
//                    Student s=studentList.get(j);
//                    if(s.getRollNumber().equals(rn)){
//                        System.out.println("Student already added!");
//                        break;
//                    }
//                    else{
//                        new_stu.addStudent(nm,rn,cg,b);
//                        studentList.add(new_stu);
//                    }
//                }
            }
            enterStudentMode();

        }else if (query.equals("3")){
            chooseMode();
        }
    }

    public static void studentFunctionalities(Student stu) throws ParseException {
        System.out.println(String.format("Welcome %s !",stu.getName()));
        System.out.println("\t1) Register for placement drive");
        System.out.println("\t2) Register for company");
        System.out.println("\t3) Get all available companies");
        System.out.println("\t4) Get current status");
        System.out.println("\t5) Update cgpa");
        System.out.println("\t6) Accept offer");
        System.out.println("\t7) Reject offer");
        System.out.println("\t8) Back");
        Scanner sc = new Scanner(System.in);
        String query2 = sc.nextLine();
        if(query2.equals("1")){
            if(stuRegChecks(stu,LocalDateTime.now())){
                stu.setReg_status();
                registeredStudentList.add(stu);
                System.out.println("Registered");
                System.out.println(LocalDateTime.now());
            }
            studentFunctionalities(stu);
        }

        else if(query2.equals("2")){
            if(registeredCompanyList.size()==0){
                System.out.println("No companies registered yet");
            }
            else if(stu.getReg_status()!="r"){
                System.out.println("Please register for placement drive first");
            }
            else if(stu.getStatus()=="Blocked"){
                System.out.println("Sorry :( you are blocked");
            }
            else{
                for(int i=0;i<registeredCompanyList.size();i++){
                    Company co=registeredCompanyList.get(i);
                    if(stuRegChecksForCompany(co,stu,LocalDateTime.now())){
                        System.out.println(String.format("%d) %s package:%f",i,co.getName(),co.getPackageOffered()));
                    }
                }
                System.out.println("Please enter index no. of company to register: ");
                int idx=sc.nextInt();
                Company co=registeredCompanyList.get(idx);
                co.AppliedStudents.add(stu);
                stu.setStatus("Applied");
            }
            studentFunctionalities(stu);
        }
        else if(query2.equals("3")){
            int no_co=0;
            for(int i=0;i<registeredCompanyList.size();i++){
                Company co=registeredCompanyList.get(i);
                if(stuRegChecksForCompany(co,stu,LocalDateTime.now())){
                    no_co+=1;
                    System.out.println(String.format("%d) Name:%s role:%s CTC:%f cgpa:%f",i,co.getName(),co.getRole(),co.getPackageOffered(),co.getCgpaCriteria()));
                }
            }
            if(no_co==0){
                System.out.println("unavailable");
            }
            studentFunctionalities(stu);
        }
        else if(query2.equals("4")){
            System.out.println(stu.getCurrentStatus());
            if(stu.getCurrentStatus().equals("Offered") || stu.getCurrentStatus().equals("Placed")){
                System.out.println(String.format(stu.getCurrentStatus()+" at "+stu.getCompany()));
            }
            studentFunctionalities(stu);
        }
        else if(query2.equals("5")){
            System.out.println("Enter new cgpa: ");
            Float new_cgpa =sc.nextFloat();
            placementCell.updateCgpa(stu,new_cgpa);
            studentFunctionalities(stu);
        }
        else if(query2.equals("6")){
            if(!stu.getStatus().equals("Blocked")){
                System.out.println("Choose company offer to accept: ");
                boolean flag_offer_present=false;
                for(int i=0;i<registeredCompanyList.size();i++){
                    Company co = registeredCompanyList.get(i);
                    for(int j=0;j<co.SentOfferToStudentList.size();j++){
                        Student s=co.SentOfferToStudentList.get(j);
                        if(s.getRollNumber().equals(stu.getRollNumber())){
                            flag_offer_present=true;
                            System.out.println(String.format("%d) %s",i,co.getName()));
                        }
                    }
                }
                if(!flag_offer_present){
                    System.out.println("No offers yet!");
                }
                else{
                    stu.setFlag_got_atleast_one_offer(true);
                    boolean flag_alr_sel=false;
                    int co_no=sc.nextInt();
                    Company com=registeredCompanyList.get(co_no);
                    for(int j=0;j<com.SelectedStudentList.size();j++){
                        Student s=com.SelectedStudentList.get(j);
                        if(s.getRollNumber().equals(stu.getRollNumber())){
                            flag_alr_sel=true;
                        }
                    }
                    if(!flag_alr_sel){
                        stu.setCompany(com.getName());
                        stu.setStatus("Placed");
                        com.SelectedStudentList.add(stu);
                        System.out.println(String.format("Placed at %s!",com.getName()));
                    }
                }
            }
            studentFunctionalities(stu);
        }
        else if(query2.equals("7")){
            System.out.println("Choose company offer to reject: ");
            boolean flag_offer_was_present=false;
            int offer_count=0;
            for(int i=0;i<registeredCompanyList.size();i++){
                Company co = registeredCompanyList.get(i);
                for(int j=0;j<co.SentOfferToStudentList.size();j++){
                    Student s=co.SentOfferToStudentList.get(j);
                    if(s.getRollNumber().equals(stu.getRollNumber())){
                        offer_count+=1;
                        flag_offer_was_present=true;
                        System.out.println(String.format("%d) %s",i,co.getName()));
                    }
                }
            }
            if(!flag_offer_was_present && offer_count==1){
                if(stu.getFlag_got_atleast_one_offer()){
                    stu.setStatus("Blocked");
                }
                else{
                    System.out.println("No offers yet!");
                }
            }
            else{
                stu.setFlag_got_atleast_one_offer(true);
                int co_no=sc.nextInt();
                Company com=registeredCompanyList.get(co_no);
                com.SentOfferToStudentList.remove(stu);
                System.out.println(String.format("Offer from %s removed!",com.getName()));
            }

            studentFunctionalities(stu);
        }
        else if(query2.equals("8")){
            enterStudentMode();
        }
    }
    public static boolean stuRegChecks(Student s,LocalDateTime dt){
        if(!placementCell.getStudentRegStarted()){
            System.out.println("Student registration not started yet!");
            return false;
        }
        if(Objects.equals(s.getReg_status(),"r")){
            System.out.println("already registered");
            return false;
        }
        if(dt.isBefore(placementCell.getStudentStartDateTime()) || dt.isAfter(placementCell.getStudentEndDateTime())){
            System.out.println("outside valid registration period");
            return false;
        }
        return true;
    }
    public static boolean stuRegChecksForCompany(Company c,Student s,LocalDateTime dt){
        boolean flag_valid=true;
        if(!(c.getCgpaCriteria()<=s.getCgpa())){
            flag_valid=false;
        }
        if(s.getPkg()>3*c.getPackageOffered()){
            flag_valid=false;
        }
        return flag_valid;
    }

    public static void enterCompanyMode() throws ParseException {
        System.out.println("Choose the Company Query to perform");
        System.out.println("\t1) Add Company and Details");
        System.out.println("\t2) Choose Company");
//        System.out.println("\t3) Get Selected students");
        System.out.println("\t3) Back\n");

        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();

        if (query.equals("1")){
            Company new_co = new Company();
            Scanner sc_reg = new Scanner(System.in);
            System.out.println("Company name: ");
            String name = sc_reg.nextLine();
            System.out.println("Role: ");
            String role = sc_reg.nextLine();
            System.out.println("Package(LPA): ");
            Float packageOffered = sc_reg.nextFloat();
            System.out.println("CGPA Required: ");
            Float cgpaCriteria = sc_reg.nextFloat();
            new_co.addCompany(name,role,packageOffered,cgpaCriteria);
            companyList.add(new_co);
            enterCompanyMode();
        }
        else if (query.equals("2")){
            System.out.println("Select company");
            if(companyList.size()==0){
                System.out.println("No companies yet, first add your company");
                enterCompanyMode();
            }
            else{
                for(int i=0;i<companyList.size();i++) {
                    Company c = companyList.get(i);
                    String cn = c.getName();
                    System.out.println(String.format("%d) %s",i,cn));
                }
                Scanner sc_choose_comp = new Scanner(System.in);
                int chosen_company = sc_choose_comp.nextInt();
                Company co = companyList.get(chosen_company);
                sel_company_mode(co);
            }
        }
//        else if (query.equals("3")){
//            for(int i=0;i< SelectedStudentList.size();i++) {
//                Student c = SelectedStudentList.get(i);
//                String cn = c.getName();
//                System.out.println(String.format("%d) %s",i,cn));
//            }
//        }
        else if (query.equals("3")){
            chooseMode();
        }
    }

    public static void sel_company_mode(Company co) throws ParseException {
        System.out.println(String.format("Welcome to %s",co.getName()));
        System.out.println("\t1) Update Role");
        System.out.println("\t2) Update Package");
        System.out.println("\t3) Update CGPA criteria");
        System.out.println("\t4) Register To Institute Drive");
        System.out.println("\t5) Get Selected students");
        System.out.println("\t6) Give offers to Students");
        System.out.println("\t7) Back");

        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();

        if (query.equals("1")){
            System.out.println("Enter new role:");
            String new_role=sc.nextLine();
            co.setRole(new_role);
            sel_company_mode(co);

        }else if (query.equals("2")){
            System.out.println("Enter new package:");
            Float new_pkg=sc.nextFloat();
            co.setPackageOffered(new_pkg);
            sel_company_mode(co);

        }else if (query.equals("3")){
            System.out.println("Enter new cgpa requirement:");
            Float new_cgpa=sc.nextFloat();
            co.setCgpaCriteria(new_cgpa);
            sel_company_mode(co);

        }else if (query.equals("4")){
            LocalDateTime dt = LocalDateTime.now();
            if(companyRegChecks(co,dt)){
                co.registerToInstituteDrive(dt);
                registeredCompanyList.add(co);
            } else {
                System.out.println("Couldn't register");
            }
            sel_company_mode(co);


        }else if (query.equals("5")){
            if(co.SelectedStudentList.size()==0){
                System.out.println("No students selected yet");
            }
            else{
                for(int i=0;i< co.SelectedStudentList.size();i++) {
                    Student s = co.SelectedStudentList.get(i);
                    String sn = s.getName();
                    String rn = s.getRollNumber();
                    Float scg = s.getCgpa();
                    String sb = s.getBranch();
                    System.out.println(String.format("%d)Name: %s",i,sn));
                    System.out.println(String.format("\troll no: %s",rn));
                    System.out.println(String.format("\tcgpa: %f",scg));
                    System.out.println(String.format("\tbranch: %s",sb));
                }
            }
            sel_company_mode(co);

        }else if (query.equals("6")){
            if(co.AppliedStudents.size()==0){
                System.out.println("Sorry, no student has applied to your company yet");
            }
            else{
                System.out.println("Select student to offer:");
                for(int i=0;i< co.AppliedStudents.size();i++) {
                    Student s = co.AppliedStudents.get(i);
                    String sn = s.getName();
                    System.out.println(String.format("%d)Name: %s",i,sn));
                }
                Scanner stu_ind = new Scanner(System.in);
                int stu_no = stu_ind.nextInt();
                boolean stu_offered_flag=false;
                for(int j=0;j< co.SentOfferToStudentList.size();j++) {
                    Student s = co.SentOfferToStudentList.get(j);
                    String sn = s.getRollNumber();
                    if(co.AppliedStudents.get(stu_no).getRollNumber().equals(sn)){
                        stu_offered_flag=true;
                    }
                }
                if(stu_offered_flag){
                    System.out.println("Student already offered");
                }
                else if(co.AppliedStudents.get(stu_no).getStatus()=="Blocked"){
                    System.out.println("Student blocked,cannot offer");
                }
                else{
                    co.SentOfferToStudentList.add(co.AppliedStudents.get(stu_no));
                    co.AppliedStudents.get(stu_no).setFlag_got_atleast_one_offer(true);
                    co.AppliedStudents.get(stu_no).setStatus("Offered");
                }
            }
            sel_company_mode(co);

        }else if (query.equals("7")){
            enterCompanyMode();
        }

    }
    public static boolean companyRegChecks(Company co,LocalDateTime registrationDateTime){
//        for(int i=0;i<companyList.size();i++){
//            Company c = companyList.get(i);
//            if (Objects.equals(c.getName(),name)){
//                System.out.println("already registered");
//                return false;
//            }
            if(!placementCell.getCompanyRegStarted()){
                System.out.println("Company registration not started yet!");
                return false;
            }
            if(Objects.equals(co.getStatus(),"r")){
                System.out.println("already registered");
                return false;
            }
            if(registrationDateTime.isBefore(placementCell.getCompanyStartDateTime()) || registrationDateTime.isAfter(placementCell.getCompanyEndDateTime())){
                System.out.println("outside valid registration period");
                return false;
            }
        return true;
    }

    public static void enterPlacementCellMode() throws ParseException {
        System.out.println("Welcome to IIITD Placement Cell");
        System.out.println("\t1) Open Student Registrations");
        System.out.println("\t2) Open Company Registrations");
        System.out.println("\t3) Get Number of Student Registrations");
        System.out.println("\t4) Get Number of Company Registrations");
        System.out.println("\t5) Get Number of Offered,Un-offered,Blocked Students");
        System.out.println("\t6) Get Student Details");
        System.out.println("\t7) Get Company Details");
        System.out.println("\t8) Get Average Package");
        System.out.println("\t9) Get Company Process Results");
        System.out.println("\t10) Back");

        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();

        if (query.equals("1")){
            if(!placementCell.getCompanyRegStarted()){
                System.out.println("Student registration can start only after company registration");
            }
            else{
                System.out.println("Enter student start date in format dd-MMM-yyyy HH:mm:ss");
                String stu_start_date_str=sc.nextLine();
                System.out.println("Enter student end date in format dd-MMM-yyyy HH:mm:ss");
                String stu_end_date_str=sc.nextLine();
                LocalDateTime stu_start_date=placementCell.stringToDate(stu_start_date_str);
                LocalDateTime stu_end_date=placementCell.stringToDate(stu_end_date_str);
                if(!stu_start_date.isAfter(placementCell.getCompanyEndDateTime())){
                    System.out.println("Student registration can start only after company registration ends");
                }
                else{
                    placementCell.openStudentRegistrations(stu_start_date,stu_end_date);
                }
            }
            enterPlacementCellMode();

        }else if (query.equals("2")){
            System.out.println("Enter company start date in format dd-MMM-yyyy HH:mm:ss");
            String com_start_date=sc.nextLine();
            System.out.println("Enter company end date in format dd-MMM-yyyy HH:mm:ss");
            String com_end_date=sc.nextLine();
            placementCell.openCompanyRegistrations(com_start_date,com_end_date);
            enterPlacementCellMode();

        }else if (query.equals("3")){
            int no_stu_reg = registeredStudentList.size();
            System.out.println(no_stu_reg);
            enterPlacementCellMode();

        }else if (query.equals("4")){
            int no_com_reg = registeredCompanyList.size();
            System.out.println(no_com_reg);
            enterPlacementCellMode();

        }else if (query.equals("5")){
            int no_not_applied =0;
            int no_applied =0;
            int no_offered =0;
            int no_placed =0;
            int no_blocked =0;
            for(int i=0;i<studentList.size();i++){
                Student s=studentList.get(i);
                if(s.getStatus().equals("Not-applied")){
                    no_not_applied+=1;
                }
                if(s.getStatus().equals("Applied")){
                    no_applied+=1;
                }
                if(s.getStatus().equals("Offered")){
                    no_offered+=1;
                }
                if(s.getStatus().equals("Placed")){
                    no_placed+=1;
                }
                if(s.getStatus().equals("Blocked")){
                    no_blocked+=1;
                }
            }
            int offered=no_offered+no_placed;
            int unoffered=no_not_applied+no_applied;
            System.out.println(String.format("Not applied: %d",no_not_applied));
            System.out.println(String.format("Applied: %d",no_applied));
            System.out.println(String.format("Offered(in literal terms): %d",no_offered));
            System.out.println(String.format("Placed: %d",no_placed));
            System.out.println(String.format("Blocked: %d\n",no_blocked));
            System.out.println("What was required");
            System.out.println(String.format("Offered: %d",offered));
            System.out.println(String.format("Un-offered: %d",unoffered));
            System.out.println(String.format("Blocked: %d",no_blocked));
            enterPlacementCellMode();

        }else if (query.equals("6")){
            System.out.println("Enter name of student: ");
            String nm = sc.nextLine();
            System.out.println("Enter roll no. of student: ");
            String rn = sc.nextLine();
//            ****************************************************************************
            Student s = null;
            for(int i=0;i<studentList.size();i++) {
                s = studentList.get(i);
                if (s.getRollNumber().equals(rn)) {
                    break;
                }
            }
            System.out.println(String.format("Name: %s",s.getName()));
            System.out.println(String.format("Roll_no: %s",s.getRollNumber()));
            System.out.println(String.format("Branch: %s",s.getBranch()));
            for(int i=0;i<companyList.size();i++) {
                Company co= companyList.get(i);
                boolean applied_flag=false;
                boolean offered_flag=false;
                boolean selected_flag=false;
                System.out.println(String.format("%d) %s",i,co.getName()));
                for(int j1=0;j1<co.AppliedStudents.size();j1++) {
                    Student s1=co.AppliedStudents.get(j1);
                    if(s1.getRollNumber().equals(s.getRollNumber())){
                        applied_flag=true;
                        break;
                    }
                }
                for(int j1=0;j1<co.SentOfferToStudentList.size();j1++) {
                    Student s1=co.SentOfferToStudentList.get(j1);
                    if(s1.getRollNumber().equals(s.getRollNumber())){
                        offered_flag=true;
                        break;
                    }
                }
                for(int j1=0;j1<co.SelectedStudentList.size();j1++) {
                    Student s1=co.SelectedStudentList.get(j1);
                    if(s1.getRollNumber().equals(s.getRollNumber())){
                        selected_flag=true;
                        break;
                    }
                }
                if(applied_flag){
                    System.out.println("Applied");
                }
                else{
                    System.out.println("Not-applied");
                }
                if(offered_flag){
                    System.out.println("Offered");
                }
                else{
                    System.out.println("Not-offered");
                }
                if(selected_flag){
                    System.out.println("Selected/placed");
                }
                else {
                    System.out.println("Not selected/placed");
                }
            }
            enterPlacementCellMode();

        }else if (query.equals("7")){
            System.out.println("Enter company name: ");
            String cname=sc.nextLine();
            for(int i=0;i<companyList.size();i++) {
                Company c = companyList.get(i);
                if(c.getName().equals(cname)){
                    System.out.println(String.format("Name: %s",c.getName()));
                    System.out.println(String.format("Role: %s",c.getRole()));
                    System.out.println(String.format("Package offered: %s",c.getPackageOffered()));
                    System.out.println(String.format("cgpa criteria: %s",c.getCgpaCriteria()));
                    for(int j=0;j<c.SentOfferToStudentList.size();j++) {
                        Student st = c.SentOfferToStudentList.get(j);
                        System.out.println(st.getName());
                        System.out.println(st.getRollNumber()+"\n");
                    }
                    break;
                }
            }
            enterPlacementCellMode();

        }else if (query.equals("8")){
            float pkg_sum=0;
            int total_no_sel_stu=0;
            float avg_pkg;
            for(int i=0;i<companyList.size();i++) {
                Company c = companyList.get(i);
                total_no_sel_stu+=c.SelectedStudentList.size();
                pkg_sum+=c.SelectedStudentList.size()*c.getPackageOffered();
            }
            if(total_no_sel_stu==0){
                System.out.println("No students selected yet, so invalid");
            }
            else{
                avg_pkg=pkg_sum/total_no_sel_stu;
                System.out.println(String.format("Average package: %f",avg_pkg));
            }
            enterPlacementCellMode();

        }else if (query.equals("9")){
            System.out.println("Enter company name: ");
            String cname=sc.nextLine();
            for(int i=0;i<companyList.size();i++) {
                Company c = companyList.get(i);
                if(c.getName().equals(cname)){
                    if(c.SelectedStudentList.size()==0){
                        System.out.println("No students selected yet!");
                    }
                    else{
                        for(int j=0;j<c.SelectedStudentList.size();j++) {
                            Student st=c.SelectedStudentList.get(j);
                            System.out.println(st.getName());
                            System.out.println(st.getRollNumber()+"\n");
                        }
                    }
                    break;
                }
            }
            enterPlacementCellMode();

        }else if (query.equals("10")){
            chooseMode();
        }
    }
}
