package server.request.processor;

import junit.framework.Assert;
import org.junit.Test;
import server.Response;

public class NotFoundTest {

    @Test
    public void testProcess(){
        Processor processor = new NotFound();
        Response response = new Response(404);
        Assert.assertEquals(response, processor.process(null));
    }
}
