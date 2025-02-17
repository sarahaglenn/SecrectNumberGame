import java.util.Random; // use Random class to generate a random secret number
import java.util.Scanner; // use Scanner class to get user input


public class SecretNumber {
    private int lowRange;
    private int highRange;
    private int secretNumber;
    private int guessCount;
    private int guessesAllowed;
    private int currentGuess;
    private Scanner inputScanObj;
    private Score score;

    // Constructors
    public SecretNumber(Scanner scanObj) {
        lowRange = 0;
        highRange = 5; // **** Change back to 100 later!!! *******
        guessesAllowed = 10; // **** Change back to 5 later!!! *******
        guessCount = 0;
        secretNumber = generateRandomNumber();
        inputScanObj = scanObj;
    }
    public SecretNumber(int customLowRange, int customHighRange, int customGuessesAllowed, Scanner scanObj) {
        if (customLowRange >= customHighRange) {
            throw new IllegalArgumentException("Low bound must be less than high bound.");
        }
        lowRange = customLowRange;
        highRange = customHighRange;
        guessesAllowed = customGuessesAllowed;
        guessCount = 0;
        secretNumber = generateRandomNumber();
        inputScanObj = scanObj;
    }

    // Class Methods
    /*
     * This method generates a random number between the low and high bounds (inclusive)
     * Returns an integer
     */
    private int generateRandomNumber() {
        Random rand = new Random();
        int randomInt = rand.nextInt(lowRange, highRange + 1);
        return randomInt;
    }

    public void playGame() {
        // while they still have guesses left
        while (guessCount < guessesAllowed) {
            setCurrentGuess(); // Get a new guess from the player
            guessCount++; // Increase the guess count when guess is updated
            if (currentGuess == secretNumber) { // Check if they won
                youWin();
                return;
            }
            else {
                giveHints(); // give feedback if they didn't win yet
            }
        }
        youLose(); // Ran out of guesses
    }
    private void setCurrentGuess() {
        while (true) {
            System.out.print("Guess a number: ");
            if (inputScanObj.hasNextInt()) {
                currentGuess = inputScanObj.nextInt(); // set currentGuess with valid input
                break;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                inputScanObj.next(); // Consume invalid input
            }
        }
    }

    private void giveHints() {
        if (currentGuess < secretNumber) {
            System.out.println("Higher");
            return;
        }
        System.out.println("Lower");
    }

    private void youWin() {
        String congrats = "\nCongratulations!\nYou won in " + guessCount + " guesses!";
        System.out.println(congrats);
        System.out.println("Score:" + calculateScore() + "\n");
    }

    private void youLose() {
        String gameOver = "\nGame over.\nYou ran out of guesses.\nThe secret number was " + secretNumber + "\n";
        System.out.println(gameOver);
        System.out.println("Score: " + calculateScore());
    }
    public void setScore(String name) {
        int scoreValue = calculateScore();
        score = new Score(name, scoreValue);
    }
    public Score getScore() {
        return score;
    }
    public int getCalculatedScore() {
        return calculateScore();
    }

    private int calculateScore() {
        if (currentGuess != secretNumber) {
            return 0;
        }
        int range = highRange - lowRange;
        if (range ==0) return 0; // Prevent division by zero
        double bonus = ((double) (guessesAllowed - guessCount + 1) / guessesAllowed) * (range / 10);
        double baseScore = ((double) (range - guessCount) / range) * 1000 + bonus;
        return (int) (baseScore + bonus);
    }
}
