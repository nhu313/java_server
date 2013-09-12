package server.request.processor;

import server.Config;
import server.Request;
import server.Response;
import server.ResponseCode;

import java.io.File;

public class IndexProcessor implements Processor{

    @Override
    public Response process(Request request) {
        File directory = new File(Config.PUBLIC_DIRECTORY);
        File[] contents = directory.listFiles();
        Response response = new Response(ResponseCode.OK);
        response.setBody(buildBody(contents));
        return response;
    }

    private String buildBody(File[] contents) {
        StringBuilder fileList = new StringBuilder();
        fileList.append("<ul>");
        for (String fileName : FileFactory.getPublicFileNames()){
            fileList.append("<li><a href=\"/" + fileName + "\">" + fileName + "</a></li>");
        }
        fileList.append("</ul>");
        return fileList.toString();
    }
}
