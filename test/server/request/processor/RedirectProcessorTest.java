package server.request.processor;

import junit.framework.Assert;
import org.junit.Test;
import server.Request;
import server.Response;
import server.ResponseCode;

public class RedirectProcessorTest {

    @Test
    public void testProcess(){
        String host = "localhost:5000";
        Response response = new Response(ResponseCode.MOVED);
        response.addHeader("Location", "http://" + host + "/");

        Processor processor = new RedirectProcessor();

        Request request = new Request("GET", "/redirect");
        request.setHost(host);
        Assert.assertEquals(response, processor.process(request));
    }
}
