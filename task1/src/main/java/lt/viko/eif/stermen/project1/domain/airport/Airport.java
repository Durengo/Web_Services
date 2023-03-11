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
 *
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
     *
     */
    public Airport() {
    }


    /**
     * @param id
     * @param name
     * @param size
     * @param latitude
     * @param longitude
     * @param country
     * @param city
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

    @XmlElement(name = "airport_id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /**
     *
     */
    @XmlElement(name = "airport_name")
    public void setName(String name) {
        this.name = name;
    }

    public AirportSize getSize() {
        return size;
    }

    /**
     *
     */
    @XmlElement(name = "airport_size")
    public void setSize(AirportSize size) {
        this.size = size;
    }

    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    @XmlElement(name = "airport_latitude")
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    @XmlElement(name = "airport_longitude")
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    @XmlElement(name = "airport_country")
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    @XmlElement(name = "airport_city")
    public void setCity(String city) {
        this.city = city;
    }

    public TrafficControlTower getTrafficControlTower() {
        return trafficControlTower;
    }

    /**
     * @param trafficControlTower
     */
    @XmlElement(name = "airport_traffic_control_tower")
    public void setTrafficControlTower(TrafficControlTower trafficControlTower) {
        this.trafficControlTower = trafficControlTower;
    }

    public List<Airplane> getAirplaneList() {
        return airplaneList;
    }

    /**
     * @param airplaneList
     */
    @XmlElement(name = "airplane")
    @XmlElementWrapper(name = "airplanes")
    public void setAirplaneList(List<Airplane> airplaneList) {
        this.airplaneList = airplaneList;
    }

    /**
     * @return String
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
     * @param plane
     */
    public void addAirplane(Airplane plane) {
        airplaneList.add(plane);
    }

    /**
     *
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
