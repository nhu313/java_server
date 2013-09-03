package server.request.processor;

import server.Request;
import server.Response;
import server.ResponseCode;

import javax.xml.bind.DatatypeConverter;

public class Logs implements Processor {
    @Override
    public Response process(Request request) {
        Response response = new Response(ResponseCode.AUTHENTICATION_FAIL);
        response.setBody("Authentication required");
        return response;
    }
}
