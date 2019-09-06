package duke.task;

import duke.io.DateTime;

import duke.exception.IllegalInstructionException;
import duke.exception.DukeException;
import duke.exception.IllegalDateException;

/**
 * The TaskFactory class should be used to create all implementing classes of the abstract base
 * class Task. The class provides the logic to handle the type of task to be returned to the
 * caller.
 */
public class TaskFactory {
    /**
     * Returns a new Task corresponding to the user's input.
     *
     * @param command user input
     * @return a new Task
     * @throws IllegalInstructionException if user input is not recognised
     */
    public static Task createTask(String command) throws IllegalInstructionException {
        String[] el = command.split("\\s+");

        switch (el[0]) {
        case "todo":
            return TaskTodo.of(command.substring("todo".length()).trim());

        case "deadline":
            String[] deadline = command.split("/by");
            String by;
            try {
                by = DateTime.of(deadline[1].trim()).toString();
            } catch (IllegalDateException e) {
                // by processed as normal string
                System.out.println(e.getMessage());
                System.out.println("Using token as string...");
                by = deadline[1].trim();
            }
            return TaskDeadline.of(deadline[0].substring("deadline".length()).trim(), by);

        case "event":
            String[] event = command.split("/at");
            String at;
            try {
                at = DateTime.of(event[1].trim()).toString();
            } catch (IllegalDateException e) {
                // at processed as normal string
                System.out.println(e.getMessage());
                System.out.println("Using token as string...");
                at = event[1].trim();
            }
            return TaskEvent.of(event[0].substring("event".length()).trim(), at);

        default:
            throw new IllegalInstructionException("Could not instantiate the given task.");
        }
    }

    /**
     * Returns a new Task corresponding to its fileFormattedString.
     *
     * @param fileFormattedString of the task
     * @return a new Task
     * @throws DukeException if the string is not in the correct format
     */
    static Task createTaskFromFileFormattedString(String fileFormattedString)
            throws DukeException {
        String[] elements = fileFormattedString.split("\\s+\\|\\s+");
        String type = elements[0];
        switch (type) {
        case "T":
            assert(elements.length == 3)
                    : String.format("Task \"%s\" is not of the correct format.", fileFormattedString);
            return TaskTodo.fromFileFormattedForm(fileFormattedString);
        case "D":
            assert(elements.length == 4)
                    : String.format("Task \"%s\" is not of the correct format.", fileFormattedString);
            return TaskDeadline.fromFileFormattedForm(fileFormattedString);
        case "E":
            assert(elements.length == 4)
                    : String.format("Task \"%s\" is not of the correct format.", fileFormattedString);
            return TaskEvent.fromFileFormattedForm(fileFormattedString);
        default:
            throw new DukeException("Invalid string");
        }
    }
}
