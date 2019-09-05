package duke.command;

import java.io.IOException;
import duke.exception.DukeException;
import duke.io.Ui;
import duke.io.Storage;

import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    /**
     * Finds the task in t, deletes it and prints the result.
     *
     * @param t the TaskList object
     * @param ui the Ui object
     * @param storage the Storage object
     */
    public String execute(TaskList t, Ui ui, Storage storage)
            throws DukeException {
        try {
            Task removed = t.remove(index);
            storage.writeToFile(t.asFileFormattedList());
            return ui.asDukeMessage(
                new String[]{"Note. I've removed this task:"},
                new String[]{String.format("You now have %d tasks in the list.", t.size())},
                removed.toString()
            );
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                String.format("That task does not exist! You have %d tasks in the list.", t.size())
            );
        } catch (IOException e) {
            throw new DukeException("Saving to file failed.");
        }
    }
}
