package lt.viko.eif.stermen.project1.connection;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.StringWriter;

/**
 *
 */
public class MessageSender extends Connection {
    private String queueName = "MY_QUEUE";
    private Connection connection;
    private Destination destination = null;
    private MessageProducer producer = null;

    /**
     * @param connectionFactory
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
     * @param text
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
