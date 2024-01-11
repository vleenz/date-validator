import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Dates {

    // I'm not too sure about these
    private static DatesValues d = new DatesValues("", "", "");
    private static final int MIN_YEAR = 1753;
    private static final int MAX_YEAR = 3000;
    private static final String SEPARATORS = "[/\\s-]";

    /**
     * FOR TESTING: This main method reads the input file line by line, then passes
     * the lines to
     * the split method to be processed. Any errors are caught and printed to the
     * console.
     * 
     * FOR IMPLEMENTATION: This main method reads the users input from stdin line by
     * line, then passes
     * the the lines to the split method to be processed. Any errors are caught and
     * printed to the console.
     * 
     * @param args Command-line arguments for the application
     * @throws Exception if an error occurs during file input
     */
    public static void main(String[] args) throws Exception {

        // try {
        // Scanner sc = new Scanner(new File("testDataCorrect.txt"));

        // while (sc.hasNextLine()) {
        // String stdin = sc.nextLine();
        // System.out.println(outputReturn(stdin));
        // }
        // sc.close();
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String stdin = sc.nextLine();
            System.out.println(outputReturn(stdin));
        }
        sc.close();

    }

    /**
     * Checks to see if the input date is in the correct format, or is a valid date
     * 
     * @param date a string representing a date
     * @return a String either in the correct output format or if the input is
     *         incorrect, echoes the input + " - INVALID"
     */
    public static String outputReturn(String date) {
        // Returns string
        if (sepCheck(date) == false || formatCheck(date) == false) {
            return date + " - INVALID";
        }
        if (validDate(d.day, d.month, d.year) == true) {
            if (d.day.length() < 2) {
                return ("0" + d.day + " " + d.month + " " + d.year);
            }
            if (d.day.length() == 2) {
                return (d.day + " " + d.month + " " + d.year);
            }
        } else {
            return date + " - INVALID";
        }
        System.err.println("Reason unknown");
        return date + " - INVALID";
    }

    /**
     * Extracts the separators from the input date, checks if they are valid
     * separators, if there is the correct number of separators, and
     * if more than one type of separator is being used.
     * 
     * @param date a string representing a date
     * @return a boolean value indicating whether the input separators are in a
     *         valid format or not.
     * 
     */
    public static boolean sepCheck(String date) {
        // Extract the separators from the input date
        String separators = date.replaceAll("\\w", "");
        String[] sepArray = separators.split("");
        String separator = sepArray[0];
        // Checks if separator is valid
        if (!separator.matches(SEPARATORS)) {
            System.err.println("Incorrect separator used");
            return false;
        }

        // Check if the number of separators is correct
        if (separators.length() != 2) {
            System.err.println("Invalid format - wrong number of separators");
            return false;
        }

        // Checks if more than one separator is used
        if (separators.charAt(0) != (separators.charAt(1))) {
            System.err.println("More than one separator used");
            return false;
        }
        return true;
    }

    /**
     * Checks if the amount of inputs is correct, and if the day and year are
     * numeric. If valid, it then splits
     * the input into 3 parts, allocates each part into day, month and year and
     * checks if each part contains any
     * illegal characters
     * 
     * @param date a string representing a date
     * @return a boolean value indicating whether the input is in a valid format or
     *         not.
     */
    public static boolean formatCheck(String date) {

        // Reset static variables
        d.month = "";
        d.day = "";
        d.year = "";

        // Extract day, month and year from input date
        String[] DateArray = date.split(SEPARATORS);

        // Checks if number of inputs without separators is correct
        if (DateArray.length != 3) {
            System.err.println("Incorrect amount of inputs");
            return false;
        }

        // Checks if day or year are numeric
        if (isNumeric(DateArray[0]) == false || isNumeric(DateArray[2]) == false) {
            System.err.println("Illegal format, day or year is not numeric");
            return false;
        }
        // Allocates variables from corresponding array
        d.day = DateArray[0];
        d.month = DateArray[1];
        d.year = DateArray[2];
        // DatesValues d = new DatesValues(DateArray[0], DateArray[1], DateArray[2]);
        // Checks if any of the split input contains an illegal character
        for (int i = 0; i < DateArray.length; i++) {
            if (DateArray[i].matches("^[^a-zA-Z0-9]*$")) {
                System.err.println("Illegal format");
                return false;
            }
        }

        return true;
    }

    /**
     * Checks to see if the given day is in the correct format and is in the valid
     * range.
     * 
     * @param d String representing the day of the date.
     * @return boolean value indicating whether the given day is valid or not.
     */
    public static boolean validDayFormat(String d) {
        // Checks if day is between 1 - 31, allowing a 0 infront numbers less than 10
        int dp = Integer.parseInt(d);
        if (!d.matches("^(?:[1-9]|[0][1-9]|[12][0-9]|3[01]$)")) {
            if (dp > 31 || dp <= 0) {
                System.err.println("Invalid day - day out of range");
                return false;
            }
            System.err.println("Invalid day format");
            return false;
        }
        return true;
    }

    /**
     * Checks to see if the given year is in the correct format and is in the valid
     * range.
     * 
     * @param y String representing the year of the date.
     * @return boolean value indicating whether the given year is valid or not.
     */
    public static boolean validYear(String y) {
        int parsedYear = Integer.parseInt(y);

        // Checks if year is either 2 or 4 digits.
        if (!y.matches("^\\d{2}(?:\\d{2})?$")) {
            System.err.println("Invalid year format");
            return false;
        }

        // If year is two digits, it converts the year from inbetween 1950 to 2049
        if (y.length() == 2 && parsedYear >= 50) {
            parsedYear = parsedYear + 1900;
            d.year = String.valueOf(parsedYear);
        }
        if (y.length() == 2 && parsedYear >= 0 && parsedYear < 50) {
            parsedYear = parsedYear + 2000;
            d.year = String.valueOf(parsedYear);
        }

        // Checks to see if year is between the minimum and maximum year
        if (parsedYear < MIN_YEAR || parsedYear > MAX_YEAR) {
            System.err.println("Year out of Range");
            return false;
        }
        return true;
    }

    /**
     * Checks to see if the given year is a leap year or not.
     * 
     * @param y String representing the year of the date.
     * @return boolean value indicating whether the given year is a leap year or
     *         not.
     */
    public static boolean leapYear(String y) {
        int parsedYear = Integer.parseInt(y);
        boolean leapYear = false;
        // checks for leap year
        if (parsedYear % 4 == 0 && parsedYear % 100 != 0 || parsedYear % 400 == 0) {
            leapYear = true;
        }
        return leapYear;

    }

    /**
     * 
     * Method takes in three string arguments representing day, month and year
     * respectively.
     * It checks if day and year are in the correct format and if the date is a
     * valid date and returns a boolean value indicating
     * whether the date is valid or not.
     * 
     * @param d String representing the day of the date.
     * @param m String representing the month of the date.
     * @param y String representing the year of the date.
     * @return A boolean value indicating whether the given date is valid or not.
     */
    public static boolean validDate(String d, String m, String y) {
        if (validDayFormat(d) == false || validYear(y) == false) {
            return false;
        }

        int dp = Integer.parseInt(d);

        return validMonth(dp, m, leapYear(y));
    }

    /**
     * Method which takes in the input day, month and whether the year is a leap
     * year or not.
     * It checks to see if the month is either a number or in the format of the
     * first 3 letters of the month.
     * It then checks to see if the day is inbetween the maximum amount of days for
     * the corresponding month and sets
     * the month in the format Mmm if it is valid
     * 
     * @param d        String representing the day of the date
     * @param m        String representing the month of the date
     * @param leapYear Boolean value incating whether the given year is a leap year
     *                 or not
     * @return boolean value whether the month or day for the month is valid or not
     */
    public static boolean validMonth(int day, String m, boolean leapYear) {
        String[] months = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };
        int parsedInt = 0;
        int maxDay = 0;
        boolean isUppercase = m.equals(m.toUpperCase());
        boolean isLowercase = m.equals(m.toLowerCase());
        boolean firstLetterUpper = m.equals(m.substring(0, 1).toUpperCase() + m.substring(1).toLowerCase());
        boolean foundMonth = false;

        // Checks if m is formatted between 1 and 12, allowing for a 0 infront of
        // numbers less than 10,
        // or if m is 3 letters long.
        if (!m.matches("^([1-9]|[0][1-9]|1[0-2]|[a-zA-Z]{3}$)")) {
            if (isNumeric(m) == true && Integer.parseInt(m) > 12 || isNumeric(m) == true && Integer.parseInt(m) < 1) {
                System.err.println("Month out of range");
                return false;
            }
            System.err.println("Incorrect month format");
            return false;
        }

        // if m is a number, set parsedInt as m
        if (isNumeric(m) == true) {
            parsedInt = Integer.parseInt(m);
        }

        // if m isn't a number, check if it matches the legal inputs
        if (isNumeric(m) == false && isUppercase == false && isLowercase == false && firstLetterUpper == false) {
            System.err.println("Month name incorrect format");
            return false;
        }

        /*
         * Iterate through each month until m matches a month or it's corresponding
         * number. If it is found,
         * set month to the format Mmm and check whether d is inbetween the maximum
         * amount of days for that month
         */
        m = m.toLowerCase();
        for (int i = 0; i < months.length; i++) {
            if (m.equals(months[i]) || parsedInt == i + 1) {
                foundMonth = true;
                d.month = months[i].substring(0, 1).toUpperCase() + months[i].substring(1);
                if (months[i].equals("apr") || months[i].equals("jun") || months[i].equals("sep")
                        || months[i].equals("nov")) {
                    maxDay = 30;
                    return validDay(day, maxDay);
                }
                if (months[i].equals("jan") || months[i].equals("mar") || months[i].equals("may") ||
                        months[i].equals("jul") || months[i].equals("aug") || months[i].equals("oct") ||
                        months[i].equals("dec")) {
                    maxDay = 31;
                    return validDay(day, maxDay);
                }
                if (months[i].equals("feb") && leapYear == true) {
                    maxDay = 29;
                    return validDay(day, maxDay);
                }
                if (months[i].equals("feb") && leapYear == false) {
                    maxDay = 28;
                    return validDay(day, maxDay);
                }

            }

        }
        // If a month isn't found, return
        if (foundMonth == false) {
            System.err.println("Invalid month");
            return false;
        }
        return false;
    }

    /**
     * Method that takes in the input day from the user and checks to see if it
     * valid for the given month
     * 
     * @param dayDate input day from user
     * @param max     max day allowed for input month
     * @return boolean value indicating whether the input day is less than the
     *         maximum day for that month
     */
    public static boolean validDay(int dayDate, int max) {
        if (dayDate > 0 && dayDate <= max) {
            return true;
        }
        System.err.println("Day out of range for month");
        return false;
    }

    /**
     * Method to check whether a number is numeric or not.
     * Sources from:
     * 
     * @link https://www.baeldung.com/java-check-string-number#:~:text=Perhaps%20the%20easiest%20and%20the
     * @param strNum String to check if is a number or not.
     * @return boolean value indicating whether input number is numeric or not
     */

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int m = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}