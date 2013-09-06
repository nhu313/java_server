package server.request.processor;

import server.Request;
import server.Response;
import server.ResponseCode;

public class NotFoundProcessor implements Processor{
    @Override
    public Response process(Request request) {
        server.Logger.info(request.getHttpMethod() + " " + request.getPath() + " HTTP/1.1");
        return new Response(ResponseCode.NOT_FOUND);
    }
}
