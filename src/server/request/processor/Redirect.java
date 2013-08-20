package server.request.processor;

import server.Request;
import server.Response;

public class Redirect implements Processor {
    @Override
    public Response process(Request request) {
        Response response = new Response(301);
        response.addHeader("Location", "http://" + request.getHost() + "/");
        return response;
    }
}
