package server.request.processor;

import server.Request;
import server.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Image implements Processor{


    @Override
    public Response process(Request request) {
        try {
            File file = new File("./resource/image.gif");
            FileInputStream in = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            in.read(buffer);
            in.close();
            Response response = new Response(200);
            response.setBody(buffer);
            response.setImage(true);
            return response;
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//        File file = new File("./resource/image.gif");
        return new Response(404);
    }
}
