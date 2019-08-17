public class EventTask extends Task {
    protected String at;

    public EventTask(String description) {
        super(description);
        this.at = "";
    }

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string form of this object.
     */
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description
            + " (at: " + at + ")";
    }
}
