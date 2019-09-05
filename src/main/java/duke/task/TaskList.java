package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
    private ArrayList<Task> list;

    /**
     * The default max capacity, although this is not enforced.
     */
    private static final int MAX_ITEMS = 100;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        list = new ArrayList<>(MAX_ITEMS);
    }

    /**
     * Constructs a new TaskList with the given string list.
     *
     * @param inputList ArrayList of Strings
     * @throws DukeException if TaskList cannot be formed from the given list
     */
    public TaskList(ArrayList<String> inputList)
        throws DukeException {
        this.list = new ArrayList<>();
        for (String str : inputList) {
            list.add(TaskFactory.createTaskFromFileFormattedString(str));
        }
    }

    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in this list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Appends the specified task to the end of this list.
     * 
     * @param t task to be appended to this list
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Marks the task at the specified index in the list as done. The specified index
     * assumes a one-based list (i.e first element of the list is of index 1).
     * 
     * @param index the index of the element to be marked as done
     */
    public void done(int index) {
        Objects.checkIndex(index - 1, list.size());
        list.get(index - 1).markAsDone();
    }

    /**
     * Returns the task at the specified index in the list. The specified index
     * assumes a one-based list (i.e first element of the list is of index 1). The
     * task is returned as a String.
     * 
     * @param index index at which the element is to be obtained.
     * @return the String representation of the task at the specified index
     */
    public String get(int index) {
        Objects.checkIndex(index - 1, list.size());
        return list.get(index - 1).toString();
    }

    /**
     * Returns a String ArrayList containing all tasks contained in this TaskList containing
     * the specified String.
     *
     * @param match the string to match to the tasks
     * @return a String ArrayList containing matching tasks
     */
    public ArrayList<String> find(String match) {
        ArrayList<String> ret = new ArrayList<>();
        int i = 1;
        for (Task t : list) {
            if (t.toString().contains(match)) {
                ret.add(String.format("%d. %s", i, t));
            }
            i++;
        }
        return ret;
    }

    /**
     * Removes and returns the task at the specified index in the list.
     * 
     * @param index index at which the element is to be removed
     * @return the task that was removed
     */
    public Task remove(int index) {
        Objects.checkIndex(index - 1, list.size());
        return list.remove(index - 1);
    }

    /**
     * Returns an String ArrayList containing the formatted form of this Task list.
     * 
     * @return an String ArrayList containing the formatted form of this Task list.
     */
    public ArrayList<String> asFormattedList() {
        ArrayList<String> ret = new ArrayList<>();
        int i = 1;
        for (Task t : list) {
            ret.add(String.format("%d. %s", i, t));
            i++;
        }
        return ret;
    }

    /**
     * Returns an String ArrayList containing the string form of this Task list.
     * 
     * @return an String ArrayList containing the string form of this Task list.
     */
    public ArrayList<String> asStringList() {
        ArrayList<String> ret = new ArrayList<>();
        for (Task t : list) {
            ret.add(t.toString());
        }
        return ret;
    }

    /**
     * Returns an String ArrayList containing the string form of this Task list to
     * write to file.
     * 
     * @return an String ArrayList containing the string form of this Task list to write to file
     */
    public ArrayList<String> asFileFormattedList() {
        ArrayList<String> ret = new ArrayList<>();
        for (Task t : list) {
            ret.add(t.toFileFormattedString());
        }
        return ret;
    }

    
    /**
     * Returns the string representation of this Task list.
     * 
     * @return the string representation of this Task list
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        int i = 1;
        for (Task t : list) {
            ret.append(String.format("%d. %s\n", i, t));
            i++;
        }
        return ret.toString();
    }
}
