package server;

import java.io.IOException;
import java.net.Socket;

public interface ServerIO {
    void writeResponse(Socket socket, String message) throws IOException;

    String readRequest(Socket socket) throws IOException;
}
