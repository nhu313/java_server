package server;

import server.request.processor.Processor;
import server.request.processor.ProcessorFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ResponseWriter writer;
    private final RequestParser requestParser;
    private final ProcessorFactory processFactory;
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket, RequestParser requestParser,
                  ResponseWriter writer, ProcessorFactory processorFactory) {
        this.serverSocket = serverSocket;
        this.requestParser = requestParser;
        this.writer = writer;
        this.processFactory = processorFactory;
    }

    public void start() {
        try {
            while(!serverSocket.isClosed()){
                Socket clientSocket = serverSocket.accept();
                Request request = requestParser.parse(clientSocket.getInputStream());
                Response response = processFactory.get(request.getPath()).process(request);
                writer.write(clientSocket.getOutputStream(), response);
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}