package server;

import mocks.MockSocket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ResponseWriterTest {
    private ResponseWriter writer;

    @Before
    public void setUp(){
        writer = new ResponseWriter();
    }

    @Test
    public void testWrite_Simple200Response() throws IOException {
        Response response = new Response(200);
        MockSocket socket = new MockSocket();
        writer.write(socket, response);
        Assert.assertEquals("HTTP/1.1 200 OK", socket.getResponse());
    }

}
