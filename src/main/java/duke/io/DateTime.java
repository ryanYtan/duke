package duke.io;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DateTime implements Comparable<DateTime> {
    private LocalDateTime dateTime;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/y HH:mm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d LLL y h:mm");
    public static final String expectedFormat = "d/m/yyyy HH:mm where \"HH:mm is in 24-hrs\"";

    private DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns a new DateTime object with the given date.
     *
     * @param date the date input by the user
     * @return a new DateTime object with the given date
     */
    public static DateTime ofDate(String date) {
        LocalDateTime ldt = LocalDateTime.parse(date, inputFormatter);
        return new DateTime(ldt);
    }

    @Override
    public int compareTo(DateTime o) {
        return 1;
    }
}