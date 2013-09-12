package server;

import junit.framework.Assert;
import mocks.MockSocket;
import org.junit.Before;
import org.junit.Test;
import server.request.processor.ProcessorFactory;

public class RequestHandlerTest {
    private MockSocket socket;
    private RequestHandler handler;

    @Before
    public void setUp(){
        ProcessorFactory processorFactory = new ProcessorFactory();
        socket = new MockSocket();
        handler = new RequestHandler(processorFactory, socket);
    }

    @Test
    public void testRun() {
        socket.setRequest("GET /nonexistence HTTP/1.1");

        handler.run();
        Assert.assertEquals("HTTP/1.1 404 Not Found\n", socket.getResponse());
    }

    @Test
    public void testRun_whenExceptionIsThrownSocketIsClosed() {
        socket.setRequest("GET /nonexistence HTTP/1.1");

        handler.run();
        Assert.assertTrue(socket.isClosed());
    }
}
