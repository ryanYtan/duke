public class Ui {
    private static final String BORDER = String.format("%40s", "\u2015").replace(' ', '\u2015');

    public Ui() {
        this.parser = new Parser();
    }

    /**
     * Prints varargs number of Strings with the format:
     * "\t string1"
     * "\t string2"
     * etc.
     * 
     * @param args strings to be printed
     */
    public void print(String... args) {
        for (String s : args) {
            System.out.println("\t" + s);
        }
    }
}
