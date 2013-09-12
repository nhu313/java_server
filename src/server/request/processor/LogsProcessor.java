package server.request.processor;

import server.Config;
import server.Request;
import server.Response;
import server.ResponseCode;

public class LogsProcessor implements Processor {
    private static final FileProcessor FILE_PROCESSOR_PROCESSOR = new FileProcessor();

    @Override
    public Response process(Request request) {
        if (authenticated(request)){
            return createAuthenticatedResponse(request);
        } else {
            return createFailedAuthenticationResponse();
        }
    }

    private Response createAuthenticatedResponse(Request request) {
        request.setPath(Config.LOG_PATH);
        return FILE_PROCESSOR_PROCESSOR.process(request);
    }

    private Response createFailedAuthenticationResponse() {
        Response response = new Response();
        response.setCode(ResponseCode.AUTHENTICATION_FAIL);
        response.setBody("Authentication required");
        return response;
    }

    private boolean authenticated(Request request) {
        if (!Config.LOG_USERNAME.equals(request.getUsername())) return false;
        if (!Config.LOG_PASSWORD.equals(request.getPassword())) return false;
        return true;
    }
}