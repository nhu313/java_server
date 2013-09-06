package server.request.processor;

import junit.framework.Assert;
import org.junit.Test;
import server.Request;
import server.Response;

import java.util.HashMap;
import java.util.Map;

public class ParametersProcessorTest {

    @Test
    public void testProcess(){
        Request request = new Request();
        String variable1 = "variable_1";
        String variable2 = "variable_2";

        Map<String, String> params = new HashMap<String, String>();
        params.put(variable1, "Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?");
        params.put(variable2, "stuff");
        request.setParams(params);

        Response response = new ParametersProcessor().process(request);
        String responseBody = new String(response.getBody());
        Assert.assertTrue(responseBody.contains(variable1 + " = " + params.get(variable1)));
        Assert.assertTrue(responseBody.contains(variable2 + " = " + params.get(variable2)));
    }
}
