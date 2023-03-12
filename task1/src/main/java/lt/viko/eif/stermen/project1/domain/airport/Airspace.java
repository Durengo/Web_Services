package lt.viko.eif.stermen.project1.domain.airport;

import jakarta.xml.bind.annotation.*;

import javax.persistence.*;

/**
 * This class is part of the object tree. It will be under the Traffic Control Tower class.
 * The airspace has a unique id (for database purposes).
 * The airspace class shows the radius, height, and airspace class for the whole airport.
 * The radius is the average radius denoted in meters and counted from the traffic control tower.
 * The object also has XML attributes for marshalling and unmarshalling.
 * The object xml accessor type is set to property (getters and setters) as per Java Beans conventions.
 * The object also has an entity tag for the database mapping.
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
     * An empty default constructor for JAXB transformations.
     */
    public Airspace() {
    }

    /**
     * This constructor should be avoided.
     *
     * @param id                  the id of the airspace. Should not be set, because the whole object tree ID's have GenerationType set to AUTO.
     * @param radius              the radius of the airspace.
     * @param airspaceClass       the airspace class of this airspace.
     * @param airplanesInVicinity the amount of planes inside the airspace.
     */
    public Airspace(int id, double radius, AirspaceClass airspaceClass, int airplanesInVicinity) {
        this.id = id;
        this.radius = radius;
        this.airspaceClass = airspaceClass;
        this.airplanesInVicinity = airplanesInVicinity;
    }

    /**
     * @param radius              the radius of the airspace.
     * @param airspaceClass       the airspace class of this airspace.
     * @param airplanesInVicinity the amount of planes inside the airspace.
     */
    public Airspace(double radius, AirspaceClass airspaceClass, int airplanesInVicinity) {
        this.radius = radius;
        this.airspaceClass = airspaceClass;
        this.airplanesInVicinity = airplanesInVicinity;
    }

    public int getId() {
        return id;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param id the id of the airspace.
     */
    @XmlElement(name = "airspace_id")
    public void setId(int id) {
        this.id = id;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param radius the radius of the airspace.
     */
    @XmlElement(name = "airspace_radius")
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public AirspaceClass getAirspaceClass() {
        return airspaceClass;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param airspaceClass the airspace class of the airspace.
     */
    @XmlElement(name = "airspace_airspace_class")
    public void setAirspaceClass(AirspaceClass airspaceClass) {
        this.airspaceClass = airspaceClass;
    }

    public int getAirplanesInVicinity() {
        return airplanesInVicinity;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param airplanesInVicinity the amount of airplanes inside the airspace.
     */
    @XmlElement(name = "airspace_airplanes_in_vicinity")
    public void setAirplanesInVicinity(int airplanesInVicinity) {
        this.airplanesInVicinity = airplanesInVicinity;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all airspace data fields.
     */
    @Override
    public String toString() {
        return String.format("Airspace:\n\t\t\tID: %d;\n\t\t\tRadius: %s;%s;\n\t\t\tAirplanes In Vicinity: %d;", this.id, this.radius, this.airspaceClass, this.airplanesInVicinity);
    }
}
