import server.Config;
import server.Server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URISyntaxException;
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

    private static void startServer() throws IOException, URISyntaxException {
        System.setProperty(Config.DIRECTORY_PATH_KEY, getResourcePath());

        ServerSocket serverSocket = new ServerSocket(5000);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Server server = new Server(serverSocket, threadPool);

        server.start();
    }

    private static void handleException(Exception e) {
        System.out.println("Failed to start the server.");
        e.printStackTrace();
        System.exit(1);
    }

    public static String getResourcePath() throws URISyntaxException {
//        String className = "resources";
//        File file = new File(Main.class.getClassLoader().getResource(className).toURI());
//        System.out.println("Does file exist" + file.exists());
//        String classPath = Main.class.getClassLoader().getResource(className).getPath();
//        String path = classPath.replace("file1", "");
        return "/Users/nhunguyen/Documents/server/Server/src/resources";
    }
}
