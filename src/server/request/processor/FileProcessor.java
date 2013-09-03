package server.request.processor;

import server.Config;
import server.Method;
import server.Request;
import server.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileProcessor implements Processor{


    @Override
    public Response process(Request request) {
        Response response = new Response();
        if (Method.GET == request.getMethod()){
            try {
                setBody(request, response);
                setContentType(request.getPath(), response);
                setCode(response);
            } catch (Exception e) {
                //            e.printStackTrace();
                response.setCode(404);
            }
        } else {
            response.setCode(405);
        }
        return response;
    }

    private void setCode(Response response) {
        if (response.getContentLength() < response.getFileLength()){
            response.setCode(206);
        } else {
            response.setCode(200);
        }
    }

    private Response setBody(Request request, Response response) throws IOException {
        String path = request.getPath();
        File file = new File(Config.DIRECTORY_PATH + path);
        byte[] buffer = getFileBytes(file, request.getMaxContentSize());
        response.setFileLength(file.length());
        response.setBody(buffer);
        return response;
    }

    private void setContentType(String path, Response response) {
        if (path.matches(".*(jpeg|gif|png).*")){
            String contentType = path.substring(path.lastIndexOf(".") + 1, path.length());
            response.setContentType("image/" + contentType);
        }
    }

    private byte[] getFileBytes(File file, int maxContentSize) throws IOException {
        int fileSize = (int)file.length();
        if (maxContentSize > 0 && maxContentSize < file.length()){
            fileSize = maxContentSize;
        }
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        in.read(buffer, 0, fileSize);
        in.close();
        return buffer;
    }
}
