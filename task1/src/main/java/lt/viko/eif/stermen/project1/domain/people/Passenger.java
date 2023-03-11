package lt.viko.eif.stermen.project1.domain.people;

import javax.persistence.*;

import jakarta.xml.bind.annotation.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDate;

/**
 *
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
     *
     */
    public Passenger() {
    }

    /**
     * @param name
     * @param surname
     * @param dateOfBirth
     * @param weight
     * @param luggage
     * @param ticket
     */
    public Passenger(String name, String surname, String dateOfBirth, double weight, Luggage luggage, Ticket ticket) {
        super(name, surname, dateOfBirth, weight);
        this.luggage = luggage;
        this.ticket = ticket;
    }

    /**
     * @param name
     * @param surname
     * @param dateOfBirth
     * @param weight
     */
    public Passenger(String name, String surname, LocalDate dateOfBirth, double weight) {
        super(name, surname, dateOfBirth, weight);
    }

    public Luggage getLuggage() {
        return luggage;
    }

    /**
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
     * @param ticket
     */
    @XmlElement(name = "passenger_ticket")
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {

        return String.format("\t\t\tPassenger:\n\t\t\t\tID: %d;\n\t\t\t\tName: %s;\n\t\t\t\tSurname: %s;" + "\n\t\t\t\tDate Of Birth: %s;\n\t\t\t\tAge: %d;\n\t\t\t\tWeight: %.2f;\n\t\t\t\t%s\n\t\t\t\t%s", super.getId(), super.getName(), super.getSurname(), super.getDateOfBirth().toString(), super.getAge(), super.getWeight(), this.luggage, this.ticket);
    }
}