package server;

import mocks.MockResponseWriter;
import mocks.MockServerSocket;
import org.junit.Before;
import org.junit.Test;

public class ServerTest {

    private MockServerSocket mockServerSocket;
    private Server server;
    private MockResponseWriter mockWriter;

    @Before
    public void setUp() throws Exception {
        mockServerSocket = new MockServerSocket();
        mockWriter = new MockResponseWriter();
        server = new Server(mockServerSocket, mockWriter);
    }


}