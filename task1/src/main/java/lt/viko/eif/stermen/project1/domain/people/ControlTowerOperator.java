package lt.viko.eif.stermen.project1.domain.people;

import jakarta.xml.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 *
 */
@XmlRootElement(name = "control_tower_operator")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlSeeAlso(Person.class)
@Entity
@Table(name = "control_tower_operator")
public class ControlTowerOperator extends Person {
    /**
     *
     */
    public ControlTowerOperator() {
    }

    /**
     * @param name
     * @param surname
     * @param dateOfBirth
     * @param weight
     */
    public ControlTowerOperator(String name, String surname, LocalDate dateOfBirth, double weight) {
        super(name, surname, dateOfBirth, weight);
    }

    /**
     * @param name
     * @param surname
     * @param dateOfBirth
     * @param weight
     */
    public ControlTowerOperator(String name, String surname, String dateOfBirth, double weight) {
        super(name, surname, dateOfBirth, weight);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.format("\t\t\tControl Tower Operator:\n\t\t\t\tID: %d;\n\t\t\t\tName: %s;\n\t\t\t\tSurname: %s;" + "\n\t\t\t\tDate Of Birth: %s;\n\t\t\t\tAge: %d;\n\t\t\t\tWeight: %.2f;", super.getId(), super.getName(), super.getSurname(), super.getDateOfBirth().toString(), super.getAge(), super.getWeight());
    }
}
