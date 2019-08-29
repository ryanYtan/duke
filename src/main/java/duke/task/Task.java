package duke.task;

public abstract class Task {
    /** Description of the task. */
    String description;

    /** True if the task is done. */
    boolean isDone;

    /** String representing the type of task. */
    String type;

    /** String representations of isDone */
    protected static final String IS_DONE = "✓";
    protected static final String IS_NOT_DONE ="✘";
    protected static final String IS_DONE_FILE = "1";
    protected static final String IS_NOT_DONE_FILE = "0";


    /**
     * Constructs a task with the given description.
     * 
     * @param description of the task
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "DEFAULT";
    }

    /**
     * Constructs a task with the given description and truth condition of isDone.
     *
     * @param description of task
     * @param isDone truth condition of the done status of the task
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.type = "";
    }

    /**
     * Returns "✓" (\u2713) or ✘ (\u2718) depending on the truth condition of isDone.
     * 
     * @return "✓" (\u2713) or ✘ (\u2718) depending on isDone
     */
    String getStatusIcon() {
        return isDone ? IS_DONE : IS_NOT_DONE;
    }

    /**
     * Marks this task as done.
     */
    void markAsDone() {
        this.isDone = true;
    }
    
    /**
     * Returns the string representation of this Task, for writing to file.
     * 
     * @return the string representation of this Task, for writing to file.
     */
    public abstract String toFileFormattedString();
}
