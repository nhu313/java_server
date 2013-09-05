package server.request.processor;

import junit.framework.Assert;
import org.junit.Test;
import server.Response;
import server.ResponseCode;

public class NotFoundProcessorTest {

    @Test
    public void testProcess(){
        Processor processor = new NotFoundProcessor();
        Response response = new Response(ResponseCode.NOT_FOUND);
        Assert.assertEquals(response, processor.process(null));
    }
}
