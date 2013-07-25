import java.io.IOException;
import java.io.OutputStream;

public class MockOutputStream extends OutputStream {
    private String value = "";
    private boolean close;

    @Override
    public void write(int i) throws IOException {
    }

    @Override
    public void write(byte[] bytes, int off, int len) throws IOException {
        value += (new String(bytes)).trim();
    }

    public String getValue(){
        return value;
    }

    public void close(){
        close = true;
    }

    public boolean isClosed(){
        return close;
    }
}
