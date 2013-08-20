package mocks;

import java.io.IOException;
import java.io.InputStream;

public class MockInputStream extends InputStream{

    private String request;

    public MockInputStream(String input) {

    }

    @Override
    public int read() throws IOException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
