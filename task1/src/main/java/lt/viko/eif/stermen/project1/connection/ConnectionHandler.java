package lt.viko.eif.stermen.project1.connection;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 */
public class ConnectionHandler {
    private String url = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
    private ActiveMQConnectionFactory connectionFactory;
    private String queueName = "MY_QUEUE";
    private MessageSender sender = null;
    private MessageReceiver receiver = null;

    /**
     *
     */
    public ConnectionHandler() {
        connectionFactory = new ActiveMQConnectionFactory(url);
    }


    /**
     * @param message
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
     *
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
     *
     * @return
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
     *
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
