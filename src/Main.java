import server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        try {
            startServer();
        } catch (Exception e) {
            handleException(e);
        }
    }

    private static void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Server server = new Server(serverSocket, threadPool);
        System.out.println("\n***** Started server on port http://localhost:5000");

        server.start();
    }

    private static void handleException(Exception e) {
        System.err.println("Failed to start the server.");
        e.printStackTrace();
        System.exit(1);
    }
}
