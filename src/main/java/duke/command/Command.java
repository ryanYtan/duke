package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.io.Ui;
import duke.io.Storage;

public abstract class Command {
    protected String command;
    protected static final String EXIT = "bye";

    public Command(String command) {
        this.command = command;
    }

    public boolean isExit() {
        return this.command.equals(EXIT);
    }

    public abstract void execute(TaskList t, Ui ui, Storage storage)
            throws DukeException;

    @Override
    public String toString() {
        return this.command;
    }
}
