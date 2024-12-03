/*
 * Ellen Coughlin
 */
import java.util.Scanner;

// Main menu function to handle user input
public class VectorMathOperationsFE1 extends VectorMathOperations1 {
    public static void mainMenu(Scanner scanner) {
        System.out.println("\nWelcome to the Vector Operations Program!");
        while (true) {
            System.out.println("\nEnter 1. To Add 2 Vectors");
            System.out.println("Enter 2. To Subtract 2 Vectors");
            System.out.println("Enter 3. To Find the Magnitude of a Vector");
            System.out.println("Enter 9. To Quit");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.println("\nVector Addition");
                int size = getVectorSize(scanner);
                if (size == -1) {
                    continue; // Go back to the main menu if size is invalid
                }
                double[] v1 = getVectorInput(scanner, "Enter values for Vector1:", size);
                double[] v2 = getVectorInput(scanner, "Enter values for Vector2:", size);

                double[] result = addVectors(v1, v2);
                System.out.println("\nResult:");
                printVectors(v1, v2, result, "+");

            } else if (choice.equals("2")) {
                System.out.println("\nVector Subtraction");
                int size = getVectorSize(scanner);
                if (size == -1) {
                    continue; // Go back to the main menu if size is invalid
                }
                double[] v1 = getVectorInput(scanner, "Enter values for Vector1:", size);
                double[] v2 = getVectorInput(scanner, "Enter values for Vector2:", size);

                double[] result = subtractVectors(v1, v2);
                System.out.println("\nResult:");
                printVectors(v1, v2, result, "-");

            } else if (choice.equals("3")) {
                System.out.println("\nVector Magnitude");
                int size = getVectorSize(scanner);
                if (size == -1) {
                    continue; // Go back to the main menu if size is invalid
                }
                double[] v = getVectorInput(scanner, "Enter values for the Vector:", size);
                double magnitude = magnitudeVector(v);
                System.out.printf("\nThe magnitude is: %.15"
                		+ "f\n", magnitude);

            } else if (choice.equals("9")) {
                System.out.println("Goodbye!");
                break;

            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Helper method to print vectors
    private static void printVectors(double[] v1, double[] v2, double[] result, String operation) {
        for (double v : v1) System.out.println(v); // prints vector1
        System.out.println(operation);
        for (double v : v2) System.out.println(v); // prints vector2
        System.out.println("=");
        for (double r : result) System.out.println(r); // prints the result
    }

    // Entry point of the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        mainMenu(scanner);
        scanner.close();
    }
}