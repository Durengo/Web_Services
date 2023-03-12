package lt.viko.eif.stermen.utilityTest;

import lt.viko.eif.stermen.project1.domain.airplane.Airplane;
import lt.viko.eif.stermen.project1.domain.airplane.AirplaneType;
import lt.viko.eif.stermen.project1.domain.airport.*;
import lt.viko.eif.stermen.project1.domain.people.*;
import lt.viko.eif.stermen.project1.utility.JaxbUtility;
import lt.viko.eif.stermen.project1.utility.JaxbUtilityOutputType;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JaxbTest {
    private Airport airport = null;

    public JaxbTest() {
        Airspace airspace = new Airspace(3, 1000.0, AirspaceClass.ClassDelta, 0);
        Runway runway = new Runway(5, 400.0, 150.0);
        Telemetry telemetry = new Telemetry(6, Date.from(Instant.parse("2023-03-11T19:26:50.848+02:00")), 100.0, 32.0, 5.0, 30.0, 80.0);
        ControlTowerOperator operator = new ControlTowerOperator(4, "Oper", "Ator", "1970-06-10", 70.0);
        List<Runway> runwayList = new ArrayList<Runway>();
        runwayList.add(runway);
        List<Telemetry> telemetryList = new ArrayList<Telemetry>();
        telemetryList.add(telemetry);
        List<ControlTowerOperator> controlTowerOperatorList = new ArrayList<ControlTowerOperator>();
        controlTowerOperatorList.add(operator);
        TrafficControlTower tower = new TrafficControlTower(2, airspace, runwayList, telemetryList, controlTowerOperatorList);

        List<Airplane> airplaneList = new ArrayList<Airplane>();
        List<CrewMember> memberList1 = new ArrayList<CrewMember>();
        List<Passenger> passengerList1 = new ArrayList<Passenger>();


        CrewMember member1 = new CrewMember(8, "Cap", "Capn", "1980-02-01", 80.0);
        member1.setType(CrewMemberType.Pilot);
        memberList1.add(member1);

        Passenger passenger1 = new Passenger(9, "Seb", "Ter", "2000-03-23", 100.0, new Luggage(10, "Toothbrush", 5.0), new Ticket(11, "Lebanon", 1));
        Passenger passenger2 = new Passenger(12, "Sebas", "Terme", "2000-03-23", 100.0, new Luggage(13, "Bat", 3.0), new Ticket(14, "Lebanon", 2));
        passengerList1.add(passenger1);
        passengerList1.add(passenger2);

        Airplane airplane1 = new Airplane(AirplaneType.LightJet);
        airplane1.setId(7);
        airplane1.setDestinationAirport("Lebanon");
        airplane1.setCrewList(memberList1);
        airplane1.setPassengerList(passengerList1);
        airplane1.setCurrentPassengers(3);
        airplaneList.add(airplane1);

        List<CrewMember> memberList2 = new ArrayList<CrewMember>();
        List<Passenger> passengerList2 = new ArrayList<Passenger>();

        CrewMember member2 = new CrewMember(16, "Cap", "Capn", "1980-02-01", 80.0);
        member2.setType(CrewMemberType.Pilot);
        memberList2.add(member2);

        Passenger passenger3 = new Passenger(17, "Sebas", "Terme", "2000-03-23", 100.0, new Luggage(13, "Bat", 3.0), new Ticket(18, "America", 0));
        passengerList2.add(passenger3);

        Airplane airplane2 = new Airplane(AirplaneType.JumboJet);
        airplane2.setId(15);
        airplane2.setDestinationAirport("America");
        airplane2.setCrewList(memberList2);
        airplane2.setPassengerList(passengerList2);
        airplane2.setCurrentPassengers(2);
        airplaneList.add(airplane2);

        airport = new Airport(1, "Waterbury Airport", AirportSize.SmallAirport, -18.1332, -27.2473, "Waterbury", "Haiti", tower, airplaneList);
    }

    @Test
    public void jaxbTransformToXml() throws IOException {
        final String expected = new String(Files.readAllBytes(Paths.get("target/classes/test_small.xml")), StandardCharsets.UTF_8) + "\n";

        JaxbUtility.setContext(Airport.class);
        JaxbUtility.setXsdSchema("target/classes/Airport.xsd");
        JaxbUtility.setOutputType(JaxbUtilityOutputType.StringWriter);
        JaxbUtility.transformToXML(airport);

        final String actual = JaxbUtility.getWriter().toString();

        assertEquals(expected, actual);
    }

    @Test
    public void jaxbTransformToPojo() throws IOException {
        String file = new String(Files.readAllBytes(Paths.get("target/classes/test_small.xml")), StandardCharsets.UTF_8) + "\n";

        JaxbUtility.setContext(Airport.class);
        JaxbUtility.setXsdSchema("target/classes/Airport.xsd");
        JaxbUtility.setOutputType(JaxbUtilityOutputType.StringWriter);
        StringWriter sw = new StringWriter();
        sw.write(file);
        JaxbUtility.setWriter(sw);
        final Airport expected = JaxbUtility.transformToPOJO(Airport.class);

        final Airport actual = airport;

        assertEquals(expected.toString(), actual.toString());
    }
}
