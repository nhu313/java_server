package server;

import junit.framework.Assert;
import mocks.MockSocket;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ServerStringIOTest {
    private ServerStringIO serverIO;
    private MockSocket socket;

    @Before
    public void setUp(){
        socket = new MockSocket();
        serverIO = new ServerStringIO();
    }

    @Test
    public void getRequest() throws IOException {
        String request = "some request";
        socket.setRequest(request);
        Assert.assertEquals(request, serverIO.readRequest(socket));
    }

    @Test
    public void testWrite_testMessage() throws IOException {
        String response = "Now, this is my brother Carlton. We can't afford new clothes, so he just doesn't grow!";
        serverIO.writeResponse(socket, response);
        Assert.assertEquals(response, socket.getResponse());
    }

    @Test
    public void testWrite_ClosesOutputStream() throws IOException {
        serverIO.writeResponse(socket, "something");
        Assert.assertTrue(socket.getOutputStream().isClosed());
    }


}
