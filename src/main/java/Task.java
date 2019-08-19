public class Task {
    /**
     * Description of the task.
     */
    protected String description;

    /**
     * Boolean variable to check if the task is done, or not done.
     */
    protected boolean isDone;

    /**
     * Type of task.
     */
    protected String type;


    /**
     * Constructs a task with the given description.
     * 
     * @param description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
    }

    /**
     * Helper method: returns ✓ or ✘ (tick or cross) depending on the
     * truth condition of isDone.
     * 
     * @return tick or cross depending on isDone
     */
    private String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of this Task.
     * 
     * @return the string representation of this Task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, getStatusIcon(), description);
    }
}
