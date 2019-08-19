public class Parser {


    public static void parse(String input) throws IllegalInstructionException {
        String[] strings = input.split("\\s+");
        String command = strings[0];
        switch (command) {
            case "list":

            case "todo":

            case "deadline":

            case "event":

            case "done":

            case "bye":


            default:
                throw new IllegalInstructionException("Sorry! I don't know what that means.");
        }
    }
}
