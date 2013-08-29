package server;

import server.request.processor.Processor;
import server.request.processor.Processors;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ResponseWriter writer;
    private final RequestParser requestParser;
    private ServerSocket serverSocket;
    private Processor processor;


    public Server(ServerSocket serverSocket, RequestParser requestParser, ResponseWriter writer) {
        this.serverSocket = serverSocket;
        this.requestParser = requestParser;
        this.writer = writer;
    }

    public void start() {
//        System.out.println("Tiana's Palace is now open ^.^ !");

        try {
            while(!serverSocket.isClosed()){
                Socket clientSocket = serverSocket.accept();
                Request request = requestParser.parse(clientSocket.getInputStream());
                Response response = Processors.get(request).process(request);
                writer.write(clientSocket.getOutputStream(), response);
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("Thank you for visiting. Tiana's Palace is now closed.");
    }
}