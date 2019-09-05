package duke.io;

import duke.exception.IllegalInstructionException;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

public class Parser {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_EXIT = "bye";

    /**
     * Handles parsing of user input within Duke.
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
            try {
                int index = Integer.parseInt(strings[1]);
                return new DoneCommand(command, index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new IllegalInstructionException("Please enter a number after \"done\"!");
            }
        
        case COMMAND_DELETE:
            try {
                int index = Integer.parseInt(strings[1]);
                return new DeleteCommand(command, index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new IllegalInstructionException("Please enter a number after \"delete\"!");
            }

        case COMMAND_FIND:
            return new FindCommand(input);

        case COMMAND_EXIT:
            return new ExitCommand(command);

        default:
            throw new IllegalInstructionException("Sorry! I don't know what that means.");
        }
    }
}
