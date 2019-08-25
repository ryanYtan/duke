package duke.task;

import duke.io.DateTime;
import duke.exception.DukeException;
import duke.exception.IllegalDateException;

public class TaskEvent extends Task {
    private String at;

    /**
     * Private constructor.
     * Constructs an TaskEvent with the specified description and time.
     * 
     * @param description of the task
     * @param at the time at which the event occurs
     */
    private TaskEvent(String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    private TaskEvent(String description, String at, String done) {
        super(description);
        this.at = at;
        this.type = "E";
        this.isDone = done.equals("Y");
    }

    /**
     * Factory method. Use this to construct this object. Returns an TaskEvent object
     * with the specified description and time.
     * 
     * @param description of the task
     * @param at the time at which the event occurs
     * @return a new TaskEvent object.
     */
    public static TaskEvent of(String description, String at) {
        try {
            String dateTime = DateTime.of(at).toString();
            return new TaskEvent(description, dateTime);
        } catch (IllegalDateException e) {
            return new TaskEvent(description, at);
        }
    }

    /**
     * Factory method. Use this to construct this object.
     * Returns a TaskEvent object from its string form.
     * 
     * @param this object's string form
     * @return a new TaskEvent object
     */
    public static TaskEvent ofFormattedForm(String formattedForm)
            throws DukeException {
        if (!formattedForm.startsWith("E")) {
            throw new DukeException("Given string is not in the correct format");
        } else {
            // FORMAT STRING T | YN | ASD | AT
            String[] el = formattedForm.split("\\s+\\|\\s+");
            return new TaskEvent(el[2].trim(), el[3].trim(), el[1].trim());
        }
    }

    /**
     * Returns the string representation of this Task, for writing to file.
     * 
     * @return the string representation of this Task suitable for writing to file
     */
    public String toFileFormattedString() {
        return String.format("%s | %s | %s | %s",
                type, getStatusIcon().equals("✓") ? "Y" : "N" , description, at);
    } 

    /**
     * Returns the string representation of this Task.
     * 
     * @return the string representation of this Task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)",
                type, getStatusIcon(), description, at);
    }
}
