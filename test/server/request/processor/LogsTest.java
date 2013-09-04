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
        Response response = new Response(ResponseCode.AUTHENTICATION_FAIL);
        response.setBody("Authentication required");
        Assert.assertEquals(response, processor.process(request));
    }

    @Test
    public void testProcess_withWrongUsernameAndPassword(){
        Request request = new Request();
        request.setUsername("something");
        request.setPassword("else");
        Response response = new Response(ResponseCode.AUTHENTICATION_FAIL);
        response.setBody("Authentication required");
        Assert.assertEquals(response, processor.process(request));
    }

    @Test
    public void testProcess_withWrongUsernameCorrectPassword(){
        Request request = new Request();
        request.setUsername("something");
        request.setPassword(Config.LOG_PASSWORD);
        Response response = new Response(ResponseCode.AUTHENTICATION_FAIL);
        response.setBody("Authentication required");
        Assert.assertEquals(response, processor.process(request));
    }

    @Test
    public void testProcess_withCorrectUsernameWrongPassword(){
        Request request = new Request();
        request.setUsername(Config.LOG_USERNAME);
        request.setPassword("not a real password");
        Response response = new Response(ResponseCode.AUTHENTICATION_FAIL);
        response.setBody("Authentication required");
        Assert.assertEquals(response, processor.process(request));
    }

    @Test
    public void testProcess_withCorrectUsernameAndPassword(){
        Request request = new Request(Method.GET.name(), "/logs");
        request.setUsername(Config.LOG_USERNAME);
        request.setPassword(Config.LOG_PASSWORD);
        Response response = processor.process(request);
        Assert.assertEquals(ResponseCode.OK, response.getCode());
        String body = new String(response.getBody());
        Assert.assertTrue(body.contains("log file"));
    }
}
