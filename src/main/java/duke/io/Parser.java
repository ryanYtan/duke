package duke.io;

import duke.exception.IllegalInstructionException;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

public class Parser {
    /**
     * Handles parsing of user input.
     *
     * @param input by the user
     * @return a new Task represented by the input, null otherwise
     * @throws IllegalInstructionException if given input is not in the expected format
     */
    public static Command parse(String input)
            throws IllegalInstructionException {
        String[] strings = input.split("\\s+");
        String command = strings[0];

        switch (command) {
        case "list":
            return new ListCommand(input);

        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(input);

        case "done":
            try {
                int index = Integer.parseInt(strings[1]);
                return new DoneCommand(command, index);
            } catch (NumberFormatException e) {
                throw new IllegalInstructionException("Please enter a number after \"done\"!");
            }
        
        case "delete":
            try {
                int index = Integer.parseInt(strings[1]);
                return new DeleteCommand(command, index);
            } catch (NumberFormatException e) {
                throw new IllegalInstructionException("Please enter a number after \"delete\"!");
            }

        case "bye":
            return new ExitCommand(command);

        default:
            throw new IllegalInstructionException("Sorry! I don't know what that means.");
        }

    }
}
