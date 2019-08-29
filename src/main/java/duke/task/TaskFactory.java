package duke.task;

import duke.io.DateTime;

import duke.exception.IllegalInstructionException;
import duke.exception.DukeException;
import duke.exception.IllegalDateException;

public class TaskFactory {
    public static Task createTask(String command)
            throws IllegalInstructionException {
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
            throw new IllegalInstructionException("");
        }
    }

    static Task createTaskFromFileFormattedString(String fileFormattedString)
            throws DukeException {
        String[] elements = fileFormattedString.split("\\s+\\|\\s+");
        String type = elements[0];
        switch (type) {
        case "T":
            return TaskTodo.fromFileFormattedForm(fileFormattedString);
        case "D":
            return TaskDeadline.fromFileFormattedForm(fileFormattedString);
        case "E":
            return TaskEvent.fromFileFormattedForm(fileFormattedString);
        default:
            throw new DukeException("Invalid string");
        }
    }
}
