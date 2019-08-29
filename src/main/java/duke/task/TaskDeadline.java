package duke.task;

public class TaskDeadline extends Task {
    private String by;

    private TaskDeadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    private TaskDeadline(String description, String by, boolean isDone) {
        this(description, by);
        this.type = "D";
        this.isDone = isDone;
    }

    /**
     * Returns a TaskDeadline object with the given description and time.
     *
     * @param description of the task to be done
     * @param by this time
     * @return a new TaskTodo object.
     */
    public static TaskDeadline of(String description, String by) {
        return new TaskDeadline(description, by);
    }

    /**
     *
     * @param description of task
     * @param isDone truth condition of the done status of the task
     * @return a new TaskTodo object
     */
    public static TaskDeadline of(String description, String by, boolean isDone) {
        return new TaskDeadline(description, by, isDone);
    }

    /**
     * Returns a TaskDeadline object from its file-formatted-form representation.
     * The expected format is "D | ✓✘ | description | by"
     *
     * @param fileFormattedForm of a TaskDeadline object
     * @return a new TaskDeadline object
     */
    public static TaskDeadline fromFileFormattedForm(String fileFormattedForm) {
        String[] elements = fileFormattedForm.split("\\s+\\|\\s+");
        String desc = elements[2];
        String by = elements[3];
        boolean done = elements[1].equals(IS_DONE_FILE);
        return TaskDeadline.of(desc, by, done);
    }

    /**
     * Returns the String representation of this Task, for writing to file.
     * 
     * @return the String representation of this Task suitable for writing to file
     */
    public String toFileFormattedString() {
        String status = getStatusIcon().equals(IS_DONE) ? IS_DONE_FILE : IS_NOT_DONE_FILE;
        return String.format("%s | %s | %s | %s", type, status, description, by);
    }

    /**
     * Returns the String representation of this Task.
     *
     * @return the String representation of this Task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", type, getStatusIcon(), description, by);
    }
}
