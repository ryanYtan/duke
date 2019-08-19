public class TaskFactory {

    public static Task createTask(String description) {
        return new TodoTask(description);
    }

    public static Task createTaskWithDate(String description, String dateTime)
            throws IllegalInstructionException {
        if (dateTime.contains("/by")) {
            return DeadlineTask.of(description, dateTime);
        } else if (dateTime.contains("/at")) {
            return EventTask.of(description, dateTime);
        } else {
            throw new IllegalInstructionException("Invalid syntax. Please consult the user guide.");
        }
    }
}
