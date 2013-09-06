package server;

import server.request.processor.ProcessorFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ProcessorFactory processFactory;
    private ServerSocket serverSocket;
    private final ExecutorService threadPool;

    public Server(ServerSocket serverSocket, ExecutorService threadPool) {
        this.serverSocket = serverSocket;
        this.threadPool = threadPool;
        this.processFactory = new ProcessorFactory();
    }

    public void start() throws IOException {
        while(!serverSocket.isClosed()){
            Socket clientSocket = serverSocket.accept();
            threadPool.execute(new RequestHandler(processFactory, clientSocket));
        }
    }
}