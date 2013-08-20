package server.request.processor;

import junit.framework.Assert;
import org.junit.Test;
import server.Request;
import server.Response;

public class RedirectTest {

    @Test
    public void testProcess(){
        String host = "localhost:5000";
        Processor processor = new Redirect();
        Response response = new Response(301);
        response.addHeader("Location", "http://" + host + "/");

        Request request = new Request("GET", "/redirect");
        request.setHost(host);
        Assert.assertEquals(response, processor.process(request));
    }
}
