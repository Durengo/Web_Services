package lt.viko.eif.stermen.project1.connection;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *
 */
public class MessageReceiver extends Connection {
    private String queueName = "MY_QUEUE";
    private Connection connection;
    private Destination destination = null;
    private MessageProducer producer = null;

    /**
     * @param connectionFactory
     */
    public MessageReceiver(ActiveMQConnectionFactory connectionFactory) {
        super(connectionFactory);

        try {
            destination = session.createQueue(queueName);
            producer = session.createProducer(destination);
        } catch (JMSException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }

    /**
     *
     */
    public void receiveMessage() {
        try {
            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive(1000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                //System.out.println("RECEIVED \"" + ((TextMessage) message).getText() + "\"");
                System.out.println("RECEIVED MESSAGE");
            }
        } catch (JMSException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        String text = new String();
        try {
            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive(1000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                text = ((TextMessage) message).getText();
                return text;
            }
        } catch (JMSException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        return text;
    }

    /**
     *
     */
    public void receiveAllMessages() {
        try {
            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive(10);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("RECEIVED \"" + ((TextMessage) message).getText() + "\"");
            }
        } catch (JMSException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }
}
