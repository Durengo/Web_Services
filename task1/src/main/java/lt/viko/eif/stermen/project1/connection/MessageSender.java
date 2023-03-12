package lt.viko.eif.stermen.project1.connection;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.StringWriter;

/**
 * This class enables the sending of messages to the ActiveMQ server queue.
 */
public class MessageSender extends Connection {
    private String queueName = "MY_QUEUE";
    private Connection connection;
    private Destination destination = null;
    private MessageProducer producer = null;

    /**
     * @param connectionFactory provides a connection factory to the super class which is the Connection class. Enables connection functionality.
     */
    public MessageSender(ActiveMQConnectionFactory connectionFactory) {
        super(connectionFactory);

        try {
            destination = session.createQueue(queueName);
            producer = session.createProducer(destination);
        } catch (JMSException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }


    /**
     * Sends a message to the server queue.
     * @param text the message to be sent to the server.
     */
    public void sendMessage(String text) {
        try {
            TextMessage message = session.createTextMessage(text);

            producer.send(message);

            //System.out.println("SENDING MESSAGE \"" + message.getText() + "\" TO " + QUEUE_NAME);
            System.out.println("SENDING MESSAGE TO " + queueName);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }

//    public void sendMessage(StringWriter writer) {}
}
