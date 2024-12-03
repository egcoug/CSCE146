/*
 * Ellen Coughlin
 */
// Import necessary classes
import java.util.Scanner;

public class VectorMathOperations1 {
    // Addition of two vectors
    public static double[] addVectors(double[] v1, double[] v2) {
        double[] result = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] + v2[i];
        }
        return result;
    }

    // Subtraction of two vectors
    public static double[] subtractVectors(double[] v1, double[] v2) {
        double[] result = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] - v2[i];
        }
        return result;
    }

    // Magnitude of a vector
    public static double magnitudeVector(double[] v) {
        double sumOfSquares = 0;
        for (double component : v) {
            sumOfSquares += component * component;
        }
        return Math.sqrt(sumOfSquares);
    }

    // User input for vector values
    public static double[] getVectorInput(Scanner scanner, String prompt, int size) {
        double[] vector = new double[size];
        System.out.println(prompt);
        for (int i = 0; i < size; i++) {
            vector[i] = Double.parseDouble(scanner.nextLine());
        }
        return vector;
    }

    // Get size from user input
    public static int getVectorSize(Scanner scanner) {
        int size;
        while (true) {
            try {
                System.out.println("Enter the size of the Vectors:");
                size = Integer.parseInt(scanner.nextLine());
                if (size < 1) {
                    System.out.println("Invalid Size");
                    return -1; // Return -1 for size < 1
                }
                return size;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}