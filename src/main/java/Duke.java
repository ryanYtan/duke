import java.util.Scanner;

public class Duke {
    private static final String EXIT = "bye";

    /**
     * Logic for looping the program. Program exits when user enters "bye".
     */
    private static void promptUser() {
        Scanner sc = new Scanner(System.in);
        TextList textList = new TextList();
        String msg = sc.nextLine();
        while (!msg.equals(EXIT)) {
            String command = msg.split("\\s+")[0];
            switch(command) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    textList.print();
                    break;
                case "done":
                    int index = Integer.parseInt(msg.split("\\s+")[1]);
                    textList.done(index);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(textList.get(index));
                    break;
                default:
                    textList.add(new Task(msg));
                    System.out.println("added: " + msg);
            }
            msg = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
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
