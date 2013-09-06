package server;

import server.request.processor.ProcessorFactory;

import java.io.IOException;
import java.net.Socket;

public class RequestHandler implements Runnable {
    private final ResponseWriter writer;
    private final RequestParser requestParser;
    private final ProcessorFactory processFactory;
    private final Socket clientSocket;


    public RequestHandler(ProcessorFactory processorFactory, Socket clientSocket){
        this.writer = new ResponseWriter();
        this.requestParser = new RequestParser();
        this.processFactory = processorFactory;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleRequest();
        } catch (IOException e) {
            Logger.error("Cannot handle request because " + e.getMessage(), e);
        } finally {
            closeSocket();
        }
    }

    private void closeSocket() {
        if (clientSocket != null){
            try {
                clientSocket.close();
            } catch (IOException e) {
                Logger.error("Cannot close socket because " + e.getMessage(), e);
            }
        }
    }

    private void handleRequest() throws IOException {
        Request request = requestParser.parse(clientSocket.getInputStream());
        Response response = processFactory.get(request.getPath()).process(request);
        writer.write(clientSocket.getOutputStream(), response);
        clientSocket.close();
    }
}
