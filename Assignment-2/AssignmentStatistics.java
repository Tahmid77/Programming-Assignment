import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Student {
    private String firstName;
    private String lastName;
    private String studentID;
    private double[] marks = new double[3];
    private double totalMark;

    public Student(String firstName, String lastName, String studentID, double[] marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.marks = marks;
        this.totalMark = calculateTotalMark();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public double[] getMarks() {
        return marks;
    }

    public double getTotalMark() {
        return totalMark;
    }

    private double calculateTotalMark() {
        double total = 0.0;
        for (double mark : marks) {
            total += mark;
        }
        return total;
    }
}

public class AssignmentStatistics {

    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        loadStudentsFromCSV("prog5001_students_grade_2022.csv");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Print students' details");
            System.out.println("2. Print students with total marks less than a threshold");
            System.out.println("3. Print top 5 students with highest total marks");
            System.out.println("4. Print top 5 students with lowest total marks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    printStudentsDetails();
                    break;
                case 2:
                    System.out.print("Enter the threshold: ");
                    double threshold = scanner.nextDouble();
                    printStudentsBelowThreshold(threshold);
                    break;
                case 3:
                    printTop5Students(true);
                    break;
                case 4:
                    printTop5Students(false);
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void loadStudentsFromCSV(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue; // Ignore comments
                }

                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String firstName = parts[0].trim();
                    String lastName = parts[1].trim();
                    String studentID = parts[2].trim();
                    double[] marks = new double[3];
                    boolean validData = true;
                    for (int i = 3; i < 6; i++) {
                        try {
                            marks[i - 3] = Double.parseDouble(parts[i].trim());
                        } catch (NumberFormatException e) {
                            validData = false;
                            // Print error message for invalid mark format
                            System.err.println("Invalid mark format for line: " + line);
                            break;
                        }
                    }

                    if (validData) {
                        students.add(new Student(firstName, lastName, studentID, marks));
                    }
                } else {
                    // Handle incorrect CSV format
                    // Print error message for invalid CSV format
                    System.err.println("Invalid CSV format for line: " + line);
                }
            }
        } catch (IOException e) {
            // Print error message for file reading error
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }

    private static void printStudentsDetails() {
        for (Student student : students) {
            // Print student details
            System.out.println("Name: " + student.getFullName());
            System.out.println("Student ID: " + student.getStudentID());
            System.out.println("Marks: " + student.getMarks()[0] + ", " + student.getMarks()[1] + ", " + student.getMarks()[2]);
            System.out.println("Total Mark: " + student.getTotalMark());
            System.out.println();
        }
    }

    private static void printStudentsBelowThreshold(double threshold) {
        System.out.println("Students with total marks below " + threshold + ":");
        for (Student student : students) {
            if (student.getTotalMark() < threshold) {
                // Print student details
                System.out.println("Name: " + student.getFullName());
                System.out.println("Student ID: " + student.getStudentID());
                System.out.println("Total Mark: " + student.getTotalMark());
                System.out.println();
            }
        }
    }

    private static void printTop5Students(boolean highest) {
        String order = highest ? "highest" : "lowest";
        int count = Math.min(5, students.size());

        Student[] topStudents = new Student[count];

        for (int i = 0; i < count; i++) {
            topStudents[i] = students.get(i);
        }

        for (int i = count; i < students.size(); i++) {
            Student student = students.get(i);
            for (int j = 0; j < count; j++) {
                if ((highest && student.getTotalMark() > topStudents[j].getTotalMark()) ||
                        (!highest && student.getTotalMark() < topStudents[j].getTotalMark())) {
                    // Shift students down the list
                    for (int k = count - 1; k > j; k--) {
                        topStudents[k] = topStudents[k - 1];
                    }
                    topStudents[j] = student;
                    break;
                }
            }
        }

        System.out.println("Top 5 students with " + order + " total marks:");
        for (int i = 0; i < count; i++) {
            Student student = topStudents[i];
            // Print student details
            System.out.println("Name: " + student.getFullName());
            System.out.println("Student ID: " + student.getStudentID());
            System.out.println("Total Mark: " + student.getTotalMark());
            System.out.println();
        }
    }
}