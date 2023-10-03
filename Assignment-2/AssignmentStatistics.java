import java.io.*;
import java.util.*;

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
                            System.err.println("Invalid mark format for line: " + line);
                            break;
                        }
                    }

                    if (validData) {
                        students.add(new Student(firstName, lastName, studentID, marks));
                    }
                } else {
                    // Handle incorrect CSV format
                    System.err.println("Invalid CSV format for line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }
    
    
    
    
}

    }
}
