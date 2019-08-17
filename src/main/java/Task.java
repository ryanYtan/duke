public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string containing a tick or cross, when this task is done
     * or not done respectively.
     */
    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "y" : "n"); // uses y/n as powershell has difficulty
                                       // displaying unicode
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string form of this object.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
