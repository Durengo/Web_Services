package lt.viko.eif.stermen.project1.domain.airport;

import jakarta.xml.bind.annotation.XmlEnum;

/**
 *
 */
@XmlEnum(String.class)
public enum AirportSize {
    SmallAirport(100, 2),
    MediumAirport(1000, 10),
    LargeAirport(10000, 100);
    private final int peopleAmount;
    private final int airplaneAmount;

    /**
     *
     * @param people
     * @param airplanes
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
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("\n\t\tMaximum People Amount: %d;\n\t\tMaximum Airplanes Amount: %d", peopleAmount, airplaneAmount);
    }
}
