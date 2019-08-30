import duke.io.Ui;
import duke.io.Storage;
import duke.io.Parser;

import duke.task.TaskList;

import duke.exception.DukeException;
import duke.exception.IllegalInstructionException;

import duke.command.Command;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
    }

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
                ui.print(e.getMessage());
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

    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
