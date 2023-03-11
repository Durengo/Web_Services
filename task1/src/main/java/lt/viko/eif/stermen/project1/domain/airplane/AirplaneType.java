package lt.viko.eif.stermen.project1.domain.airplane;

import jakarta.xml.bind.annotation.XmlEnum;

/**
 *
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
     *
     * @param name
     */
    AirplaneType(String name) {
        string = name;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return string;
    }
};
