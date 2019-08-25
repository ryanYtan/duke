package duke.task;

import duke.io.DateTime;
import duke.exception.DukeException;
import duke.exception.IllegalDateException;

public class TaskDeadline extends Task {
    private String by;

    /**
     * Private constructor.
     * Constructs an TaskDeadline with the specified description and time.
     * 
     * @param description of the task
     * @param by the time at which the event is due
     */
    private TaskDeadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    private TaskDeadline(String description, String by, String done) {
        super(description);
        this.by = by;
        this.type = "D";
        this.isDone = done.equals("Y");
    }


    /**
     * Factory method. Use this to construct this object. Returns a
     * TaskDeadline object with the specified description and time.
     * 
     * @param description of the task
     * @param by the time at which the event is due
     * @return a new TaskDeadline object.
     */
    public static TaskDeadline of(String description, String by) {
        try {
            String dateTime = DateTime.of(by).toString();
            return new TaskDeadline(description, dateTime);
        } catch (IllegalDateException e) {
            // 'by' processed as normal string
            return new TaskDeadline(description, by);
        }
    }

    /**
     * Returns a TaskDeadline object from its string form.
     *
     * Factory method. Use this to construct this object.
     *
     * @param this object's string form
     * @return a new TaskDeadline object
     */
    static TaskDeadline ofFormattedForm(String formattedForm)
            throws DukeException {
        if (!formattedForm.startsWith("D")) {
            throw new DukeException("Given string is not in the correct format");
        } else {
            // FORMAT STRING T | YN | ASD | BY
            String[] el = formattedForm.split("\\s+\\|\\s+");
            return new TaskDeadline(el[2].trim(), el[3].trim(), el[1].trim());
        }
    }

    /**
     * Returns the string representation of this Task, for writing to file.
     * 
     * @return the string representation of this Task suitable for writing to file
     */
    public String toFileFormattedString() {
        return String.format("%s | %s | %s | %s",
                type, getStatusIcon().equals("✓") ? "Y" : "N" , description, by);
    } 

    /**
     * Returns the string representation of this Task.
     * 
     * @return the string representation of this Task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                type, getStatusIcon(), description, by);
    }
}
