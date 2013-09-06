package server.request.processor;

import server.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileProcessor implements Processor{

    @Override
    public Response process(Request request) {
        Response response = new Response();
        if (Method.GET == request.getMethod()){
            buildRequestAndHandleException(request, response);
        } else {
            response.setCode(ResponseCode.METHOD_NOT_ALLOW);
        }
        return response;
    }

    private void buildRequestAndHandleException(Request request, Response response) {
        try {
            buildRequest(request, response);
        } catch (IOException e) {
            response.setCode(ResponseCode.NOT_FOUND);
        }
    }

    private void buildRequest(Request request, Response response) throws IOException {
        setBody(request, response);
        setContentType(request.getPath(), response);
        setCode(response);
    }

    private void setCode(Response response) {
        if (response.getContentLength() < response.getFileLength()){
            response.setCode(ResponseCode.PARTIAL_CONTENT);
        } else {
            response.setCode(ResponseCode.OK);
        }
    }

    private Response setBody(Request request, Response response) throws IOException {
        File file = new File(Config.DIRECTORY_PATH + request.getPath());
        byte[] content = getFileContent(file, request.getMaxContentSize());

        response.setFileLength(file.length());
        response.setBody(content);
        return response;
    }

    private void setContentType(String path, Response response) {
        if (path.matches(".*(jpeg|gif|png).*")){
            String contentType = path.substring(path.lastIndexOf(".") + 1, path.length());
            response.setContentType("image/" + contentType);
        }
    }

    private byte[] getFileContent(File file, int maxContentSize) throws IOException {
        int fileSize = getMaxContentSize(file, maxContentSize);

        FileInputStream in = new FileInputStream(file);
        byte[] content = new byte[(int) fileSize];
        in.read(content, 0, fileSize);

        in.close();
        return content;
    }

    private int getMaxContentSize(File file, int maxContentSize) {
        int fileSize = (int)file.length();
        if (maxContentSize > 0 && maxContentSize < file.length()){
            fileSize = maxContentSize;
        }
        return fileSize;
    }
}
