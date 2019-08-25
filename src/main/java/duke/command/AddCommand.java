package duke.command;

import java.io.IOException;
import duke.exception.DukeException;
import duke.exception.IllegalInstructionException;
import duke.io.Ui;
import duke.io.Storage;

import duke.task.TaskFactory;
import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {
    public AddCommand(String command) {
        super(command);
    }

    /**
     * Appends the given task to t, and prints the result.
     *
     * @param t the TaskList object
     * @param ui the Ui object
     * @param storage the Storage object
     */
    public void execute(TaskList t, Ui ui, Storage storage)
            throws DukeException {
        try {
            Task task = TaskFactory.createTask(command);
            t.add(task);
            storage.writeToFile(t.asFileFormattedList());
            ui.print(
                new String[]{"Got it. I've added this task:"},
                new String[]{String.format("Now you have %d tasks in the list", t.size())},
                task.toString()
            );
        } catch (IOException e) {
            throw new DukeException("Saving to file failed.");
        } catch (IllegalInstructionException e) {
            throw new DukeException("Invalid syntax! Did you forget a keyword in there?");
        }
    }
}
