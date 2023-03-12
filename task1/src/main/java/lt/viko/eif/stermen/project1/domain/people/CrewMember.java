package lt.viko.eif.stermen.project1.domain.people;

import javax.persistence.*;

import jakarta.xml.bind.annotation.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDate;

/**
 * This class is part of the object tree. It will be under the Airplane class.
 * This class extends the Person class.
 * The crew member class holds all super (person) data fields.
 * The crew members are an integral part in the operation of the airplane.
 * The type depicts the type of the crew member: pilot or stewardess.
 * The luggage field represents the luggage a crew member has.
 * The object also has XML attributes for marshalling and unmarshalling.
 * The object xml accessor type is set to property (getters and setters) as per Java Beans conventions.
 * The object also has XmlSeeAlso for marshalling/unmarshalling inheritance mapping.
 * The object also has an entity tag for the database mapping.
 * All OneToOne and OneToMany relationships are cascaded.
 * All OneToOne and OneToMany relationships have LazyCollectionOption off so when objects are retrieved from the database, they can be instantiated.
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
     * An empty default constructor for JAXB transformations.
     */
    public CrewMember() {
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
     * @param type        the type of crew member.
     * @param luggage     the luggage a person has.
     */
    public CrewMember(int id, String name, String surname, LocalDate dateOfBirth, double weight, CrewMemberType type, Luggage luggage) {
        super(id, name, surname, dateOfBirth, weight);
        this.type = type;
        this.luggage = luggage;
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
     * @param type        the type of crew member.
     * @param luggage     the luggage a person has.
     */
    public CrewMember(int id, String name, String surname, String dateOfBirth, double weight, CrewMemberType type, Luggage luggage) {
        super(id, name, surname, dateOfBirth, weight);
        this.type = type;
        this.luggage = luggage;
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
    public CrewMember(int id, String name, String surname, LocalDate dateOfBirth, double weight) {
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
    public CrewMember(int id, String name, String surname, String dateOfBirth, double weight) {
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
     * @param type        the type of crew member.
     * @param luggage     the luggage a person has.
     */
    public CrewMember(String name, String surname, LocalDate dateOfBirth, double weight, CrewMemberType type, Luggage luggage) {
        super(name, surname, dateOfBirth, weight);
        this.type = type;
        this.luggage = luggage;
    }

    /**
     * Here the dateOfBirth can be provided as String and if valid will be parsed into LocalDate.
     * Age is calculated automatically.
     *
     * @param name        the name of the person.
     * @param surname     the surname of the person.
     * @param dateOfBirth the date on which the person has been born.
     * @param weight      the weight of the person.
     * @param type        the type of crew member.
     * @param luggage     the luggage a person has.
     */
    public CrewMember(String name, String surname, String dateOfBirth, double weight, CrewMemberType type, Luggage luggage) {
        super(name, surname, dateOfBirth, weight);
        this.type = type;
        this.luggage = luggage;
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
    public CrewMember(String name, String surname, LocalDate dateOfBirth, double weight) {
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
    public CrewMember(String name, String surname, String dateOfBirth, double weight) {
        super(name, surname, dateOfBirth, weight);
    }

    public CrewMemberType getType() {
        return type;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param type the type of crew member.
     */
    @XmlElement(name = "crew_member_type")
    public void setType(CrewMemberType type) {
        this.type = type;
    }

    public Luggage getLuggage() {
        return luggage;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param luggage the luggage a person has.
     */
    @XmlElement(name = "crew_member_luggage")
    public void setLuggage(Luggage luggage) {
        this.luggage = luggage;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all crew member and person data fields.
     */
    @Override
    public String toString() {
        return String.format("\t\t\tCrew Member:\n\t\t\t\tID: %d;\n\t\t\t\tName: %s;\n\t\t\t\tSurname: %s;" + "\n\t\t\t\tDate Of Birth: %s;\n\t\t\t\tAge: %d;\n\t\t\t\tWeight: %.2f;\n\t\t\t\tCrew Member Type: %s\n\t\t\t\t%s", super.getId(), super.getName(), super.getSurname(), super.getDateOfBirth().toString(), super.getAge(), super.getWeight(), this.type, this.luggage);
    }
}