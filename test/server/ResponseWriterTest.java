package server;

import mocks.MockOutputStream;
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
        MockOutputStream output = new MockOutputStream();
        writer.write(output, response);
        Assert.assertEquals("HTTP/1.1 200 OK", output.getValue());
    }

    @Test
    public void testWrite_ResponseWithBody() throws IOException {
        String body = "Why can't you trust atom? They make up everything.";
        Response response = new Response(200);
        response.setBody(body);
        MockOutputStream output = new MockOutputStream();
        writer.write(output, response);
        Assert.assertEquals("HTTP/1.1 200 OK\r\n" + body + "\r\n", output.getValue());
    }

}
