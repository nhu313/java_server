package server.request.processor;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import server.Request;
import server.Response;
import server.ResponseCode;

public class FormTest {

    private Processor processor;

    @Before
    public void setUp(){
        processor = new Form();
    }

    @Test
    public void testProcess_SimpleGetWithNoDataSet(){
        assertProcess("GET", null, null);
    }

    @Test
    public void testProcess_SimplePostWithData(){
        assertProcess("POST", "data=cosby", "data = cosby");
    }

    @Test
    public void testProcess_SimplePutWithData(){
        assertProcess("PUT", "data=heathcliff", "data = heathcliff");
    }

    @Test
    public void testProcess_PostGetWithData(){
        String body = "Maybe";
        Request postRequest = new Request("POST", "/form");
        postRequest.setBody(body);
        processor.process(postRequest);
        assertProcess("GET", null, body);
    }

    private void assertProcess(String method, String body, String responseBody){
        Request request = new Request(method, "/form");
        request.setBody(body);
        Response response = new Response(ResponseCode.OK);
        response.setBody(responseBody);
        Assert.assertEquals(response, processor.process(request));
    }
}
