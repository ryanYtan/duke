public class Ui {
    private static final String BORDER = String.format("%40s", "\u2015").replace(' ', '\u2015');

    public Ui() {
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

    public void showWelcome() {
        String logoOne =   " ____        _        ";
        String logoTwo =   "|  _ \\ _   _| | _____ ";
        String logoThree = "| | | | | | | |/ / _ \\";
        String logoFour =  "| |_| | |_| |   <  __/";
        String logoFive =  "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = "Welcome to";
        String question = "What can I do for you today?";
        print(greeting, logoOne, logoTwo, logoThree, logoFour, logoFive, question);
    }
}
