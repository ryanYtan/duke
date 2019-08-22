import java.util.ArrayDeque;
import java.util.Scanner;

public class Ui {
    /**
     * Separator line between user input and program output.
     */
    private static final String BORDER =
            String.format("%40s", "\u2015").replace(' ', '\u2015');
    
    /**
     * List to track user input history.
     */
    private ArrayDeque<String> buffer;

    /**
     * Creates a new Ui object with an empty buffer.
     */
    public Ui() {
        this.buffer = new ArrayDeque<>();
    }

    /**
     * Reads and returns a new string from stdin.
     * 
     * @return a new input string from stdin
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String ret = sc.nextLine();
        sc.close();
        return ret;
    }

    /**
     * Prints the current buffer to System.out.
     */
    public void showLine() {
        print(BORDER, buffer.peek(), BORDER);
    }

    /**
     * Prints welcome message to System.out.
     */
    public void showWelcome() {
        String logoOne =   " ____        _        ";
        String logoTwo =   "|  _ \\ _   _| | _____ ";
        String logoThree = "| | | | | | | |/ / _ \\";
        String logoFour =  "| |_| | |_| |   <  __/";
        String logoFive =  "|____/\\__,_|_|\\_\\___|\n";

        String greeting = "Welcome to";
        String question = "What can I do for you today?";
        print(greeting, logoOne, logoTwo, logoThree, logoFour, logoFive, question);
    }

    /**
     * Prints error message if loading from storage fails.
     */
    public void showLoadingError() {
        print("No Task list found! Creating new Task list...");
    }

    /**
     * Prints varargs number of Strings with the format:
     * "\t string1"
     * "\t string2"
     * etc.
     * 
     * @param args strings to be printed
     */
    private void print(String... args) {
        for (String s : args) {
            System.out.println("\t" + s);
        }
    }
}
