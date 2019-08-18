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
     * Returns this object as a string to save into a File.
     * @return formatted string
     */
    public String formatAsData() {
        return String.format("E | %s | %s", getStatusIcon(), description);
    }

    /**
     * Returns the string form of this object.
     */
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description, at);
    }
}
