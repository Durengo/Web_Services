package lt.viko.eif.stermen.project1.domain.people;

import jakarta.xml.bind.annotation.XmlEnum;

/**
 *
 */
@XmlEnum(String.class)
public enum CrewMemberType {
    Pilot("Pilot"),
    Stewardess("Stewardess");
    private final String string;

    /**
     * @param name
     */
    CrewMemberType(String name) {
        string = name;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return string;
    }
}
