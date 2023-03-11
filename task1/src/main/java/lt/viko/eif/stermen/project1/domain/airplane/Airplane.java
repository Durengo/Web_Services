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
 *
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
     *
     */
    public Airplane() {
    }

    /**
     *
     * @param id
     * @param destinationAirport
     * @param name
     * @param type
     * @param passengerList
     * @param maxDistance
     * @param baseWeight
     * @param currentWeight
     * @param maxFuel
     * @param currentFuel
     * @param maxPassengers
     * @param currentPassengers
     * @param maxSpeed
     * @param averageFuelConsumption
     */
    public Airplane(int id, String destinationAirport, String name, AirplaneType type, List<Passenger> passengerList, double maxDistance, double baseWeight, double currentWeight, double maxFuel, double currentFuel, int maxPassengers, int currentPassengers, double maxSpeed, double averageFuelConsumption) {
        this.id = id;
        this.destinationAirport = destinationAirport;
        this.name = name;
        this.type = type;
        this.passengerList = passengerList;
        this.maxDistance = maxDistance;
        this.baseWeight = baseWeight;
        this.currentWeight = currentWeight;
        this.maxFuel = maxFuel;
        this.currentFuel = currentFuel;
        this.maxPassengers = maxPassengers;
        this.currentPassengers = currentPassengers;
        this.maxSpeed = maxSpeed;
        this.averageFuelConsumption = averageFuelConsumption;
    }

    public Airplane(String name, AirplaneType type) {
        this.name = name;
        this.type = type;
        checkAirplaneType(this.type);
    }

    public Airplane(AirplaneType type) {
        this.type = type;
        this.name = type.toString();
        checkAirplaneType(this.type);
    }

    public Airplane(AirplaneType type, String destinationAirport) {
        this.type = type;
        this.name = type.toString();
        this.destinationAirport = destinationAirport;
        checkAirplaneType(this.type);
    }

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
     * @param id
     */
    @XmlElement(name = "airplane_id")
    public void setId(int id) {
        this.id = id;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * @param destinationAirport
     */
    @XmlElement(name = "destination_airport")
    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    @XmlElement(name = "airplane_name")
    public void setName(String name) {
        this.name = name;
    }

    public AirplaneType getType() {
        return type;
    }

    /**
     * @param type
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
     * @param maxDistance
     */
    @XmlElement(name = "maximum_distance")
    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public double getBaseWeight() {
        return baseWeight;
    }

    /**
     * @param baseWeight
     */
    @XmlElement(name = "base_weight")
    public void setBaseWeight(double baseWeight) {
        this.baseWeight = baseWeight;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    /**
     * @param currentWeight
     */
    @XmlElement(name = "current_weight")
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    /**
     * @param maxFuel
     */
    @XmlElement(name = "maximum_fuel")
    public void setMaxFuel(double maxFuel) {
        this.maxFuel = maxFuel;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    /**
     * @param currentFuel
     */
    @XmlElement(name = "current_fuel")
    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    /**
     * @param maxPassengers
     */
    @XmlElement(name = "maximum_passengers")
    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public int getCurrentPassengers() {
        return currentPassengers;
    }

    /**
     * @param currentPassengers
     */
    @XmlElement(name = "current_passengers")
    public void setCurrentPassengers(int currentPassengers) {
        this.currentPassengers = currentPassengers;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @param maxSpeed
     */
    @XmlElement(name = "maximum_speed")
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getAverageFuelConsumption() {
        return averageFuelConsumption;
    }

    /**
     * @param averageFuelConsumption
     */
    @XmlElement(name = "average_fuel_consumption")
    public void setAverageFuelConsumption(double averageFuelConsumption) {
        this.averageFuelConsumption = averageFuelConsumption;
    }

    public List<CrewMember> getCrewList() {
        return crewList;
    }

    /**
     * @param crewList
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
     * @param passengerList
     */
    @XmlElement(name = "passenger")
    @XmlElementWrapper(name = "passengers")
    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    /**
     * @return
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
     * @param type
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
     * @param passenger
     */
    public void addPassenger(Passenger passenger) {
        passenger.getTicket().setSeatNumber(this.currentPassengers);
        passenger.getTicket().setDestination(this.destinationAirport);
        this.passengerList.add(passenger);
        this.currentPassengers++;
    }

    /**
     * @param crewMember
     */
    public void addCrewMember(CrewMember crewMember) {
        this.crewList.add(crewMember);
        this.currentPassengers++;
    }
}
