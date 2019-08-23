import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
    }

    public void execute(TaskList t, Ui ui, Storage storage)
            throws DukeException {
        try {
            storage.writeToFile(t.asFileFormattedList());
            ui.print(
                new String[]{"These are your tasks"},
                new String[]{"Duke is exiting..."},
                t.asFormattedList().toArray(new String[t.size()])
            );
        } catch (IOException e) {
            throw new DukeException("Saving to file failed.");
        }
    }
}
