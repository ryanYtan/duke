package duke.task;

import duke.exception.IllegalInstructionException;
import duke.exception.IllegalDateException;
import duke.exception.DukeException;
import duke.io.DateTime;

public class TaskFactory {
    /**
     * Returns a new Task from the given user input command.
     *
     * @param command user input
     * @return a new Task from the given user input command
     * @throws IllegalInstructionException if user input is not in expected format
     */
    public static Task createTask(String command)
            throws IllegalInstructionException {
        try {
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
                    System.out.println(e);
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
                    System.out.println(e);
                    System.out.println("Using token as string...");
                    at = event[1].trim();
                }
                return TaskEvent.of(event[0].substring("event".length()).trim(), at);

            default:
                throw new IllegalInstructionException("Invalid syntax. Please consult user manual.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalInstructionException("Invalid syntax! Did you miss a keyword?");
        }
    }

    /**
     * Returns a task from its formatted String form.
     * 
     * @param formattedString task in its string form
     * @return a new task representing the formattedString
     * @throws DukeException if formattedString does not match an expected format
     */
    static Task createTaskFromFormattedString(String formattedString)
            throws DukeException {
        try {
            return TaskTodo.ofFormattedForm(formattedString);
        } catch (DukeException e1) {
            try {
                return TaskDeadline.ofFormattedForm(formattedString); 
            } catch (DukeException e2) {
                try {
                    return TaskEvent.ofFormattedForm(formattedString); 
                } catch (DukeException e3) {
                    throw new DukeException("String is not of a valid form.");
                }
            }
        }
    }
}
