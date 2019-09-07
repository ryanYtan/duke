package duke.io;

import duke.exception.IllegalInstructionException;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import java.util.Map;

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
        Map<String, Command> commandMapper = Map.of(
                COMMAND_LIST, new ListCommand(input),
                COMMAND_TODO, new AddCommand(input),
                COMMAND_DEADLINE, new AddCommand(input),
                COMMAND_EVENT, new AddCommand(input),
                COMMAND_DONE, handleDoneCommand(input),
                COMMAND_DELETE, handleDeleteCommand(input),
                COMMAND_FIND, new FindCommand(input),
                COMMAND_EXIT, new ExitCommand(input.split("\\s+")[0])
        );
        String command = input.split("\\s+")[0];
        Command ret = commandMapper.getOrDefault(command, null);
        if (ret == null) {
            throw new IllegalInstructionException("Sorry! I don't know what that means.");
        }
        return ret;
    }

    /**
     * Returns a Done Command if the command is in the proper form.
     *
     * @param splitCommand the input command split by spaces
     * @return a new Done Command
     * @throws IllegalInstructionException if the instruction is not valid
     */
    private static Command handleDoneCommand(String input)
            throws IllegalInstructionException {
        String[] splitCommand = input.split("\\s+");
        validateNumberBasedCommands(splitCommand);
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
    private static Command handleDeleteCommand(String input)
            throws IllegalInstructionException {
        String[] splitCommand = input.split("\\s+");
        validateNumberBasedCommands(splitCommand);
        int index = Integer.parseInt(splitCommand[1]);
        return new DeleteCommand(splitCommand[0], index);
    }

    private static void validateNumberBasedCommands(String[] splitCommand)
            throws IllegalInstructionException {
        boolean isInCorrectFormat = splitCommand.length < 2;
        if (isInCorrectFormat || !isInteger(splitCommand[1])) {
            throw new IllegalInstructionException("Please enter a number after \"delete\"!");
        }
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
