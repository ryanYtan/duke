public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns this object as a string to save into a File.
     * @return formatted string
     */
    public String formatAsData() {
        return String.format("T | %s | %s", getStatusIcon(), description);
    }

    /**
     * Returns the string form of this object.
     */
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
   }
}
