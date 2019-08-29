package duke.io;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    /**
     * Separator line between user input and program output.
     */
    private static final String BORDER =
            String.format("%40s", "―").replace(" ", "―");

    /**
     * List to track user input history. Acts as a Stack data structure.
     */
    private ArrayDeque<String> buffer;

    /**
     * Creates a new Ui object with an empty buffer.
     */
    public Ui() {
        sc = new Scanner(System.in);
        this.buffer = new ArrayDeque<>();
    }

    /**
     * Reads and returns a new string from stdin.
     * 
     * @return a new input string from stdin
     */
    public String readCommand() {
        String ret = sc.nextLine();
        buffer.push(ret);
        return ret;
    }
    
    /**
     * Get the last command input by the user.
     */
    public String getCommand() {
        return this.buffer.peek();
    }

    /**
     * Helper method to flush the buffer.
     * 
     * @return true if the buffer is flushed
     */
    private boolean flushBuffer() {
        this.buffer = new ArrayDeque<>();
        return true;
    }

    /**
     * Prints the current buffer to System.out.
     */
    public void showLine() {
        print(buffer.peek());
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
        print(
            new String[]{greeting, logoOne, logoTwo, logoThree, logoFour, logoFive, question},
            new String[0]
        );
    }

    /**
     * Prints error message if loading from storage fails.
     */
    public void showLoadingError() {
        print(
            new String[]{},
            new String[]{},
            "No Task list found! Creating new Task list..."
        );
    }

    /**
     * Prints success message if loading from storage succeeds.
     */
    public void showLoadingSuccess() {
        print(
            new String[]{},
            new String[]{},
            "Saved tasks successfully loaded."
        );
    }

    /**
     * Prints varargs number of Strings with the format:
     * --BORDER--
     * beforeText[]
     * "\t string1"
     * "\t string2"
     * etc.
     * afterText[]
     * --BORDER--
     * 
     * @param args strings to be printed
     */
    public void print(String[] beforeText, String[] afterText, String... args) {
        System.out.println("\t" + BORDER);
        for (String s : beforeText) {
            System.out.println("\t" + s);
        }
        for (String s : args) {
            System.out.println("\t" + s);
        }
        for (String s : afterText) {
            System.out.println("\t" + s);
        }
        System.out.println("\t" + BORDER);
    }

    /**
     * Prints varargs number of Strings with the format:
     * --BORDER--
     * "\t string1"
     * "\t string2"
     * etc.
     * --BORDER--
     * 
     * @param args strings to be printed
     */
    public void print(String... args) {
        System.out.println("\t" + BORDER);
        for (String s : args) {
            System.out.println("\t" + s);
        }
        System.out.println("\t" + BORDER);
    }


}
