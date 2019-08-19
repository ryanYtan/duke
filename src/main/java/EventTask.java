public class EventTask extends Task {
    private String at;

    /**
     * Private constructor.
     * Constructs an EventTask with the specified description and time.
     * 
     * @param description of the task
     * @param at the time at which the event occurs
     */
    private EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Factory method. Use this to construct this object. Returns an EventTask object
     * with the specified description and time.
     * 
     * @param description of the task
     * @param at the time at which the event occurs
     * @return a new EventTask object.
     */
    public static EventTask of(String description, String at) {
        try {
            String dateTime = DateTime.of(at).toString();
            return new EventTask(description, dateTime);
        } catch (IllegalDateException e) {
            return new EventTask(description, at);
        }
    }
}
