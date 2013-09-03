import server.Config;
import server.RequestParser;
import server.ResponseWriter;
import server.Server;
import server.request.processor.ProcessorFactory;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setProperty(Config.DIRECTORY_PATH_KEY, "/Users/nhunguyen/Documents/server/Server/resource");
        Server server = new Server(new ServerSocket(5000));
        server.start();
    }
}
