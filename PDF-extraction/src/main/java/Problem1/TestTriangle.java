package Problem1;
import java.util.Scanner;
//reference:exception trycatch:https://github.com/gautamkrishnar/tcso/blob/master/java/tcso.java
public class TestTriangle {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Enter the lengths of the sides of the triangle:");
        System.out.print("Side 1: ");
        double side1 = console.nextDouble();
        System.out.print("Side 2: ");
        double side2 = console.nextDouble();
        System.out.print("Side 3: ");
        double side3 = console.nextDouble();

        try {//try to catch exception
            Triangle.checkifvalid(side1, side2, side3);
            System.out.println("Triangle with sides " + side1 + ", " + side2 + ", " + side3 + " is valid.");
        } catch (InvalidSideLengthException e) {//i use field here
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        console.close();
    }
}
