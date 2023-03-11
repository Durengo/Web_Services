package lt.viko.eif.stermen.project1.domain.airport;

import jakarta.xml.bind.annotation.*;

import javax.persistence.*;

/**
 *
 */
@XmlType(propOrder = {"id", "radius", "airspaceClass", "airplanesInVicinity"})
@XmlRootElement(name = "airspace")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "airspace")
public class Airspace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private double radius;
    private AirspaceClass airspaceClass;
    private int airplanesInVicinity;

    /**
     *
     */
    public Airspace() {
    }

    /**
     * @param id
     * @param radius
     * @param airspaceClass
     * @param airplanesInVicinity
     */
    public Airspace(int id, double radius, AirspaceClass airspaceClass, int airplanesInVicinity) {
        this.id = id;
        this.radius = radius;
        this.airspaceClass = airspaceClass;
        this.airplanesInVicinity = airplanesInVicinity;
    }

    public Airspace(double radius, AirspaceClass airspaceClass, int airplanesInVicinity) {
        this.radius = radius;
        this.airspaceClass = airspaceClass;
        this.airplanesInVicinity = airplanesInVicinity;
    }

    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    @XmlElement(name = "airspace_id")
    public void setId(int id) {
        this.id = id;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * @param radius
     */
    @XmlElement(name = "airspace_radius")
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public AirspaceClass getAirspaceClass() {
        return airspaceClass;
    }

    /**
     * @param airspaceClass
     */
    @XmlElement(name = "airspace_airspace_class")
    public void setAirspaceClass(AirspaceClass airspaceClass) {
        this.airspaceClass = airspaceClass;
    }

    public int getAirplanesInVicinity() {
        return airplanesInVicinity;
    }

    /**
     * @param airplanesInVicinity
     */
    @XmlElement(name = "airspace_airplanes_in_vicinity")
    public void setAirplanesInVicinity(int airplanesInVicinity) {
        this.airplanesInVicinity = airplanesInVicinity;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.format("Airspace:\n\t\t\tID: %d;\n\t\t\tRadius: %s;%s;\n\t\t\tAirplanes In Vicinity: %d;",
                this.id, this.radius, this.airspaceClass, this.airplanesInVicinity);
    }
}
