import java.util.*; 

public class TA extends Student {
    private Course assignedCourse;

    public TA(String email, String password) {
        super(email, password);
    }

    public void assignCourse(Course course) {
        this.assignedCourse = course;
    }

    public Course getAssignedCourse() {
        return assignedCourse;
    }

    public void setAssignCourse(Course course) {
        this.assignedCourse = course;
    }

    public void viewAssignedCourse() {
        if (assignedCourse != null) {
            System.out.println("Assigned Course: " + assignedCourse.getTitle());
        } else {
            System.out.println("No course assigned.");
        }
    }

    public void viewStudents(List<Student> students) {
        System.out.println(" Students in " + assignedCourse.getTitle() + ":");
        for (Student student : students) {
            if (student.getRegisteredCourses().contains(assignedCourse)) {
                System.out.println("Student: " + student.getEmail());
            }
        }
    }

    public void assignCGPA(List<Student> students, Scanner scanner) {
        String courseCode= assignedCourse.getCode();
        for (Student student : students) {
            if (student.getRegisteredCourses().contains(assignedCourse)) {
                System.out.print("Enter new CGPA for " + student.getEmail() + " in " + assignedCourse.getTitle() + ": ");
                String cgpaInput = scanner.nextLine();
                if (cgpaInput.trim().isEmpty()) {
                    System.out.println("CGPA cannot be blank.");
                    continue;
                }
                try {
                    double cgpa = Double.parseDouble(cgpaInput);
                    student.setCGPAForCourse(courseCode, cgpa);
                    assignedCourse.setCGPAForStudent(student.getEmail(), cgpa);
                    System.out.println("CGPA updated successfully for " + student.getEmail());
                    // student.getCompletedCourses().add(assignedCourse);
                    // assignedCourse.setCompleted(true);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid CGPA input.");
                }
            }
        }
    }

}
