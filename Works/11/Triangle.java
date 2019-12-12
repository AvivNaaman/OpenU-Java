
import java.util.Scanner;

public class Triangle {
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println ("This program calculates the area "
            + "and the perimeter of a given triangle. ");
        System.out.println ("Please enter the three lengths"
            + " of the triangle's sides"); 
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        // Check if lengths are valid (length can only be positive!)
        if (!(a > 0 && b > 0 && c > 0)) {
            System.out.println("Lengths cannot be negative or zero." +
                        " The lenghts are: " + a + ", " + b + ", " + c);
        }
        else {
            // Check again if lengths are valid (with triangle inequality state):
            if (a + b <= c || a + c <= b || b + c <= a) {
                // Error output
                System.out.println("Please enter valid lengths. " +
                        "sum of two lengths must be grater than the other length." +
                        " The lenghts are: " + a + ", " + b + ", " + c);
            }
            else {
                // Perimeter is the sum of lengths
                final int perimeter = a + b + c;
                // Let's calculate Heron's formula. Splitted to make it readable.
                final double halfPerimeter = perimeter / 2.0;
                final double area = Math.sqrt(halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));
                // And output to the user.
                System.out.println("The perimeter of the triangle is " + perimeter +
                        ", and it's area is " + area);
            }
        }
    } // end of method main
} // end of class triangle