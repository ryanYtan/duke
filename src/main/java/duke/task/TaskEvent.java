package duke.task;

public class TaskEvent extends Task {
    private String at;

    private TaskEvent(String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    private TaskEvent(String description, String at, boolean isDone) {
        this(description, at);
        this.type = "E";
        this.isDone = isDone;
    }

    /**
     * Returns a TaskEvent object with the given description and time.
     *
     * @param description of the task occurring
     * @param at this time
     * @return a new TaskEvent object.
     */
    public static TaskEvent of(String description, String at) {
        return new TaskEvent(description, at);
    }

    /**
     * Returns a TaskEvent object with the given description, time and truth condition of isDone.
     *
     * @param description of the task occurring
     * @param at this time
     * @param isDone truth condition of the done status of the task
     * @return a new TaskEvent object
     */
    public static TaskEvent of(String description, String at, boolean isDone) {
        return new TaskEvent(description, at, isDone);
    }

    /**
     * Returns a TaskEvent object from its file-formatted-form representation.
     * The expected format is "E | ✓✘ | description | at"
     *
     * @param fileFormattedForm of a TaskEvent object
     * @return a new TaskEvent object
     */
    public static TaskEvent fromFileFormattedForm(String fileFormattedForm) {
        String[] elements = fileFormattedForm.split("\\s+\\|\\s+");
        String desc = elements[2];
        String at = elements[3];
        boolean done = elements[1].equals(IS_DONE_FILE);
        return TaskEvent.of(desc, at, done);
    }

    /**
     * Returns the String representation of this Task, for writing to file.
     * 
     * @return the String representation of this Task suitable for writing to file
     */
    public String toFileFormattedString() {
        String status = getStatusIcon().equals(IS_DONE) ? IS_DONE_FILE : IS_NOT_DONE_FILE;
        return String.format("%s | %s | %s | %s", type, status, description, at);
    }

    /**
     * Returns the String representation of this Task.
     *
     * @return the String representation of this Task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", type, getStatusIcon(), description, at);
    }
}
