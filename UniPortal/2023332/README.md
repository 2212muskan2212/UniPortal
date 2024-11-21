# Incoporation of Generic programming
Generic programming is implemented in the Feedback class using a type parameter <T>, allowing it to store various feedback types (e.g., Integer for ratings and String for comments). This enables the Student class to manage feedback efficiently without multiple implementations.

# Incoporation of Object classes
Object classes are utilized through the definition of specific classes like Student and TA, where the TA class inherits functionalities from the Student class, promoting code reuse. Encapsulation is achieved by using private attributes along with public methods, ensuring controlled access. Each class contains methods that define the behaviors associated with the objects. For example, the assignCGPA method in the TA class allows TAs to manage student grades, demonstrating how objects interact through their methods.

# Incoporation of Exception Handling
Exception handling is implemented through custom exceptions to enhance user experience:
- CourseFullException: Thrown when a student attempts to register for a course that is already full (enrollment limit exceeded).
- InvalidLoginException: Triggered when a user enters incorrect login credentials.
- DropDeadlinePassedException: Raised if a student tries to drop a course after the specified deadline.
These exceptions are handled using try-catch blocks, ensuring that users receive meaningful feedback without causing the application to crash.