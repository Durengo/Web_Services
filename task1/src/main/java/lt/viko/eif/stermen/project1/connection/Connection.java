package lt.viko.eif.stermen.project1.connection;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 *
 */
public class Connection {
    protected javax.jms.Connection connection = null;
    protected Session session = null;
    private ActiveMQConnectionFactory connectionFactory;

    /**
     *
     * @param factory
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
     *
     */
    public void startSession() {
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            System.out.println("FAILURE STARTING SESSION | EXCEPTION: " + e.getMessage());
        }
    }

    /**
     *
     */
    public void stopSession() {
        try {
            session.close();
        } catch (JMSException e) {
            System.out.println("FAILURE STARTING SESSION | EXCEPTION: " + e.getMessage());
        }
    }

    /**
     *
     */
    public void openConnection() {
        try {
            connection.start();
        } catch (JMSException e) {
            System.out.println("FAILURE OPENING CONNECTION | EXCEPTION: " + e.getMessage());
        }
    }

    /**
     *
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException e) {
            System.out.println("FAILURE CLOSING CONNECTION | EXCEPTION: " + e.getMessage());
        }
    }
}
