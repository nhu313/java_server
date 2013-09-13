package server.request.processor;

import server.*;

import java.io.File;

public class IndexProcessor implements Processor {

    @Override
    public Response process(Request request) {
        Response response = new Response(ResponseCode.OK);
        response.setBody(buildBody());
        return response;
    }

    private String buildBody() {
        StringBuilder fileList = new StringBuilder();
        fileList.append("<ul>");
        for (String fileName : FileFactory.getPublicFileNames()){
            fileList.append("<li><a href=\"/" + fileName + "\">" + fileName + "</a></li>");
        }
        fileList.append("</ul>");
        return fileList.toString();
    }
}
