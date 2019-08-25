package duke.command;

import java.io.IOException;
import duke.command.Command;
import duke.exception.DukeException;
import duke.task.*;
import duke.io.Ui;
import duke.io.Storage;

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Quits the program.
     *
     * @param t the TaskList object
     * @param ui the Ui object
     * @param storage the Storage object
     */
    public void execute(TaskList t, Ui ui, Storage storage)
            throws DukeException {
        try {
            storage.writeToFile(t.asFileFormattedList());
            ui.print(
                new String[]{"Thank you for using Duke!", "These are your tasks:"},
                new String[]{"Duke is exiting..."},
                t.asFormattedList().toArray(new String[t.size()])
            );
        } catch (IOException e) {
            throw new DukeException("Saving to file failed.");
        }
    }
}
