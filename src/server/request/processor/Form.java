package server.request.processor;

import server.Method;
import server.Request;
import server.Response;

public class Form implements Processor{
    private String value;

    @Override
    public Response process(Request request) {
        Response response = new Response(200);
        String body = request.getBody();
        if (body != null && Method.GET != request.getMethod()){
            String responseBody = body.replace("=", " = ");
            value = responseBody;
        }
        response.setBody(value);
        return response;
    }
}
