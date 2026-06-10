import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Enrollment> enrollments = new ArrayList<>();
    static ArrayList<Feedback> feedbacks = new ArrayList<>();

    static final String ADMIN_USERNAME = "admin";
    static final String ADMIN_PASSWORD = "admin123";

    // ==========================
    // ABSTRACT PERSON CLASS
    // ==========================

    static abstract class Person {

        protected int id;
        protected String name;
        protected String email;

        public Person(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public abstract void displayDetails();
    }

    // ==========================
    // STUDENT CLASS
    // ==========================

    static class Student extends Person {

        private String password;

        public Student(int id,
                       String name,
                       String email,
                       String password) {

            super(id, name, email);
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        @Override
        public void displayDetails() {

            System.out.println(
                    "ID: " + id +
                    " | Name: " + name +
                    " | Email: " + email
            );
        }
    }

    // ==========================
    // COURSE CLASS
    // ==========================

    static class Course {

        private int courseId;
        private String courseName;
        private String instructor;
        private double fees;

        public Course(int courseId,
                      String courseName,
                      String instructor,
                      double fees) {

            this.courseId = courseId;
            this.courseName = courseName;
            this.instructor = instructor;
            this.fees = fees;
        }

        public int getCourseId() {
            return courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public String getInstructor() {
            return instructor;
        }

        public double getFees() {
            return fees;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public void setInstructor(String instructor) {
            this.instructor = instructor;
        }

        public void setFees(double fees) {
            this.fees = fees;
        }

        @Override
        public String toString() {

            return courseId +
                    "," +
                    courseName +
                    "," +
                    instructor +
                    "," +
                    fees;
        }
    }

    // ==========================
    // ENROLLMENT CLASS
    // ==========================

    static class Enrollment {

        private int studentId;
        private int courseId;
        private int progress;

        public Enrollment(int studentId,
                          int courseId) {

            this.studentId = studentId;
            this.courseId = courseId;
            this.progress = 0;
        }

        public int getStudentId() {
            return studentId;
        }

        public int getCourseId() {
            return courseId;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }
    }

    // ==========================
    // FEEDBACK CLASS
    // ==========================

    static class Feedback {

        private String studentName;
        private String courseName;
        private String comment;

        public Feedback(String studentName,
                        String courseName,
                        String comment) {

            this.studentName = studentName;
            this.courseName = courseName;
            this.comment = comment;
        }

        @Override
        public String toString() {

            return studentName +
                    " | " +
                    courseName +
                    " | " +
                    comment;
        }
    }

    // ==========================
    // MAIN METHOD
    // ==========================

    public static void main(String[] args) {

        loadDefaultCourses();

        while (true) {

            System.out.println("\n================================");
            System.out.println(" ONLINE COURSE MANAGEMENT SYSTEM");
            System.out.println("================================");

            System.out.println("1. Admin Login");
            System.out.println("2. Student Registration");
            System.out.println("3. Student Login");
            System.out.println("4. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    adminLogin();
                    break;

                case 2:
                    registerStudent();
                    break;

                case 3:
                    studentLogin();
                    break;

                case 4:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // ==========================
    // DEFAULT COURSES
    // ==========================

    static void loadDefaultCourses() {

        courses.add(
                new Course(
                        101,
                        "Java Programming",
                        "John Smith",
                        4999
                ));

        courses.add(
                new Course(
                        102,
                        "Web Development",
                        "David Lee",
                        3999
                ));

        courses.add(
                new Course(
                        103,
                        "Python Programming",
                        "Alice Brown",
                        4500
                ));

        courses.add(
                new Course(
                        104,
                        "Data Structures",
                        "Michael Scott",
                        3500
                ));
    }
        // ==========================
    // STUDENT REGISTRATION
    // ==========================

    static void registerStudent() {

        try {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            for (Student s : students) {
                if (s.getId() == id) {
                    System.out.println("Student ID already exists!");
                    return;
                }
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            if (!email.contains("@")) {
                System.out.println("Invalid Email!");
                return;
            }

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            Student student =
                    new Student(
                            id,
                            name,
                            email,
                            password);

            students.add(student);

            System.out.println("Student Registered Successfully!");

        } catch (Exception e) {

            System.out.println("Registration Error!");

            sc.nextLine();
        }
    }

    // ==========================
    // STUDENT LOGIN
    // ==========================

    static void studentLogin() {

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        for (Student s : students) {

            if (s.getEmail().equals(email)
                    && s.getPassword().equals(password)) {

                System.out.println(
                        "Welcome " + s.getName());

                studentMenu(s);

                return;
            }
        }

        System.out.println("Invalid Login!");
    }

    // ==========================
    // ADMIN LOGIN
    // ==========================

    static void adminLogin() {

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        if (username.equals(ADMIN_USERNAME)
                && password.equals(ADMIN_PASSWORD)) {

            adminMenu();

        } else {

            System.out.println("Invalid Admin Credentials!");
        }
    }

    // ==========================
    // ADMIN MENU
    // ==========================

    static void adminMenu() {

        while (true) {

            System.out.println("\n========== ADMIN MENU ==========");

            System.out.println("1. Add Course");
            System.out.println("2. Update Course");
            System.out.println("3. Delete Course");
            System.out.println("4. View Courses");
            System.out.println("5. View Students");
            System.out.println("6. View Feedback");
            System.out.println("7. View Report");
            System.out.println("8. Logout");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addCourse();
                    break;

                case 2:
                    updateCourse();
                    break;

                case 3:
                    deleteCourse();
                    break;

                case 4:
                    viewCourses();
                    break;

                case 5:
                    viewStudents();
                    break;

                case 6:
                    viewFeedback();
                    break;

                case 7:
                    viewReport();
                    break;

                case 8:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // ==========================
    // ADD COURSE
    // ==========================

    static void addCourse() {

        System.out.print("Course ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Course c : courses) {

            if (c.getCourseId() == id) {

                System.out.println("Course ID Already Exists!");
                return;
            }
        }

        System.out.print("Course Name: ");
        String name = sc.nextLine();

        System.out.print("Instructor: ");
        String instructor = sc.nextLine();

        System.out.print("Fees: ");
        double fees = sc.nextDouble();

        courses.add(
                new Course(
                        id,
                        name,
                        instructor,
                        fees));

        System.out.println("Course Added Successfully!");
    }

    // ==========================
    // UPDATE COURSE
    // ==========================

    static void updateCourse() {

        System.out.print("Enter Course ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Course c : courses) {

            if (c.getCourseId() == id) {

                System.out.print("New Course Name: ");
                c.setCourseName(sc.nextLine());

                System.out.print("New Instructor: ");
                c.setInstructor(sc.nextLine());

                System.out.print("New Fees: ");
                c.setFees(sc.nextDouble());

                System.out.println("Course Updated!");

                return;
            }
        }

        System.out.println("Course Not Found!");
    }

    // ==========================
    // DELETE COURSE
    // ==========================

    static void deleteCourse() {

        System.out.print("Enter Course ID: ");
        int id = sc.nextInt();

        Course removeCourse = null;

        for (Course c : courses) {

            if (c.getCourseId() == id) {

                removeCourse = c;
                break;
            }
        }

        if (removeCourse != null) {

            courses.remove(removeCourse);

            System.out.println("Course Deleted!");

        } else {

            System.out.println("Course Not Found!");
        }
    }

    // ==========================
    // VIEW COURSES
    // ==========================

    static void viewCourses() {

        System.out.println("\n========== COURSES ==========");

        for (Course c : courses) {

            System.out.println(
                    c.getCourseId()
                            + " | "
                            + c.getCourseName()
                            + " | "
                            + c.getInstructor()
                            + " | ₹"
                            + c.getFees());
        }
    }

    // ==========================
    // VIEW STUDENTS
    // ==========================

    static void viewStudents() {

        System.out.println("\n========== STUDENTS ==========");

        if (students.isEmpty()) {

            System.out.println("No Students Registered!");
            return;
        }

        for (Student s : students) {

            s.displayDetails();
        }
    }

    // ==========================
    // VIEW FEEDBACK
    // ==========================

    static void viewFeedback() {

        System.out.println("\n========== FEEDBACK ==========");

        if (feedbacks.isEmpty()) {

            System.out.println("No Feedback Available!");
            return;
        }

        for (Feedback f : feedbacks) {

            System.out.println(f);
        }
    }

    // ==========================
    // REPORT
    // ==========================

    static void viewReport() {

        System.out.println("\n========== REPORT ==========");

        System.out.println(
                "Total Students : "
                        + students.size());

        System.out.println(
                "Total Courses : "
                        + courses.size());

        System.out.println(
                "Total Enrollments : "
                        + enrollments.size());

        System.out.println(
                "Total Feedback : "
                        + feedbacks.size());
    }
        // ==========================
    // STUDENT MENU
    // ==========================

    static void studentMenu(Student student) {

        while (true) {

            System.out.println("\n========== STUDENT MENU ==========");
            System.out.println("1. View Courses");
            System.out.println("2. Search Course");
            System.out.println("3. Select / Enroll Course");
            System.out.println("4. View My Courses");
            System.out.println("5. Update Progress");
            System.out.println("6. Submit Feedback");
            System.out.println("7. Generate Certificate");
            System.out.println("8. Save Data");
            System.out.println("9. Logout");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewCourses();
                    break;

                case 2:
                    searchCourse();
                    break;

                case 3:
                    enrollCourse(student);
                    break;

                case 4:
                    viewMyCourses(student);
                    break;

                case 5:
                    updateProgress(student);
                    break;

                case 6:
                    submitFeedback(student);
                    break;

                case 7:
                    generateCertificate(student);
                    break;

                case 8:
                    saveData();
                    break;

                case 9:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // ==========================
    // SEARCH COURSE
    // ==========================

    static void searchCourse() {

        System.out.print("Enter Course Name: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;

        for (Course c : courses) {

            if (c.getCourseName()
                    .toLowerCase()
                    .contains(keyword)) {

                System.out.println(
                        c.getCourseId()
                                + " | "
                                + c.getCourseName()
                                + " | "
                                + c.getInstructor()
                                + " | ₹"
                                + c.getFees());

                found = true;
            }
        }

        if (!found) {
            System.out.println("Course Not Found!");
        }
    }

    // ==========================
    // ENROLL COURSE
    // ==========================

    static void enrollCourse(Student student) {

        viewCourses();

        System.out.print("Enter Course ID: ");
        int courseId = sc.nextInt();

        for (Enrollment e : enrollments) {

            if (e.getStudentId() == student.getId()
                    && e.getCourseId() == courseId) {

                System.out.println(
                        "Already Enrolled In This Course!");

                return;
            }
        }

        for (Course c : courses) {

            if (c.getCourseId() == courseId) {

                enrollments.add(
                        new Enrollment(
                                student.getId(),
                                courseId));

                System.out.println(
                        "Course Enrolled Successfully!");

                return;
            }
        }

        System.out.println("Course Not Found!");
    }

    // ==========================
    // VIEW MY COURSES
    // ==========================

    static void viewMyCourses(Student student) {

        System.out.println(
                "\n========== MY COURSES ==========");

        boolean found = false;

        for (Enrollment e : enrollments) {

            if (e.getStudentId() == student.getId()) {

                for (Course c : courses) {

                    if (c.getCourseId()
                            == e.getCourseId()) {

                        System.out.println(
                                c.getCourseId()
                                        + " | "
                                        + c.getCourseName()
                                        + " | Progress: "
                                        + e.getProgress()
                                        + "%");

                        found = true;
                    }
                }
            }
        }

        if (!found) {

            System.out.println(
                    "No Courses Enrolled!");
        }
    }

    // ==========================
    // UPDATE PROGRESS
    // ==========================

    static void updateProgress(Student student) {

        System.out.print(
                "Enter Course ID: ");

        int courseId = sc.nextInt();

        System.out.print(
                "Enter Progress (0-100): ");

        int progress = sc.nextInt();

        if (progress < 0 || progress > 100) {

            System.out.println(
                    "Invalid Progress!");

            return;
        }

        for (Enrollment e : enrollments) {

            if (e.getStudentId()
                    == student.getId()
                    &&
                    e.getCourseId()
                            == courseId) {

                e.setProgress(progress);

                System.out.println(
                        "Progress Updated!");

                return;
            }
        }

        System.out.println(
                "Enrollment Not Found!");
    }

    // ==========================
    // SUBMIT FEEDBACK
    // ==========================

    static void submitFeedback(Student student) {

        System.out.print(
                "Enter Course Name: ");

        String courseName =
                sc.nextLine();

        System.out.print(
                "Enter Feedback: ");

        String comment =
                sc.nextLine();

        feedbacks.add(
                new Feedback(
                        student.getName(),
                        courseName,
                        comment));

        System.out.println(
                "Feedback Submitted!");
    }

    // ==========================
    // GENERATE CERTIFICATE
    // ==========================

    static void generateCertificate(Student student) {

        System.out.print(
                "Enter Course ID: ");

        int courseId =
                sc.nextInt();

        sc.nextLine();

        for (Enrollment e : enrollments) {

            if (e.getStudentId()
                    == student.getId()
                    &&
                    e.getCourseId()
                            == courseId) {

                if (e.getProgress() < 100) {

                    System.out.println(
                            "Course Not Completed!");

                    return;
                }

                String courseName = "";

                for (Course c : courses) {

                    if (c.getCourseId()
                            == courseId) {

                        courseName =
                                c.getCourseName();
                    }
                }

                try {

                    FileWriter writer =
                            new FileWriter(
                                    "Certificate_"
                                            + student.getName()
                                            + ".txt");

                    writer.write(
                            "=================================\n");

                    writer.write(
                            "CERTIFICATE OF COMPLETION\n");

                    writer.write(
                            "=================================\n\n");

                    writer.write(
                            "This Certifies That\n\n");

                    writer.write(
                            student.getName()
                                    + "\n\n");

                    writer.write(
                            "Successfully Completed\n\n");

                    writer.write(
                            courseName
                                    + "\n");

                    writer.close();

                    System.out.println(
                            "Certificate Generated!");

                } catch (Exception ex) {

                    System.out.println(
                            "Certificate Error!");
                }

                return;
            }
        }

        System.out.println(
                "Enrollment Not Found!");
    }

    // ==========================
    // SAVE DATA
    // ==========================

    static void saveData() {

        saveStudents();
        saveEnrollments();
        saveFeedbacks();

        System.out.println(
                "All Data Saved Successfully!");
    }

    static void saveStudents() {

        try {

            FileWriter writer =
                    new FileWriter(
                            "students.txt");

            for (Student s : students) {

                writer.write(
                        s.getId()
                                + ","
                                + s.getName()
                                + ","
                                + s.getEmail()
                                + "\n");
            }

            writer.close();

        } catch (Exception e) {

            System.out.println(
                    "Student Save Error!");
        }
    }

    static void saveEnrollments() {

        try {

            FileWriter writer =
                    new FileWriter(
                            "enrollments.txt");

            for (Enrollment e : enrollments) {

                writer.write(
                        e.getStudentId()
                                + ","
                                + e.getCourseId()
                                + ","
                                + e.getProgress()
                                + "\n");
            }

            writer.close();

        } catch (Exception e) {

            System.out.println(
                    "Enrollment Save Error!");
        }
    }

    static void saveFeedbacks() {

        try {

            FileWriter writer =
                    new FileWriter(
                            "feedback.txt");

            for (Feedback f : feedbacks) {

                writer.write(
                        f.toString()
                                + "\n");
            }

            writer.close();

        } catch (Exception e) {

            System.out.println(
                    "Feedback Save Error!");
        }
    }
}