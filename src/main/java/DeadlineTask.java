public class DeadlineTask extends Task {
    protected String by;
    protected DateTime date;

    public DeadlineTask(String description) {
        super(description);
        this.by = "";
    }

    public DeadlineTask(String description, String date) {
        super(description);
        this.by = date;
    }

    public DeadlineTask(String description, DateTime date) {
        super(description);
        this.date = date;
        this.by = date.asDateTime();
    }

    /**
     * Returns the string form of this object.
     */
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description
            + " (by: " + by + ")";
    }
}
