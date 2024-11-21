import java.util.*; 
import java.time.LocalDate;

public class Main {
    private static List<Student> students = new ArrayList<>();
    private static List<Professor> professors = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>(); 
    private static List<Administrator> administrators = new ArrayList<>(); 
    private static List<TA> TAs = new ArrayList<>();

    public static void main(String[] args) {
        // new Course("CSE121", "Discrete Mathematics", "Bappi Chatterjee", 4, new ArrayList<>(), "Mon 9:30 AM"),
    //     new Course("CSE201", "Advanced Programming", "Sambuddho", 4, Arrays.asList("CSE101"), "Mon 3 PM"),
    //     new Course("MTH203", "Maths III", "Sarthok Sircar", 4, new ArrayList<>(), "Tue 4:30 PM"),
    //     new Course("SSH201", "RMSSD", "Gayatri Nair", 4, new ArrayList<>(), "Fri 11AM"),
    //     new Course("CSE231", "Operating Systems", "Raghav", 4, Arrays.asList("CSE101"), "Tue 3 PM")
    // );
        Course course1 = new Course("CSE121", "Discrete Mathematics", "bappi@iiitd.ac.in", 4,
                                    new ArrayList<>(), "Mon 9:30 AM", "Basic discrete math principles", 2, "Mon 2 PM - 4 PM");
        Course course2 = new Course("CSE201", "Advance Programming", "sambuddho@iiitd.ac.in", 4, 
                                    Arrays.asList("CSE101"), "Mon 9:30 AM", "Java", 200, "Wed 1 PM - 3 PM");
        Course course3 = new Course("SSH201", "RMSSD", "gayatri@iiitd.ac.in", 4, 
                                   new ArrayList<>(), "Fri 11AM", "Qualitative research", 250, "Wed 2 PM - 4 PM");
        Course course4 = new Course("CSE231", "Operating Systems", "raghav@iiitd.ac.in", 4, 
                                    new ArrayList<>(), "Tue 3 PM", "Fork, schedules", 150, "Thur 1 PM - 3 PM");
        Course course5 = new Course("MTH203", "Maths III", "sarthok@iiitd.ac.in", 4, 
                                    new ArrayList<>(), "Tue 4:30 PM", "Three dimensional integrals", 350, "Tue 3 PM - 4 PM");
        Course course6 = new Course("CSE122", "Discrete structures", "ravi@iiitd.ac.in", 4, 
                                    new ArrayList<>(), "Thur 4:30 PM", "Graph theory", 350, "Mon 3 PM - 4 PM");
        Course course7 = new Course("CSE101", "Intro to programming", "shad@iiitd.ac.in", 4, 
                                    new ArrayList<>(), "Wed 1 PM", "Functions, recursion", 300, "Thur 1 PM - 3 PM");

                    

        course1.setDropDeadline(LocalDate.of(2024, 9, 10));
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);
        courses.add(course6);
        courses.add(course7);

        List<Course> student1Courses = Arrays.asList(course1, course3, course4, course5, course6);

        Professor professor1 = new Professor("bappi@iiitd.ac.in", "123");
        professor1.addManagedCourse(course1);
        professors.add(professor1);
        
        Professor professor2 = new Professor("sambuddho@iiitd.ac.in", "123");
        professor2.addManagedCourse(course2);
        professors.add(professor2);

        Professor professor3 = new Professor("gayatri@iiitd.ac.in", "123");
        professor3.addManagedCourse(course3);
        professors.add(professor3);

        Professor professor4 = new Professor("raghav@iiitd.ac.in", "123");
        professor4.addManagedCourse(course4);
        professors.add(professor4);

        Professor professor5 = new Professor("sarthok@iiitd.ac.in", "123");
        professor5.addManagedCourse(course5);
        professors.add(professor5);

        Professor professor6 = new Professor("ravi@iiitd.ac.in", "123");
        professor6.addManagedCourse(course6);
        professors.add(professor6);

        Professor professor7 = new Professor("shad@iiitd.ac.in", "123");
        professor7.addManagedCourse(course7);
        professors.add(professor7);

        Student student1 = new Student("muskan@iiitd.ac.in", "123st");
        student1.submitComplaint("Issue with hostel.");
        student1.setContactNumber("1234512345");
        student1.setRegisteredCourses(student1Courses);
        student1.setCompletedCourses(student1Courses);
        students.add(student1);

        Student student2 = new Student("ridhi@iiitd.ac.in", "123st");
        student2.submitComplaint("Doubts regarding office hours.");
        student2.setContactNumber("2345623456");
        // student2.setRegisteredCourses(student1Courses);
        students.add(student2);

        Student student3 = new Student("mansi@iiitd.ac.in", "123st");
        student3.submitComplaint("Deadline should be postponed.");
        // student3.setRegisteredCourses(student1Courses);
        student3.setContactNumber("3456734567");
        students.add(student3);

        // for (Student student : students) {
        //     System.out.println("Stored student email: " + student.getEmail()+ student.getPassword());
        // }

        TA ta1= new TA("kartik@iiitd.ac.in", "123ta");
        ta1.setAssignCourse(course1);
        TAs.add(ta1);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Student SignUp");
            System.out.println("2. Professor SignUp");
            System.out.println("3. Administrator SignUp"); 
            System.out.println("4. Student Login");
            System.out.println("5. Professor Login");
            System.out.println("6. Administrator Login");
            System.out.println("7. TA SignUp"); 
            System.out.println("8. TA Login");  
            System.out.println("9. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    signUpStudent(scanner);
                    break;
                case 2:
                    signUpProfessor(scanner);
                    break;
                case 3:
                    signUpAdministrator(scanner);
                    break;
                case 4:
                    loginStudent(scanner);
                    break;
                case 5:
                    loginProfessor(scanner);
                    break;
                case 6:
                    loginAdministrator(scanner);
                    break;
                case 7:
                    signUpTA(scanner);
                    break;
                case 8:
                    loginTA(scanner);
                    break;
                case 9:
                    scanner.close();
                    return;
                default:
                    System.out.println("choice is invalid");
            }
        }
    }

    public static void signUpStudent(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Create password: ");
        String password = scanner.nextLine();

        Student student = new Student(email, password);
        students.add(student);
        System.out.println("Student sign-up is done. You can further log in.");
    }

    public static void signUpProfessor(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Create password: ");
        String password = scanner.nextLine();

        Professor professor = new Professor(email, password);
        professors.add(professor);
        System.out.println("Professor sign-up is done. You can further log in.");
    }

    public static void signUpAdministrator(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        Administrator administrator = new Administrator(email);
        administrators.add(administrator);
        System.out.println("Administrator sign-up is done. You can further log in.");
    }

    public static void signUpTA(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Create password: ");
        String password = scanner.nextLine();

        TA ta = new TA(email, password);
        TAs.add(ta);
        System.out.println("TA sign-up is done. You can further log in.");
    }

    public static void loginStudent(Scanner scanner) {
        try {
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
    
            boolean found = false;
            for (Student student : students) {
                if (student.email.equals(email) && student.password.equals(password)) {
                    System.out.println("Student login successful");
                    welcomeStudent(scanner, student);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new InvalidLoginException("Invalid email or password");
            }
        } catch (InvalidLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loginProfessor(Scanner scanner) {
        try {
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            boolean found = false;
            for (Professor professor : professors) {
                if (professor.email.equals(email) && professor.password.equals(password)) {
                    System.out.println("Professor login successful");
                    welcomeProfessor(scanner, professor);
                    found= true;
                    return;
                }
            }
            if (!found) {
                throw new InvalidLoginException("Invalid email or password");
            }
        } catch (InvalidLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loginAdministrator(Scanner scanner) {
        try {
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            boolean found = false;
            for (Administrator administrator : administrators) {
                if (administrator.email.equals(email) && administrator.password.equals(password)) {
                    System.out.println("Administrator login successful");
                    welcomeAdministrator(scanner, administrator);
                    found= true;
                    return;
                }
            }
            if (!found) {
                throw new InvalidLoginException("Invalid email or password");
            }
        } catch (InvalidLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loginTA(Scanner scanner) {
        try {
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            boolean found = false;
    
            for (TA ta : TAs) {
                if (ta.email.equals(email) && ta.password.equals(password)) {
                    System.out.println("TA login successful");
                    welcomeTA(scanner, ta);
                    found= true;
                    return;
                }
            }
            if (!found) {
                throw new InvalidLoginException("Invalid email or password");
            }
        } catch (InvalidLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void welcomeStudent(Scanner scanner, Student student) {
        while (true) {
            System.out.println("\n--> Welcome Student <--");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. View Schedule");
            System.out.println("4. Track Academic Progress");
            System.out.println("5. Drop Course");
            System.out.println("6. Submit Complaint");
            System.out.println("7. View Complaints");
            System.out.println("8. Give feedback");
            System.out.println("9. Logout");
            System.out.print("Select an option: ");
            // try{
                int choice = scanner.nextInt();
                scanner.nextLine(); 
        
                switch (choice) {
                    case 1:
                        student.viewAvailableCourses(courses);
                        break;
                    case 2:
                        System.out.print("Enter course code to register: ");
                        String courseCode = scanner.nextLine();
                        for (Course course : courses) {
                            if (course.code.equals(courseCode)) {
                                student.registerForCourse(course);
                                break;
                            }
                        }
                        break;
                    case 3:
                        student.viewSchedule();
                        break;
                    case 4:
                        student.trackAcademicProgress();
                        break;
                    case 5:
                        System.out.print("Enter course code to drop: ");
                        String dropCourseCode = scanner.nextLine();
                        for (Course course : courses) {
                            if (course.code.equals(dropCourseCode)) {
                                student.dropCourse(course);
                                break;
                            }
                        }
                        break;
                    case 6:
                        System.out.print("Enter complaint description: ");
                        String complaintDescription = scanner.nextLine();
                        student.submitComplaint(complaintDescription);
                        break;
                    case 7:
                        student.viewComplaints();
                        break;
                    case 8:
                        student.giveFeedback(scanner);
                        break;
                    case 9:
                        System.out.println("---Logging out---");
                        return;
                    default:
                        System.out.println("choice is invalid");
                    
                }
            // }
            // catch (InputMismatchException e) {
            //     System.out.println("Invalid choice. Please enter a number.");
            //     scanner.nextLine(); // Consume invalid input
            // }
        }
    }

public static void welcomeProfessor(Scanner scanner, Professor professor) {
    while (true) {
        System.out.println("\n--> Welcome Professor <--");
        System.out.println("1. Manage Courses");
        System.out.println("2. View Enrolled Students");
        System.out.println("3. View Feedback");
        System.out.println("4. Logout");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                professor.manageCourses(scanner);
                break;
            case 2:
                professor.viewEnrolledStudents(students);
                break;
            case 3:
                for (Course course : professor.getManagedCourses()) {
                    professor.viewFeedback(students, course);
                }
                break;    
            case 4:
                System.out.println("---Logging out---");
                return;
            default:
                System.out.println("choice is invalid");
            }
        }
    }

    public static void welcomeAdministrator(Scanner scanner, Administrator administrator) {
        while (true) {
            System.out.println("\n--> Welcome Administrator <--");
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Manage Student Records");
            System.out.println("3. Assign professor to courses");
            System.out.println("4. Handle Complaints"); 
            System.out.println("5. Logout");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    manageCourseCatalog(scanner, administrator);
                    break;
                case 2:
                    manageStudentRecords(scanner, administrator);
                    break;
                case 3:
                    administrator.assignProfessorToCourse(scanner, courses, professors);
                    break;
                case 4:
                    handleComplaints(scanner, administrator);
                    break;
                case 5:
                    System.out.println("---Logging out---");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    public static void manageCourseCatalog(Scanner scanner, Administrator administrator) {
        while (true) {
            System.out.println("\n--> Manage Course Catalog <--");
            System.out.println("1. View Courses");
            System.out.println("2. Add Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
    
            switch (choice) {
                case 1:
                    administrator.viewCourses(courses);
                    break;
                case 2:
                    administrator.addCourse(scanner, courses);
                    break;
                case 3:
                    administrator.deleteCourse(scanner, courses);
                    break;
                case 4:
                    return; 
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    
public static void manageStudentRecords(Scanner scanner, Administrator administrator) {
    while (true) {
        System.out.println("\n--> Manage Student Records <--");
        System.out.println("1. View Students");
        System.out.println("2. Update Student Records");
        System.out.println("3. Back to Admin Menu");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        
        switch (choice) {
            case 1:
                administrator.viewStudents(students);
                break;
            case 2:
                System.out.print("Enter the email of the student to update: ");
                String email = scanner.nextLine();
                Student studentToUpdate = null;
                for (Student student : students) {
                    if (student.getEmail().equals(email)) {
                        studentToUpdate = student;
                        break;
                    }
                }
                administrator.updateStudentRecords(scanner, students, email, studentToUpdate.getRegisteredCourses());
                break;
            case 3:
                return; 
            default:
                System.out.println("Invalid choice.");
            }
        }
    }

    public static void handleComplaints(Scanner scanner, Administrator administrator) {
        while (true) {
            System.out.println("\n--> Handle Complaints <--");
            System.out.println("1. View All Complaints");
            System.out.println("2. Update Complaint Status");
            System.out.println("3. Filter Complaints by Status");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    administrator.viewComplaints(students);
                    break;
                case 2:
                    administrator.updateComplaintStatus(scanner, students);
                    break;
                case 3:
                    administrator.filterComplaintsByStatus(scanner, students);
                    break;
                case 4:
                    return; 
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void welcomeTA(Scanner scanner, TA ta) {
        while (true) {
            System.out.println("\n--> Welcome TA <--");
            System.out.println("1. View Assigned Course");
            System.out.println("2. View Available Courses");
            System.out.println("3. Register for Course");
            System.out.println("4. View Schedule");
            System.out.println("5. Track Academic Progress");
            System.out.println("6. Drop Course");
            System.out.println("7. Submit Complaint");
            System.out.println("8. View Complaints");
            System.out.println("9. Give feedback");
            System.out.println("10. View Students in assigned course");
            System.out.println("11. Assign CGPA to Students");
            System.out.println("12. Logout");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ta.viewAssignedCourse();
                    break;
                case 2:
                    ta.viewAvailableCourses(courses);
                    break;
                case 3:
                    System.out.print("Enter course code to register: ");
                    String courseCode = scanner.nextLine();
                    for (Course course : courses) {
                        if (course.getCode().equals(courseCode)) {
                            ta.registerForCourse(course);
                            break;
                        }
                    }
                    break;
                case 4:
                    ta.viewSchedule();
                    break;
                case 5:
                    ta.trackAcademicProgress();
                    break;
                case 6:
                    System.out.print("Enter course code to drop: ");
                    String dropCourseCode = scanner.nextLine();
                    for (Course course : courses) {
                        if (course.getCode().equals(dropCourseCode)) {
                            ta.dropCourse(course);
                            break;
                        }
                    }
                    break;
                case 7:
                    System.out.print("Enter complaint description: ");
                    String complaintDescription = scanner.nextLine();
                    ta.submitComplaint(complaintDescription);
                    break;
                case 8:
                    ta.viewComplaints();
                    break;
                case 9:
                    ta.giveFeedback(scanner);
                    break;
                case 10:
                    ta.viewStudents(students);
                    break;
                case 11:
                    ta.assignCGPA(students, scanner);
                    break;
                case 12:
                    System.out.println("---Logging out---");
                    return;
                default:
                    System.out.println("choice is invalid");
            }
        }
    }
}    