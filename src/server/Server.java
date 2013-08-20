package server;

import server.request.processor.Index;
import server.request.processor.Processor;
import server.request.processor.Processors;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private ServerIO serverIO;

    public Server(ServerSocket serverSocket, ServerIO serverIO) {
        this.serverSocket = serverSocket;
        this.serverIO = serverIO;
    }

    public void start() {
        System.out.println("Tiana's Palace is now open ^.^ !");

        try {
            while(!serverSocket.isClosed()){
                Socket clientSocket = serverSocket.accept();
                Request request = new RequestParser().parse(clientSocket.getInputStream());
                Response response = Processors.getProcessor(request).process(request);
                ResponseWriter writer = new ResponseWriter();
                writer.write(clientSocket.getOutputStream(), response);
                clientSocket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Thank you for visiting. Tiana's Palace is now closed.");
    }
}