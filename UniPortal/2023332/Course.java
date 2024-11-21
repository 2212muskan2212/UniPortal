import java.util.*;
import java.time.LocalDate;

class Course {
    String code;
    String title;
    String professor;
    int credits;
    List<String> prerequisites;
    String timing;
    String syllabus;
    int enrollmentLimit;
    String officeHours;
    private Map<String, Double> studentCGPA;
    private boolean isCompleted;
    private int enrollmentCount;
    LocalDate dropDeadline;

    private static List<Course> allCourses = new ArrayList<>();

    public Course(String code, String title, String professor, int credits, List<String> prerequisites, String timing,
                  String syllabus, int enrollmentLimit, String officeHours) {
        this.code = code;
        this.title = title;
        this.professor = professor;
        this.credits = credits;
        this.prerequisites = prerequisites;
        this.timing = timing;
        this.syllabus = syllabus;
        this.enrollmentLimit = enrollmentLimit;
        this.officeHours = officeHours;
        this.studentCGPA = new HashMap<>();
        this.enrollmentCount = 0;
        allCourses.add(this);
    }

    public int getEnrollmentCount() {
        return enrollmentCount;
    }

    public void incrementEnrollmentCount() {
        enrollmentCount++;
    }

    public static List<Course> getAllCourses() {
        return allCourses;
    }


    public void displayCourseDetails() {
        System.out.println("Code: " + code + ", Title: " + title + ", Professor: " + professor +
                ", Credits: " + credits + ", Prerequisites: " + prerequisites + ", Timing: " + timing +
                ", Syllabus: " + syllabus + ", Enrollment Limit: " + enrollmentLimit + ", Office Hours: " + officeHours);
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public LocalDate getDropDeadline() {
        return dropDeadline;
    }

    public void setDropDeadline(LocalDate dropDeadline) {
        this.dropDeadline = dropDeadline;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void setEnrollmentLimit(int enrollmentLimit) {
        this.enrollmentLimit = enrollmentLimit;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public void setCGPAForStudent(String studentEmail, double cgpa) {
        studentCGPA.put(studentEmail, cgpa);
    }

    public double getCGPAForStudent(String studentEmail) {
        return studentCGPA.getOrDefault(studentEmail, 0.0);
    }

    @Override
    public String toString() {
        return "Course Code: " + code + "\n" +
               "Course Title: " + title + "\n" +
               "Professor: " + professor + "\n" +
               "Credits: " + credits + "\n" +
               "Class Timing: " + timing + "\n" +
               "Prerequisites: " + prerequisites + "\n" +
               "Syllabus: " + syllabus + "\n" +
               "Enrollment Limit: " + enrollmentLimit + "\n" +
               "Office Hours: " + officeHours + "\n";
    }

    public void setProfessor(String professorEmail) {
        this.professor = professorEmail;
    }

    public String getProfessor() {
        return professor;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

}


