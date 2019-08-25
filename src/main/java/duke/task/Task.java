package duke.task;

public class Task {
    /**
     * Description of the task.
     */
    String description;

    /**
     * Boolean variable to check if the task is done, or not done.
     */
    boolean isDone;

    /**
     * Type of task.
     */
    String type;


    /**
     * Constructs a task with the given description.
     * 
     * @param description of the task
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
    }

    /**
     * Helper method: returns ✓ or ✘ (tick or cross) depending on the
     * truth condition of isDone.
     * 
     * @return tick or cross depending on isDone
     */
    String getStatusIcon() {
        return isDone ? "✓" : "✘";
    }

    /**
     * Marks this task as done.
     */
    void markAsDone() {
        this.isDone = true;
    }
    
    /**
     * Returns the string representation of this Task, for writing to file.
     * 
     * @return the string representation of this Task suitable for writing to file
     */
    public String toFileFormattedString() {
        return String.format("%s | %s | %s)",
                type, getStatusIcon().equals("✓") ? "Y" : "N", description);
    } 

    /**
     * Returns the string representation of this Task.
     * 
     * @return the string representation of this Task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, getStatusIcon(), description);
    }
}
