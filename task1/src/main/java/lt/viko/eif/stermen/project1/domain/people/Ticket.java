package lt.viko.eif.stermen.project1.domain.people;

import javax.persistence.*;

import jakarta.xml.bind.annotation.*;

/**
 *
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
     *
     */
    public Ticket() {
    }

    /**
     * @param destination
     * @param seatNumber
     */
    public Ticket(String destination, int seatNumber) {
        this.destination = destination;
        this.seatNumber = seatNumber;
    }

    public Ticket(String destination) {
        this.destination = destination;
    }


    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    @XmlElement(name = "ticket_id")
    public void setId(int id) {
        this.id = id;
    }


    public String getDestination() {
        return destination;
    }

    /**
     * @param destination
     */
    @XmlElement(name = "ticket_destination")
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * @param seatNumber
     */
    @XmlElement(name = "ticket_seat")
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.format("Ticket:\n\t\t\t\t\tID: %d;\n\t\t\t\t\tDestination: %s;\n\t\t\t\t\tSeat Number: %d;", this.id, this.destination, this.seatNumber);
    }

}
