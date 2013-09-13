package server.request.processor;

import server.Processor;
import server.Request;
import server.Response;
import server.ResponseCode;

import java.util.Map;

public class ParametersProcessor implements Processor {


    @Override
    public Response process(Request request) {
        Response response = new Response(ResponseCode.OK);
        String body = "";
        for (Map.Entry<String, String> entry : request.getParams().entrySet()){
            body += entry.getKey() + " = " + entry.getValue();
        }
        response.setBody(body);
        return response;
    }
}
