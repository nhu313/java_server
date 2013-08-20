import server.ResponseWriter;
import server.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        ResponseWriter writer = new ResponseWriter();
        Server server = new Server(new ServerSocket(5000), writer);
        server.start();
    }
}
