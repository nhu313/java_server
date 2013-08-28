package server.request.processor;

import server.Request;
import server.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileProcessor implements Processor{


    @Override
    public Response process(Request request) {
        try {
            String path = request.getPath();
            java.io.File file = new java.io.File("./public" + path);
            FileInputStream in = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            in.read(buffer);
            in.close();
            Response response = new Response(200);
            response.setBody(buffer);
            if (path.matches(".*(jpeg|gif|png).*")){
                String contentType = path.substring(path.lastIndexOf(".") + 1, path.length());
                response.setContentType("image/" + contentType);
                response.setImage(true);
            }
            return response;
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return new Response(404);
    }
}
