package server;

import junit.framework.Assert;
import mocks.MockSocket;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import server.request.processor.ProcessorFactory;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerTest {

    private static final String HTTP_REQUEST = "GET /nonexistence HTTP/1.1";

    @Before
    public void setUp(){
        System.setProperty(Config.DIRECTORY_PATH_KEY, "./test/resource");
    }

    @Test
    public void testStart_whenSocketIsClosed(){
        ServerSocket serverSocket = getMockServerSocket(true);
        EasyMock.replay(serverSocket);

        Server server = new Server(serverSocket);
        server.start();

        EasyMock.verify(serverSocket);
    }

    @Test
    public void testStart_whenSocketIsOpened() throws IOException {
        ServerSocket serverSocket = getMockServerSocket(false, true);

        MockSocket socket = new MockSocket();
        EasyMock.expect(serverSocket.accept()).andReturn(socket);
        socket.setRequest(HTTP_REQUEST);

        EasyMock.replay(serverSocket);
        Server server = new Server(serverSocket);
        server.start();

        Assert.assertEquals("HTTP/1.1 404 Not Found\n", socket.getResponse());

        EasyMock.verify(serverSocket);
    }

    @Test
    public void testStart_closeClientSocket() throws IOException {
        ServerSocket serverSocket = getMockServerSocket(false, true);

        MockSocket socket = new MockSocket();
        EasyMock.expect(serverSocket.accept()).andReturn(socket);
        socket.setRequest(HTTP_REQUEST);

        EasyMock.replay(serverSocket);
        Server server = new Server(serverSocket);
        server.start();

        Assert.assertTrue(socket.isClosed());

        EasyMock.verify(serverSocket);
    }

    private ServerSocket getMockServerSocket(boolean... returnValues) {
        ServerSocket serverSocket = EasyMock.createMock(ServerSocket.class);
        for (boolean value : returnValues){
            EasyMock.expect(serverSocket.isClosed()).andReturn(value);
        }
        return serverSocket;
    }
}