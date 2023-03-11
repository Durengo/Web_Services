package lt.viko.eif.stermen.project1.domain.people;

import javax.persistence.*;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lt.viko.eif.stermen.project1.utility.LocalDateAdapter;

import java.time.LocalDate;

/**
 *
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
     *
     */
    public Person() {
    }

    /**
     * @param name
     * @param surname
     * @param dateOfBirth
     * @param weight
     */
    public Person(String name, String surname, String dateOfBirth, double weight) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        calculateAge();
        this.weight = weight;
    }

    /**
     * @param name
     * @param surname
     * @param dateOfBirth
     * @param weight
     */
    public Person(String name, String surname, LocalDate dateOfBirth, double weight) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        calculateAge();
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    @XmlElement(name = "person_id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    @XmlElement(name = "person_first_name")
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    @XmlElement(name = "person_last_name")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * @param dateOfBirth
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
     *
     * @param age
     */
    @XmlElement(name = "person_age")
    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     */
    @XmlElement(name = "person_weight")
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return String.format("\n\t\t\t\tName: %s;\n\t\t\t\tSurname: %s;" + "\n\t\t\t\tDate Of Birth: %s;\n\t\t\t\tAge: %d;\n\t\t\t\tWeight: %.2f;", this.name, this.surname, this.dateOfBirth.toString(), this.age, this.weight);
    }

    /**
     *
     */
    private void calculateAge() {
        //creating a constructor of the LocalDate class passing year, month and date of the DOB and current date
        LocalDate dob = this.dateOfBirth;
        //constructor for current date
        LocalDate curDate = LocalDate.now();
        //determines the period of years between two LocalDate instances
        //gets the number of years and print the same
        this.age = curDate.getYear() - dob.getYear();
    }
}
