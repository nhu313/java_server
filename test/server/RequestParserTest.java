package server;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

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
        testRequest(method, route, "");
    }

    private void testRequest(String method, String route, String body) {
        String requestString = method + ' ' + route + " HTTP/1.1\nHost: localhost: 5000\n\r" + body;
        HttpRequest expectedRequest = new HttpRequest(method, route, body);
//        Assert.assertEquals(expectedRequest, handler.parse(requestString));
    }}
