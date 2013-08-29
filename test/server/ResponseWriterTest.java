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
        Assert.assertEquals("HTTP/1.1 200 OK\n", output.getValue());
    }

    @Test
    public void testWrite_ResponseWithBody() throws IOException {
        String body = "Why can't you trust atom? They make up everything.";
        Response response = new Response(200);
        response.setBody(body);
        MockOutputStream output = new MockOutputStream();
        writer.write(output, response);
        String expectedString = "HTTP/1.1 200 OK\n" +
                "Content-Type: text/html\n" +
                "Content-Length: " + body.length() + "\n" +
                "\r\n" + body;
        Assert.assertEquals(expectedString, output.getValue());
    }

    @Test
    public void testWrite_ResponseWithBodyWithContentType() throws IOException {
        String body = "The Internet: where men are men, women are men, and children are FBI agents.";
        String contentType = "text/plain";
        Response response = new Response(200);
        response.setBody(body);
        response.setContentType(contentType);

        MockOutputStream output = new MockOutputStream();

        writer.write(output, response);

        String expectedString = "HTTP/1.1 200 OK\n" +
                "Content-Type: " + contentType + "\n" +
                "Content-Length: " + body.length() + "\n" +
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
                          "Location:" + host + "\n";

        Assert.assertEquals(expected, output.getValue());
    }

}
