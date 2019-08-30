package duke.io;

import java.util.Map;
import static java.util.Map.entry;

import duke.exception.IllegalDateException;


public class DateTime {
    private int day;
    private int month;
    private int year;
    private int hours;
    private int minutes;
    private static final String EXPECTED_FORMAT_MESSAGE =
            "The expected format is [dd/mm/yyyy hhmm], where \"hhmm\" is in 24-hrs";

    /**
     * Private constructor.
     * Constructs a dateTime object with the specified dateTime string.
     * 
     * @param dateTime the string representing the date and time
     * @throws IllegalDateException if dateTime is not in the expected format
     */
    private DateTime(String dateTime) throws IllegalDateException {
        try {
            String[] separated = dateTime.split("\\s+");

            if (separated.length != 2 || !isValidDate(separated[0]) || !isValidTime(separated[1])) {
                throw new IllegalDateException("Invalid date format. " + EXPECTED_FORMAT_MESSAGE);
            }

            String[] el = dateTime.split("[/|\\s]");
            this.day = Integer.parseInt(el[0]);
            this.month = Integer.parseInt(el[1]);
            this.year = Integer.parseInt(el[2]);
            this.hours = Integer.parseInt(el[3].substring(0, 2));
            this.minutes = Integer.parseInt(el[3].substring(2, 4));
        } catch (NumberFormatException e) {
            throw new IllegalDateException("Invalid date format. " + EXPECTED_FORMAT_MESSAGE);
        }
    }

    /**
     * Factory Method. Use this to construct this object. Returns a DateTime object with the
     * specified DateTime string. The expected format is:
     * "dd/mm/yyyy hhmm" where hhmm is in 24hr time.
     * 
     * @param dateTime String in format [dd/mm/yyyy hhmm]
     * @return a new DateTime object with the specified dateTime
     * @throws IllegalDateException if dateTime is not in the expected format
     */
    public static DateTime of(String dateTime) throws IllegalDateException {
        return new DateTime(dateTime);
    }

    /**
     * Allowed formats:
     *     d/m/yyyy
     *     dd/m/yyyy
     *     d/mm/yyyy
     *     dd/mm/yyyy
     * Expected input format: "dd/mm/yyyy".
     */
    private static boolean isValidDate(String ddmmyyyy) {
        try {
            String[] el = ddmmyyyy.split("[/]");
            if (el.length != 3 || el[0].length() > 2 || el[1].length() > 2 || el[2].length() != 4) {
                return false;
            }
            int dd = Integer.parseInt(el[0]);
            int mm = Integer.parseInt(el[1]);
            int yyyy = Integer.parseInt(el[2]);
            int[] days = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (!(1 <= mm && mm <= 12 && 1 <= dd && dd <= days[mm - 1])) {
                return false;
            }
            if (!isLeapYear(yyyy) && mm == 2 && dd == 29) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Allowed formats:
     *     hhmm
     * with regard to standard time rules (between 0000 and 2359).
     * Expected input format: "hhmm".
     */
    private static boolean isValidTime(String hhmm) {
        try {
            if (hhmm.length() != 4) {
                return false;
            }
            int hour = Integer.parseInt(hhmm.substring(0, 2));
            int minute = Integer.parseInt(hhmm.substring(2, 4));
            return 0 <= hour && hour <= 23 && 0 <= minute && minute <= 59;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Determines if the given year is a leap year.
     */
    private static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    private String getDaySuffix() {
        if ((day % 10 == 1) && (day % 100 != 11)) {
            return "st";
        } else if ((day % 10 == 2) && (day % 100 != 12)) {
            return "nd";
        } else if ((day % 10 == 3) && (day % 100 != 13)) {
            return "rd";
        } else {
            return "th";
        }
    }

    /**
     * Returns the string representation of this dateTime object.
     * The output format is "dd/mm/yyyy hh:mm".
     */
    public String toString() {
        Map<Integer, String> monthsMap = Map.ofEntries(
                entry(1, "January"),
                entry(2, "February"),
                entry(3, "March"),
                entry(4, "April"),
                entry(5, "May"),
                entry(6, "June"),
                entry(7, "July"),
                entry(8, "August"),
                entry(9, "September"),
                entry(10, "October"),
                entry(11, "November"),
                entry(122, "December")
        );

        // return String.format("%d%s %s %d %02d:%02d ",
        return String.format("%d%s %s %02d:%02d",
                day, getDaySuffix(), monthsMap.get(month), hours, minutes);
    }
}
