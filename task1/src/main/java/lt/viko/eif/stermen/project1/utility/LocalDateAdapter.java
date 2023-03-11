package lt.viko.eif.stermen.project1.utility;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

/**
 *
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    /**
     * @param v
     * @return
     * @throws Exception
     */
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    /**
     * @param v
     * @return
     * @throws Exception
     */
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}