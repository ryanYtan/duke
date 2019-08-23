public class TaskTodo extends Task {
    private TaskTodo(String description) {
        super(description);
        this.type = "T";
    }

    private TaskTodo(String description, String done) {
        super(description);
        this.type = "T";
        this.isDone = done.equals("Y") ? true : false;
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
        if (!formattedForm.startsWith("T")) {
            throw new DukeException("Given string is not in the correct format");
        } else {
            // FORMAT STRING T | YN | ASD
            String[] el = formattedForm.split("\\s+\\|\\s+");
            return new TaskTodo(el[2].trim(), el[1].trim());
        }
    }

    /**
     * Returns the string representation of this Task, for writing to file.
     * 
     * @return the string representation of this Task suitable for writing to file
     */
    public String toFileFormattedString() {
        return String.format("%s | %s | %s",
                type, getStatusIcon().equals("✓") ? "Y" : "N" , description);
    } 
}
