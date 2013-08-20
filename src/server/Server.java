package server;

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
            String responseHeader = "HTTP/1.1 200 OK\n";
            while(!serverSocket.isClosed()){
                Socket clientSocket = serverSocket.accept();
                String response = responseHeader;
                Request request = new RequestParser().parse(clientSocket.getInputStream());
                if (request.getBody() != null){
                    response += "Content-Type: text/xml; charset=utf-8\n";
                    response += "Content-Length: " + request.getBody().length();
                    response += "\n\r\n" + request.getBody();
                }
                System.out.println("response" + response);
                serverIO.writeResponse(clientSocket, response);
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Thank you for visiting. Tiana's Palace is now closed.");
    }
}