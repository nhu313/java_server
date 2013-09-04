package server;

import server.request.processor.ProcessorFactory;

import java.io.IOException;
import java.net.Socket;

public class MyThread implements Runnable {
    private final ResponseWriter writer;
    private final RequestParser requestParser;
    private final ProcessorFactory processFactory;
    private Socket clientSocket;


    public MyThread(ProcessorFactory processorFactory, Socket clientSocket){
        this.writer = new ResponseWriter();
        this.requestParser = new RequestParser();
        this.processFactory = processorFactory;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            Request request = requestParser.parse(clientSocket.getInputStream());
            Response response = processFactory.get(request.getPath()).process(request);
            writer.write(clientSocket.getOutputStream(), response);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
