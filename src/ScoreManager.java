import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class ScoreManager {
    private ArrayList<Score> highScores;
    private Scanner scanObj;

    public ScoreManager (Scanner scObj) {
        scanObj = scObj;
        highScores = new ArrayList<Score>();
        loadScores("highscores.dat");
    }
    public void loadScores(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            try (ObjectInputStream input = new ObjectInputStream(fileIn)) {
                while(true) {
                    try {
                        Score tempScore = (Score) input.readObject(); // Read next object
                        if (tempScore != null) highScores.add(tempScore); // If score is not null, add to list
                    } catch(EOFException e) {
                        break; // reached the end of the file
                    } catch(ClassNotFoundException e) {
                        System.err.println("Object read in is not a Score");
                    }
                }
            } catch (IOException e) {
                System.err.println("Error occurred while loading scores.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("No previous high scores found.");
        }
    }
    public void processScore(SecretNumber game) {
        if (isHighScore(game)) {
            String playerName = "";
            if (scanObj.hasNextLine()) {
                scanObj.nextLine();  // Consume any leftover newline characters
            }

            System.out.print("New high score! Enter your name: "); // Prompt for player's name
            while (true) {
                if (scanObj.hasNextLine()) {
                    playerName = scanObj.nextLine().trim();
                    if (!playerName.isEmpty()) {
                        break; // Valid name received
                    } else {
                    System.out.println("Name can't be empty. Please enter your name: ");
                    }
                } else {
                    System.out.println("Please enter your name.");
                    break;
                }
            }
            game.setScore(playerName);
            if (highScores.size() < 5) {
                highScores.add(game.getScore());
            } else {
                highScores.remove(4);
                highScores.add(game.getScore());
            }
            Collections.sort(highScores);
            saveScores("highscores.dat");
        }
    }
    public boolean isHighScore(SecretNumber game) {
        int currentScore = game.getCalculatedScore();
        if (highScores.size() < 5) return true; // Fewer than 5 highs scores saved
        for (Score score: highScores) {
            if (currentScore > score.getValue()) {
                return true;
            }
        }
        return false;
    }
    public void saveScores(String filename) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Score score: highScores) {
                output.writeObject(score);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving the score.");
            e.printStackTrace();
        }
    }
    public void printScores() {
        System.out.println("High Scores: ");
        for (Score score: highScores) {
            System.out.println(score);
        }
        System.out.println("\n");
    }
}
