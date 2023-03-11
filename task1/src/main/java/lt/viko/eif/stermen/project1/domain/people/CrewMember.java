package lt.viko.eif.stermen.project1.domain.people;

import javax.persistence.*;

import jakarta.xml.bind.annotation.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDate;

/**
 *
 */
@XmlType(propOrder = {"type", "luggage"})
@XmlRootElement(name = "crew_member")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlSeeAlso(Person.class)
@Entity
@Table(name = "crew_member")
public class CrewMember extends Person {
    private CrewMemberType type;
    @OneToOne(targetEntity = Luggage.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Luggage luggage;

    /**
     *
     */
    public CrewMember() {
    }

    /**
     * @param name
     * @param surname
     * @param dateOfBirth
     * @param weight
     * @param type
     * @param luggage
     */
    public CrewMember(String name, String surname, String dateOfBirth, double weight, CrewMemberType type, Luggage luggage) {
        super(name, surname, dateOfBirth, weight);
        this.type = type;
        this.luggage = luggage;
    }

    /**
     * @param name
     * @param surname
     * @param dateOfBirth
     * @param weight
     */
    public CrewMember(String name, String surname, LocalDate dateOfBirth, double weight) {
        super(name, surname, dateOfBirth, weight);
    }

    public CrewMemberType getType() {
        return type;
    }

    /**
     * @param type
     */
    @XmlElement(name = "crew_member_type")
    public void setType(CrewMemberType type) {
        this.type = type;
    }

    public Luggage getLuggage() {
        return luggage;
    }

    /**
     * @param luggage
     */
    @XmlElement(name = "crew_member_luggage")
    public void setLuggage(Luggage luggage) {
        this.luggage = luggage;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return String.format("\t\t\tCrew Member:\n\t\t\t\tID: %d;\n\t\t\t\tName: %s;\n\t\t\t\tSurname: %s;" + "\n\t\t\t\tDate Of Birth: %s;\n\t\t\t\tAge: %d;\n\t\t\t\tWeight: %.2f;\n\t\t\t\tCrew Member Type: %s\n\t\t\t\t%s", super.getId(), super.getName(), super.getSurname(), super.getDateOfBirth().toString(), super.getAge(), super.getWeight(), this.type, this.luggage);
    }
}