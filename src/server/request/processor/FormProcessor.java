package server.request.processor;

import server.*;

public class FormProcessor implements Processor {
    private String value;

    @Override
    public Response process(Request request) {
        Response response = new Response(ResponseCode.OK);
        String body = request.getBody();
        if (body != null && HttpMethod.GET != request.getHttpMethod()){
            String responseBody = body.replace("=", " = ");
            value = responseBody;
        }
        response.setBody(value);
        return response;
    }
}
