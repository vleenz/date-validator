
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DatesTest {

    @Test
    public void testValidDate() {
        String validDate = "31/01/2022";
        String formattedDate = Dates.split(validDate);
        assertEquals("31 Jan 2022", formattedDate);
    }

    @Test
    public void testYear() {
        for (int year = 1753; year < 3001; year++) {
            for (int month = 1; month < 13; month++) {
            }
            String validYear = "01/01/" + year;
            String formattedDate = Dates.split(validYear);
            assertEquals("01 Jan " + year, formattedDate);

        }
    }

    @Test
    public void testInvalidSeparator() {
        String invalidDate = "31.01.2022";
        String formattedDate = Dates.split(invalidDate);
        assertEquals("31.01.2022 - INVALID", formattedDate);
    }

    @Test
    public void testWrongNumberOfSeparators() {
        String invalidDate = "31/01-2022";
        String formattedDate = Dates.split(invalidDate);
        assertEquals("31/01-2022 - INVALID", formattedDate);
    }

    @Test
    public void testMultipleSeparators() {
        String invalidDate = "31/01//2022";
        String formattedDate = Dates.split(invalidDate);
        assertEquals("31/01//2022 - INVALID", formattedDate);
    }

    @Test
    public void testYearFormat() {
        String invalidDate = "31/01/0022";
        String formattedDate = Dates.split(invalidDate);
        assertEquals("31/01/0022 - INVALID", formattedDate);
    }

}
