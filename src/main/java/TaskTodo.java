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
        String regex = "\\[[T]\\]\\[[\\p{L}]\\] ([^\\s(]*)($)";
        if (!regex.matches(formattedForm)) {
            throw new DukeException("Given string is not in the correct format");
        } else {
            String description = formattedForm.split("\\s+")[1];
            return new TaskTodo(description);
        }
    }
}
