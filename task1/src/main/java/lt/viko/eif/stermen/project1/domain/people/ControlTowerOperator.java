package lt.viko.eif.stermen.project1.domain.people;

import jakarta.xml.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * This class is part of the object tree. It will be under the TrafficControlOperator class.
 * This class extends the Person class.
 * The control tower operator class holds all super (person) data fields.
 * The object also has XML attributes for marshalling and unmarshalling.
 * The object xml accessor type is set to property (getters and setters) as per Java Beans conventions.
 * The object also has XmlSeeAlso for marshalling/unmarshalling inheritance mapping.
 * The object also has an entity tag for the database mapping.
 */
@XmlRootElement(name = "control_tower_operator")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlSeeAlso(Person.class)
@Entity
@Table(name = "control_tower_operator")
public class ControlTowerOperator extends Person {
    /**
     * An empty default constructor for JAXB transformations.
     */
    public ControlTowerOperator() {
    }

    /**
     * This constructor should be avoided.
     * <p>
     * Here the dateOfBirth can be provided as LocalDate.
     * Age is calculated automatically.
     *
     * @param id          the id of the person.
     * @param name        the name of the person.
     * @param surname     the surname of the person.
     * @param dateOfBirth the date on which the person has been born.
     * @param weight      the weight of the person.
     */
    public ControlTowerOperator(int id, String name, String surname, LocalDate dateOfBirth, double weight) {
        super(id, name, surname, dateOfBirth, weight);
    }

    /**
     * This constructor should be avoided.
     * <p>
     * Here the dateOfBirth can be provided as String and if valid will be parsed into LocalDate.
     * Age is calculated automatically.
     *
     * @param id          the id of the person.
     * @param name        the name of the person.
     * @param surname     the surname of the person.
     * @param dateOfBirth the date on which the person has been born.
     * @param weight      the weight of the person.
     */
    public ControlTowerOperator(int id, String name, String surname, String dateOfBirth, double weight) {
        super(id, name, surname, dateOfBirth, weight);
    }

    /**
     * Here the dateOfBirth can be provided as LocalDate.
     * Age is calculated automatically.
     *
     * @param name        the name of the person.
     * @param surname     the surname of the person.
     * @param dateOfBirth the date on which the person has been born.
     * @param weight      the weight of the person.
     */
    public ControlTowerOperator(String name, String surname, LocalDate dateOfBirth, double weight) {
        super(name, surname, dateOfBirth, weight);
    }

    /**
     * Here the dateOfBirth can be provided as String and if valid will be parsed into LocalDate.
     * Age is calculated automatically.
     *
     * @param name        the name of the person.
     * @param surname     the surname of the person.
     * @param dateOfBirth the date on which the person has been born.
     * @param weight      the weight of the person.
     */
    public ControlTowerOperator(String name, String surname, String dateOfBirth, double weight) {
        super(name, surname, dateOfBirth, weight);
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all control tower operator and person data fields.
     */
    @Override
    public String toString() {
        return String.format("\t\t\tControl Tower Operator:\n\t\t\t\tID: %d;\n\t\t\t\tName: %s;\n\t\t\t\tSurname: %s;" + "\n\t\t\t\tDate Of Birth: %s;\n\t\t\t\tAge: %d;\n\t\t\t\tWeight: %.2f;", super.getId(), super.getName(), super.getSurname(), super.getDateOfBirth().toString(), super.getAge(), super.getWeight());
    }
}
