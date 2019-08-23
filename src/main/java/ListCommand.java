import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    public void execute(TaskList t, Ui ui, Storage storage)
            throws DukeException {
        ui.print(
            new String[]{"Here are the tasks in your list:"},
            new String[0],
            t.asFormattedList().toArray(new String[t.size()])
        );
    }
}
