package lt.viko.eif.stermen.project1.domain.airplane;

import jakarta.xml.bind.annotation.XmlEnum;

/**
 * This enum has 3 simple options to select the type of airplane.
 * The object also has XML attributes for marshalling and unmarshalling.
 */
@XmlEnum(String.class)
public enum AirplaneType {
    /**
     * Based on Phenom 300E
     */
    LightJet("Light Jet"),
    /**
     * Based on Cessna Citation X
     */
    MidSizeJet("Mid Size Jet"),
    /**
     * Based on Boeing 747-8
     */
    JumboJet("Jumbo Jet");
    private final String string;

    /**
     * The enums holds the name of the airplane.
     * @param name name of enum.
     */
    AirplaneType(String name) {
        string = name;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     * @return String all airplane type data fields.
     */
    @Override
    public String toString() {
        return string;
    }
};
