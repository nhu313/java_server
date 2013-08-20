package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ResponseWriter {
    public static final String HTTP_VERSION = "HTTP/1.1";

    public void write(Socket socket, Response response) throws IOException {
        String message = getResponseMessage(response);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        output.println(message);


    }

    private String getResponseMessage(Response response) {
        return HTTP_VERSION + " " + response.getCode() + " OK\n";
    }

}
