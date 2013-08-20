package server;

import server.request.processor.Processors;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ResponseWriter writer;
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket, ResponseWriter writer) {
        this.serverSocket = serverSocket;
        this.writer = writer;
    }

    public void start() {
        System.out.println("Tiana's Palace is now open ^.^ !");

        try {
            while(!serverSocket.isClosed()){
                Socket clientSocket = serverSocket.accept();
                Request request = new RequestParser().parse(clientSocket.getInputStream());
                Response response = Processors.get(request).process(request);
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