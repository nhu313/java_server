package server.request.processor;

import server.Request;
import server.Response;
import server.ResponseCode;

public class NotFound implements Processor{
    @Override
    public Response process(Request request) {
        return new Response(ResponseCode.NOT_FOUND);
    }
}
