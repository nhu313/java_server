import server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        try {
            startServer(getRootDirectory(args));
        } catch (Exception e) {
            handleException(e);
        }
    }

    private static String getRootDirectory(String[] args) {
        String directory = Main.class.getResource("/resources").getPath();
        for (int i = 0; i < args.length; i++){
            if (args[i].contains("-d")){
                directory = args[i + 1];
            }
        }
        return directory;
    }

    private static void startServer(String rootDirectory) throws IOException {
        System.setProperty("root_directory", rootDirectory);
        ServerSocket serverSocket = new ServerSocket(5000);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Server server = new Server(serverSocket, threadPool);

        server.start();
    }

    private static void handleException(Exception e) {
        System.err.println("Failed to start the server.");
        e.printStackTrace();
        System.exit(1);
    }
}
