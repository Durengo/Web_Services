package lt.viko.eif.stermen.project1.connection;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * This is the main wrapper around the active mq library.
 */
public class ConnectionHandler {
    private String url = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
    private ActiveMQConnectionFactory connectionFactory;
    private String queueName = "MY_QUEUE";
    private MessageSender sender = null;
    private MessageReceiver receiver = null;

    /**
     * A default constructor which creates a new connectionFactory with the default url. The default url in this case will be localhost.
     */
    public ConnectionHandler() {
        connectionFactory = new ActiveMQConnectionFactory(url);
    }


    /**
     * Sends a String message to a server.
     *
     * @param message the message to be sent.
     */
    public void sendMessage(String message) {
        if (sender != null) {
            receiver.openConnection();
            sender.startSession();
            sender.sendMessage(message);
            sender.stopSession();
        }
    }

    /**
     * Receives a message from the server and prints it to the console.
     */
    public void receiveMessage() {
        if (receiver != null) {
            receiver.openConnection();
            receiver.startSession();
            receiver.receiveMessage();
            receiver.stopSession();
        }
    }

    /**
     * This method receives a message from the server.
     *
     * @return String that is the message received from the server.
     */
    public String getMessage() {
        String msg = null;
        if (receiver != null) {
            receiver.openConnection();
            receiver.startSession();
            msg = receiver.getMessage();
            receiver.stopSession();
            return msg;
        } else {
            return msg;
        }
    }

    /**
     * Creates a MessageSender and MessageReceiver objects, so that messages can be sent or received.
     */
    public void createSenderAndReceiver() {
        if (sender == null) {
            sender = new MessageSender(connectionFactory);
            //sender = new MessageSender(session);
        }
        if (receiver == null) {
            receiver = new MessageReceiver(connectionFactory);
        }
    }

//    public void getMessageCount(String messageSelector) {}
}
