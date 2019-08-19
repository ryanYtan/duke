public class Parser {


    public static void parse(String input) {
        String[] strings = input.split("\\s+");
        String command = strings[0];
        switch (command) {
            case "list":

            case "todo":

            case "deadline":

            case "event":

            case "bye":
        }
    }
}
