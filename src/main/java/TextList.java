import java.util.ArrayList;

public class TextList {
    private ArrayList<Task> list;
    private static final int MAX_ITEMS = 100;

    public TextList() {
        list = new ArrayList<Task>(MAX_ITEMS);
    }

    /**
     * Returns size of this TextLis object.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Adds the given text into this list of text.
     * @param text  text to be entered
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Marks the i-th element in the list as "done". The value of i treats
     * the list as a one-indexed list.
     * @param i     The element to be marked.
     */
    public void done(int i) {
        list.get(i - 1).markAsDone();
    }

    /**
     * Gets the i-th element in the list. The value of i treats the list as a
     * one-indexed list.
     * @param i     The element to be returned.
     */
    public String get(int i) throws IndexOutOfBoundsException {
        if (i <= 0 || i > list.size()) {
            throw new IndexOutOfBoundsException("Given value is out-of-bounds of the list!"
                    + " You have " + list.size() + " items in the list.");
        }
        return list.get(i - 1).toString();
    }

    public Task delete(int i) throws IndexOutOfBoundsException {
        if (i <= 0 || i > list.size()) {
            throw new IndexOutOfBoundsException("Given value is out-of-bounds of the list!"
                    + " You have " + list.size() + " items in the list.");
        }
        return list.remove(i - 1);
    }

    /**
     * Prints the given list in the following format:
     * 1. item-1
     * 2. item-2
     * ...
     * n. item-n
     */
    public void print() {
        if (list.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            int i = 1;
            for (Task t : list) {
                System.out.println(i + ". " + t.toString());
                i++;
            }
        }
    }
}
