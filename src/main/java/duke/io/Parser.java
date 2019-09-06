package duke.io;

import duke.exception.IllegalInstructionException;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

/**
 * The Parser class handles the parsing of user input directly in the duke.ui.Duke program.
 */
public class Parser {
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_EXIT = "bye";

    /**
     * Handles parsing of user input within duke.ui.Duke.
     *
     * @param input by the user
     * @return a new Command represented by the input, null otherwise
     * @throws IllegalInstructionException if given input is not in the expected format
     */
    public static Command parse(String input) throws IllegalInstructionException {
        String[] strings = input.split("\\s+");
        String command = strings[0];

        switch (command) {
        case COMMAND_LIST:
            return new ListCommand(input);

        case COMMAND_TODO:
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            return new AddCommand(input);

        case COMMAND_DONE:
            return handleDoneCommand(strings);

        case COMMAND_DELETE:
            return handleDeleteCommand(strings);

        case COMMAND_FIND:
            return new FindCommand(input);

        case COMMAND_EXIT:
            return new ExitCommand(command);

        default:
            throw new IllegalInstructionException("Sorry! I don't know what that means.");
        }
    }

    /**
     * Returns a Done Command if the command is in the proper form.
     *
     * @param splitCommand the input command split by spaces
     * @return a new Done Command
     * @throws IllegalInstructionException if the instruction is not valid
     */
    private static Command handleDoneCommand(String[] splitCommand)
            throws IllegalInstructionException {
        if (splitCommand.length < 2 || !isInteger(splitCommand[1])) {
            throw new IllegalInstructionException("Please enter a number after \"done\"!");
        }
        int index = Integer.parseInt(splitCommand[1]);
        return new DoneCommand(splitCommand[0], index);
    }

    /**
     * Returns a Delete Command if the command is in the proper form.
     *
     * @param splitCommand the input command split by spaces
     * @return a new Delete Command
     * @throws IllegalInstructionException if the instruction is not valid
     */
    private static Command handleDeleteCommand(String[] splitCommand)
            throws IllegalInstructionException {
        if (splitCommand.length < 2 || !isInteger(splitCommand[1])) {
            throw new IllegalInstructionException("Please enter a number after \"delete\"!");
        }
        int index = Integer.parseInt(splitCommand[1]);
        return new DeleteCommand(splitCommand[0], index);
    }

    private static boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
