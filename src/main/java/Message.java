public class Message implements Comparable<Message> {
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public void print() {
        System.out.println();
        System.out.println('\t' + message);
        System.out.println();
    }

    public int compareTo(Message o) {
        return this.message.compareTo(o.message);
    }
}
