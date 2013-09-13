package server.request.processor;

import server.Processor;
import server.Request;
import server.Response;
import server.ResponseCode;

public class RedirectProcessor implements Processor {
    @Override
    public Response process(Request request) {
        Response response = new Response(ResponseCode.MOVED);
        response.addHeader("Location", "http://" + request.getHost() + "/");
        return response;
    }
}
