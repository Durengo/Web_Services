package lt.viko.eif.stermen.project1.domain.people;

import javax.persistence.*;

import jakarta.xml.bind.annotation.*;

/**
 * This class is part of the object tree. It will be under the Passenger or CrewMember classes.
 * The luggage is the personal effects of a person.
 * The luggage has a unique id (for database purposes).
 * The contents are the description or a list of items that are stored within this luggage.
 * The weight is how much the luggage weights, depicted in kg.
 * The object also has XML attributes for marshalling and unmarshalling.
 * The object xml accessor type is set to property (getters and setters) as per Java Beans conventions.
 * The object also has an entity tag for the database mapping.
 */
@XmlType(propOrder = {"id", "contents", "weight"})
@XmlRootElement(name = "Luggage")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "Luggage")
public class Luggage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String contents;
    private double weight;

    /**
     * An empty default constructor for JAXB transformations.
     */
    public Luggage() {
    }

    /**
     * This constructor should be avoided.
     *
     * @param id       the id of the luggage.
     * @param contents the description of the luggage.
     * @param weight   the weight of the luggage, denoted in kg.
     */
    public Luggage(int id, String contents, double weight) {
        this.id = id;
        this.contents = contents;
        this.weight = weight;
    }

    /**
     * @param contents the description of the luggage.
     * @param weight   the weight of the luggage, denoted in kg.
     */
    public Luggage(String contents, double weight) {
        this.contents = contents;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param id the id of the luggage.
     */
    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param contents the description of the luggage.
     */
    @XmlElement(name = "contents")
    public void setContents(String contents) {
        this.contents = contents;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param weight the weight of the luggage, denoted in kg.
     */
    @XmlElement(name = "weight")
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all luggage data fields.
     */
    @Override
    public String toString() {
        return String.format("Luggage:\n\t\t\t\t\tID: %d;\n\t\t\t\t\tContents: %s;\n\t\t\t\t\tWeight: %.2f;", this.id, this.contents, this.weight);
    }
}
