package lt.viko.eif.stermen.project1.domain.people;

import javax.persistence.*;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lt.viko.eif.stermen.project1.utility.LocalDateAdapter;

import java.time.LocalDate;

/**
 * The person is an integral part in defying any people within the Airport,
 * whether it be a passenger, a crew member, or a control tower operator.
 * It is important to note that weight would play an integral part in how average fuel consumption calculations would be executed for when airplanes are flying.
 * Also, different age groups (defined by age) get different discounts in airports.
 * The person class will be used in an inherited manner to confine to SOLID principles.
 * The person has a unique id (for database purposes).
 * The name depicts the name of a person.
 * The surname depicts the surname of a person.
 * The dateOfBirth depicts the date on which a person has been born.
 * The age depicts the age of the person.
 * The weight depicts the weight of the person.
 * The object also has XML attributes for marshalling and unmarshalling.
 * The object xml accessor type is set to property (getters and setters) as per Java Beans conventions.
 * The object also has an MapperSuperclass tag for the database mapping.
 */
@XmlType(propOrder = {"id", "name", "surname", "dateOfBirth", "age", "weight"})
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.PROPERTY)
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private int age = 0;
    private double weight;

    /**
     * An empty default constructor for JAXB transformations.
     */
    public Person() {
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
    public Person(int id, String name, String surname, LocalDate dateOfBirth, double weight) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        calculateAge();
        this.weight = weight;
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
    public Person(int id, String name, String surname, String dateOfBirth, double weight) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        calculateAge();
        this.weight = weight;
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
    public Person(String name, String surname, LocalDate dateOfBirth, double weight) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        calculateAge();
        this.weight = weight;
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
    public Person(String name, String surname, String dateOfBirth, double weight) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        calculateAge();
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param id the id of the person.
     */
    @XmlElement(name = "person_id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param name the name of the person.
     */
    @XmlElement(name = "person_first_name")
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param surname the surname of the person.
     */
    @XmlElement(name = "person_last_name")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param dateOfBirth the date on which the person has been born.
     */
    @XmlElement(name = "person_date_of_birth")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param age the age of the person.
     */
    @XmlElement(name = "person_age")
    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param weight the weight of the person.
     */
    @XmlElement(name = "person_weight")
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all person data fields.
     */
    @Override
    public String toString() {
        return String.format("\n\t\t\t\tName: %s;\n\t\t\t\tSurname: %s;" + "\n\t\t\t\tDate Of Birth: %s;\n\t\t\t\tAge: %d;\n\t\t\t\tWeight: %.2f;", this.name, this.surname, this.dateOfBirth.toString(), this.age, this.weight);
    }

    /**
     * This method is for internal use only. When the dateOfBirth is changed or this method is called and dateOfBirth is not null it will calculate the age of the person object.
     */
    private void calculateAge() {
        LocalDate dob = this.dateOfBirth;
        LocalDate curDate = LocalDate.now();
        this.age = curDate.getYear() - dob.getYear();
    }
}
