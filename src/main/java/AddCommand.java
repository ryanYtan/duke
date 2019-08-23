import java.io.IOException;

public class AddCommand extends Command {
    public AddCommand(String command) {
        super(command);
    }

    public void execute(TaskList t, Ui ui, Storage storage)
            throws DukeException {
        try {
            Task task = TaskFactory.createTask(command);
            t.add(task);
            storage.writeToFile(t.asStringList());
            ui.print(
                new String[]{"Got it. I've added this task:"},
                new String[]{String.format("Now you have %d tasks in the list", t.size())},
                task.toString()
            );
        } catch (IOException e) {
            throw new DukeException("Saving to file failed.");
        }
    }
}
