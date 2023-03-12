package lt.viko.eif.stermen.project1.domain.airport;

import jakarta.xml.bind.annotation.XmlEnum;

/**
 * This enum has 4 simple options to depict the airspace class of an airspace.
 * The airspace class type dictates the minimum and maximum height ceilings of a particular airspace.
 * <a href="https://www.faa.gov/air_traffic/publications/atpubs/aip_html/part2_enr_section_1.4.html">FAA Reference</a>
 * The maximum and minimum height ceilings are counted from MSL - ABOVE MEAN SEA LEVEL.
 * Maximum and minimum height ceilings will be denoted in meters.
 * The object also has XML attributes for marshalling and unmarshalling.
 */
@XmlEnum(String.class)
public enum AirspaceClass {
    ClassAlpha("Alpha Class", 5486.4, 18288), ClassBravo("Bravo Class", 609.6, 2743.2), ClassCharlie("Charlie Class", 365.76, 1219.2), ClassDelta("Delta Class", 0.0, 365.76);
    private final String className;
    private final double minimumCeiling;
    private final double maximumCeiling;

    /**
     * @param className      the name of the airspace class.
     * @param minimumCeiling the minimum height ceiling of the airspace.
     * @param maximumCeiling the maximum height ceiling of the airspace.
     */
    AirspaceClass(String className, double minimumCeiling, double maximumCeiling) {
        this.className = className;
        this.minimumCeiling = minimumCeiling;
        this.maximumCeiling = maximumCeiling;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all airspace class data fields.
     */
    @Override
    public String toString() {
        return String.format("\n\t\t\tAirspace Class:\n\t\t\t\tType: %s\n\t\t\t\tMinimum Ceiling Height: %f;\n\t\t\t\tMaximum Ceiling Height: %f", className, minimumCeiling, maximumCeiling);
    }
}
