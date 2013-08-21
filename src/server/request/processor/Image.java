package server.request.processor;

import server.Request;
import server.Response;

import java.io.File;

public class Image implements Processor{


    @Override
    public Response process(Request request) {
        File file = new File("./");
        Response response = new Response(200);
        String body = "";
        for (String value : file.list()){
            body += value;
        }
        response.setBody(body);
        return response;
    }
}
