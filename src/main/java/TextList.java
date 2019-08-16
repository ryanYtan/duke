import java.util.ArrayList;

public class TextList {
    private ArrayList<String> list;
    private static final int MAX_ITEMS = 100;

    public TextList() {
        list = new ArrayList<String>(MAX_ITEMS);
    }

    /**
     * Adds the given text into this list of text.
     * @param text  text to be entered
     */
    public void add(String text) {
        list.add(text);
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
            for (String s : list) {
                System.out.println(i + ". " + s);
                i++;
            }
        }
    }
}
