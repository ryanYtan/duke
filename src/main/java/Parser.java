public class Parser {
    /**
     * Handles parsing of user input.
     * @param input
     * @return a new Task represented by the input, null otherwise
     * @throws IllegalInstructionException
     */
    public static Task parse(String input) throws IllegalInstructionException {
        String[] strings = input.split("\\s+");
        String command = strings[0];
        switch (command) {
            case "list":
                return null;

            case "todo":
                return TaskFactory.createTask(input);

            case "deadline":
            case "event":
                
                return TaskFactory.createTaskWithDate()

            case "done":
                return null;

            case "bye":

            default:
                throw new IllegalInstructionException("Sorry! I don't know what that means.");
        }
    }
}
