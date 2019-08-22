import java.io.IOException;

public class TaskFactory {

    public static Task createTask(String description) {
        return TaskTodo.of(description);
    }

    public static Task createTaskWithDate(String description, String dateTime)
            throws IllegalInstructionException {
        if (dateTime.contains("/by")) {
            return TaskDeadline.of(description, dateTime);
        } else if (dateTime.contains("/at")) {
            return TaskEvent.of(description, dateTime);
        } else {
            throw new IllegalInstructionException("Invalid syntax. Please consult the user guide.");
        }
    }

    /**
     * 
     * @param line
     * @return
     * @throws DukeException
     */
    public static Task createTaskFromFormattedString(String formattedString)
            throws DukeException {
    }
}
