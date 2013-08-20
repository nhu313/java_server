package mocks;

import java.io.IOException;
import java.net.Socket;

public class MockServerIO implements ServerIO {

    private String responseMessage;
    private String request;

    public String getResponseMessage(){
        return responseMessage;
    }

    public void writeResponse(Socket socket, String message) throws IOException {
        this.responseMessage = message;
    }

    public String readRequest(Socket socket) throws IOException {
        return request;
    }

    public void setRequest(String request){
        this.request = request;
    }
}
