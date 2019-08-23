import java.util.Arrays;

public class TaskFactory {

    public static Task createTask(String command)
            throws IllegalArgumentException {
        String[] el = command.split("\\s+");

        switch (el[0]) {
        case "todo":
            return TaskTodo.of(command.substring("todo".length()).trim());

        case "deadline":
            String[] deadline = command.split("/by");
            return TaskDeadline.of(deadline[0].substring("deadline".length()).trim(),
                    deadline[1].trim());

        case "event":
            String[] event = command.split("/at");
            return TaskEvent.of(event[0].substring("event".length()).trim(),
                    event[1].trim());

        default:
            throw new IllegalArgumentException("Invalid syntax. Please consult user manual.");
        }
    }

    /**
     * Returns a task from its formatted String form.
     * 
     * @param formattedString task in its string form
     * @return a new task representing the formattedString
     * @throws DukeException
     */
    public static Task createTaskFromFormattedString(String formattedString)
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
