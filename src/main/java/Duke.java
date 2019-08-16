import java.util.Scanner;

public class Duke {
    private static final String EXIT = "bye";

    private static void promptUser() {
        Scanner sc = new Scanner(System.in);
        TextList textList = new TextList();
        String message = sc.nextLine();
        while (!message.equals(EXIT)) {
            switch (message) {
                case "list":
                    textList.print();
                    break;
                default:
                    System.out.println("added: " + message);
                    textList.add(message);
            }
            message = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Main method.
     * @param args  list of inputs
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        promptUser();
    }
}
