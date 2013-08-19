package mocks;

import java.io.*;
import java.net.Socket;

public class MockSocket extends Socket{
    private MockOutputStream output;
    private InputStream input;

    public MockOutputStream getOutputStream(){
        if (output == null){
            output = new MockOutputStream();
        }
        return output;
    }

    public InputStream getInputStream(){
        return input;
    }

    public void setRequest(String request) {
        input = new ByteArrayInputStream(request.getBytes());
    }

    public String getResponse() {
        return output.getValue();
    }

}
