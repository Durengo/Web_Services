package lt.viko.eif.stermen.project1.domain.airport;

import jakarta.xml.bind.annotation.XmlEnum;

/**
 * This enum has 3 simple options to depict the size of the airport.
 * The object also has XML attributes for marshalling and unmarshalling.
 */
@XmlEnum(String.class)
public enum AirportSize {
    SmallAirport(100, 2), MediumAirport(1000, 10), LargeAirport(10000, 100);
    private final int peopleAmount;
    private final int airplaneAmount;

    /**
     * The enums represent 2 values: the amount of people the airport can hold, and the amount of airplanes an airport can hold.
     *
     * @param people    maximum amount of people.
     * @param airplanes maximum amount of airplanes.
     */
    AirportSize(int people, int airplanes) {
        peopleAmount = people;
        airplaneAmount = airplanes;
    }

    public int getPeopleAmount() {
        return peopleAmount;
    }

    public int getAirplaneAmount() {
        return airplaneAmount;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all airport size data fields.
     */
    @Override
    public String toString() {
        return String.format("\n\t\tMaximum People Amount: %d;\n\t\tMaximum Airplanes Amount: %d", peopleAmount, airplaneAmount);
    }
}
