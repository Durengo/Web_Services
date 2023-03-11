package lt.viko.eif.stermen.project1.domain.airport;

import jakarta.xml.bind.annotation.XmlEnum;

/**
 *
 */
@XmlEnum(String.class)
public enum AirspaceClass {
    ClassAlpha("Alpha Class", 5486.4, 18288),
    ClassBravo("Bravo Class", 609.6, 2743.2),
    ClassCharlie("Charlie Class", 365.76, 1219.2),
    ClassDelta("Delta Class", 0.0, 365.76);
    private final String className;
    /**
     * IN MSL - ABOVE MEAN SEA LEVEL
     */
    private final double minimumCeiling;
    private final double maximumCeiling;

    /**
     * @param className
     * @param minimumCeiling
     * @param maximumCeiling
     */
    AirspaceClass(String className, double minimumCeiling, double maximumCeiling) {
        this.className = className;
        this.minimumCeiling = minimumCeiling;
        this.maximumCeiling = maximumCeiling;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.format("\n\t\t\tAirspace Class:\n\t\t\t\tType: %s\n\t\t\t\tMinimum Ceiling Height: %f;\n\t\t\t\tMaximum Ceiling Height: %f", className, minimumCeiling, maximumCeiling);
    }
}
