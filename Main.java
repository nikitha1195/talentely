import java.util.*;

class Student {
    private String name;
    private String address;
    private int rollNo;

    // Constructor
    public Student(String name, String address, int rollNo) {
        this.name = name;
        this.address = address;
        this.rollNo = rollNo;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getRollNo() {
        return rollNo;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    // Display Student Info
    public void displayStudentInfo() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("----------------------");
    }
}

class StudentManager {
    private static final int MAX_STUDENTS = 100;
    private Student[] students;
    private int studentCount;

    // Constructor
    public StudentManager() {
        students = new Student[MAX_STUDENTS];
        studentCount = 0;
    }

    // Add a new student
    public boolean addStudent(String name, String address, int rollNo) {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Cannot add more students. Maximum limit reached.");
            return false;
        }
        students[studentCount] = new Student(name, address, rollNo);
        studentCount++;
        System.out.println("Student added successfully!");
        return true;
    }

    // Display all students
    public void displayAllStudents() {
        if (studentCount == 0) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("Student List:");
        for (int i = 0; i < studentCount; i++) {
            students[i].displayStudentInfo();
        }
    }

    // Find a student by roll number
    public Student findStudentByRollNo(int rollNo) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getRollNo() == rollNo) {
                return students[i];
            }
        }
        System.out.println("Student with Roll No " + rollNo + " not found.");
        return null;
    }

    // Update student details
    public boolean updateStudent(int rollNo, String newName, String newAddress) {
        Student student = findStudentByRollNo(rollNo);
        if (student != null) {
            student.setName(newName);
            student.setAddress(newAddress);
            System.out.println("Student details updated successfully!");
            return true;
        }
        return false;
    }

    // Delete a student by roll number
    public boolean deleteStudent(int rollNo) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getRollNo() == rollNo) {
                // Shift all elements after the found student
                for (int j = i; j < studentCount - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[studentCount - 1] = null; // Clear the last element
                studentCount--;
                System.out.println("Student deleted successfully!");
                return true;
            }
        }
        System.out.println("Student with Roll No " + rollNo + " not found.");
        return false;
    }
}

class StudentMarks {
    private int rollNo;
    private String name;
    private int[] marks = new int[6]; // Marks for 6 subjects
    private double total;
    private double average;
    private double percentage;
    private double percentile;
    private char grade;

    // Constructor
    public StudentMarks(int rollNo, String name, int[] marks) {
        this.rollNo = rollNo;
        this.name = name;
        setMarks(marks); // Set marks and auto-calculate
    }

    // Getters
    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public int[] getMarks() {
        return marks.clone();
    }

    public double getTotal() {
        return total;
    }

    public double getAverage() {
        return average;
    }

    public double getPercentage() {
        return percentage;
    }

    public double getPercentile() {
        return percentile;
    }

    public char getGrade() {
        return grade;
    }

    // Set marks and recalculate statistics
    public void setMarks(int[] marks) {
        if (marks.length != 6) {
            System.out.println("Error: Please provide exactly 6 subject marks.");
            return;
        }
        this.marks = marks.clone();
        calculateStatistics();
    }

    // Calculate total, average, percentage, and grade
    private void calculateStatistics() {
        total = 0;
        for (int mark : marks) {
            total += mark;
        }
        average = total / 6.0;
        percentage = (total / 600) * 100;
        grade = calculateGrade();
    }

    // Grade calculation logic
    private char calculateGrade() {
        if (percentage >= 90) {
            return 'A';
        } else if (percentage >= 75) {
            return 'B';
        } else if (percentage >= 60) {
            return 'C';
        } else if (percentage >= 50) {
            return 'D';
        } else {
            return 'F';
        }
    }

    // Display student's marks information
    public void displayMarksInfo() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.print("Marks: ");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
        System.out.println();
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Grade: " + grade);
        System.out.println("----------------------");
    }

    // Set the percentile (this can be calculated later)
    public void setPercentile(double percentile) {
        this.percentile = percentile;
    }
}

class StudentMarksManager {
    private static final int MAX_STUDENTS = 100;
    private StudentMarks[] students;
    private int studentCount;

    // Constructor
    public StudentMarksManager() {
        students = new StudentMarks[MAX_STUDENTS];
        studentCount = 0;
    }

    // Add a student with marks
    public boolean addStudent(int rollNo, String name, int[] marks) {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Cannot add more students. Maximum limit reached.");
            return false;
        }
        if (marks.length != 6) {
            System.out.println("Error: Please provide exactly 6 subject marks.");
            return false;
        }
        students[studentCount] = new StudentMarks(rollNo, name, marks);
        studentCount++;
        System.out.println("Student added successfully!");
        return true;
    }

    // Display all students
    public void displayAllStudents() {
        if (studentCount == 0) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("List of Students:");
        for (int i = 0; i < studentCount; i++) {
            students[i].displayMarksInfo();
        }
    }

    // Calculate percentiles for all students
    public void calculatePercentiles() {
        double[] percentages = new double[studentCount];
        for (int i = 0; i < studentCount; i++) {
            percentages[i] = students[i].getPercentage();
        }

        // Sort percentages to find ranks
        double[] sortedPercentages = percentages.clone();
        java.util.Arrays.sort(sortedPercentages);

        for (int i = 0; i < studentCount; i++) {
            double rank = java.util.Arrays.binarySearch(sortedPercentages, students[i].getPercentage()) + 1;
            double percentile = ((studentCount - rank) / (double) studentCount) * 100;
            students[i].setPercentile(percentile);
        }
        System.out.println("Percentiles calculated for all students!");
    }

    // Display students with their percentiles
    public void displayAllWithPercentiles() {
        if (studentCount == 0) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("Students with Percentiles:");
        for (int i = 0; i < studentCount; i++) {
            students[i].displayMarksInfo();
            System.out.println("Percentile: " + students[i].getPercentile() + "%");
            System.out.println("----------------------");
        }
    }

    // Find a student by roll number
    public StudentMarks findStudentByRollNo(int rollNo) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getRollNo() == rollNo) {
                return students[i];
            }
        }
        System.out.println("Student with Roll No " + rollNo + " not found.");
        return null;
    }

    // Delete a student by roll number
    public boolean deleteStudent(int rollNo) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getRollNo() == rollNo) {
                for (int j = i; j < studentCount - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[studentCount - 1] = null;
                studentCount--;
                System.out.println("Student with Roll No " + rollNo + " has been deleted.");
                return true;
            }
        }
        System.out.println("Student with Roll No " + rollNo + " not found.");
        return false;
    }
}




class StudentPreferences {
    private String name;
    private String rollNo;
    private String favoriteColor;
    private String favoriteFood;
    private String favoriteSport;
    private String favoriteSubject;

    // Constructor
    public StudentPreferences(String name, String rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public void setFavoriteSport(String favoriteSport) {
        this.favoriteSport = favoriteSport;
    }

    public void setFavoriteSubject(String favoriteSubject) {
        this.favoriteSubject = favoriteSubject;
    }

    // Display Preferences
    public void displayPreferences() {
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + rollNo);
        System.out.println("Favorite Color: " + favoriteColor);
        System.out.println("Favorite Food: " + favoriteFood);
        System.out.println("Favorite Sport: " + favoriteSport);
        System.out.println("Favorite Subject: " + favoriteSubject);
        System.out.println("----------------------");
    }

    // Method to take input for the preferences
    public void takePreferencesInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student's favorite color: ");
        favoriteColor = scanner.nextLine();

        System.out.print("Enter student's favorite food: ");
        favoriteFood = scanner.nextLine();

        System.out.print("Enter student's favorite sport: ");
        favoriteSport = scanner.nextLine();

        System.out.print("Enter student's favorite subject: ");
        favoriteSubject = scanner.nextLine();
    }
}





public class Main {
    public static void main(String[] args) {
        // Creating instances of managers and preferences
        StudentManager studentManager = new StudentManager();
        StudentMarksManager studentMarksManager = new StudentMarksManager();
        Scanner scanner = new Scanner(System.in);

        // Taking input for number of students
        System.out.println("Enter number of students to add:");
        int numOfStudents = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        for (int i = 0; i < numOfStudents; i++) {
            System.out.println("\nEnter details for Student " + (i + 1));

            // Taking personal details
            System.out.print("Enter student's name: ");
            String name = scanner.nextLine();

            System.out.print("Enter student's address: ");
            String address = scanner.nextLine();

            System.out.print("Enter student's roll number: ");
            int rollNo = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Adding student to StudentManager
            studentManager.addStudent(name, address, rollNo);

            // Taking marks input for 6 subjects
            System.out.println("Enter marks for 6 subjects:");
            int[] marks = new int[6];
            for (int j = 0; j < 6; j++) {
                System.out.print("Enter mark for subject " + (j + 1) + ": ");
                marks[j] = scanner.nextInt();
            }
            scanner.nextLine(); // Consume newline

            // Adding student marks to StudentMarksManager
            studentMarksManager.addStudent(rollNo, name, marks);

            // Creating an instance of StudentPreferences for each student
            StudentPreferences studentPreferences = new StudentPreferences(name, String.valueOf(rollNo));

            // Taking preferences input for the student
            studentPreferences.takePreferencesInput();

            // Displaying all student preferences
            System.out.println("\nDisplaying Student Preferences:");
            studentPreferences.displayPreferences();
        }

        // Display all students with personal details
        System.out.println("\nAll Students' Personal Details:");
        studentManager.displayAllStudents();

        // Display all students with marks, total, percentage, and grade
        System.out.println("\nAll Students' Marks and Statistics:");
        studentMarksManager.displayAllStudents();

        // Calculate and display percentiles for all students
        studentMarksManager.calculatePercentiles();
        System.out.println("\nAll Students with Percentiles:");
        studentMarksManager.displayAllWithPercentiles();

        // Searching for a student by roll number
        System.out.print("\nEnter roll number to search for a student: ");
        int searchRollNo = scanner.nextInt();
        StudentMarks foundStudent = studentMarksManager.findStudentByRollNo(searchRollNo);
        if (foundStudent != null) {
            System.out.println("Found student: " + foundStudent.getName());
        } else {
            System.out.println("Student not found.");
        }

        // Deleting a student by roll number
        System.out.print("\nEnter roll number to delete a student: ");
        int deleteRollNo = scanner.nextInt();
        boolean deleted = studentMarksManager.deleteStudent(deleteRollNo);
        if (deleted) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student with roll number " + deleteRollNo + " not found.");
        }

        // Display the remaining students after deletion
        System.out.println("\nRemaining Students:");
        studentMarksManager.displayAllStudents();
    }
}
