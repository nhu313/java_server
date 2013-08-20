package server;

import junit.framework.Assert;
import mocks.MockInputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
        InputStream input = new ByteArrayInputStream(buildRequestString(method, route, body).getBytes());
        try {
            Assert.assertEquals(expectedRequest, handler.parse(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildRequestString(String method, String route, String body) {
        StringBuffer request = new StringBuffer();
        request.append(method + ' ' + route + " HTTP/1.1\n");
        setBodyContent(body, request);
        return request.toString();
    }

    private void setBodyContent(String body, StringBuffer request) {
        if (body != null){
            int contentLength = getContentLength(body);
            request.append("Content-Length: " + contentLength);
        }
        request.append("\n\r" + body);
    }

    private int getContentLength(String body) {
        return (body == null) ? 0 : body.length();
    }

    private Request createHttpRequest(String method, String route, String body) {
        Request expectedRequest = new Request();
        expectedRequest.setMethod(method);
        expectedRequest.setPath(route);
        expectedRequest.setBody(body);
        expectedRequest.setContentLength(getContentLength(body));
        return expectedRequest;
    }
}
