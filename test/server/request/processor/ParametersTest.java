package server.request.processor;

import junit.framework.Assert;
import org.junit.Test;
import server.Request;
import server.Response;

import java.util.HashMap;
import java.util.Map;

public class ParametersTest {

    @Test
    public void testProcess(){
        Request request = new Request();

        Map<String, String> params = new HashMap<String, String>();
        params.put("variable_1", "Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?");
        params.put("variable_2", "stuff");
        request.setParams(params);

        String responseBody = new Parameters().process(request).getBody();
        Assert.assertTrue(responseBody.contains("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?"));
        Assert.assertTrue(responseBody.contains("variable_2 = stuff"));
    }
}
