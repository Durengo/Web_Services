package lt.viko.eif.stermen.project1.domain.airport;

import javax.persistence.*;

import jakarta.xml.bind.annotation.*;
import lt.viko.eif.stermen.project1.domain.airplane.Airplane;
import lt.viko.eif.stermen.project1.domain.people.ControlTowerOperator;
import lt.viko.eif.stermen.project1.domain.people.CrewMember;
import lt.viko.eif.stermen.project1.domain.people.Passenger;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the root class of the domain model.
 * The airport consists of the following data fields.
 * The airport has a unique id (for database purposes).
 * Name represents the name of the airport.
 * Size represents the amount of passengers an airport can hold as well as the amount of planes that it can hold.
 * The latitude is the location of the airport - measured distance north or south of the equator.
 * The longitude is the location of the airport - measured distance east or west of the prime meridian.
 * The country is the location of the airport - airport location on a political map.
 * The city is the location of the airport - airport location within the resided country.
 * The traffic control tower is the tower which monitors all airplane activities, defines airspace, manages runways, and logs telemetry.
 * The airport also has a list of airplanes, which holds all other objects - like passengers, crew members, etc.
 * The object also has XML attributes for marshalling and unmarshalling.
 * The object xml accessor type is set to property (getters and setters) as per Java Beans conventions.
 * The object also has an entity tag for the database mapping.
 * All OneToOne and OneToMany relationships are cascaded.
 * All OneToOne and OneToMany relationships have LazyCollectionOption off so when objects are retrieved from the database, they can be instantiated.
 */
@XmlType(propOrder = {"id", "name", "size", "latitude", "longitude", "country", "city", "trafficControlTower", "airplaneList"})
@XmlRootElement(name = "airport")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String name;
    private AirportSize size;
    private double latitude;
    private double longitude;
    private String country;
    private String city;
    @OneToOne(targetEntity = TrafficControlTower.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private TrafficControlTower trafficControlTower;
    @OneToMany(targetEntity = Airplane.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Airplane> airplaneList = new ArrayList<Airplane>();

    /**
     * An empty default constructor for JAXB transformations.
     */
    public Airport() {
    }

    /**
     * This constructor should be avoided.
     *
     * @param id                  the id of the airport. Should not be set, because the whole object tree ID's have GenerationType set to AUTO.
     * @param name                the name of the airport.
     * @param size                the size of the airport as enum.
     * @param latitude            the latitude location of the airport.
     * @param longitude           the longitude location of the airport.
     * @param country             the country location of the airport.
     * @param city                the city location of the airport.
     * @param trafficControlTower the traffic control tower object of the airport.
     * @param airplaneList        the airplane list of the airport.
     */
    public Airport(int id, String name, AirportSize size, double latitude, double longitude, String country, String city, TrafficControlTower trafficControlTower, List<Airplane> airplaneList) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.trafficControlTower = trafficControlTower;
        this.airplaneList = airplaneList;
    }

    /**
     * This constructor should be avoided.
     *
     * @param id        the id of the airport. Should not be set, because the whole object tree ID's have GenerationType set to AUTO.
     * @param name      the name of the airport.
     * @param size      the size of the airport as enum.
     * @param latitude  the latitude location of the airport.
     * @param longitude the longitude location of the airport.
     * @param country   the country location of the airport.
     * @param city      the city location of the airport.
     */
    public Airport(int id, String name, AirportSize size, double latitude, double longitude, String country, String city) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
    }

    /**
     * @param name      the name of the airport.
     * @param size      the size of the airport as enum.
     * @param latitude  the latitude location of the airport.
     * @param longitude the longitude location of the airport.
     * @param country   the country location of the airport.
     * @param city      the city location of the airport.
     */
    public Airport(String name, AirportSize size, double latitude, double longitude, String country, String city) {
        this.name = name;
        this.size = size;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param id the airport id.
     */
    @XmlElement(name = "airport_id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param name the airport name.
     */
    @XmlElement(name = "airport_name")
    public void setName(String name) {
        this.name = name;
    }

    public AirportSize getSize() {
        return size;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param size the airport size as enum.
     */
    @XmlElement(name = "airport_size")
    public void setSize(AirportSize size) {
        this.size = size;
    }

    public double getLatitude() {
        return latitude;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param latitude the airport location in latitude.
     */
    @XmlElement(name = "airport_latitude")
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param longitude the airport location in longitude.
     */
    @XmlElement(name = "airport_longitude")
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param country the airport location.
     */
    @XmlElement(name = "airport_country")
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param city the airport location.
     */
    @XmlElement(name = "airport_city")
    public void setCity(String city) {
        this.city = city;
    }

    public TrafficControlTower getTrafficControlTower() {
        return trafficControlTower;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param trafficControlTower the airport traffic control tower.
     */
    @XmlElement(name = "airport_traffic_control_tower")
    public void setTrafficControlTower(TrafficControlTower trafficControlTower) {
        this.trafficControlTower = trafficControlTower;
    }

    public List<Airplane> getAirplaneList() {
        return airplaneList;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param airplaneList the list of airplanes in the airport.
     */
    @XmlElement(name = "airplane")
    @XmlElementWrapper(name = "airplanes")
    public void setAirplaneList(List<Airplane> airplaneList) {
        this.airplaneList = airplaneList;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all airport data fields.
     */
    @Override
    public String toString() {
        String planes = new String("\tAirplanes:\n");
        for (int i = 0; i < airplaneList.size(); i++) {
            planes += airplaneList.get(i).toString();
            if (i != airplaneList.size() - 1) {
                planes += "\n";
            }
        }
        return String.format("Airport:\n\tID: %d;\n\tName: %s;\n\tSize: %s;" + "\n\tLatitude: %f;\n\tLongitude: %f;\n\tCountry: %s;\n\tCity: %s;\n%s\n%s", this.id, this.name, this.size, this.latitude, this.longitude, this.country, this.city, trafficControlTower, planes);
    }

    /**
     * Instead of getting the airplane list, simply use this method to add plane.
     *
     * @param plane
     */
    public void addAirplane(Airplane plane) {
        airplaneList.add(plane);
    }

    /**
     * When retrieving an object from the database or a file or generating it, the ID's of the object tree must be set to 0.
     * This is done because the database uses GenerationType AUTO for all object tree ID's.
     */
    public void resetIds() {
        this.id = 0;
        for (Airplane plane : airplaneList) {
            plane.setId(0);
            trafficControlTower.setId(0);
            trafficControlTower.getAirspace().setId(0);
            for (Runway runway : trafficControlTower.getRunwayList()) {
                runway.setId(0);
            }
            for (Telemetry telemetry : trafficControlTower.getTelemetryList()) {
                telemetry.setId(0);
            }
            for (ControlTowerOperator controlTowerOperator : trafficControlTower.getControlTowerOperatorList()) {
                controlTowerOperator.setId(0);
            }
            for (Passenger pass : plane.getPassengerList()) {
                pass.setId(0);
                if (pass.getLuggage() != null) {
                    pass.getLuggage().setId(0);
                }
                if (pass.getTicket() != null) {
                    pass.getTicket().setId(0);
                }
            }
            for (CrewMember crew : plane.getCrewList()) {
                crew.setId(0);
                if (crew.getLuggage() != null) {
                    crew.getLuggage().setId(0);
                }
            }
        }
    }
}
