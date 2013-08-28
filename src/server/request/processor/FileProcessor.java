package server.request.processor;

import server.Request;
import server.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileProcessor implements Processor{


    @Override
    public Response process(Request request) {
        if ("GET".equals(request.getMethod())){
        try {

            String path = request.getPath();

            File file = new java.io.File("./public" + path);
            byte[] buffer = getFileBytes(file, request.getMaxContentSize());
            Response response = null;
            if (buffer.length < file.length()){
                response = new Response(206);
            } else {
                response = new Response(200);
            }
            response.setBody(buffer);
            if (path.matches(".*(jpeg|gif|png).*")){
                String contentType = path.substring(path.lastIndexOf(".") + 1, path.length());
                response.setContentType("image/" + contentType);
                response.setImage(true);
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(404);
        } else {
            return new Response(405);
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
