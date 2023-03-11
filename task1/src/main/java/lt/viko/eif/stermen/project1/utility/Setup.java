package lt.viko.eif.stermen.project1.utility;

import lt.viko.eif.stermen.project1.domain.airplane.Airplane;
import lt.viko.eif.stermen.project1.domain.airplane.AirplaneType;
import lt.viko.eif.stermen.project1.domain.airport.*;
import lt.viko.eif.stermen.project1.domain.people.*;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 */
public class Setup {
    /**
     *
     */
    private Setup() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and it cannot be instantiated.");
    }

    /**
     * @return
     */
    public static Airport generateXml() {
        List<String> destinations = new ArrayList<String>();
        destinations.add("Waterbury Airport");
        destinations.add("Utica Airport");
        destinations.add("Fresno Airport");
        destinations.add("Reading Airport");
        destinations.add("Downey Airport");
        destinations.add("Clarke County Airport");
        destinations.add("Rancho Cucamonga Airport");
        destinations.add("San Diego Airport");
        destinations.add("Billings Airport");
        destinations.add("Amarillo Airport");

        List<Airport> airportList = new ArrayList<Airport>();
        List<Passenger> passengerList = new ArrayList<Passenger>();
        List<CrewMember> crewList = new ArrayList<CrewMember>();
        List<Luggage> luggageList = new ArrayList<Luggage>();
        List<Airplane> airplaneList = new ArrayList<Airplane>();
        List<ControlTowerOperator> controlTowerOperatorList = new ArrayList<ControlTowerOperator>();
        List<Telemetry> telemetryList = new ArrayList<Telemetry>();

        CsvUtility.setHeaders(new String[]{"first_name", "last_name", "date_of_birth", "weight"});
        CsvUtility.parseCsv("task1/target/classes/csv_data/PassengersBasic.csv");
        for (CSVRecord record : CsvUtility.getRecords()) {
        /*  String first_name = record.get("first_name");
            String last_name = record.get("last_name");
            LocalDate date = LocalDate.parse(record.get("date_of_birth"));
            double weight = Double.parseDouble(record.get("weight"));
            System.out.println(String.format("First Name: %s; Last Name: %s; Date Of Birth: %s; Weight: %f", first_name, last_name, date.toString(), weight));
            Passenger passenger = new Passenger(first_name, last_name, date, weight);*/
            Passenger passenger = new Passenger(record.get("first_name"), record.get("last_name"), LocalDate.parse(record.get("date_of_birth")), Double.parseDouble(record.get("weight")));
            passengerList.add(passenger);
        }

        CsvUtility.setHeaders(new String[]{"first_name", "last_name", "date_of_birth", "weight"});
        CsvUtility.parseCsv("task1/target/classes/csv_data/CrewMemberBasic.csv");
        for (CSVRecord record : CsvUtility.getRecords()) {
            CrewMember member = new CrewMember(record.get("first_name"), record.get("last_name"), LocalDate.parse(record.get("date_of_birth")), Double.parseDouble(record.get("weight")));
            crewList.add(member);
        }

        CsvUtility.setHeaders(new String[]{"contents", "weight"});
        CsvUtility.parseCsv("task1/target/classes/csv_data/Luggage.csv");
        for (CSVRecord record : CsvUtility.getRecords()) {
            Luggage luggage = new Luggage(record.get("contents"), Double.parseDouble(record.get("weight")));
            luggageList.add(luggage);
        }

        CsvUtility.setHeaders(new String[]{"first_name", "last_name", "date_of_birth", "weight"});
        CsvUtility.parseCsv("task1/target/classes/csv_data/CrewMemberBasic.csv");
        for (CSVRecord record : CsvUtility.getRecords()) {
            ControlTowerOperator member = new ControlTowerOperator(record.get("first_name"), record.get("last_name"), LocalDate.parse(record.get("date_of_birth")), Double.parseDouble(record.get("weight")));
            controlTowerOperatorList.add(member);
        }


        CsvUtility.setHeaders(new String[]{"date", "area", "temperature", "wind_speed", "humidity", "local_atmospheric_pressure"});
        CsvUtility.parseCsv("task1/target/classes/csv_data/Telemetry.csv");
        for (CSVRecord record : CsvUtility.getRecords()) {
            Telemetry telemetry = new Telemetry(Date.from(Instant.parse(record.get("date"))), Double.parseDouble(record.get("area")), Double.parseDouble(record.get("temperature")), Double.parseDouble(record.get("wind_speed")), Double.parseDouble(record.get("humidity")), Double.parseDouble(record.get("local_atmospheric_pressure")));
            telemetryList.add(telemetry);
        }

        Airport airport1 = new Airport("Waterbury Airport", AirportSize.SmallAirport, -18.1332, -27.2473, "Waterbury", "Haiti");
//        Airport airport2 = new Airport("Utica Airport", AirportSize.SmallAirport, -141.8862, 5.5451, "United Kingdom", "Utica");
//        Airport airport3 = new Airport("Fresno Airport", AirportSize.MediumAirport, -110.6358, 61.1584, "East Timor", "Fresno");
//        Airport airport4 = new Airport("Reading Airport", AirportSize.LargeAirport, 158.6418, -65.3966, "Kuwait", "Reading");
//        Airport airport5 = new Airport("Downey Airport", AirportSize.SmallAirport, 0.5260, -36.3299, "South Africa", "Downey");
//        Airport airport6 = new Airport("Clarke County Airport", AirportSize.MediumAirport, -16.1996, 65.9259, "Australia", "Clarke County");
//        Airport airport7 = new Airport("Rancho Cucamonga Airport", AirportSize.SmallAirport, -141.2547, 20.2214, "Congo", "Rancho Cucamonga");
//        Airport airport8 = new Airport("San Diego Airport", AirportSize.LargeAirport, -76.5109, -84.5470, "Sri Lanka", "San Diego");
//        Airport airport9 = new Airport("Billings Airport", AirportSize.SmallAirport, 21.3965, 44.9243, "East Timor", "Billings");
//        Airport airport10 = new Airport("Amarillo Airport", AirportSize.SmallAirport, -87.7410, -82.4711, "China", "Amarillo");
        airportList.add(airport1);
//        airportList.add(airport2);
//        airportList.add(airport3);
//        airportList.add(airport4);
//        airportList.add(airport5);
//        airportList.add(airport6);
//        airportList.add(airport7);
//        airportList.add(airport8);
//        airportList.add(airport9);
//        airportList.add(airport10);

        Random random = new Random();

        TrafficControlTower trafficControlTower = new TrafficControlTower();
        trafficControlTower.setTelemetryList(telemetryList);
        trafficControlTower.getRunwayList().add(new Runway(1000, 150));
        Airspace airspace = new Airspace(1000.0, AirspaceClass.ClassDelta, 0);
        trafficControlTower.setAirspace(airspace);

        for (int i = 0; i < 10; i++) {
            trafficControlTower.getControlTowerOperatorList().add(controlTowerOperatorList.get(i));
        }
        airport1.setTrafficControlTower(trafficControlTower);

        for (Passenger passenger : passengerList) {
            int number = random.nextInt(luggageList.size() - 1);
            passenger.setLuggage(luggageList.get(number));
            Ticket ticket = new Ticket();
            passenger.setTicket(ticket);
        }

        for (CrewMember member : crewList) {
            int number = random.nextInt(luggageList.size() - 1);
            member.setLuggage(luggageList.get(number));
        }

        int randomAiplane = random.nextInt(100);


        boolean jumboExist = false;
        for (int i = 0; i < 10; i++) {
            int type = random.nextInt(100);
            int destination = random.nextInt(9);
            Airplane airplane = null;
            if (type < 35) {
                airplane = new Airplane(AirplaneType.LightJet, destinations.get(destination));
            } else if (type >= 35 && type < 75) {
                airplane = new Airplane(AirplaneType.MidSizeJet, destinations.get(destination));
            } else if (type >= 75 && type <= 100) {

                if (!jumboExist) {
                    airplane = new Airplane(AirplaneType.JumboJet, destinations.get(destination));
                    jumboExist = true;
                } else {
                    airplane = new Airplane(AirplaneType.LightJet, destinations.get(destination));
                }
            }
            airplaneList.add(airplane);
        }

        int crewIncrement = 0;
        int passengerCount = 0;
        for (Airplane plane : airplaneList) {
            crewList.get(crewIncrement + 0).setType(CrewMemberType.Pilot);
            crewList.get(crewIncrement + 1).setType(CrewMemberType.Stewardess);
            plane.addCrewMember(crewList.get(crewIncrement + 0));
            plane.addCrewMember(crewList.get(crewIncrement + 1));
            crewIncrement += 2;
            while (plane.getCurrentPassengers() < plane.getMaxPassengers()) {
                if (passengerCount >= 1000) {
                    passengerCount = 0;
                }
                passengerList.get(passengerCount).getTicket().setDestination(plane.getDestinationAirport());
                passengerList.get(passengerCount).getTicket().setSeatNumber(plane.getMaxPassengers() - 1);
                plane.addPassenger(passengerList.get(passengerCount));
                passengerCount++;
            }
        }

//        for (int i = 0; i < airplaneList.size(); i++) {
//            airportList.get(i % 10).addAirplane(airplaneList.get(i));
//        }
        for (Airplane plane : airplaneList) {
            airport1.addAirplane(plane);
        }

        return airport1;

//        String compound = "";
//        try {
//            JaxbUtility.setContext(Airport.class);
//            JaxbUtility.setOutputType(JaxbUtilityOutputType.StringWriter);
////            JaxbUtility.Marshal(airportList);
//            JaxbUtility.Marshal(airport1);
//            compound += JaxbUtility.getWriter().toString();
//            System.out.println(JaxbUtility.getWriter().toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            JaxbUtility.setContext(Airport.class);
//            JaxbUtility.setOutputType(JaxbUtilityOutputType.File);
//            JaxbUtility.setFilepath("main.xml");
//            JaxbUtility.Marshal(airport1);
//            JaxbUtility.Unmarshall(Airport.class);
////            System.out.println(JaxbUtility.getReader().toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        for (Airport port : airportList) {
//            //HibernateUtility.SaveObject(port);
//            //System.out.println(port);
//            try {
//                JaxbUtility.setContext(Airport.class);
//                JaxbUtility.setOutputType(JaxbUtilityOutputType.StringWriter);
//                JaxbUtility.Marshal(port);
//                compound += JaxbUtility.getWriter().toString();
//                System.out.println(JaxbUtility.getWriter().toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            FileWriter xmlFile = new FileWriter("main.xml");
//            //xmlFile.write(compound);
//            xmlFile.write(JaxbUtility.getWriter().toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
