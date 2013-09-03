package server.request.processor;

import server.Request;
import server.Response;
import server.ResponseCode;

public class Redirect implements Processor {
    @Override
    public Response process(Request request) {
        Response response = new Response(ResponseCode.MOVED);
        response.addHeader("Location", "http://" + request.getHost() + "/");
        return response;
    }
}
