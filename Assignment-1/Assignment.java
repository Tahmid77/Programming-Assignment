import java.util.Scanner;
/**
 * Write a description of class Assignment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Assignment
{
    // instance variables - replace the example below with your own
    private int[] studentMarks;

    /**
     * Constructor for objects of class Assignment
     */
    public Assignment()
    {
        // initialise instance variables
        getAssignmentName();
        studentMarks = new int[30];
        getAssignmentMarks();
        findHighestMark(studentMarks);
        findLowestMark(studentMarks);
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void getAssignmentName()
    {
        Scanner scanner = new Scanner(System.in);

        // Allow the user to input the assignment name
        System.out.print("Enter the assignment name: ");
        String assignmentName = scanner.nextLine();
        
        // Print the assignment name
        System.out.println("Assignment Name is: " + assignmentName);
        
    }
    
    public void getAssignmentMarks()
    {
        Scanner scanner = new Scanner(System.in);

        // Allow the user to input students' marks
        
        for (int i = 0; i < 30; i++) {
            while (true) {
                System.out.print("Enter the mark for student " + (i + 1) + ": ");
                int mark = scanner.nextInt();

                // F3: Report invalid input
                if (mark >= 0 && mark <= 30) {
                    studentMarks[i] = mark;
                    break;
                } else {
                    System.out.println("Invalid input. Marks must be between 0 and 30.");
                }
            }
        }
        
        // Print the students' marks
        System.out.println("Students' Marks:");
        for (int i = 0; i < 30; i++) {
            System.out.println("Student " + (i + 1) + ": " + studentMarks[i]);
        }
        
    }
    
    // Algorithm to find the highest mark
    public static int findHighestMark(int[] marks) {
        int highest = marks[0];
        for (int mark : marks) {
            if (mark > highest) {
                highest = mark;
            }
        }
        System.out.println("Highest Mark: " + highest);
        return highest;
    }

    // Algorithm to find the lowest mark
    public static int findLowestMark(int[] marks) {
        int lowest = marks[0];
        for (int mark : marks) {
            if (mark < lowest) {
                lowest = mark;
            }
        }
        System.out.println("Lowest Mark: " + lowest);
        return lowest;
    }
    
    
    
}
