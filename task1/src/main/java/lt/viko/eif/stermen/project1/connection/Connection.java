package lt.viko.eif.stermen.project1.connection;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * This is the base class for MessageReceiver and MessageSender. It provides the basic functionality to start/stop the entire connection and/or a session.
 */
public class Connection {
    protected javax.jms.Connection connection = null;
    protected Session session = null;
    private ActiveMQConnectionFactory connectionFactory;

    /**
     * This constructor creates the initial session with all the required parameters.
     * @param factory the ActiveMQConnectionFactory object created from the ConnectionHandler class wrapper.
     */
    public Connection(ActiveMQConnectionFactory factory) {
        try {
            connectionFactory = factory;
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            System.out.println("FAILURE IN INSTANTIATING CONNECTION | EXCEPTION: " + e.getMessage());
        }
    }

    /**
     * Starts the session with the already enabled connection, so that an action can be taken.
     */
    public void startSession() {
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            System.out.println("FAILURE STARTING SESSION | EXCEPTION: " + e.getMessage());
        }
    }

    /**
     * Stops the session with the already enabled connection, to indicate that an action has been taken.
     */
    public void stopSession() {
        try {
            session.close();
        } catch (JMSException e) {
            System.out.println("FAILURE STARTING SESSION | EXCEPTION: " + e.getMessage());
        }
    }

    /**
     * Opens the connection to the server. Afterwards a session must be opened to execute an action.
     */
    public void openConnection() {
        try {
            connection.start();
        } catch (JMSException e) {
            System.out.println("FAILURE OPENING CONNECTION | EXCEPTION: " + e.getMessage());
        }
    }

    /**
     * Closes the connection to the server. The connection will not be restored unless a new factory is created.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException e) {
            System.out.println("FAILURE CLOSING CONNECTION | EXCEPTION: " + e.getMessage());
        }
    }
}
