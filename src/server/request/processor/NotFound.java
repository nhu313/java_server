package server.request.processor;

import server.Request;
import server.Response;

public class NotFound implements Processor{
    @Override
    public Response process(Request request) {
        return new Response(404);
    }
}
