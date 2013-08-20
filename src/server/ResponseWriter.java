package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ResponseWriter {
    public static final String HTTP_VERSION = "HTTP/1.1";

    public void write(OutputStream output, Response response) throws IOException {
        String message = getResponseMessage(response);
        PrintWriter outputWriter = new PrintWriter(output, true);
        outputWriter.println(message);
    }

    private String getResponseMessage(Response response) {
        String header = HTTP_VERSION + " " + response.getCode() + " OK\n";
        String body = "";
        String bodyContent = response.getBody();
        if (bodyContent != null){
            header += "Content-Type: text/xml; charset=utf-8\n";
            header += "Content-Length:" + bodyContent.length() + '\n';
            body = "\r\n" + bodyContent + "\r\n";
        }
        return header + body;
    }

}
