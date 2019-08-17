import java.util.Scanner;

public class Duke {
    private static final String EXIT = "bye";
    private static TextList textList;

    private static String[] splitString(String msg) {
        int i;
        String[] arr = msg.split("\\s+");
        for (i = 0; i < arr.length; i++)
            if (arr[i].startsWith("/"))
                break;
        StringBuilder taskDescription = new StringBuilder();
        StringBuilder dateTime = new StringBuilder();
        for (int j = 1; j < i; j++) 
            taskDescription.append(arr[j] + " ");
        for (int j = i + 1; j < arr.length; j++)
            dateTime.append(arr[j] + " ");
        String[] ret = {taskDescription.toString().trim(), dateTime.toString().trim()};
        return ret;
    }

    private static void handleAddingTasks(String msg) {
        System.out.println("Got it. I've added this task:");
        String command = msg.split("\\s+")[0];
        Task t;
        switch (command) {
            case "todo":
                t = new TodoTask(msg.substring("todo".length() + 1));
                break;
            case "deadline":
                String[] infoDeadline = splitString(msg);
                t = new DeadlineTask(infoDeadline[0], infoDeadline[1]);
                break;
            case "event":
                String[] infoEvent = splitString(msg);
                t = new EventTask(infoEvent[0], infoEvent[1]);
                break;
            default:
                t = new Task(null);
        }
        textList.add(t);
        System.out.println("\t" + t);
        System.out.println("Now you have " + textList.size() + " tasks in the list.");
    }

    /**
     * Logic for looping the program. Program exits when user enters "bye".
     */
    private static void promptUser() {
        Scanner sc = new Scanner(System.in);
        textList = new TextList();

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

                default: // todo, deadline, event
                    handleAddingTasks(msg);
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
