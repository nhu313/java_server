import server.Server;
import server.ServerStringIO;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        Server server = new Server(new ServerSocket(5000), new ServerStringIO());
        server.start();
    }
}
