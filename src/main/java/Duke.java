import java.util.Scanner;

public class Duke {
    private static final String EXIT = "bye";
    private static TextList textList;

    private static String[] splitString(String msg) throws IllegalInstructionException {
        int i;
        boolean slashExists = false;
        String[] arr = msg.split("\\s+");
        for (i = 0; i < arr.length; i++) {
            if (arr[i].startsWith("/")) {
                slashExists = true;
                break;
            }
        }

        if (!slashExists) {
            throw new IllegalInstructionException("Certain commands require specific syntax, "
                    + "please consult the user manual for more information.");

        } else if (i == arr.length - 1) { // nothing after /at or /by
            throw new IllegalInstructionException(
                    (String.format("No timing given for %s command", arr[0])));

        } else if (i == 1) { // nothing between command, or /at or /by
            throw new IllegalInstructionException(
                    String.format("No description given for %s command", arr[0]));

        } else {
            StringBuilder taskDescription = new StringBuilder();
            StringBuilder dateTime = new StringBuilder();
            for (int j = 1; j < i; j++) 
                taskDescription.append(arr[j] + " ");
            for (int j = i + 1; j < arr.length; j++)
                dateTime.append(arr[j] + " ");
            return new String[]{taskDescription.toString().trim(), dateTime.toString().trim()};
        }
    }

    private static String handleAddingTasks(String msg) throws IllegalInstructionException {
        Task t;
        String command = msg.split("\\s+")[0];
        switch (command) {
            case "todo":
                if (msg.split("\\s+").length <= 1) {
                    throw new IllegalInstructionException(
                            "The description of a todo cannot be empty");
                }
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
                throw new IllegalInstructionException("Sorry! I don't know what that means.");
        }
        textList.add(t);
        return t.toString();
    }

    private static String handleDelete(String msg) throws IllegalInstructionException {
        if (msg.split("\\s+").length <= 1) {
            throw new IllegalInstructionException("No value given to delete.");
        }
        int index = Integer.parseInt(msg.split("\\s+")[1]);
        String deleted = textList.get(index);
        textList.delete(index);
        return deleted;
    }

    private static String handleDone(String msg) throws IllegalInstructionException {
        if (msg.split("\\s+").length <= 1) {
            throw new IllegalInstructionException("No value given to mark as done.");
        }
        int index = Integer.parseInt(msg.split("\\s+")[1]);
        textList.done(index);
        return textList.get(index);
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
            try {
                switch (command) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        textList.print();
                        break;

                    case "done":
                        String finished = handleDone(msg);
                        System.out.println(String.format("Nice! I've marked this task as done:",
                                finished));
                        break;
                    
                    case "delete":
                        String deleted = handleDelete(msg);
                        System.out.println("Noted. I've removed this task:\n"
                                + "\t" + deleted + "\n"
                                + String.format("Now you have %d tasks in the list.",
                                        textList.size()));
                        break;

                    default: // todo, deadline, event
                        String added = handleAddingTasks(msg);
                        System.out.println("Got it. I've added this task:\n"
                                + "\t" + added + "\n"
                                + String.format("Now you have %d tasks in the list",
                                        textList.size()));
                }
            } catch (IllegalInstructionException e) {
                System.out.println(e);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The value specified is out-of-bounds of the list. "
                        + String.format("You currently have %d task(s) in the list.",
                        textList.size()));
            } finally {
                msg = sc.nextLine();
            }
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
