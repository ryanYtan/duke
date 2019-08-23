import java.io.IOException;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    public void execute(TaskList t, Ui ui, Storage storage)
            throws DukeException {
        try {
            t.done(index);
            ui.print(
                new String[]{"Nice! I've marked this task as done:"},
                new String[]{},
                t.get(index)
            );
            storage.writeToFile(t.asFileFormattedList());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                String.format("That task does not exist! You have %d tasks in the list.", t.size())
            );
        } catch (IOException e) {
            throw new DukeException("Saving to file failed.");
        }
    }
}
