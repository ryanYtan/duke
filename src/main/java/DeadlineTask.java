public class DeadlineTask extends Task {
    private String by;

    /**
     * Private constructor.
     * Constructs an DeadlineTask with the specified description and time.
     * 
     * @param description of the task
     * @param by the time at which the event is due
     */
    private DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Factory method. Use this to construct this object. Returns an DeadlineTask object
     * with the specified description and time.
     * 
     * @param description of the task
     * @param by the time at which the event is due
     * @return a new DeadlineTask object.
     */
    public static DeadlineTask of(String description, String by) {
        try {
            String dateTime = DateTime.of(by).toString();
            return new DeadlineTask(description, dateTime);
        } catch (IllegalDateException e) {
            return new DeadlineTask(description, by);
        }
    }
}
