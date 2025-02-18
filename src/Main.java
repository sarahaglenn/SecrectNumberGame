
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\n\nWelcome to Guess the Secret Number!");
        // Initialize scanner obj for getting input
        Scanner scanObj = new Scanner(System.in);
        // Ask for game type
        int gameType = getGameType(scanObj);
        // initialize score manager
        ScoreManager manager = new ScoreManager(scanObj);
        // start the correct version of the game
        switch (gameType) {
            case 1:
                SecretNumber defaultGame = new SecretNumber(scanObj);
                defaultGame.playGame();
                manager.processScore(defaultGame);
                break;
            case 2:
                int[] gameVars = getCustomGameVariables(scanObj);
                SecretNumber customGame = new SecretNumber(gameVars[0], gameVars[1], gameVars[2], scanObj);
                customGame.playGame();
                manager.processScore(customGame);
        }
        scanObj.close();
        System.out.println();
        manager.printScores();
    }
    // Get values for custom game settings
    public static int[] getCustomGameVariables(Scanner scanVarsObj) {
        int lowBound = getValue(scanVarsObj, "lower bound");
        int highBound = getValue(scanVarsObj, "upper bound");
        int numGuessesAllowed = getValue(scanVarsObj, "number of guesses");
        int[] gameVars = {lowBound, highBound, numGuessesAllowed};
        return gameVars;
    }
    // Get an individual value for one of the game settings
    public static int getValue(Scanner scanObj, String variableName) {
        while(true) {
            System.out.print("Enter the " + variableName + ": ");
            if (scanObj.hasNextInt()) {
                return scanObj.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanObj.next();
            }
        }
    }
    // Ask which game to play and capture the input
    public static int getGameType(Scanner scanObj) {
        System.out.println("Would you like to play:\n1. Default Game\n2. Custom Game");
        while (true) {
            if (scanObj.hasNextInt()) {
                    int choice = scanObj.nextInt();
                    if (choice == 1 || choice == 2) {
                        return choice;
                    }
                } else {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    scanObj.next();
            }
        }
    }
}