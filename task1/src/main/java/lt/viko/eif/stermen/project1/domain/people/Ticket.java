package lt.viko.eif.stermen.project1.domain.people;

import javax.persistence.*;

import jakarta.xml.bind.annotation.*;

/**
 * This class is part of the object tree. It will be under the Passenger class.
 * The ticket represents the start and end point of the travel or the services rendered by the airport.
 * The passenger must always have a ticket.
 * The ticket has a unique id (for database purposes).
 * The destination is the destination to which the passenger will travel by airplane.
 * The seatNumber is the number of the seat that is located on the airplane.
 * The object also has XML attributes for marshalling and unmarshalling.
 * The object xml accessor type is set to property (getters and setters) as per Java Beans conventions.
 * The object also has an entity tag for the database mapping.
 */
@XmlType(propOrder = {"id", "destination", "seatNumber"})
@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String destination;
    private int seatNumber;

    /**
     * An empty default constructor for JAXB transformations.
     */
    public Ticket() {
    }

    /**
     * This constructor should be avoided.
     *
     * @param id          the id of the ticket.
     * @param destination the destination to which the passenger will travel by airplane.
     * @param seatNumber  the number of the seat that is located on the airplane.
     */
    public Ticket(int id, String destination, int seatNumber) {
        this.id = id;
        this.destination = destination;
        this.seatNumber = seatNumber;
    }

    /**
     * @param destination the destination to which the passenger will travel by airplane.
     * @param seatNumber  the number of the seat that is located on the airplane.
     */
    public Ticket(String destination, int seatNumber) {
        this.destination = destination;
        this.seatNumber = seatNumber;
    }

    /**
     * @param destination the destination to which the passenger will travel by airplane.
     */
    public Ticket(String destination) {
        this.destination = destination;
    }


    public int getId() {
        return id;
    }

    /**
     * @param id the id of the ticket.
     */
    @XmlElement(name = "ticket_id")
    public void setId(int id) {
        this.id = id;
    }


    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to which the passenger will travel by airplane.
     */
    @XmlElement(name = "ticket_destination")
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * @param seatNumber the number of the seat that is located on the airplane.
     */
    @XmlElement(name = "ticket_seat")
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all ticket data fields.
     */
    @Override
    public String toString() {
        return String.format("Ticket:\n\t\t\t\t\tID: %d;\n\t\t\t\t\tDestination: %s;\n\t\t\t\t\tSeat Number: %d;", this.id, this.destination, this.seatNumber);
    }

}
