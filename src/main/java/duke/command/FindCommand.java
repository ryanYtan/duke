package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    public FindCommand(String command) {
        super(command);
    }

    public void execute(TaskList t, Ui ui, Storage storage)
            throws DukeException {
        try {
            String search = command.substring("find".length() + 1);
            ArrayList<String> tasks = t.find(search);
            if (tasks.isEmpty()) {
                throw new IndexOutOfBoundsException("No index found.");
            } else {
                ui.print(
                    new String[]{"Here are the matching tasks in your list:"},
                    new String[]{},
                    tasks.toArray(new String[0])
                );
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry! I could not find any tasks matching that search term");
        }
    }
}
