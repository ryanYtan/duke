import java.util.Arrays;
public class DateTime {
    private int year;
    private int month;
    private int day;
    private int hours;
    private int minutes;

    /**
     * Constructor.
     * @param dateTime in the format "DD/MM/YYYY hhmm", where hhmm is in 24hr format
     */
    public DateTime(String dateTime) throws IllegalInstructionException {
        String[] el = dateTime.split("[/|\\s]");
        if (!DateTime.isValidDateFormat(dateTime))
            throw new IllegalInstructionException("Date is not in valid format");
        this.day = Integer.parseInt(el[0]);
        this.month = Integer.parseInt(el[1]);
        this.year = Integer.parseInt(el[2]);
        this.hours = Integer.parseInt(el[3].substring(0, 2));
        this.minutes = Integer.parseInt(el[3].substring(2, 4));
    }

    public static boolean isValidDateFormat(String date) {
        String[] el = date.split("[/|\\s]");
        try {
            if (el.length != 4) 
                throw new IllegalInstructionException("not valid");
            Integer.parseInt(el[0]);
            Integer.parseInt(el[1]);
            Integer.parseInt(el[2]);
            Integer.parseInt(el[3].substring(0, 2));
            Integer.parseInt(el[3].substring(2, 4));
        } catch (NumberFormatException e) {
            return false;
        } catch (IllegalInstructionException e) {
            return false;
        }
        return true;
    }

    public String asDateTime() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        return String.format("%d %s %d %s%s", day, months[month - 1], year,
                String.format("%02d", hours), String.format("%02d", minutes));
    }

    public String toString() {
        return String.format("%d/%d/%d %d:%d", year, month, day, hours, minutes);
    }
}
