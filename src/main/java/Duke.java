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
            throw new IllegalInstructionException("Deadline or Event commands require \"/by\" or \"/at\" within the command");
        } else if (i == arr.length - 1) { // nothing after /at or /by
            throw new IllegalInstructionException("No timing given for " + arr[0] + " command.");
        } else if (i == 1) { // nothing between command, or /at or /by
            throw new IllegalInstructionException("No description given for " + arr[0] + " command.");
        } else {
            StringBuilder taskDescription = new StringBuilder();
            StringBuilder dateTime = new StringBuilder();
            for (int j = 1; j < i; j++) 
                taskDescription.append(arr[j] + " ");
            for (int j = i + 1; j < arr.length; j++)
                dateTime.append(arr[j] + " ");
            String[] ret = {taskDescription.toString().trim(), dateTime.toString().trim()};
            return ret;
        }
    }

    private static void handleAddingTasks(String msg) throws IllegalInstructionException {
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
                if (DateTime.isValidDateFormat(infoDeadline[1])) {
                    t = new DeadlineTask(infoDeadline[0], new DateTime(infoDeadline[1]));
                } else {
                    t = new DeadlineTask(infoDeadline[0], infoDeadline[1]);
                }
                break;

            case "event":
                String[] infoEvent = splitString(msg);
                if (DateTime.isValidDateFormat(infoEvent[1])) {
                    t = new EventTask(infoEvent[0], new DateTime(infoEvent[1]));
                } else {
                    t = new EventTask(infoEvent[0], infoEvent[1]);
                }
                break;

            default:
                throw new IllegalInstructionException(
                        "Sorry! I don't know what that means.");
        }
        textList.add(t);
        System.out.println("Got it. I've added this task:");
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
            try {
                switch(command) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        textList.print();
                        break;

                    case "done":
                        if (msg.split("\\s+").length <= 1) {
                            throw new IllegalInstructionException("No value given to mark as done.");
                        }
                        int index = Integer.parseInt(msg.split("\\s+")[1]);
                        textList.done(index);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(textList.get(index));
                        break;
                    
                    case "delete":
                        if (msg.split("\\s+").length <= 1) {
                            throw new IllegalInstructionException("No value given to delete.");
                        }
                        index = Integer.parseInt(msg.split("\\s+")[1]);
                        String deleted = textList.get(index);
                        textList.delete(index);
                        System.out.println("Noted. I've removed this task");
                        System.out.println(deleted);
                        System.out.println("Now you have " + textList.size() + " tasks in the list.");
                        break;

                    default: // todo, deadline, event
                        handleAddingTasks(msg);
                }
            } catch (IllegalInstructionException e) {
                System.out.println(e);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
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
