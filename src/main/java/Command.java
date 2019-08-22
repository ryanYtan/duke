public abstract class Command {
    protected String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskList t, Ui ui, Storage storage);

    @Override
    public String toString() {
        return this.command;
    }
}
