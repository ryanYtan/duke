public class DeadlineTask extends Task {
    private String at;

    public DeadlineTask(String description, String at) {
        super(description);
        this.at = at;
    }

    public DeadlineTask(String description, dateTime at) {
        super(description);
        this.at = at.toString();
    }
}
