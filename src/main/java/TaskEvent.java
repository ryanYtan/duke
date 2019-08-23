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
        this.type = "E";
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
     * Factory method. Use this to construct this object.
     * Returns a TaskEvent object from its string form.
     * 
     * @param this object's string form
     * @return a new TaskEvent object
     */
    public static TaskEvent ofFormattedForm(String formattedForm)
            throws DukeException {
        if (!formattedForm.startsWith("E")) {
            throw new DukeException("Given string is not in the correct format");
        } else {
            String description = formattedForm.split("\\s+")[1];
            String at = formattedForm.substring(
                    formattedForm.indexOf("at:") + 4, formattedForm.length() - 1);
            return TaskEvent.of(description, at);
        }
    }

    /**
     * Returns the string representation of this Task, for writing to file.
     * 
     * @return the string representation of this Task suitable for writing to file
     */
    public String toFileFormattedString() {
        return String.format("%s | %s | %s | %s)",
                type, getStatusIcon().equals("✓") ? "Y" : "N" , description, at);
    } 

    /**
     * Returns the string representation of this Task.
     * 
     * @return the string representation of this Task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)",
                type, getStatusIcon(), description, at);
    }
}
