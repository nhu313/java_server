package server;

import junit.framework.Assert;
import mocks.MockSocket;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class RequestParserTest {
    RequestParser handler;

    @Before
    public void setUp(){
        handler = new RequestParser();
    }

    @Test
    public void parseGetIndexRequest(){
        testRequest("GET", "/");
    }

    @Test
    public void parseGetSpecificRouteRequest(){
        testRequest("GET", "/board");
    }

    @Test
    public void parseEmptyPostRequest(){
        testRequest("POST", "/form");
    }

    @Test
    public void testParseRequest_withBody(){
        String textBody = "request body";
        testRequest("POST", "/file", textBody);
    }

    @Test
    public void testParseRequest_withNewLineInBody(){
        String textBody =
                "Vivian: Philip, when I met you, you were into James Brown.\n" +
                "Will: He liked James Brown?\n" +
                "Vivian: He even wore his hair like him.\n" +
                "Will: [laughs] He had hair?";
        testRequest("POST", "/file", textBody);
    }

    private void testRequest(String method, String route) {
        testRequest(method, route, null);
    }

    private void testRequest(String method, String route, String body) {
        Request expectedRequest = createHttpRequest(method, route, body);
        StringBuffer request = new StringBuffer();
        request.append(method + ' ' + route + " HTTP/1.1\n");
        setBodyContent(body, expectedRequest, request);
        MockSocket socket = new MockSocket();
        socket.setRequest(request.toString());
        try {
            Assert.assertEquals(expectedRequest, handler.parse(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setBodyContent(String body, Request expectedRequest, StringBuffer request) {
        if (body != null){
            int contentLength = (body == null) ? 0 : body.length();
            request.append("Content-Length: " + contentLength);
            expectedRequest.setContentLength(contentLength);
        }
        request.append("\n\r" + body);
    }

    private Request createHttpRequest(String method, String route, String body) {
        Request expectedRequest = new Request();
        expectedRequest.setMethod(method);
        expectedRequest.setPath(route);
        expectedRequest.setBody(body);
        return expectedRequest;
    }
}
