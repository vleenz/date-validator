
import java.io.FileWriter;
import java.io.IOException;

/**
 * Creates a file named leapYears.txt and writes to it each leap year between
 * 1753 and 3000.
 */
public class LeapYearGenerator {

    public static void main(String[] args) throws Exception {
        try {
            FileWriter myWriter = new FileWriter("leapYears.txt");

            for (int i = 1753; i <= 3000; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    myWriter.write(i + "\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
