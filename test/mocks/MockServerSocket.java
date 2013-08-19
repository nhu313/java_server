package mocks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServerSocket extends ServerSocket {
    private MockSocket clientSocket;

    public MockServerSocket() throws IOException {
        super();
        clientSocket = new MockSocket();
    }

    public Socket accept(){
        return clientSocket;
    }

//    public String getOutputValue(){
//        return clientSocket.getOutputStream();
//    }
}
