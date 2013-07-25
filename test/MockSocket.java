import java.io.OutputStream;
import java.net.Socket;

public class MockSocket extends Socket{
    private MockOutputStream output;
    private boolean close;

    public MockSocket(){
        output = new MockOutputStream();
    }

    public MockOutputStream getOutputStream(){
        return output;
    }
}
