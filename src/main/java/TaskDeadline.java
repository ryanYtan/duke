public class TaskDeadline extends Task {
    private String by;

    /**
     * Private constructor.
     * Constructs an TaskDeadline with the specified description and time.
     * 
     * @param description of the task
     * @param by the time at which the event is due
     */
    private TaskDeadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    /**
     * Factory method. Use this to construct this object. Returns a
     * TaskDeadline object with the specified description and time.
     * 
     * @param description of the task
     * @param by the time at which the event is due
     * @return a new TaskDeadline object.
     */
    public static TaskDeadline of(String description, String by) {
        try {
            String dateTime = DateTime.of(by).toString();
            return new TaskDeadline(description, dateTime);
        } catch (IllegalDateException e) {
            // 'by' processed as simple String
            return new TaskDeadline(description, by);
        }
    }

    /**
     * Returns the string representation of this Task.
     * 
     * @return the string representation of this Task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                type, getStatusIcon(), description, by);
    }
}
