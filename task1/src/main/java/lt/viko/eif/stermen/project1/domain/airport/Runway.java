package lt.viko.eif.stermen.project1.domain.airport;

import jakarta.xml.bind.annotation.*;

import javax.persistence.*;

/**
 *
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
     *
     */
    public Runway() {
    }

    /**
     * @param id
     * @param length
     * @param width
     */
    public Runway(int id, double length, double width) {
        this.id = id;
        this.length = length;
        this.width = width;
    }

    public Runway(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    @XmlElement(name = "runway_id")
    public void setId(int id) {
        this.id = id;
    }

    public double getLength() {
        return length;
    }

    /**
     * @param length
     */
    @XmlElement(name = "runway_length")
    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    /**
     * @param width
     */
    @XmlElement(name = "runway_width")
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.format("\t\t\tRunway\n\t\t\t\tID: %d;\n\t\t\t\tLength: %f;\n\t\t\t\tWidth: %f;", this.id, this.length, this.width);
    }
}
