package lt.viko.eif.stermen.project1.domain.people;

import javax.persistence.*;

import jakarta.xml.bind.annotation.*;

/**
 *
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
     *
     */
    public Luggage() {
    }

    /**
     * @param id
     * @param contents
     * @param weight
     */
    public Luggage(int id, String contents, double weight) {
        this.id = id;
        this.contents = contents;
        this.weight = weight;
    }

    public Luggage(String contents, double weight) {
        this.contents = contents;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    /**
     * @param contents
     */
    @XmlElement(name = "contents")
    public void setContents(String contents) {
        this.contents = contents;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * @param weight
     */
    @XmlElement(name = "weight")
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.format("Luggage:\n\t\t\t\t\tID: %d;\n\t\t\t\t\tContents: %s;\n\t\t\t\t\tWeight: %.2f;", this.id, this.contents, this.weight);
    }
}
