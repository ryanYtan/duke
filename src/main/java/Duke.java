import duke.io.Ui;
import duke.io.Storage;
import duke.io.Parser;

import duke.task.TaskList;

import duke.exception.DukeException;
import duke.exception.IllegalInstructionException;

import duke.command.Command;

import javafx.application.Application;
import javafx.scene.layout.Region;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /** Elements for Ui. */
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

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


    /**
     * Returns a string containing the user's input with some response.
     *
     * @param input user's input
     * @return a String containing the user's input and some program response
     */
    public String getResponse(String input) {
        return String.format("I heard \"%s\"", input);
    }
}
