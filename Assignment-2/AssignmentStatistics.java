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
    
    
    
}

    }
}
