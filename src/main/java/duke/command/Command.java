package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.io.Ui;
import duke.io.Storage;

public abstract class Command {
    protected String command;
    public static final String EXIT = "bye";

    public Command(String command) {
        this.command = command;
    }

    public boolean isExit() {
        return this.command.equals(EXIT);
    }

    /**
     * Executes the command defined by this object.
     *
     * @param t the TaskList object
     * @param ui the Ui object
     * @param storage the Storage object
     * @throws DukeException if the command is unable to execute correctly
     */
    public abstract String execute(TaskList t, Ui ui, Storage storage)
            throws DukeException;

    @Override
    public String toString() {
        return this.command;
    }
}
