package lt.viko.eif.stermen.project1.utility;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;

/**
 * A simple utility class that wraps over the org.apache.commons.csv library.
 * Firstly, the labels of each column are provided to the headers String array.
 * Then parseCsv is used to parse the file.
 * Finally, records object is used to retrieve any and all objects from the parsed file.
 */
public class CsvUtility {
    private static String[] headers;
    private static CSVFormat csvFormat = null;
    private static Iterable<CSVRecord> records = null;
    private static Reader in = null;

    /**
     * Utility class cannot have an instance. Throw exception if instantiated.
     */
    private CsvUtility() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and it cannot be instantiated.");
    }
    public static String[] getHeaders() {
        return headers;
    }

    /**
     * For any further class functionality these headers must be set.
     * @param headers used to set the headers for parsing the CSV file.
     */
    public static void setHeaders(String[] headers) {
        CsvUtility.headers = headers;
    }

    /**
     * This method should be used only after CSV file has been parsed.
     * @return all the parsed records.
     */
    public static Iterable<CSVRecord> getRecords() {
        return records;
    }

    public static void setRecords(Iterable<CSVRecord> records) {
        CsvUtility.records = records;
    }

    /**
     * Parser a .csv file that is provided from the relative filepath of the program.
     * @param filepath the .csv file location.
     */
    public static void parseCsv(String filepath) {
        if (headers != null) {
            try {
                in = new FileReader(filepath);
                csvFormat = CSVFormat.DEFAULT.builder().setHeader(headers).setSkipHeaderRecord(true).build();
                records = csvFormat.parse(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
