package server.request.processor;

import junit.framework.Assert;
import org.junit.Test;
import server.Config;
import server.Request;
import server.Response;
import server.ResponseCode;

public class NotFoundProcessorTest {

    @Test
    public void testProcess(){
        System.setProperty(Config.DIRECTORY_PATH_KEY, "./test/resource");
        Processor processor = new NotFoundProcessor();
        Response response = new Response(ResponseCode.NOT_FOUND);
        Assert.assertEquals(response, processor.process(new Request()));
    }
}
