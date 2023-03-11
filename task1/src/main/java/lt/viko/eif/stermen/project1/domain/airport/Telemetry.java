package lt.viko.eif.stermen.project1.domain.airport;

import jakarta.xml.bind.annotation.*;

import javax.persistence.*;
import java.util.Date;

/**
 *
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
     *
     */
    public Telemetry() {
    }

    /**
     * @param id
     * @param date
     * @param area
     * @param temperature
     * @param windSpeed
     * @param humidity
     * @param localAtmosphericPressure
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
     * @param id
     */
    @XmlElement(name = "telemetry_id")
    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    @XmlElement(name = "telemetry_date_time")
    public void setDate(Date date) {
        this.date = date;
    }

    public double getArea() {
        return area;
    }

    /**
     * @param area
     */
    @XmlElement(name = "telemetry_area")
    public void setArea(double area) {
        this.area = area;
    }

    public double getTemperature() {
        return temperature;
    }

    /**
     * @param temperature
     */
    @XmlElement(name = "telemetry_temperature")
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * @param windSpeed
     */
    @XmlElement(name = "telemetry_wind_speed")
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getHumidity() {
        return humidity;
    }

    /**
     * @param humidity
     */
    @XmlElement(name = "telemetry_humidity")
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getLocalAtmosphericPressure() {
        return localAtmosphericPressure;
    }

    /**
     * @param localAtmosphericPressure
     */
    @XmlElement(name = "telemetry_local_atmospheric_pressure")
    public void setLocalAtmosphericPressure(double localAtmosphericPressure) {
        this.localAtmosphericPressure = localAtmosphericPressure;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.format("\t\t\tTelemetry:\n\t\t\t\tID: %d;\n\t\t\t\tDate: %s;\n\t\t\t\tArea: %f;\n\t\t\t\tTemperature: %f;\n\t\t\t\tWind Speed: %f;\n\t\t\t\tHumidity: %f;\n\t\t\t\tLocal Atmospheric Pressure: %f;", this.id, this.date.toString(), this.area, this.temperature, this.windSpeed, this.humidity, this.localAtmosphericPressure);
    }
}
