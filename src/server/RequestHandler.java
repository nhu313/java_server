package server;

import server.request.processor.ProcessorFactory;

import java.io.IOException;
import java.net.Socket;

public class RequestHandler implements Runnable {
    private final ProcessorFactory processFactory;
    private final Socket clientSocket;


    public RequestHandler(ProcessorFactory processorFactory, Socket clientSocket){
        this.processFactory = processorFactory;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            Request request = parseRequest();
            Response response = processRequest(request);
            writeRequest(response);
            clientSocket.close();
        } catch (IOException e) {
            Logger.error("Cannot handle request because " + e.getMessage(), e);
        } finally {
            if (clientSocket != null){
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    Logger.error("Cannot close socket because " + e.getMessage(), e);
                }
            }
        }
    }

    private Request parseRequest() throws IOException {
        RequestParser requestParser = new RequestParser();
        return requestParser.parse(clientSocket.getInputStream());
    }

    private Response processRequest(Request request) {
        return processFactory.get(request.getPath()).process(request);
    }

    private void writeRequest(Response response) throws IOException {
        ResponseWriter writer = new ResponseWriter();
        writer.write(clientSocket.getOutputStream(), response);
    }
}
