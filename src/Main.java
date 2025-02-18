
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\n\nWelcome to Guess the Secret Number!");
        Scanner scanObj = new Scanner(System.in);
        int gameType = getGameType(scanObj);
        ScoreManager manager = new ScoreManager(scanObj);
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
    public static int[] getCustomGameVariables(Scanner scanVarsObj) {
        int lowBound = getValue(scanVarsObj, "lower bound");
        int highBound = getValue(scanVarsObj, "upper bound");
        int numGuessesAllowed = getValue(scanVarsObj, "number of guesses");
        int[] gameVars = {lowBound, highBound, numGuessesAllowed};
        return gameVars;
    }
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

/*
 * source - w3 schools
 * Use final keyword to make a variable read-only (like private or const)
 * use d at end end when defining a double variable
 * non-primitive data types start with capital letter
 * only non-primitive types can be null
 * if the default statement of switch is written last, it doesn't need a break
 * continue keyword to break one iteration of a loop when a specified condition is met.
 * multidimensional array int[][] myNumbers = {{}, {}}
 * Then access with [whichArray][whichElement]
 * multiple methods can have the same name as long as the type or number of parameters is different (method overloading)
 * a class is a template for objects, an object is an instance of a class
 * static methods can be accessed without creating an object of the class (unlike public)
 * constructors cannot have a return type
 * if you don't specify (like public) the class modifier is default and means the class is only accessible by classes in the same package
 * classes can also be final (not inheritable) or abstract (not used to create objects)
 * methods and attributes can be public, private, or protected (same package and subclasses)
 * can also be final (cannot be overridden/modified), static (belong to the class not an object), abstract (only be used in abstract class and only on methods - body provided by subclass)
 *             transient (skipped when serializing the object), synchronized (only accessed by one thread at a time), or volatile (the value is not cached thread-locally, always read from main memory)
 * To get user input, import the scanner class:
 *      import java.util.Scanner;
 * class MyClass {
 *   public static void main(String[] args) {
 *   Scanner myObj = new Scanner(System.in);
 *   System.out.println("Enter username");
 *
 *   String userName = myObj.nextLine();
 *   System.out.println("Username is: " + userName);
 * }
 *}
 * use extends to inherit. Ex: class Car extends Vehicle
 * and use protected on variables you want subclasses to be able to access
 * don't need any specific keyword to override functions in subclasses
 * abstract classes and methods -must be inherited to use
 * ArrayList is a resizable array - import from java.util
 * ArrayList<String> cars = new ArrayList<String>();
 *      cars.add(0, "Mazda");
 *      cars.get(0);
 *      cars.set(0, "Opel");
 *      cars.remove(0);
 *      cars.clear();
 * Must use object wrapper class Ex: ArrayLst<Integer> myNumbers = new ArrayList<Integer>();
 * import .Collections for sorting
 * Use ArrayList for storing and accessing data, LinkedList to manipulate data (can more efficiently change/get first and last elements)
 * Collections.sort(cars, Collections.reverseOrder());
 * HashMap<String, String> capitalCities = new HashMap<String, String>();
 *   capitalCities.put("England", "London");
 *   capitalCities.get("England");
 *   capitalCities.keySet()
 * Iterator objects for any collection
 *      while (it.hasNext()) {
 *          System.out.println(it.next());
 *      }
 * 
 */