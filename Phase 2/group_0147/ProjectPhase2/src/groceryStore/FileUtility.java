package groceryStore;

import java.io.FileWriter;
import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

/**
 * This class FileUtility. This class is acts as a helper class that
 * reads and writes into Files that are passed into it by
 * other class methods.
 *
 * @author Sam Q., Felix L, Jasper W.
 * @version 1.8
 */

public class FileUtility {

    /**
     * Given the name of the file, this method goes through it  and finds writes into
     * the pre-existing file.
     *
     * @param    fileName    A string that contains the name for the file.
     * @param    data        A string that contains where the contents is
     *                       to be written in the file.
     */
    public static void FileWriter(String fileName, String data) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            Formatter f = new Formatter(fileWriter);
            f.format(data);
            f.close();
        } catch (Exception e) {
            System.out.println("File does not exist.");
        }
    }
    /**
     * Given the name of the file, this method goes through it and finds a
     * specific string, Object.
     *
     * @param    fileName    A string that contains the name for the file.
     * @param    object      A string that that is being found in the file.
     */
    public static StringBuffer ProductFileScanner(String fileName, String object) {
        String line;
        StringBuffer orders = new StringBuffer("");
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                line = sc.nextLine();
                if (line.contains(object)) {
                    orders.append(line + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println("File does not exist.");
        }
        return orders;
    }
    /**
     * Given the name of the file, this method goes through it and finds the total
     * revenue or profit for specific class functions.
     *
     * @param    position    An int of where the string profit/revenue is to be found.
     * @param    date        A string containing the date of which you are trying to find
     *                       the profit/revenue for.
     * @param    year        A string containing the year of which you are trying to find
     *                       the profit/revenue for.
     * @return   sum         The double that represents the profit or revenue.
     */
    public static Double SumScanner (int position, String date, String year) {
        String line;
        Double sum = 0.0;
        String[] stringSale;
        try {
            File file = new File("SaleHistory.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                line = sc.nextLine();
                if (line.contains(date) && line.contains(year)) {
                    stringSale = line.split(",");
                    sum += Double.parseDouble(stringSale[position]);
                }
            }
        } catch (Exception e) {
            System.out.println("File does not exist.");
        }
        return sum;
    }

}
