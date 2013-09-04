package server.request.processor;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import server.*;

public class LogsTest {

    private Processor processor;

    @Before
    public void setUp(){
        System.setProperty(Config.DIRECTORY_PATH_KEY, "./test/resource");
        processor = new Logs();
    }

    @Test
    public void testProcess_withNoNoUsernameOrPassword(){
        Request request = new Request();
        assertAuthenticationFailureResponse(request);
    }

    private void assertAuthenticationFailureResponse(Request request) {
        Response response = new Response(ResponseCode.AUTHENTICATION_FAIL);
        response.setBody("Authentication required");
        Assert.assertEquals(response, processor.process(request));
    }

    @Test
    public void testProcess_withWrongUsernameAndPassword(){
        Request request = createRequest("something", "else");
        assertAuthenticationFailureResponse(request);
    }

    @Test
    public void testProcess_withWrongUsernameCorrectPassword(){
        Request request = createRequest("something", Config.LOG_PASSWORD);
        assertAuthenticationFailureResponse(request);
    }

    @Test
    public void testProcess_withCorrectUsernameWrongPassword(){
        Request request = createRequest(Config.LOG_USERNAME, "not a real password");
        assertAuthenticationFailureResponse(request);
    }

    private Request createRequest(String username, String password) {
        Request request = new Request(Method.GET.name(), "/logs");
        request.setUsername(username);
        request.setPassword(password);
        return request;
    }

    @Test
    public void testProcess_withCorrectUsernameAndPassword(){
        Request request = createRequest(Config.LOG_USERNAME, Config.LOG_PASSWORD);
        Response response = processor.process(request);
        Assert.assertEquals(ResponseCode.OK, response.getCode());
        String body = new String(response.getBody());
        Assert.assertTrue(body.contains("log file"));
    }
}
