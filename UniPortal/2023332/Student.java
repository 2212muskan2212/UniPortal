import java.util.*;
import java.time.LocalDate;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class DropDeadlinePassedException extends Exception {
    public DropDeadlinePassedException(String message) {
        super(message);
    }
}

class Feedback<T> {
    private T feedback;
    private String courseCode;

    public Feedback(T feedback, String courseCode) {
        this.feedback = feedback;
        this.courseCode = courseCode;
    }

    public T getFeedback() {
        return feedback;
    }

    public String getCourseCode() {
        return courseCode;
    }
}

class Student extends User {
    private int currentSemester;
    private int totalCredits;
    private List<Course> registeredCourses= new ArrayList<>();
    private List<Complaint> complaints;
    private List<Course> completedCourses; 
    private double cgpa;
    private Map<String, Double> courseCGPA;
    private String contactNumber;
    private List<Feedback<?>> feedbacks;

    public Student(String email, String password) {
        super(email, password);
        this.currentSemester = 1; 
        this.totalCredits = 0;
        this.cgpa= 0; 
        this.courseCGPA = new HashMap<>();
        this.contactNumber= "";
        this.registeredCourses = new ArrayList<>();
        this.complaints = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
    }

    @Override
    public boolean login(String email, String password) throws InvalidLoginException {
        if (this.email.equals(email) && this.password.equals(password)) {
            return true;
        } else {
            throw new InvalidLoginException("Invalid email or password");
        }
    }

    public void setCGPAForCourse(String courseCode, double cgpa) {
        if (courseCGPA == null) {
            courseCGPA = new HashMap<>();
        }
        courseCGPA.put(courseCode, cgpa);
    }

    public double getCGPAForCourse(String courseCode) {
        return courseCGPA != null ? courseCGPA.getOrDefault(courseCode, 0.0) : 0.0;
    }

    public List<Course> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(List<Course> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public String getEmail() {
        return super.getEmail();
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Student: " + email + " [Contact: " + contactNumber + "]";
    }

    // public void completeCourse(Course course) {
    //     if (registeredCourses.contains(course)) {
    //         if (this.getCGPAForCourse(course.getCode()) != 0.0) {
    //             course.setCompleted(true);
    //             System.out.println("Course completed: " + course.title);
    //         } else {
    //             System.out.println("CGPA not assigned for this course.");
    //         }
    //     } else {
    //         System.out.println("Not registered for this course.");
    //     }
    // }

    // 1. View Available Courses
    public void viewAvailableCourses(List<Course> availableCourses) {
        System.out.println("Available Courses for Semester " + currentSemester + ":");
        for (Course course : availableCourses) {
            if (courseIsForCurrentSemester(course)) {
                course.displayCourseDetails();
            }
        }
    }

    private boolean courseIsForCurrentSemester(Course course) {
        // assuming: all courses are available in the current semester
        return true;
    }

    // 2. Register for courses
    public void registerForCourse(Course course) {
        if (totalCredits + course.credits > 20) {
            System.out.println("Credit limit exceeded!");
            return;
        }
        // Checking prerequisites first
        for (String prerequisite : course.prerequisites) {
            boolean prerequisiteCompleted = false;
            for (Course completedCourse : completedCourses) {
                if (completedCourse.code.equals(prerequisite)) {
                    prerequisiteCompleted = true;
                    break;
                }
            }
            if (!prerequisiteCompleted) {
                System.out.println(" Not completed the prerequisite: " + prerequisite);
                return;
            }
        }
        // Checking enrollment limit
        if (course.getEnrollmentCount() >= course.enrollmentLimit) {
            try {
                throw new CourseFullException("Enrollment limit is exceeded for " + course.title);
            } catch (CourseFullException e) {
                System.out.println(e.getMessage());
            }
            return;
        }
        registeredCourses.add(course);
        totalCredits += course.credits;
        System.out.println("Successfully registered for " + course.title);
        course.incrementEnrollmentCount();
    }
    

    // 3. View Schedule
    public void viewSchedule() {
        System.out.println("Your Course Schedule:");
        for (Course course : registeredCourses) {
            System.out.println(course.title + " at " + course.timing + " by " + course.professor);
        }
    }

    // 4. Track Academic Progress 
    public void trackAcademicProgress() {
        System.out.println("Courses and Course Grades (CG):");

        List<Course> registeredCourses = this.getRegisteredCourses();
    
        for (Course course : registeredCourses) {
            double courseGrade = getCGPAForCourse(course.getCode()); // Get the CG for the course
            System.out.println("Course: " + course.getTitle() + " (Code: " + course.getCode() + "), CG: " + courseGrade);
        }
    
        // Print the final CGPA
        System.out.println("Final CGPA: " + getCgpa());
    }
    
    // 5. Drop Course
    // public void dropCourse(Course course) {
    //     if (registeredCourses.contains(course)) {
    //         registeredCourses.remove(course);
    //         totalCredits -= course.credits;
    //         System.out.println("Dropped course: " + course.title);
    //     } else {
    //         System.out.println(" Not registered for this course.");
    //     }
    // }
    // public void dropCourse(Course course) {
    //     LocalDate currentDate = LocalDate.now(); 
    //     if (registeredCourses.contains(course)) {
    //         // Check if the current date is before or on the deadline
    //         if (currentDate.isBefore(course.dropDeadline) || currentDate.isEqual(course.dropDeadline)) {
    //             registeredCourses.remove(course);
    //             totalCredits -= course.credits;
    //             System.out.println("Dropped course: " + course.title);
    //         } else {
    //             System.out.println("Drop deadline for this course has ended.");
    //         }
    //     } else {
    //         System.out.println("Not registered for this course.");
    //     }
    // }

    public void dropCourse(Course course) {
        LocalDate currentDate = LocalDate.now();
        
        try {
            if (registeredCourses.contains(course)) {
                // Check if the current date is before or on the deadline
                if (currentDate.isBefore(course.dropDeadline) || currentDate.isEqual(course.dropDeadline)) {
                    registeredCourses.remove(course);
                    totalCredits -= course.credits;
                    System.out.println("Dropped course: " + course.title);
                } else {
                    throw new DropDeadlinePassedException("Dealine to drop " + course.title + " has ended.");
                }
            } else {
                System.out.println("Not registered for this course.");
            }
        } catch (DropDeadlinePassedException e) {
            System.out.println(e.getMessage());
        }
    }

    // 6. Submit Complaints
    public void submitComplaint(String description) {
        Complaint complaint = new Complaint(description);
        complaints.add(complaint);
        System.out.println("Complaint submitted: " + description);
    }

    public void viewComplaints() {
        for (Complaint complaint : complaints) {
            complaint.displayComplaint();
        }
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    //7. Give feedback
    public void giveFeedback(Scanner scanner) {
        System.out.print("Enter course code to give feedback: ");
        String courseCode = scanner.nextLine();
        Course course = null;
        for (Course c : Course.getAllCourses()) {
            if (c.getCode().equals(courseCode)) {
                course = c;
                break;
            }
        }
        
        if (course != null) {
            if (completedCourses.contains(course)) {
                System.out.print("Enter feedback type (1 for rating, 2 for comment): ");
                int feedbackType = scanner.nextInt();
                scanner.nextLine(); 
        
                Feedback<?> feedback = null;
                if (feedbackType == 1) {
                    System.out.print("Enter rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine(); 
                    feedback = new Feedback<>(rating, course.getCode());
                } else if (feedbackType == 2) {
                    System.out.print("Enter comment: ");
                    String comment = scanner.nextLine();
                    feedback = new Feedback<>(comment, course.getCode());
                }
        
                if (feedback != null) {
                    feedbacks.add(feedback);
                    System.out.println("Feedback submitted for " + course.getCode());
                } else {
                    System.out.println("Invalid feedback type");
                }
            } else {
                System.out.println("Course not completed yet");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    public List<Feedback<?>> getFeedbacks() {
        return feedbacks;
    }
}
