package lt.viko.eif.stermen.project1.ui;

import lt.viko.eif.stermen.project1.connection.ConnectionHandler;
import lt.viko.eif.stermen.project1.domain.airport.Airport;
import lt.viko.eif.stermen.project1.utility.HibernateUtility;
import lt.viko.eif.stermen.project1.utility.JaxbUtility;
import lt.viko.eif.stermen.project1.utility.JaxbUtilityOutputType;
import lt.viko.eif.stermen.project1.utility.Setup;
import org.apache.log4j.Logger;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;

import static lt.viko.eif.stermen.project1.input.Input.*;

/**
 * A simple user interface class that utilizes the console to display the functionality of JAXB transformations, Database functionality, and others that are necessary for task 1.
 */
public class Ui {
    final static Logger logger = Logger.getLogger("LOGGER");
    ConnectionHandler connection = null;
    private Airport airport = null;
    private boolean airportSaved = false;
    private boolean messageSent = false;
    private boolean messageReceived = false;
    private boolean fileLoaded = false;

    /**
     * A default constructor that creates the initial connection to the database, creates the ActiveMQ handlers, and sets the JAXB properties.
     */
    public Ui() {
        HibernateUtility.getConnectionHandler();
        connection = new ConnectionHandler();
        connection.createSenderAndReceiver();
        JaxbUtility.setContext(Airport.class);
        JaxbUtility.setFilepath("main.xml");
        JaxbUtility.setXsdSchema("task1/target/classes/Airport.xsd");
    }

    /**
     * The initial main menu from which the user will navigate to all the functionality.
     */
    public void begin() {
        int input = -1;
        while (true) {
            logger.debug("SELECT FUNCTION:\n0. EXIT\n1. SHOW XML\n2. SHOW DATABASE\n3. GENERATE AIRPORT XML\n4. SAVE XML\n5. LOAD XML\n6. SEND XML OVER ACTIVEMQ\n7. RECEIVE XML OVER ACTIVEMQ\n8. DEBUG");
            if (!airportSaved && airport != null) {
                logger.info("AIRPORT HAS NOT BEEN SAVED YET");
            }
            input = gibi(0, 8);
            switch (input) {
                case 0:
                    System.exit(0);
                    return;
                case 1:
                    displayXml();
                    break;
                case 2:
                    displayDatabase();
                    break;
                case 3:
                    generateXml();
                    break;
                case 4:
                    saveXml();
                    break;
                case 5:
                    loadXml();
                    break;
                case 6:
                    sendXml();
                    break;
                case 7:
                    receiveXml();
                    break;
                case 8:
                    debug();
                    break;
            }
        }

    }

    /**
     * Allows the display of the airport object if it is not null. Can be displayed as a console print of object or as XML file.
     */
    private void displayXml() {
        logger.debug("DISPLAYING XML:");

        if (airport == null) {
            logger.error("XML IS NULL");
        } else {
            int input = -1;
            while (true) {
                logger.debug("HOW TO DISPLAY?\n0. RETURN\n1. AS XML\n2. AS CONSOLE");
                input = gibi();
                switch (input) {
                    case 0:
                        return;
                    case 1:
                        JaxbUtility.setOutputType(JaxbUtilityOutputType.Console);
                        JaxbUtility.transformToXML(airport);
                        break;
                    case 2:
                        logger.debug(airport);
                        break;
                }
            }
        }
    }

    /**
     * Displays the all the airport objects in the database.
     */
    private void displayDatabase() {
        logger.debug("DISPLAYING DATABASE:");
        List<Airport> airportDatabase = HibernateUtility.loadObjects(Airport.class);
        for (Airport port : airportDatabase) {
            logger.info(port);
        }
        if (airportDatabase.size() == 0) {
            logger.debug("DATABASE EMPTY");
        }
    }

    /**
     * Generates a semi-randomized xml file from the Setup class and saves it as the airport object.
     */
    private void generateXml() {
        if (!airportSaved && airport != null) {
            logger.debug("AIRPORT HAS NOT BEEN SAVED. DO YOU WANT TO CONTINUE? (Y/N)");
            String input = GetYorN();
            if (input.equals("N")) {
                return;
            }
        }
        logger.debug("GENERATING RANDOM AIRPORT XML");
        airport = Setup.generateXml();
        airportSaved = false;
        fileLoaded = false;
    }

    /**
     * Saves the airport object if it is not null. Can be saved to the database or saved to file.
     */
    private void saveXml() {
        if (airport == null) {
            logger.error("AIRPORT IS NULL");
            return;
        }
        int input = -1;
        while (true) {
            logger.debug("HOW TO SAVE?\n0. RETURN\n1. TO DATABASE\n2. TO FILE");
            input = gibi(0, 2);
            switch (input) {
                case 0:
                    return;
                case 1:
                    if ((messageReceived || fileLoaded) && airport != null) {
                        airport.resetIds();
                        messageReceived = false;
                        fileLoaded = false;
                    }
                    HibernateUtility.saveObject(airport);
                    airportSaved = true;
                    logger.info("AIRPORT SAVED TO DATABASE SUCCESSFULLY!");
                    return;
                case 2:
                    JaxbUtility.setOutputType(JaxbUtilityOutputType.File);
                    JaxbUtility.transformToXML(airport);
                    airportSaved = true;
                    logger.info("AIRPORT SAVED TO FILE AS XML SUCCESSFULLY!");
                    return;
            }

        }
    }

    /**
     * Loads a new airport object. If the current airport object is not null informs user that it will be removed from the instance.
     * Airport object can be loaded from the database or as file.
     * main.xml is a large file.
     * small.xml is a small file.
     */
    private void loadXml() {
        if (airport != null) {
            logger.debug("CURRENT AIRPORT WILL BE UNLOADED. DO YOU WANT TO CONTINUE? (Y/N)");
            String input = GetYorN();
            if (input.equals("N")) {
                return;
            }
        }
        int input = -1;
        while (true) {
            logger.debug("HOW TO LOAD?\n0. RETURN (AIRPORT WILL NOT BE REMOVED)\n1. FROM DATABASE (BY INDEX)\n2. FROM FILE (\"main.xml\")\n3. FROM FILE (\"small.xml\")");
            input = gibi(0, 3);
            switch (input) {
                case 0:
                    return;
                case 1:
                    List<Airport> airportList = HibernateUtility.loadObjects(Airport.class);
                    if (airportList == null || airportList.size() == 0) {
                        logger.error("NO AIRPORTS PRESENT IN DATABASE. RETURNING.");
                        break;
                    }
                    logger.debug("CHOOSE INDEX: {0}-{" + (airportList.size() - 1) + "}");
                    int input2 = gibi(0, airportList.size() - 1);
                    logger.debug(airportList.get(input2));
                    logger.info("SELECT THIS AS AIRPORT? (Y/N)");
                    String input3 = GetYorN();
                    if (input3.equals("Y")) {
                        airport = airportList.get(input2);
                        airportSaved = true;
                        fileLoaded = false;
                        logger.info("AIRPORT SELECTED FROM DATABASE SUCCESSFULLY!");
                        return;
                    } else {
                        break;
                    }
                case 2:
                    JaxbUtility.setOutputType(JaxbUtilityOutputType.File);
                    JaxbUtility.setFilepath("main.xml");
                    airport = JaxbUtility.transformToPOJO(Airport.class);
                    airportSaved = false;
                    fileLoaded = true;
                    logger.info("AIRPORT SAVED TO FILE AS XML SUCCESSFULLY!");
                    return;
                case 3:
                    JaxbUtility.setOutputType(JaxbUtilityOutputType.File);
                    JaxbUtility.setFilepath("task1/target/classes/small.xml");
                    airport = JaxbUtility.transformToPOJO(Airport.class);
                    airportSaved = false;
                    fileLoaded = true;
                    logger.info("AIRPORT SAVED TO FILE AS XML SUCCESSFULLY!");
                    return;
            }
        }
    }

    /**
     * Utilizes the ConnectionHandler class to connect to an ActiveMQ server and send the airport object as XML String to the server queue.
     */
    private void sendXml() {
        if (airport == null) {
            logger.debug("AIRPORT IS NULL");
            return;
        }
        if (messageSent) {
            logger.error("MESSAGE HAS ALREADY BEEN SENT AND MUST BE RECEIVED FIRST.");
        }
        JaxbUtility.setOutputType(JaxbUtilityOutputType.StringWriter);
        JaxbUtility.transformToXML(airport);
        connection.sendMessage(JaxbUtility.getWriter().toString());
        messageSent = true;
    }

    /**
     * Utilizes the ConnectionHandler class to connect to an ActiveMQ server and receive an airport object as XML String from the server queue.
     */
    private void receiveXml() {
        if (airport != null) {
            logger.debug("CURRENT AIRPORT WILL BE UNLOADED. DO YOU WANT TO CONTINUE? (Y/N)");
            String input = GetYorN();
            if (input.equals("N")) {
                return;
            }
        }
        String str = "";
        str = connection.getMessage();
        if (!Objects.equals(str, "")) {
            logger.debug(str.toString());
            logger.debug("MESSAGE RECEIVED");
            JaxbUtility.setOutputType(JaxbUtilityOutputType.StringWriter);
            StringWriter sw = new StringWriter();
            sw.write(str);
            JaxbUtility.setWriter(sw);
            airport = JaxbUtility.transformToPOJO(Airport.class);
            logger.debug("AIRPORT CREATED");
            messageSent = false;
            messageReceived = true;
        }
    }

    /**
     * Debugging functionality.
     * 1. Clearing all the records from the database. This does not reset the sequence counter.
     */
    private void debug() {
        int input = -1;
        while (true) {
            logger.debug("DEBUG OPTIONS:\n0. RETURN\n1. CLEAR DATABASE");
            input = gibi(0, 1);
            switch (input) {
                case 0:
                    return;
                case 1:
                    HibernateUtility.clearDatabase();
                    break;
            }
        }

    }
}
