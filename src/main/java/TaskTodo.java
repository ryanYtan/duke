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
        String regex = "\\[[T]\\]\\[[\\u2713\\u2718]\\]\\s([^\\s(]*)($)";
        System.out.println("\"" + formattedForm + "\"");
        System.out.println("reg: " + regex.matches(formattedForm));
        if (!regex.matches(formattedForm)) {
            throw new DukeException("Given string is not in the correct format");
        } else {
            String description = formattedForm.substring("[a][a]".length()).trim();
            return new TaskTodo(description);
        }
    }
}
