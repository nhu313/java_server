package server;

import server.request.processor.ProcessorFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ResponseWriter writer;
    private final RequestParser requestParser;
    private final ProcessorFactory processFactory;
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.requestParser = new RequestParser();
        this.writer = new ResponseWriter();
        this.processFactory = new ProcessorFactory();
    }

    public void start() throws IOException {
        while(!serverSocket.isClosed()){
            Socket clientSocket = serverSocket.accept();
            Thread runnable = new Thread(new RequestHandler(processFactory, clientSocket));
            runnable.start();
        }
    }
}