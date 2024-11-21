import java.util.*; 

class Administrator extends User {
    private static final String FIXED_PASS = "123admin"; 

    public Administrator(String email) {
        super(email, FIXED_PASS); 
    }

    @Override
    public boolean login(String email, String password) throws InvalidLoginException {
        if (this.email.equals(email) && this.password.equals(password)) {
            return true;
        } else {
            throw new InvalidLoginException("Invalid email or password");
        }
    }

    // View all courses
    public void viewCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    // Add a course 
    public void addCourse(Scanner scanner, List<Course> courses) {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course title: ");
        String name = scanner.nextLine();
        System.out.print("Enter professor name: ");
        String professor = scanner.nextLine();
        System.out.print("Enter number of credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter class timing: ");
        String timing = scanner.nextLine();
        System.out.print("Enter syllabus description: ");
        String syllabus = scanner.nextLine();
        System.out.print("Enter maximum enrollment limit: ");
        int enrollmentLimit = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter office hours: ");
        String officeHours = scanner.nextLine();

        Course newCourse = new Course(code, name, professor, credits, new ArrayList<>(), timing, syllabus, enrollmentLimit, officeHours);
        courses.add(newCourse);
        System.out.println("Course added successfully.");
    }

    // Delete a course 
    public void deleteCourse(Scanner scanner, List<Course> courses) {
        System.out.print("Enter course code to delete: ");
        String code = scanner.nextLine();

        Course courseToRemove = null;
        for (Course course : courses) {
            if (course.code.equals(code)) {
                courseToRemove = course;
                break;
            }
        }

        if (courseToRemove != null) {
            courses.remove(courseToRemove);
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void assignProfessorToCourse(Scanner scanner, List<Course> courses, List<Professor> professors) {
        System.out.print("Enter the existing course code: ");
        String newCourseCode = scanner.nextLine();
        Course newCourse = null;
    
        // Find the existing course from the list
        for (Course course : courses) {
            if (course.code.equals(newCourseCode)) {
                newCourse = course;
                break;
            }
        }
    
        if (newCourse == null) {
            System.out.println("Course not found.");
            return;
        }
    
        System.out.print("Enter the professor's email to assign: ");
        String professorEmail = scanner.nextLine();
    
        Professor availableProfessor = null;
        boolean isConflict = false;
    
        // in order to see if the professor is managing any courses and their timings
        for (Professor professor : professors) {
            // courses managed by this professor
            for (Course course : professor.getManagedCourses()) {
                if (course.getProfessor().equals(professorEmail)) {  
                    // if timing conflicts with the professor's current schedule
                    if (course.timing.equals(newCourse.timing)) {
                        System.out.println("Professor " + professorEmail + " is already teaching a course at this time.");
                        isConflict = true; 
                        break;
                    }
                }
            }
            if (isConflict) {
                break; 
            }
        }
    
        if (!isConflict) {
            // fINALLY assign the professor to the new course
            for (Professor professor : professors) {
                if (professor.getEmail().equals(professorEmail)) {
                    availableProfessor = professor;
                    break;
                }
            }
    
            if (availableProfessor != null) {
                availableProfessor.addManagedCourse(newCourse); 
                newCourse.setProfessor(professorEmail);  
                System.out.println("Professor " + professorEmail + " has been assigned to the course " + newCourse.title);
            } else {
                System.out.println("No available professor found with the given email.");
            }
        }
    }

    // To view complaints

    public void viewComplaints(List<Student> students) {
        List<Complaint> allComplaints = new ArrayList<>();
        for (Student student : students) {
            allComplaints.addAll(student.getComplaints());
        }

        if (allComplaints.isEmpty()) {
            System.out.println("No complaints available.");
            return;
        }

        for (Complaint complaint : allComplaints) {
            System.out.println(complaint);
        }
    }

    // To update complaint status
    public void updateComplaintStatus(Scanner scanner, List<Student> students) {
        System.out.print("Enter complaint description to update: ");
        String description = scanner.nextLine();

        boolean found = false;
        for (Student student : students) {
            for (Complaint complaint : student.getComplaints()) {
                if (complaint.getDescription().equals(description)) {
                    found = true;
                    System.out.println("Current Status: " + complaint.getStatus());
                    System.out.print("Enter new status (Pending/Resolved): ");
                    String status = scanner.nextLine();
                    complaint.setStatus(status);

                    if (status.equals("Resolved")) {
                        System.out.print("Enter resolution details: ");
                        String resolutionDetails = scanner.nextLine();
                        complaint.setResolutionDetails(resolutionDetails);
                    }

                    System.out.println("Complaint status updated successfully.");
                    break;
                }
            }
            if (found) break;
        }

        if (!found) {
            System.out.println("Complaint not found.");
        }
    }

    // To filter complaints by status
    public void filterComplaintsByStatus(Scanner scanner, List<Student> students) {
        System.out.print("Enter status to filter (Pending/Resolved): ");
        String statusFilter = scanner.nextLine();

        List<Complaint> filteredComplaints = new ArrayList<>();
        for (Student student : students) {
            for (Complaint complaint : student.getComplaints()) {
                if (complaint.getStatus().equals(statusFilter)) {
                    filteredComplaints.add(complaint);
                }
            }
        }

        if (filteredComplaints.isEmpty()) {
            System.out.println("No complaints with the status " + statusFilter + ".");
            return;
        }

        for (Complaint complaint : filteredComplaints) {
            System.out.println(complaint);
        }
    }

    // To view all students
    public void viewStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student student : students) {
            System.out.println("Email: " + student.getEmail());
            System.out.println("CGPA: " + student.getCgpa());
            System.out.println("Contact Number: " + student.getContactNumber());
            System.out.println(); 
        }
    }

    // To update student records
    public void updateStudentRecords(Scanner scanner, List<Student> students, String email, List<Course> registeredCourses) {
        
        Student studentToUpdate = null;
        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                studentToUpdate = student;
                break;
            }
        }
        
        if (studentToUpdate == null) {
            System.out.println("Student not found.");
            return;
        }
        
        System.out.println("Updating records for: " + email);
        
        // Display available courses
        System.out.println("\nAvailable Courses:");
        for (Course course : registeredCourses) {
            System.out.println(course.getCode() + ": " + course.getTitle());
        }
        
        // Update CGPA for courses
        while (true) {
            System.out.print("\nEnter course code to update CGPA (or type 'done' to finish): ");
            String courseCode = scanner.nextLine();
            
            if (courseCode.equalsIgnoreCase("done")) {
                break;
            }
            
            Course courseToUpdate = null;
            for (Course course : registeredCourses) {
                if (course.getCode().equals(courseCode)) {
                    courseToUpdate = course;
                    break;
                }
            }
            
            if (courseToUpdate == null) {
                System.out.println("Course not registered by student.");
                continue;
            }
            
            System.out.print("Enter new CGPA for the course: ");
            String cgpaInput = scanner.nextLine();
            try {
                double cgpa = Double.parseDouble(cgpaInput);
                studentToUpdate.setCGPAForCourse(courseCode, cgpa);
                courseToUpdate.setCGPAForStudent(email, cgpa);
                System.out.println("CGPA updated successfully.");
                studentToUpdate.getCompletedCourses().add(courseToUpdate);
                courseToUpdate.setCompleted(true);
            } catch (NumberFormatException e) {
                System.out.println("Invalid CGPA input.");
            }
        }
        
        // Compute and update final CGPA
        double totalCGPA = 0;
        int courseCount = 0;
        
        for (Course course : registeredCourses) {
            double courseCGPA = studentToUpdate.getCGPAForCourse(course.getCode());
            if (courseCGPA > 0) {
                totalCGPA += courseCGPA;
                courseCount++;
            }
        }
        
        double finalCGPA;
        if (courseCount > 0) {
            finalCGPA = totalCGPA / courseCount;
        } else {
            finalCGPA = 0;
        }

        studentToUpdate.setCgpa(finalCGPA); 
        
        System.out.println("Final CGPA for student " + email + " is: " + finalCGPA);
    
        System.out.print("Enter new contact number (or leave blank to keep current): ");
        String contactNumber = scanner.nextLine();
        if (!contactNumber.isEmpty()) {
            studentToUpdate.setContactNumber(contactNumber);
        }
        System.out.println("Student records updated successfully.");
    }
}
    