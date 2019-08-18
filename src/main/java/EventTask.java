public class EventTask extends Task {
    protected String at;
    protected DateTime date;

    public EventTask(String description) {
        super(description);
        this.at = "";
    }

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
        this.date = null;
    }

    public EventTask(String description, DateTime date) {
        super(description);
        this.date = date;
        this.at = date.asDateTime();
    }

    /**
     * Returns the string form of this object.
     */
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description
            + " (at: " + at + ")";
    }
}
