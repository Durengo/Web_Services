package lt.viko.eif.stermen.utilityTest;

import lt.viko.eif.stermen.project1.domain.people.Passenger;
import lt.viko.eif.stermen.project1.utility.CsvUtility;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvTest {
    private List<Passenger> passengerList = new ArrayList<Passenger>();
    public CsvTest()
    {
        passengerList.add(new Passenger("Doria","Conigsby","1985-10-08",95.32));
        passengerList.add(new Passenger("Diannne","Bassett","2022-10-17",44.66));
        passengerList.add(new Passenger("Port","Semeradova","1984-01-15",63.82));
        passengerList.add(new Passenger("Catha","Drayn","1995-10-15",2.61));
        passengerList.add(new Passenger("Nydia","Iban","1988-01-14",78.12));
        passengerList.add(new Passenger("Mirilla","Hiom","2014-08-26",78.3));
        passengerList.add(new Passenger("Linet","Whalley","1983-02-20",98.13));
        passengerList.add(new Passenger("Ewan","Pearl","2017-11-15",97.19));
        passengerList.add(new Passenger("Farris","Noddings","2006-12-24",31.7));
        passengerList.add( new Passenger("Bethina","Eburne","2015-12-25",3.76));
    }
    @Test
    public void parseTest() {
        List<Passenger> expected = passengerList;
        List<Passenger> actual = new ArrayList<Passenger>();

        CsvUtility.setHeaders(new String[]{"first_name", "last_name", "date_of_birth", "weight"});
        CsvUtility.parseCsv("target/classes/csv_data/PassengersBasic.csv");
        for(CSVRecord record: CsvUtility.getRecords())
        {
            Passenger passenger = new Passenger(record.get("first_name"), record.get("last_name"), LocalDate.parse(record.get("date_of_birth")), Double.parseDouble(record.get("weight")));
            actual.add(passenger);
        }

        for(int i = 0; i < expected.size(); i++)
        {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
        }
    }
}
