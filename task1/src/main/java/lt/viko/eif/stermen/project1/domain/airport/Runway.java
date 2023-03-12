package lt.viko.eif.stermen.project1.domain.airport;

import jakarta.xml.bind.annotation.*;

import javax.persistence.*;

/**
 * This class is part of the object tree. It will be under the Traffic Control Tower class.
 * The runway is the place where planes land and take off.
 * The runway has a unique id (for database purposes).
 * An airport can have multiple runways.
 * The dimension of the runway are denoted in meters.
 * The object also has XML attributes for marshalling and unmarshalling.
 * The object xml accessor type is set to property (getters and setters) as per Java Beans conventions.
 * The object also has an entity tag for the database mapping.
 */
@XmlType(propOrder = {"id", "length", "width"})
@XmlRootElement(name = "runway")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "runway")
public class Runway {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;
    private double length;
    private double width;

    /**
     * An empty default constructor for JAXB transformations.
     */
    public Runway() {
    }

    /**
     * This constructor should be avoided.
     *
     * @param id     the id of the runway. Should not be set, because the whole object tree ID's have GenerationType set to AUTO.
     * @param length the length of the runway in meters.
     * @param width  the width of the runway in meters.
     */
    public Runway(int id, double length, double width) {
        this.id = id;
        this.length = length;
        this.width = width;
    }

    /**
     * @param length the length of the runway in meters.
     * @param width  the width of the runway in meters.
     */
    public Runway(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public int getId() {
        return id;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param id the id of the runway.
     */
    @XmlElement(name = "runway_id")
    public void setId(int id) {
        this.id = id;
    }

    public double getLength() {
        return length;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param length the length of the runway in meters.
     */
    @XmlElement(name = "runway_length")
    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param width the width of the runway in meters.
     */
    @XmlElement(name = "runway_width")
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all runway data fields.
     */
    @Override
    public String toString() {
        return String.format("\t\t\tRunway\n\t\t\t\tID: %d;\n\t\t\t\tLength: %f;\n\t\t\t\tWidth: %f;", this.id, this.length, this.width);
    }
}
