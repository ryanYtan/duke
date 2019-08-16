import java.util.Scanner;

public class Duke {
    private static final String EXIT = "bye";

    private static void promptUser() {
        Scanner sc = new Scanner(System.in);
        Message msg = new Message(sc.nextLine());
        while (msg.compareTo(new Message(EXIT)) != 0) {
            msg.print();
            msg = new Message(sc.nextLine());
        }
        new Message("Bye. Hope to see you again soon!").print();
    }

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
