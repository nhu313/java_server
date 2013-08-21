package server.request.processor;

import server.Request;
import server.Response;

import java.util.Map;

public class Parameters implements Processor{


    @Override
    public Response process(Request request) {
        Response response = new Response(200);
        String body = "";
        for (Map.Entry<String, String> entry : request.getParams().entrySet()){
            body += entry.getKey() + " = " + entry.getValue();
        }
        response.setBody(body);
        return response;
    }
}
