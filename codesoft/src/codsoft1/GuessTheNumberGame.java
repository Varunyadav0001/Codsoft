package codsoft1;

import java.util.Scanner;
import java.util.Random;

public class GuessTheNumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalScore = 0;
        boolean playAgain;

        System.out.println("Welcome to the Guess the Number Game!");

        do {
            int numberToGuess = random.nextInt(100) + 1;
            int attemptsLeft = 10;
            boolean numberGuessed = false;

            System.out.println("I have picked a number between 1 and 100. Can you guess it?");
            System.out.println("You have 10 attempts.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the number!");
                    totalScore += attemptsLeft + 1;
                    numberGuessed = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!numberGuessed) {
                System.out.println("Sorry, you've run out of attempts. The number was: " + numberToGuess);
            }

            System.out.println("Your current score: " + totalScore);
            System.out.print("Would you like to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thank you for playing! Your final score: " + totalScore);
        scanner.close();
    }
}
