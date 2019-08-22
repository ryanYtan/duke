public class TaskEvent extends Task {
    private String at;

    /**
     * Private constructor.
     * Constructs an TaskEvent with the specified description and time.
     * 
     * @param description of the task
     * @param at the time at which the event occurs
     */
    private TaskEvent(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Factory method. Use this to construct this object. Returns an TaskEvent object
     * with the specified description and time.
     * 
     * @param description of the task
     * @param at the time at which the event occurs
     * @return a new TaskEvent object.
     */
    public static TaskEvent of(String description, String at) {
        try {
            String dateTime = DateTime.of(at).toString();
            return new TaskEvent(description, dateTime);
        } catch (IllegalDateException e) {
            return new TaskEvent(description, at);
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
                type, getStatusIcon(), description, at);
    }
}
