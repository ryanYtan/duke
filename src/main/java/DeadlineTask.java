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
     * Returns this object as a string to save into a File.
     * @return formatted string
     */
    public String formatAsData() {
        return String.format("D | %s | %s", getStatusIcon(), description);
    }

    /**
     * Returns the string form of this object.
     */
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, by);
    }
}
