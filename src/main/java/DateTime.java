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
     * @param dateTime
     * @throws IllegalDateException if dateTime is not in the expected format
     */
    private DateTime(String dateTime) throws IllegalDateException {
        try {
            String[] separated = dateTime.split("\\s+");

            if (separated.length != 2 || !isValidDate(separated[0]) || !isValidTime(separated[1]))
                throw new IllegalDateException("Invalid date format. " + EXPECTED_FORMAT_MESSAGE);

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
            if (hhmm.length() != 4) return false;
            int hour = Integer.parseInt(hhmm.substring(0, 2));
            int minute = Integer.parseInt(hhmm.substring(2, 4));
            if (0 <= hour && hour <= 23 && 0 <= minute && minute <= 59) {
                return true;
            }
            return false;
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

    /**
     * Returns the string representation of this dateTime object.
     * The output format is "dd/mm/yyyy hh:mm".
     */
    public String toString() {
        return String.format("%02d/%02d/%d %02d:%02d",
                day, month, year, hours, minutes);
    }
}
