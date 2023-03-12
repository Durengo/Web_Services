package lt.viko.eif.stermen.project1.domain.airport;

import jakarta.xml.bind.annotation.*;

import javax.persistence.*;
import java.util.Date;

/**
 * This class is part of the object tree. It will be under the Traffic Control Tower class.
 * This class holds any meteorological data collected around the airspace.
 * An airspace can hold an infinite amount of telemetry.
 * The telemetry has a unique id (for database purposes).
 * Area is defined as the average radius of the airspace.
 * Temperature is denoted in Celsius.
 * Wind speed is denoted in meters per second.
 * Humidity is denoted in percentages.
 * Local atmospheric pressure is denoted in atmospheres.
 * The object also has XML attributes for marshalling and unmarshalling.
 * The object xml accessor type is set to property (getters and setters) as per Java Beans conventions.
 * The object also has an entity tag for the database mapping.
 */
@XmlType(propOrder = {"id", "date", "area", "temperature", "windSpeed", "humidity", "localAtmosphericPressure"})
@XmlRootElement(name = "telemetry")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "telemetry")
public class Telemetry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;
    private Date date;
    private double area;
    private double temperature;
    private double windSpeed;
    private double humidity;
    private double localAtmosphericPressure;

    /**
     * An empty default constructor for JAXB transformations.
     */
    public Telemetry() {
    }

    /**
     * This constructor should be avoided.
     *
     * @param id                       the id of the telemetry. Should not be set, because the whole object tree ID's have GenerationType set to AUTO.
     * @param date                     the date when the telemetry was logged.
     * @param area                     the radius in meters from the traffic control tower in which the telemetry was logged.
     * @param temperature              the temperature in Celsius.
     * @param windSpeed                the wind speed in meters per second.
     * @param humidity                 the humidity in percentages.
     * @param localAtmosphericPressure the local atmospheric pressure in atmospheres.
     */
    public Telemetry(int id, Date date, double area, double temperature, double windSpeed, double humidity, double localAtmosphericPressure) {
        this.id = id;
        this.date = date;
        this.area = area;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.localAtmosphericPressure = localAtmosphericPressure;
    }

    /**
     * @param date                     the date when the telemetry was logged.
     * @param area                     the radius in meters from the traffic control tower in which the telemetry was logged.
     * @param temperature              the temperature in Celsius.
     * @param windSpeed                the wind speed in meters per second.
     * @param humidity                 the humidity in percentages.
     * @param localAtmosphericPressure the local atmospheric pressure in atmospheres.
     */
    public Telemetry(Date date, double area, double temperature, double windSpeed, double humidity, double localAtmosphericPressure) {
        this.date = date;
        this.area = area;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.localAtmosphericPressure = localAtmosphericPressure;
    }

    public int getId() {
        return id;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param id the id of the telemetry.
     */
    @XmlElement(name = "telemetry_id")
    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param date the date when the telemetry was logged.
     */
    @XmlElement(name = "telemetry_date_time")
    public void setDate(Date date) {
        this.date = date;
    }

    public double getArea() {
        return area;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param area the radius in meters from the traffic control tower in which the telemetry was logged.
     */
    @XmlElement(name = "telemetry_area")
    public void setArea(double area) {
        this.area = area;
    }

    public double getTemperature() {
        return temperature;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param temperature the temperature in Celsius.
     */
    @XmlElement(name = "telemetry_temperature")
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param windSpeed the wind speed in meters per second.
     */
    @XmlElement(name = "telemetry_wind_speed")
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getHumidity() {
        return humidity;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param humidity the humidity in percentages.
     */
    @XmlElement(name = "telemetry_humidity")
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getLocalAtmosphericPressure() {
        return localAtmosphericPressure;
    }

    /**
     * Setter for JAXB transformations.
     *
     * @param localAtmosphericPressure the local atmospheric pressure in atmospheres.
     */
    @XmlElement(name = "telemetry_local_atmospheric_pressure")
    public void setLocalAtmosphericPressure(double localAtmosphericPressure) {
        this.localAtmosphericPressure = localAtmosphericPressure;
    }

    /**
     * Overridden toString method to represent object in XML like fashion when printed to console.
     *
     * @return String all telemetry data fields.
     */
    @Override
    public String toString() {
        return String.format("\t\t\tTelemetry:\n\t\t\t\tID: %d;\n\t\t\t\tDate: %s;\n\t\t\t\tArea: %f;\n\t\t\t\tTemperature: %f;\n\t\t\t\tWind Speed: %f;\n\t\t\t\tHumidity: %f;\n\t\t\t\tLocal Atmospheric Pressure: %f;", this.id, this.date.toString(), this.area, this.temperature, this.windSpeed, this.humidity, this.localAtmosphericPressure);
    }
}
