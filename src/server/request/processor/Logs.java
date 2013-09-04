package server.request.processor;

import server.Config;
import server.Request;
import server.Response;
import server.ResponseCode;

import javax.xml.bind.DatatypeConverter;

public class Logs implements Processor {
    private static final Resource resourceProcessor = new Resource();

    @Override
    public Response process(Request request) {
        if (authenticated(request)){
            String path = "/" + Config.PRIVATE_PATH + request.getPath();
            request.setPath(path);
            return resourceProcessor.process(request);
        } else {
            Response response = new Response();
            response.setCode(ResponseCode.AUTHENTICATION_FAIL);
            response.setBody("Authentication required");
            return response;
        }
    }

    private boolean authenticated(Request request) {
        if (!Config.LOG_USERNAME.equals(request.getUsername())) return false;
        if (!Config.LOG_PASSWORD.equals(request.getPassword())) return false;
        return true;
    }
}
