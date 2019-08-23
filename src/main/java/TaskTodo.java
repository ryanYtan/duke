public class TaskTodo extends Task {
    private TaskTodo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Factory method. Use this to construct this object.
     * Returns a TaskTodo object with the specified description.
     * 
     * @param description of the task
     * @return a new TaskTodo object.
     */
    public static TaskTodo of(String description) {
        return new TaskTodo(description);
    }

    /**
     * Factory method. Use this to construct this object.
     * Returns a TaskTodo object from its string form.
     * 
     * @param this object's string form
     * @return a new TaskTodo object
     */
    public static TaskTodo ofFormattedForm(String formattedForm)
            throws DukeException {
                System.out.println(formattedForm);
        if (!formattedForm.startsWith("T")) {
            throw new DukeException("Given string is not in the correct format");
        } else {
            String description = formattedForm.substring("[a][a]".length()).trim();
            return new TaskTodo(description);
        }
    }

    /**
     * Returns the string representation of this Task, for writing to file.
     * 
     * @return the string representation of this Task suitable for writing to file
     */
    public String toFileFormattedString() {
        return String.format("%s | %s | %s)",
                type, getStatusIcon().equals("✓") ? "Y" : "N" , description);
    } 
}
