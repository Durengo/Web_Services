package lt.viko.eif.stermen.project1.domain.airport;

import jakarta.xml.bind.annotation.*;
import lt.viko.eif.stermen.project1.domain.airplane.Airplane;
import lt.viko.eif.stermen.project1.domain.people.ControlTowerOperator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is part of the object tree. It will be under the Airport class.
 * There could be more than one traffic control tower, but in this domain model there will only be one.
 * The traffic control tower has a unique id (for database purposes).
 * This class holds all other complex types (classes), such as: airspace, runways, telemetries, and control tower operators.
 * The object also has XML attributes for marshalling and unmarshalling.
 * The object xml accessor type is set to property (getters and setters) as per Java Beans conventions.
 * The object also has an entity tag for the database mapping.
 * All OneToOne and OneToMany relationships are cascaded.
 * All OneToOne and OneToMany relationships have LazyCollectionOption off so when objects are retrieved from the database, they can be instantiated.
 */
@XmlType(propOrder = {"id", "airspace", "runwayList", "telemetryList", "controlTowerOperatorList"})
@XmlRootElement(name = "traffic_control_tower")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "traffic_control_tower")
public class TrafficControlTower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @OneToOne(targetEntity = Airspace.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Airspace airspace;
    @OneToMany(targetEntity = Runway.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Runway> runwayList = new ArrayList<Runway>();
    @OneToMany(targetEntity = Telemetry.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Telemetry> telemetryList = new ArrayList<Telemetry>();
    @OneToMany(targetEntity = ControlTowerOperator.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ControlTowerOperator> controlTowerOperatorList = new ArrayList<ControlTowerOperator>();

    /**
     * An empty default constructor for JAXB transformations.
     */
    public TrafficControlTower() {
    }

    /**
     * This constructor should be avoided.
     *
     * @param id                       the id of the traffic control tower. Should not be set, because the whole object tree ID's have GenerationType set to AUTO.
     * @param airspace                 the airspace of the traffic control tower.
     * @param runwayList               the runways of the airport and which the traffic control tower monitors.
     * @param telemetryList            the gathered telemetry of the traffic control tower.
     * @param controlTowerOperatorList the operators that work in the traffic control tower.
     */
    public TrafficControlTower(int id, Airspace airspace, List<Runway> runwayList, List<Telemetry> telemetryList, List<ControlTowerOperator> controlTowerOperatorList) {
        this.id = id;
        this.airspace = airspace;
        this.runwayList = runwayList;
        this.telemetryList = telemetryList;
        this.controlTowerOperatorList = controlTowerOperatorList;
    }

    /**
     * @param airspace                 the airspace of the traffic control tower.
     * @param runwayList               the runways of the airport and which the traffic control tower monitors.
     * @param telemetryList            the gathered telemetry of the traffic control tower.
     * @param controlTowerOperatorList the operators that work in the traffic control tower.
     */
    public TrafficControlTower(Airspace airspace, List<Runway> runwayList, List<Telemetry> telemetryList, List<ControlTowerOperator> controlTowerOperatorList) {
        this.airspace = airspace;
        this.runwayList = runwayList;
        this.telemetryList = telemetryList;
        this.controlTowerOperatorList = controlTowerOperatorList;
    }

    public int getId() {
        return id;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param id the id of the traffic control tower.
     */
    @XmlElement(name = "traffic_control_tower_id")
    public void setId(int id) {
        this.id = id;
    }

    public Airspace getAirspace() {
        return airspace;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param airspace the airspace of the traffic control tower.
     */
    @XmlElement(name = "traffic_control_tower_airspace")
    public void setAirspace(Airspace airspace) {
        this.airspace = airspace;
    }

    public List<Runway> getRunwayList() {
        return runwayList;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param runwayList the runways that the traffic control tower monitors.
     */
    @XmlElement(name = "traffic_control_tower_runway")
    @XmlElementWrapper(name = "traffic_control_tower_runways")
    public void setRunwayList(List<Runway> runwayList) {
        this.runwayList = runwayList;
    }

    public List<Telemetry> getTelemetryList() {
        return telemetryList;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param telemetryList the telemetries gathered by the traffic control tower.
     */
    @XmlElement(name = "traffic_control_tower_telemetry")
    @XmlElementWrapper(name = "traffic_control_tower_telemetries")
    public void setTelemetryList(List<Telemetry> telemetryList) {
        this.telemetryList = telemetryList;
    }

    public List<ControlTowerOperator> getControlTowerOperatorList() {
        return controlTowerOperatorList;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param controlTowerOperatorList the control tower operators that work at the traffic control tower.
     */
    @XmlElement(name = "traffic_control_tower_control_tower_operator")
    @XmlElementWrapper(name = "traffic_control_tower_control_tower_operators")
    public void setControlTowerOperatorList(List<ControlTowerOperator> controlTowerOperatorList) {
        this.controlTowerOperatorList = controlTowerOperatorList;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all traffic control tower data fields.
     */
    @Override
    public String toString() {
        String runways = new String("Runways:\n");
        for (int i = 0; i < runwayList.size(); i++) {
            runways += runwayList.get(i).toString();
            if (i != runwayList.size() - 1) {
                runways += "\n";
            }
        }
        String telemetries = new String("Telemetries:\n");
        for (int i = 0; i < telemetryList.size(); i++) {
            telemetries += telemetryList.get(i).toString();
            if (i != telemetryList.size() - 1) {
                telemetries += "\n";
            }
        }
        String operators = new String("Operators:\n");
        for (int i = 0; i < controlTowerOperatorList.size(); i++) {
            operators += controlTowerOperatorList.get(i).toString();
            if (i != controlTowerOperatorList.size() - 1) {
                operators += "\n";
            }
        }
        return String.format("\tTraffic Control Tower:\n\t\tID: %d\n\t\t%s\n\t\t%s\n\t\t%s\n\t\t%s", this.id, this.airspace, runways, telemetries, operators);
    }
}
