package duke.task;

import java.util.Map;
import java.util.function.Supplier;

import duke.io.DateTime;
import duke.io.Parser;

import duke.exception.IllegalInstructionException;
import duke.exception.DukeException;
import duke.exception.IllegalDateException;

/**
 * The TaskFactory class should be used to create all implementing classes of the abstract base
 * class Task. The class provides the logic to handle the type of task to be returned to the
 * caller.
 */
public class TaskFactory {
    public static Task createTask(String command) throws IllegalInstructionException {
        Map<String, Task> taskMapper = Map.of(
             Parser.COMMAND_TODO, TaskTodo.of(command)
        );
        Task ret = taskMapper.getOrDefault(type, null);
    }

    /**
     * Returns a new Task corresponding to its fileFormattedString.
     *
     * @param fileFormattedString of the task
     * @return a new Task
     * @throws DukeException if the string is not in the correct format
     */
    static Task createTaskFromFileFormattedString(String fileFormattedString)
            throws DukeException {
        Map<String, Task> taskMapper = Map.of(
            "T", TaskTodo.ofFileFormattedForm(fileFormattedString),
            "D", TaskDeadline.ofFileFormattedForm(fileFormattedString),
            "E", TaskEvent.ofFileFormattedForm(fileFormattedString)
        );
        String type = fileFormattedString.split("\\s+\\|\\s+")[0];
        Task ret = taskMapper.getOrDefault(type, null);
        if (ret == null) {
            throw new DukeException("String is in invalid form.");
        }
        return ret;
    }
}
