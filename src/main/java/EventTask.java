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
