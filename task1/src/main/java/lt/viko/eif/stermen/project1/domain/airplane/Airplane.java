package lt.viko.eif.stermen.project1.domain.airplane;

import javax.persistence.*;

import jakarta.xml.bind.annotation.*;
import lt.viko.eif.stermen.project1.domain.people.CrewMember;
import lt.viko.eif.stermen.project1.domain.people.Passenger;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is part of the object tree. It will be under the Airport class.
 * The airplane is the main point of an airport.
 * The airplane carries passengers and the crew to another airport.
 * The airplane has a unique id (for database purposes).
 * The destination airport designates the destination of the airport the airplane has to fly to.
 * The name is the name of the airplane.
 * The type is the type of airplane that defines all the characteristics (maxDistance, baseWeight, currentWeight, maxFuel, currentFuel, maxPassengers, currentPassengers, maxSpeed, averageFuelConsumption) of a plane.
 * The maxDistance is the maximum distance an airplane can fly in ideal conditions (empty cargo, full fuel tanks, excellent weather conditions), denoted in kilometers.
 * The baseWeight is the weight of the airplane when it has no passengers on board and empty fuel tanks, denoted in kilograms.
 * The currentWeight is the current weight with passenger weight and fuel amount, denoted in kilograms.
 * The maxFuel is the maximum amount of fuel an airplane can store, denoted in cubical meters.
 * The currentFuel is the amount of fuel that is currently store in an airplane, denoted in cubical meters.
 * The maxPassengers is the amount of passengers an airplane can hold (including crew members), denoted in people.
 * The currentPassengers is the amount of passengers currently present on an airplane (including crew members), denoted in people.
 * The maxSpeed is the maximum speed an airplane can technically reach, denoted in km/h.
 * The averageFuelConsumption is the amount of fuel that is consumed on average by an airplane when in flight, regardless of the conditions.
 * The airplane also holds a list of crew members.
 * The airplane also holds a list of passengers.
 * The object also has XML attributes for marshalling and unmarshalling.
 * The object xml accessor type is set to property (getters and setters) as per Java Beans conventions.
 * The object also has an entity tag for the database mapping.
 * All OneToOne and OneToMany relationships are cascaded.
 * All OneToOne and OneToMany relationships have LazyCollectionOption off so when objects are retrieved from the database, they can be instantiated.
 */
@XmlType(propOrder = {"id", "destinationAirport", "name", "type", "maxDistance", "baseWeight", "currentWeight", "maxFuel", "currentFuel", "maxPassengers", "currentPassengers", "maxSpeed", "averageFuelConsumption", "crewList", "passengerList"})
@XmlRootElement(name = "airplane")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "airplane")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String destinationAirport;
    private String name;
    private AirplaneType type;
    private double maxDistance;
    private double baseWeight;
    private double currentWeight;
    private double maxFuel;
    private double currentFuel;
    private int maxPassengers;
    private int currentPassengers;
    private double maxSpeed;
    private double averageFuelConsumption;
    @OneToMany(targetEntity = CrewMember.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CrewMember> crewList = new ArrayList<CrewMember>();
    @OneToMany(targetEntity = Passenger.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Passenger> passengerList = new ArrayList<Passenger>();

    /**
     * An empty default constructor for JAXB transformations.
     */
    public Airplane() {
    }

    /**
     * This constructor should be avoided.
     *
     * @param id                     the id of the airplane. Should not be set, because the whole object tree ID's have GenerationType set to AUTO.
     * @param destinationAirport     designates the destination of the airport the airplane has to fly to.
     * @param name                   the name of the airplane.
     * @param maxDistance            the maximum distance an airplane can fly in ideal conditions (empty cargo, full fuel tanks, excellent weather conditions), denoted in kilometers.
     * @param baseWeight             the weight of the airplane when it has no passengers on board and empty fuel tanks, denoted in kilograms.
     * @param currentWeight          the current weight with passenger weight and fuel amount, denoted in kilograms.
     * @param maxFuel                the maximum amount of fuel an airplane can store, denoted in cubical meters.
     * @param currentFuel            the amount of fuel that is currently store in an airplane, denoted in cubical meters.
     * @param maxPassengers          the amount of passengers an airplane can hold (including crew members), denoted in people.
     * @param currentPassengers      the amount of passengers currently present on an airplane (including crew members), denoted in people.
     * @param maxSpeed               the maximum speed an airplane can technically reach, denoted in km/h.
     * @param averageFuelConsumption the amount of fuel that is consumed on average by an airplane when in flight, regardless of the conditions.
     * @param crewList               the list of crew members.
     * @param passengerList          the list of passengers.
     */
    public Airplane(int id, String destinationAirport, String name, double maxDistance, double baseWeight, double currentWeight, double maxFuel, double currentFuel, int maxPassengers, int currentPassengers, double maxSpeed, double averageFuelConsumption, List<CrewMember> crewList, List<Passenger> passengerList) {
        this.id = id;
        this.destinationAirport = destinationAirport;
        this.name = name;
        this.maxDistance = maxDistance;
        this.baseWeight = baseWeight;
        this.currentWeight = currentWeight;
        this.maxFuel = maxFuel;
        this.currentFuel = currentFuel;
        this.maxPassengers = maxPassengers;
        this.currentPassengers = currentPassengers;
        this.maxSpeed = maxSpeed;
        this.averageFuelConsumption = averageFuelConsumption;
        this.crewList = crewList;
        this.passengerList = passengerList;
    }

    /**
     * This constructor should always be used.
     *
     * @param type type of airplane that defines all the characteristics (maxDistance, baseWeight, currentWeight, maxFuel, currentFuel, maxPassengers, currentPassengers, maxSpeed, averageFuelConsumption) of a plane.
     */
    public Airplane(AirplaneType type) {
        this.type = type;
        this.name = type.toString();
        checkAirplaneType(this.type);
    }

    /**
     * @param name the name of the airplane.
     * @param type type of airplane that defines all the characteristics (maxDistance, baseWeight, currentWeight, maxFuel, currentFuel, maxPassengers, currentPassengers, maxSpeed, averageFuelConsumption) of a plane.
     */
    public Airplane(String name, AirplaneType type) {
        this.name = name;
        this.type = type;
        checkAirplaneType(this.type);
    }


    /**
     * @param type               type of airplane that defines all the characteristics (maxDistance, baseWeight, currentWeight, maxFuel, currentFuel, maxPassengers, currentPassengers, maxSpeed, averageFuelConsumption) of a plane.
     * @param destinationAirport designates the destination of the airport the airplane has to fly to.
     */
    public Airplane(AirplaneType type, String destinationAirport) {
        this.type = type;
        this.name = type.toString();
        this.destinationAirport = destinationAirport;
        checkAirplaneType(this.type);
    }

    /**
     * @param name               the name of the airplane.
     * @param type               type of airplane that defines all the characteristics (maxDistance, baseWeight, currentWeight, maxFuel, currentFuel, maxPassengers, currentPassengers, maxSpeed, averageFuelConsumption) of a plane.
     * @param destinationAirport designates the destination of the airport the airplane has to fly to.
     */
    public Airplane(String name, AirplaneType type, String destinationAirport) {
        this.name = name;
        this.type = type;
        this.destinationAirport = destinationAirport;
        checkAirplaneType(this.type);
    }

    public int getId() {
        return id;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param id the id of the airplane.
     */
    @XmlElement(name = "airplane_id")
    public void setId(int id) {
        this.id = id;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param destinationAirport designates the destination of the airport the airplane has to fly to.
     */
    @XmlElement(name = "destination_airport")
    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getName() {
        return name;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param name the name of the airplane.
     */
    @XmlElement(name = "airplane_name")
    public void setName(String name) {
        this.name = name;
    }

    public AirplaneType getType() {
        return type;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param type type of airplane that defines all the characteristics (maxDistance, baseWeight, currentWeight, maxFuel, currentFuel, maxPassengers, currentPassengers, maxSpeed, averageFuelConsumption) of a plane.
     */
    @XmlElement(name = "airplane_type")
    public void setType(AirplaneType type) {
        this.type = type;
        checkAirplaneType(this.type);
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param maxDistance the maximum distance an airplane can fly in ideal conditions (empty cargo, full fuel tanks, excellent weather conditions), denoted in kilometers.
     */
    @XmlElement(name = "maximum_distance")
    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public double getBaseWeight() {
        return baseWeight;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param baseWeight the weight of the airplane when it has no passengers on board and empty fuel tanks, denoted in kilograms.
     */
    @XmlElement(name = "base_weight")
    public void setBaseWeight(double baseWeight) {
        this.baseWeight = baseWeight;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param currentWeight the current weight with passenger weight and fuel amount, denoted in kilograms.
     */
    @XmlElement(name = "current_weight")
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param maxFuel the maximum amount of fuel an airplane can store, denoted in cubical meters.
     */
    @XmlElement(name = "maximum_fuel")
    public void setMaxFuel(double maxFuel) {
        this.maxFuel = maxFuel;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param currentFuel the amount of fuel that is currently store in an airplane, denoted in cubical meters.
     */
    @XmlElement(name = "current_fuel")
    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param maxPassengers the amount of passengers an airplane can hold (including crew members), denoted in people.
     */
    @XmlElement(name = "maximum_passengers")
    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public int getCurrentPassengers() {
        return currentPassengers;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param currentPassengers the amount of passengers currently present on an airplane (including crew members), denoted in people.
     */
    @XmlElement(name = "current_passengers")
    public void setCurrentPassengers(int currentPassengers) {
        this.currentPassengers = currentPassengers;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param maxSpeed the maximum speed an airplane can technically reach, denoted in km/h.
     */
    @XmlElement(name = "maximum_speed")
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getAverageFuelConsumption() {
        return averageFuelConsumption;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param averageFuelConsumption the amount of fuel that is consumed on average by an airplane when in flight, regardless of the conditions.
     */
    @XmlElement(name = "average_fuel_consumption")
    public void setAverageFuelConsumption(double averageFuelConsumption) {
        this.averageFuelConsumption = averageFuelConsumption;
    }

    public List<CrewMember> getCrewList() {
        return crewList;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param crewList the list of crew members.
     */
    @XmlElement(name = "crew_member")
    @XmlElementWrapper(name = "crew_members")
    public void setCrewList(List<CrewMember> crewList) {
        this.crewList = crewList;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param passengerList the list of passengers.
     */
    @XmlElement(name = "passenger")
    @XmlElementWrapper(name = "passengers")
    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all airplane data fields.
     */
    @Override
    public String toString() {
        String people = new String("\t\tPassengers:\n");
        for (int i = 0; i < passengerList.size(); i++) {
            people += passengerList.get(i).toString();
            if (i != passengerList.size() - 1) {
                people += "\n";
            }
        }

        String crew = new String("\t\tCrew Members:\n");
        for (int i = 0; i < crewList.size(); i++) {
            crew += crewList.get(i).toString();
            if (i != crewList.size() - 1) {
                crew += "\n";
            }
        }
        return String.format("\t\tAirplane\n\t\t\tID: %d;\n\t\t\tDestination: %s;\n\t\t\tName: %s;\n\t\t\tType: %s;\n\t\t\tMaximum Distance: %f;" + "\n\t\t\tBase Weight: %f;\n\t\t\tCurrent Weight: %f;\n\t\t\tMaximum Fuel: %f;\n\t\t\tCurrent Fuel: %f;" + "\n\t\t\tMaximum Passengers: %d;\n\t\t\tCurrent Passengers: %d;\n\t\t\tMaximum Speed: %f;\n\t\t\tAverage Fuel Consumption: %f;\n%s\n%s", this.id, this.destinationAirport, this.name, this.type, this.maxDistance, this.baseWeight, this.currentWeight, this.maxFuel, this.currentFuel, this.maxPassengers, this.currentPassengers, this.maxSpeed, this.averageFuelConsumption, people, crew);
    }

    /**
     * Everytime an airplane type is set, checks the type, and based on the type, sets all other data fields accordingly.
     *
     * @param type enum type of airplane.
     */
    private void checkAirplaneType(AirplaneType type) {
        switch (type) {
            case LightJet:
                this.maxDistance = 3723;
                this.baseWeight = 5253;
                this.maxFuel = 2428;
                this.maxPassengers = 9;
                this.maxSpeed = 838;
                this.averageFuelConsumption = 567;
                this.currentWeight += this.baseWeight;
                break;
            case MidSizeJet:
                this.maxDistance = 5926;
                this.baseWeight = 10038;
                this.maxFuel = 5897;
                this.maxPassengers = 9;
                this.maxSpeed = 959;
                this.averageFuelConsumption = 1527;
                this.currentWeight += this.baseWeight;
                break;
            case JumboJet:
                this.maxDistance = 14430;
                this.baseWeight = 190000;
                this.maxFuel = 243400;
                this.maxPassengers = 660;
                this.maxSpeed = 920;
                this.averageFuelConsumption = 10230;
                this.currentWeight += this.baseWeight;
                break;
        }
    }

    /**
     * Instead of using get list and add method, this method should be used, as it also sets other parameters for the data to be valid.
     *
     * @param passenger passenger that will board the plane.
     */
    public void addPassenger(Passenger passenger) {
        passenger.getTicket().setSeatNumber(this.currentPassengers);
        passenger.getTicket().setDestination(this.destinationAirport);
        this.passengerList.add(passenger);
        this.currentPassengers++;
    }

    /**
     * Instead of using get list and add method, this method should be used, as it also sets other parameters for the data to be valid.
     *
     * @param crewMember crew member that will board the plane.
     */
    public void addCrewMember(CrewMember crewMember) {
        this.crewList.add(crewMember);
        this.currentPassengers++;
    }
}
