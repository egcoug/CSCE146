/*
 * Ellen Coughlin
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

class Prize {
    String name;
    double price;

    public Prize(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class PriceGuessingGame {

    // Read prizes from the file and return an array of Prize objects
    public static Prize[] readPrizesFromFile(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            Prize[] prizes = new Prize[100]; // Assume max 100 prizes in file
            int count = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splitLine = line.split("\t");
                if (splitLine.length == 2) {
                    try {
                        String name = splitLine[0];
                        double price = Double.parseDouble(splitLine[1]);
                        prizes[count++] = new Prize(name, price);
                    } catch (NumberFormatException e) {
                        // Skip lines with non-numeric prices
                        continue;
                    }
                }
            }
            fileScanner.close();
            Prize[] result = new Prize[count];
            System.arraycopy(prizes, 0, result, 0, count);
            return result;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return new Prize[0];
        }
    }

    // Randomly select 5 unique prizes for the showcase
    public static Prize[] selectShowcasePrizes(Prize[] prizes) {
        Random random = new Random();
        Prize[] showcase = new Prize[5];
        boolean[] selected = new boolean[prizes.length];

        for (int i = 0; i < 5; i++) {
            int index;
            do {
                index = random.nextInt(prizes.length);
            } while (selected[index]);
            showcase[i] = prizes[index];
            selected[index] = true;
        }
        return showcase;
    }

    // Display the prizes in the showcase
    public static void displayShowcase(Prize[] showcase) {
        System.out.println("\nYour prizes are:");
        for (Prize prize : showcase) {
            System.out.println(prize.name);
        }
    }

    // Calculate the total price of the showcase
    public static double calculateShowcaseTotal(Prize[] showcase) {
        double total = 0;
        for (Prize prize : showcase) {
            total += prize.price;
        }
        return total;
    }

    // Get the user's guess
    public static double getUserGuess(Scanner scanner) {
        System.out.print("\nEnter your guess: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a valid number: ");
            scanner.next(); // Clear the invalid input
        }
        return scanner.nextDouble();
    }

    // Check if the guess is within the winning range
    public static boolean isWinningGuess(double guess, double showcaseTotal) {
        return guess >= showcaseTotal - 1300 && guess <= showcaseTotal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the showcase show down!");

        // Use the fixed filename
        String filename = "./prizeList.txt";
        Prize[] prizes = readPrizesFromFile(filename);

        if (prizes.length < 5) {
            System.out.println("Not enough prizes in the file to play the game.");
            return;
        }

        boolean playAgain;
        do {
            // Select and display showcase prizes
            Prize[] showcase = selectShowcasePrizes(prizes);
            displayShowcase(showcase);

            System.out.println("\nYou must guess the total cost of the prizes without going over and within $1,300 of its actual price");

            // Calculate total and get user guess
            double showcaseTotal = calculateShowcaseTotal(showcase);
            double userGuess = getUserGuess(scanner);

            // Display the actual total price
            System.out.printf("The actual cost was %.1f\n", showcaseTotal);

            // Check if the user won
            if (isWinningGuess(userGuess, showcaseTotal)) {
                System.out.println("You win!!!");
            } else if (userGuess > showcaseTotal) {
                System.out.println("Your guess was over. You lose.");
            } else {
                System.out.println("Your guess was close, but not close enough. You lose.");
            }

            // Ask if the user wants to play again
            System.out.print("\nWould you like to quit? Enter \"yes\" to quit: ");
            playAgain = !scanner.next().equalsIgnoreCase("yes");
            scanner.nextLine(); // Consume the remaining newline

        } while (playAgain);

        System.out.println("Goodbye!");
        scanner.close();
    }
}
