package lt.viko.eif.stermen.project1.domain.people;

import javax.persistence.*;

import jakarta.xml.bind.annotation.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDate;

/**
 * This class is part of the object tree. It will be under the Airplane class.
 * This class extends the Person class.
 * The passenger is the most important part for the airport business, it is the main customer and the main revenue generator.
 * The passenger class holds all super (person) data fields.
 * The luggage field represents the luggage a passenger has.
 * The ticket field represents the ticket a passenger has.
 * The object also has XML attributes for marshalling and unmarshalling.
 * The object xml accessor type is set to property (getters and setters) as per Java Beans conventions.
 * The object also has XmlSeeAlso for marshalling/unmarshalling inheritance mapping.
 * The object also has an entity tag for the database mapping.
 * All OneToOne and OneToMany relationships are cascaded.
 * All OneToOne and OneToMany relationships have LazyCollectionOption off so when objects are retrieved from the database, they can be instantiated.
 */
@XmlType(propOrder = {"luggage", "ticket"})
@XmlRootElement(name = "passenger")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlSeeAlso(Person.class)
@Entity
@Table(name = "passenger")
public class Passenger extends Person {
    @OneToOne(targetEntity = Luggage.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Luggage luggage;
    @OneToOne(targetEntity = Ticket.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Ticket ticket;

    /**
     * An empty default constructor for JAXB transformations.
     */
    public Passenger() {
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
     * @param luggage     the luggage a person has.
     * @param ticket      the ticket a person holds.
     */
    public Passenger(int id, String name, String surname, LocalDate dateOfBirth, double weight, Luggage luggage, Ticket ticket) {
        super(id, name, surname, dateOfBirth, weight);
        this.luggage = luggage;
        this.ticket = ticket;
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
     * @param luggage     the luggage a person has.
     * @param ticket      the ticket a person holds.
     */
    public Passenger(int id, String name, String surname, String dateOfBirth, double weight, Luggage luggage, Ticket ticket) {
        super(id, name, surname, dateOfBirth, weight);
        this.luggage = luggage;
        this.ticket = ticket;
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
    public Passenger(int id, String name, String surname, LocalDate dateOfBirth, double weight) {
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
    public Passenger(int id, String name, String surname, String dateOfBirth, double weight) {
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
     * @param luggage     the luggage a person has.
     * @param ticket      the ticket a person holds.
     */
    public Passenger(String name, String surname, LocalDate dateOfBirth, double weight, Luggage luggage, Ticket ticket) {
        super(name, surname, dateOfBirth, weight);
        this.luggage = luggage;
        this.ticket = ticket;
    }

    /**
     * Here the dateOfBirth can be provided as String and if valid will be parsed into LocalDate.
     * Age is calculated automatically.
     *
     * @param name        the name of the person.
     * @param surname     the surname of the person.
     * @param dateOfBirth the date on which the person has been born.
     * @param weight      the weight of the person.
     * @param luggage     the luggage a person has.
     * @param ticket      the ticket a person holds.
     */
    public Passenger(String name, String surname, String dateOfBirth, double weight, Luggage luggage, Ticket ticket) {
        super(name, surname, dateOfBirth, weight);
        this.luggage = luggage;
        this.ticket = ticket;
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
    public Passenger(String name, String surname, LocalDate dateOfBirth, double weight) {
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
    public Passenger(String name, String surname, String dateOfBirth, double weight) {
        super(name, surname, dateOfBirth, weight);
    }

    public Luggage getLuggage() {
        return luggage;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param luggage
     */
    @XmlElement(name = "passenger_luggage")
    public void setLuggage(Luggage luggage) {
        this.luggage = luggage;
    }

    public Ticket getTicket() {
        return ticket;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param ticket
     */
    @XmlElement(name = "passenger_ticket")
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all passenger and person data fields.
     */
    @Override
    public String toString() {

        return String.format("\t\t\tPassenger:\n\t\t\t\tID: %d;\n\t\t\t\tName: %s;\n\t\t\t\tSurname: %s;" + "\n\t\t\t\tDate Of Birth: %s;\n\t\t\t\tAge: %d;\n\t\t\t\tWeight: %.2f;\n\t\t\t\t%s\n\t\t\t\t%s", super.getId(), super.getName(), super.getSurname(), super.getDateOfBirth().toString(), super.getAge(), super.getWeight(), this.luggage, this.ticket);
    }
}