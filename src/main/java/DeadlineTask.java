public class DeadlineTask extends Task {
    private String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    public DeadlineTask(String description, dateTime by) {
        super(description);
        this.by = by.toString();
    }
}
