package lt.viko.eif.stermen.project1.utility;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;

/**
 *
 */
public class CsvUtility {

    private static String[] HEADERS;
    private static CSVFormat csvFormat = null;
    private static Iterable<CSVRecord> records = null;
    private static Reader in = null;

    /**
     *
     */
    private CsvUtility() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and it cannot be instantiated.");
    }

    /**
     * @return
     */
    public static String[] getHeaders() {
        return HEADERS;
    }

    /**
     * @param HEADERS
     */
    public static void setHeaders(String[] HEADERS) {
        CsvUtility.HEADERS = HEADERS;
    }

    /**
     * @return
     */
    public static Iterable<CSVRecord> getRecords() {
        return records;
    }

    /**
     * @param records
     */
    public static void setRecords(Iterable<CSVRecord> records) {
        CsvUtility.records = records;
    }

    /**
     * @param filepath
     */
    public static void parseCsv(String filepath) {
        if (HEADERS != null) {
            try {
                in = new FileReader(filepath);
                csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();
                records = csvFormat.parse(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
