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
        String expectedString = "HTTP/1.1 200 OK\n" +
                "Content-Type: text/xml; charset=utf-8\n" +
                "Content-Length:" + body.length() + "\n" +
                "\r\n" + body;
        Assert.assertEquals(expectedString, output.getValue());
    }

    @Test
    public void testWrite_ResponseWithHeader() throws IOException {
        String host = "http://localhost/";
        Response response = new Response(301);
        response.addHeader("Location", host);

        MockOutputStream output = new MockOutputStream();
        writer.write(output, response);

        String expected = "HTTP/1.1 301 Moved Permanently\n" +
                            "Location:" + host;

        Assert.assertEquals(expected, output.getValue());
    }

}
