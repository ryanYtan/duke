public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns the string form of this object.
     */
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + description;
    }
}
