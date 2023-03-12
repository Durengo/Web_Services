package lt.viko.eif.stermen.utilityTest;

import lt.viko.eif.stermen.project1.connection.ConnectionHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageTest {
    @Test
    public void sendAndReceive()
    {
        final String expected = "test_msg";
        ConnectionHandler connection = new ConnectionHandler();
        connection.createSenderAndReceiver();
        connection.sendMessage(expected);
        final String actual = connection.getMessage();
        assertEquals(expected, actual);
    }
}
