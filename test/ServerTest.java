import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.OutputStream;
import java.net.Socket;

public class ServerTest {
    private MockServerSocket mockServerSocket;
    private Server server;
    @Before
    public void setUp() throws Exception {
        mockServerSocket = new MockServerSocket();

        server = new Server();
        server.setSocket(mockServerSocket);
    }

    @Test
    public void testStart_returnsHttpOKResponseCode() throws Exception {
        server.start();
        Assert.assertEquals("HTTP/1.1 200 OK", mockServerSocket.getOutputValue());
    }

    @Test
    public void testStart_closeClientSocket() throws Exception {
        server.start();
        Socket clientSocket = mockServerSocket.accept();
        Assert.assertTrue(clientSocket.isClosed());
    }

    @Test
    public void testStart_closeOutputStream() throws Exception {
        server.start();
        MockOutputStream clientSocket = (MockOutputStream)mockServerSocket.accept().getOutputStream();
        Assert.assertTrue(clientSocket.isClosed());
    }
}