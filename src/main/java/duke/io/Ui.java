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
     *
     * @return a String containing the last command input by the user
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
        asDukeMessage(buffer.peek());
    }

    /**
     * Prints welcome message to System.out.
     */
    public String showWelcome() {
        String logoOne =   " ____        _        ";
        String logoTwo =   "|  _ \\ _   _| | _____ ";
        String logoThree = "| | | | | | | |/ / _ \\";
        String logoFour =  "| |_| | |_| |   <  __/";
        String logoFive =  "|____/\\__,_|_|\\_\\___|\n";

        String greeting = "Welcome to";
        String question = "What can I do for you today?";
        return asDukeMessage(
            greeting, logoOne, logoTwo, logoThree, logoFour, logoFive, question
        );
    }

    /**
     * Prints error message if loading from storage fails.
     */
    public String showLoadingError() {
        return asDukeMessage(
            "No Task list found! Creating new Task list..."
        );
    }

    /**
     * Prints success message if loading from storage succeeds.
     */
    public String showLoadingSuccess() {
        return asDukeMessage(
            "Saved tasks successfully loaded."
        );
    }

    /**
     * Returns a String containing the elements of beforeText, args then afterText. Each individual
     * String is delimited with a newline character.
     *
     * @param args strings to be printed
     */
    public String asDukeMessage(String[] beforeText, String[] afterText, String... args) {
        StringBuilder ret = new StringBuilder();
        for (String s : beforeText) {
            ret.append(s).append("\n");
        }
        for (String s : args) {
            ret.append(s).append("\n");
        }
        for (String s : afterText) {
            ret.append(s).append("\n");
        }
        return ret.toString();
    }

    /**
     * Returns a String containing the elements of args. Each individual String is delimited with
     * a newline character.
     *
     * @param args strings to be printed
     */
    public String asDukeMessage(String... args) {
        return asDukeMessage(new String[]{}, new String[]{}, args);
    }
}
