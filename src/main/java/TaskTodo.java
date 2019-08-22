public class TaskTodo extends Task {
    private TaskTodo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Factory method. Use this to construct this object. Returns a TaskTodo
     * object with the specified description.
     * 
     * @param description of the task
     * @return a new TaskTodo object.
     */
    public static TaskTodo of(String description) {
        return new TaskTodo(description);
    }
}
