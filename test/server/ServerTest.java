package server;

import junit.framework.Assert;
import mocks.MockOutputStream;
import mocks.MockServerIO;
import mocks.MockServerSocket;
import org.junit.Before;
import org.junit.Test;
import server.Server;

import java.net.Socket;

public class ServerTest {

    private MockServerSocket mockServerSocket;
    private Server server;
    private MockServerIO mockServerIO;

    @Before
    public void setUp() throws Exception {
        mockServerSocket = new MockServerSocket();
        mockServerIO = new MockServerIO();
        server = new Server(mockServerSocket, mockServerIO);
    }

    @Test
    public void testStart_returnsHttpOKResponseCode() throws Exception {
        server.start();
        Assert.assertEquals("HTTP/1.1 200 OK", mockServerIO.getResponseMessage());
    }



    @Test
    public void testStart_ReturnContentOnPOSTToRoute() {
        String request =
                "POST /somepage.php HTTP/1.1\n" +
                "Host: example.com\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Content-Length: 19\n" +
                "\n" +
                "data = cosby";
        mockServerIO.setRequest(request);
        server.start();
        Assert.assertTrue(mockServerIO.getResponseMessage().contains("200"));
        Assert.assertTrue(mockServerIO.getResponseMessage().contains("data = cosby"));
    }

    @Test
    public void testStart_closeClientSocket() throws Exception {
        mockServerIO.setRequest("H");
        server.start();
        Socket clientSocket = mockServerSocket.accept();
        Assert.assertTrue(clientSocket.isClosed());
    }
}