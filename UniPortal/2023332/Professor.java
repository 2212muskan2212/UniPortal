import java.util.*;

class Professor extends User {
    private List<Course> managedCourses; 

    public Professor(String email, String password) {
        super(email, password);
        this.managedCourses = new ArrayList<>();
    }

    @Override
    public boolean login(String email, String password) throws InvalidLoginException {
        if (this.email.equals(email) && this.password.equals(password)) {
            return true;
        } else {
            throw new InvalidLoginException("Invalid email or password");
        }
    }

    public String getEmail() {
        return this.email;
    }

    public List<Course> getManagedCourses() {
        return this.managedCourses;
    }

    public void addManagedCourse(Course course) {
        if (!managedCourses.contains(course)) {
            managedCourses.add(course);
        }
    }

    // 1. Manage courses (view and/or update)
    public void manageCourses(Scanner scanner) {
        while (true) {
            System.out.println("\n--> Manage Courses <--");
            System.out.println("1. View Course Details");
            System.out.println("2. Update Course Details");
            System.out.println("3. Go Back");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewCourseDetails();
                    break;
                case 2:
                    updateCourseDetails(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("choice is invalid");
            }
        }
    }

    private void viewCourseDetails() {
        System.out.println("Courses Managed:");
        for (Course course : managedCourses) {
            course.displayCourseDetails();
        }
    }

    private void updateCourseDetails(Scanner scanner) {
        // It firsts view the courses which are available 
        System.out.println("Available Courses:"); 
        for (Course course : managedCourses) {
            System.out.println("Code: " + course.code + ", Title: " + course.title);
        }

        // Then ask for further dets
        System.out.print("Enter course code to update: ");
        String courseCode = scanner.nextLine();
        Course courseToUpdate = null;
        for (Course course : managedCourses) {
            if (course.code.equals(courseCode)) {
                courseToUpdate = course;
                break;
            }
        }
    
        if (courseToUpdate == null) {
            System.out.println("Course not found.");
            return;
        }
    
        // Finally updating course details
        System.out.println("Updating details for course: " + courseToUpdate.title);
        System.out.print("Enter new syllabus (leave blank to keep same): ");
        String syllabus = scanner.nextLine();
        if (!syllabus.isEmpty()) {
            courseToUpdate.setSyllabus(syllabus);
        }
    
        System.out.print("Enter new class timings (leave blank to keep same): ");
        String timing = scanner.nextLine();
        if (!timing.isEmpty()) {
            courseToUpdate.timing = timing;
        }
    
        System.out.print("Enter new credits (leave blank to keep same): ");
        String credits = scanner.nextLine();
        if (!credits.isEmpty()) {
            courseToUpdate.credits = Integer.parseInt(credits);
        }
    
        System.out.print("Enter new prerequisites (separated by a comma, leave blank to keep same): ");
        String prerequisites = scanner.nextLine();
        if (!prerequisites.isEmpty()) {
            courseToUpdate.prerequisites = Arrays.asList(prerequisites.split(","));
        }
    
        System.out.print("Enter new enrollment limit (leave blank to keep same): ");
        String enrollmentLimit = scanner.nextLine();
        if (!enrollmentLimit.isEmpty()) {
            courseToUpdate.setEnrollmentLimit(Integer.parseInt(enrollmentLimit)); //string to int of limit
        }
    
        System.out.print("Enter new office hours (leave blank to keep same): ");
        String officeHours = scanner.nextLine();
        if (!officeHours.isEmpty()) {
            courseToUpdate.setOfficeHours(officeHours);
        }
    
        System.out.println("Course details updated.");
    }
    
    
    // 2. View enrolled students
    public void viewEnrolledStudents(List<Student> students) {
        System.out.println("Enrolled Students:");
        for (Course course : managedCourses) {
            for (Student student : students) {
                if (student.getRegisteredCourses().contains(course)) {
                    System.out.println("Student: " + student.email); 
                }
            }
        }
    }

    // 3. View feedback
    public void viewFeedback(List<Student> students, Course course){
        List<Feedback<?>> feedbacks = new ArrayList<>();
        for (Student student : students) {
            for (Feedback<?> feedback : student.getFeedbacks()) {
                if (feedback.getCourseCode().equals(course.getCode())) {
                    feedbacks.add(feedback);
                }
            } 
        }
        if (feedbacks.isEmpty()) {
            System.out.println("No feedback submitted yet");
        } else {
            for (Feedback<?> feedback : feedbacks) {
                System.out.println("Feedback: " + feedback.getFeedback());
            }
        }
    }
}