package server.request.processor;

import server.Config;
import server.Request;
import server.Response;

import java.io.File;
import java.io.IOException;

public class Index implements Processor{

    @Override
    public Response process(Request request) {
        File directory = new File(Config.DIRECTORY_PATH);
        File[] contents = directory.listFiles();
        Response response = new Response(200);
        response.setBody(buildBody(contents));
        return response;
    }

    private String buildBody(File[] contents) {
        StringBuilder fileList = new StringBuilder();
        fileList.append("<ul>");
        for (String fileName : DirectoryReader.fileNames()){
            fileList.append("<li><a href=\"/" + fileName + "\">" + fileName + "</a></li>");
        }
        fileList.append("</ul>");
        return fileList.toString();
    }
}
