package codsoft1;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    static int score = 0;
    static boolean timeUp = false;

    public static void main(String[] args) {
        // Array to store quiz questions
        Question[] questions = {
                new Question("What is the capital of France?", new String[]{"Berlin", "Paris", "Madrid", "Rome"}, 1),
                new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1),
                new Question("What is the largest planet in the Solar System?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 2),
                new Question("Who wrote 'Romeo and Juliet'?", new String[]{"Shakespeare", "Charles Dickens", "J.K. Rowling", "Leo Tolstoy"}, 0),
                new Question("What is the speed of light?", new String[]{"300,000 km/s", "150,000 km/s", "299,792 km/s", "200,000 km/s"}, 2)
        };

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz! Answer each question within 10 seconds.\n");

        // Loop through all the questions
        for (int i = 0; i < questions.length; i++) {
            Question q = questions[i];
            timeUp = false; // Reset the timer flag for each question

            System.out.println("Question " + (i + 1) + ": " + q.questionText);
            for (int j = 0; j < q.options.length; j++) {
                System.out.println((j + 1) + ". " + q.options[j]);
            }

            // Start the timer
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeUp = true; // Timer expired
                    System.out.println("\nTime's up! Moving to the next question.");
                    timer.cancel();
                }
            }, 10000); // 10 seconds for each question

            // Capture user input within the time limit
            int userAnswer = -1; // Default invalid answer
            while (!timeUp) {
                if (scanner.hasNextInt()) {
                    userAnswer = scanner.nextInt() - 1; // Convert to 0-based index
                    break;
                }
            }
            timer.cancel(); // Stop the timer when input is received or time is up

            // Validate the answer only if submitted within the time
            if (!timeUp) {
                if (userAnswer == q.correctAnswer) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong! The correct answer was: " + q.options[q.correctAnswer]);
                }
            }
        }

        // Display the result screen
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score: " + score + "/" + questions.length);
        System.out.println("Thank you for playing!");
        scanner.close();
    }
}

// Class to represent each quiz question
class Question {
    String questionText;
    String[] options;
    int correctAnswer;

    // Constructor
    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}
