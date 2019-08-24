package duke.command;

import java.io.IOException;
import duke.command.Command;
import duke.exception.DukeException;
import duke.task.*;
import duke.io.Ui;
import duke.io.Storage;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    public void execute(TaskList t, Ui ui, Storage storage)
            throws DukeException {
        try {
            Task removed = t.remove(index);
            ui.print(
                new String[]{"Note. I've removed this task:"},
                new String[]{String.format("You now have %d tasks in the list.", t.size())},
                removed.toString()
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
