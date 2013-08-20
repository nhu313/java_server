package server.request.processor;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import server.Request;
import server.Response;

public class IndexTest {
    private Processor processor;

    @Before
    public void setUp(){
        processor = new Index();
    }

    @Test
    public void testProcess_SimpleRequest(){
        Request request = new Request("GET", "/");
        Response response = new Response(200);
        Assert.assertEquals(response, processor.process(request));
    }
}
