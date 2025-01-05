package codsoft1;
import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolledStudents;
    String schedule;

    // Constructor
    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0; // Initially no students are enrolled
        this.schedule = schedule;
    }

    // Method to check if a course has available slots
    public boolean hasAvailableSlots() {
        return enrolledStudents < capacity;
    }

    // Enroll a student in the course
    public boolean enrollStudent() {
        if (hasAvailableSlots()) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    // Drop a student from the course
    public boolean dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        }
        return false;
    }

    // Display course details
    public void displayCourseInfo() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Capacity: " + capacity);
        System.out.println("Enrolled Students: " + enrolledStudents);
        System.out.println("Available Slots: " + (capacity - enrolledStudents));
        System.out.println();
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses;

    // Constructor
    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    // Register a student for a course
    public boolean registerForCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    // Drop a course
    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.dropStudent();
            registeredCourses.remove(course);
            return true;
        }
        return false;
    }

    // Display student information and registered courses
    public void displayStudentInfo() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Registered Courses:");
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println("- " + course.title + " (" + course.courseCode + ")");
            }
        }
        System.out.println();
    }
}

public class CourseRegistrationSystem {
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample data for courses
        courses.add(new Course("CS101", "Introduction to Computer Science", "Learn the basics of programming", 3, "Mon-Wed 10:00-12:00"));
        courses.add(new Course("MATH101", "Calculus I", "Fundamentals of calculus", 2, "Tue-Thu 09:00-11:00"));
        courses.add(new Course("PHY101", "Physics I", "Introduction to mechanics and motion", 2, "Mon-Wed 14:00-16:00"));

        // Sample data for students
        students.add(new Student("S101", "Alice"));
        students.add(new Student("S102", "Bob"));

        while (true) {
            System.out.println("Welcome to the Course Registration System");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Student Info");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character after number input

            switch (choice) {
                case 1:
                    displayAvailableCourses();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    viewStudentInfo();
                    break;
                case 5:
                    System.out.println("Thank you for using the Course Registration System!");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    // Display all available courses
    public static void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            course.displayCourseInfo();
        }
    }

    // Allow a student to register for a course
    public static void registerForCourse() {
        System.out.println("Enter your student ID:");
        String studentID = scanner.nextLine();
        Student student = findStudent(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enter the course code to register for:");
        String courseCode = scanner.nextLine();
        Course course = findCourse(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.registerForCourse(course)) {
            System.out.println("Successfully registered for " + course.title);
        } else {
            System.out.println("Failed to register. The course may be full.");
        }
    }

    // Allow a student to drop a course
    public static void dropCourse() {
        System.out.println("Enter your student ID:");
        String studentID = scanner.nextLine();
        Student student = findStudent(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enter the course code to drop:");
        String courseCode = scanner.nextLine();
        Course course = findCourse(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.dropCourse(course)) {
            System.out.println("Successfully dropped the course " + course.title);
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    // View student information and their registered courses
    public static void viewStudentInfo() {
        System.out.println("Enter your student ID:");
        String studentID = scanner.nextLine();
        Student student = findStudent(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        student.displayStudentInfo();
    }

    // Helper method to find a student by ID
    public static Student findStudent(String studentID) {
        for (Student student : students) {
            if (student.studentID.equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    // Helper method to find a course by course code
    public static Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
