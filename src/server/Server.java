package server;

import java.io.IOException;
import java.io.PrintWriter;
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
            String responseHeader = "HTTP/1.1 200 OK";
            Socket clientSocket = serverSocket.accept();
            clientSocket.getInputStream().close();
//            String request = serverIO.readRequest(clientSocket);
            String response = responseHeader;

//            if (request != null){
//                System.out.print(request);
            HttpRequest httpRequest = new RequestParser().parse(clientSocket.getInputStream());
            if (httpRequest.getMethod().equals("POST")){
                response += httpRequest.getBody();
            }
//            }
            System.out.println("response" + response);
            serverIO.writeResponse(clientSocket, response);
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Thank you for visiting. Tiana's Palace is now closed.");
    }
}