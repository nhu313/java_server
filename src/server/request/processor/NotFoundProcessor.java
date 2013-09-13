package server.request.processor;

import server.*;

public class NotFoundProcessor implements Processor {
    @Override
    public Response process(Request request) {
        Logger.info(request.getHttpMethod() + " " + request.getPath() + " HTTP/1.1");
        return new Response(ResponseCode.NOT_FOUND);
    }
}
