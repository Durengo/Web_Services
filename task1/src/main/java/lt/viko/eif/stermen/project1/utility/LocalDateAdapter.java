package lt.viko.eif.stermen.project1.utility;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

/**
 * And XML adapter class that is used for marshalling and unmarshalling LocalDate variables.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    /**
     * @param v the date as a String.
     * @return parsed String to LocalDate.
     * @throws Exception failed to parse String.
     */
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    /**
     * @param v the date as a LocalDate.
     * @return parsed LocalDate as String.
     * @throws Exception failed to parse LocalDate.
     */
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}