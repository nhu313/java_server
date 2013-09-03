package server;

import junit.framework.Assert;
import mocks.MockSocket;
import org.easymock.EasyMock;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerTest {

    @Test
    public void testStart_whenSocketIsClosed(){
        ServerSocket serverSocket = getMockServerSocket(true);
        EasyMock.replay(serverSocket);

        Server server = new Server(serverSocket, null, null);
        server.start();

        EasyMock.verify(serverSocket);
    }

    @Test
    public void testStart_whenSocketIsOpened() throws IOException {
        MockSocket socket = new MockSocket();
        ServerSocket serverSocket = getMockServerSocket(false, true);
        EasyMock.expect(serverSocket.accept()).andReturn(socket);

        RequestParser parser = EasyMock.createMock(RequestParser.class);
        Request request = new Request();
        EasyMock.expect(parser.parse(socket.getInputStream())).andReturn(request);

        Response response = new Response(404);
        ResponseWriter writer = EasyMock.createMock(ResponseWriter.class);
        writer.write(socket.getOutputStream(), response);
        EasyMock.expectLastCall();

        EasyMock.replay(serverSocket, parser, writer);

        Server server = new Server(serverSocket, parser, writer);
        server.start();

        EasyMock.verify(serverSocket, parser, writer);
    }

    @Test
    public void testStart_closeClientSocket() throws IOException {
        MockSocket socket = new MockSocket();
        ServerSocket serverSocket = getMockServerSocket(false, true);
        EasyMock.expect(serverSocket.accept()).andReturn(socket);

        Request request = new Request();
        RequestParser parser = EasyMock.createMock(RequestParser.class);
        EasyMock.expect(parser.parse(socket.getInputStream())).andReturn(request);

        ResponseWriter writer = EasyMock.createNiceMock(ResponseWriter.class);

        EasyMock.replay(serverSocket, parser, writer);

        Server server = new Server(serverSocket, parser, writer);
        server.start();

        EasyMock.verify(serverSocket, parser, writer);

        Assert.assertTrue(socket.isClosed());
    }

    private ServerSocket getMockServerSocket(boolean... returnValues) {
        ServerSocket serverSocket = EasyMock.createMock(ServerSocket.class);
        for (boolean value : returnValues){
            EasyMock.expect(serverSocket.isClosed()).andReturn(value);
        }
        return serverSocket;
    }
}