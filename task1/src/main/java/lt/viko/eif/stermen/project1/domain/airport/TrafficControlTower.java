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
 *
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
     *
     */
    public TrafficControlTower() {
    }

    /**
     * @param id
     * @param airspace
     * @param runwayList
     * @param telemetryList
     * @param controlTowerOperatorList
     */
    public TrafficControlTower(int id, Airspace airspace, List<Runway> runwayList, List<Telemetry> telemetryList, List<ControlTowerOperator> controlTowerOperatorList) {
        this.id = id;
        this.airspace = airspace;
        this.runwayList = runwayList;
        this.telemetryList = telemetryList;
        this.controlTowerOperatorList = controlTowerOperatorList;
    }

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
     * @param id
     */
    @XmlElement(name = "traffic_control_tower_id")
    public void setId(int id) {
        this.id = id;
    }

    public Airspace getAirspace() {
        return airspace;
    }

    /**
     * @param airspace
     */
    @XmlElement(name = "traffic_control_tower_airspace")
    public void setAirspace(Airspace airspace) {
        this.airspace = airspace;
    }

    public List<Runway> getRunwayList() {
        return runwayList;
    }

    /**
     * @param runwayList
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
     * @param telemetryList
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
     * @param controlTowerOperatorList
     */
    @XmlElement(name = "traffic_control_tower_control_tower_operator")
    @XmlElementWrapper(name = "traffic_control_tower_control_tower_operators")
    public void setControlTowerOperatorList(List<ControlTowerOperator> controlTowerOperatorList) {
        this.controlTowerOperatorList = controlTowerOperatorList;
    }

    /**
     * @return
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
