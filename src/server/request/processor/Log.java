package server.request.processor;

import server.Request;
import server.Response;

public class Log implements Processor {
    @Override
    public Response process(Request request) {
        Response response = new Response(401);
        response.setBody("Authentication required");
        return response;
    }
}
