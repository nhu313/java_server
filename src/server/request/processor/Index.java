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
        for (File file : contents){
            String filePath = file.getPath().replace(Config.DIRECTORY_PATH, "");
            fileList.append("<li><a href=\"" + filePath + "\">" + file.getName() + "</a></li>");
        }
        fileList.append("</ul>");
        return fileList.toString();
    }
}
