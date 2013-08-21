import server.RequestParser;
import server.ResponseWriter;
import server.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        RequestParser parser = new RequestParser();
        ResponseWriter writer = new ResponseWriter();
        Server server = new Server(new ServerSocket(5000), parser, writer);
        server.start();
    }
}
