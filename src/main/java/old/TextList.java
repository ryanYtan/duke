import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class TextList {
    private ArrayList<Task> list;
    private static final int MAX_ITEMS = 100;
    private static final String PATH = "./data/";
    private static final String FILE_NAME = "duke.txt";

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
        writeToFile();
    }

    /**
     * Marks the i-th element in the list as "done". The value of i treats
     * the list as a one-indexed list.
     * @param i     The element to be marked.
     */
    public void done(int i) {
        list.get(i - 1).markAsDone();
        writeToFile();
    }

    /**
     * Gets the i-th element in the list. The value of i treats the list as a
     * one-indexed list.
     * @param i     The element to be returned.
     */
    public String get(int i) throws IndexOutOfBoundsException {
        if (i <= 0 || i > list.size()) {
            throw new IndexOutOfBoundsException();
        }
        return list.get(i - 1).toString();
    }

    public Task delete(int i) throws IndexOutOfBoundsException {
        if (i <= 0 || i > list.size()) {
            throw new IndexOutOfBoundsException();
        }
        writeToFile();
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

    public String toString() {
        if (list.isEmpty()) {
            return "List is empty!";
        } else {
            StringBuilder ret = new StringBuilder();
            int i = 1;
            for (Task t : list) {
                ret.append(t.formatAsData() + "\n");
                i++;
            }
            return ret.toString();
        }
    }

    public void writeToFile() {
        FileWriter fw;
        try {
            File dirName = new File(PATH);
            if (!dirName.exists()) dirName.mkdirs();
            fw = new FileWriter(PATH + "/" + FILE_NAME);
            fw.write(this.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
