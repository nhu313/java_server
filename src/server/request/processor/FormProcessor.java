package server.request.processor;

import server.Method;
import server.Request;
import server.Response;
import server.ResponseCode;

public class FormProcessor implements Processor{
    private String value;

    @Override
    public Response process(Request request) {
        Response response = new Response(ResponseCode.OK);
        String body = request.getBody();
        if (body != null && Method.GET != request.getMethod()){
            String responseBody = body.replace("=", " = ");
            value = responseBody;
        }
        response.setBody(value);
        return response;
    }
}
