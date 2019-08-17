public class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description) {
        super(description);
        this.by = "";
    }

    public DeadlineTask(String description, String date) {
        super(description);
        this.by = date;
    }

    /**
     * Returns the string form of this object.
     */
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description
            + " (by: " + by + ")";
    }
}
