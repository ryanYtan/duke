import duke.io.Ui;
import duke.io.Storage;
import duke.task.TaskList;
import duke.exception.DukeException;
import duke.exception.IllegalInstructionException;
import duke.io.Parser;
import duke.command.Command;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a new Duke object using the given file path.
     *
     * @param filePath filePath to saved task list
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadingSuccess();
        } catch (DukeException e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

     /**
      * Main logic of the program.
      */
     private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IllegalInstructionException e) {
                ui.print(
                    new String[]{},
                    new String[]{},
                    e.getMessage()
                );
            }
        }
    }

    /**
     * Main method.
     *
     * @param args arguments called with the program
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
