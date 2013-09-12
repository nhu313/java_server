package server.request.processor;

import server.Config;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileFactory {

    public static String[] getPublicFileNames() {
        try {
            return getFile(Config.PUBLIC_DIRECTORY).list();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static File getFile(String path) throws URISyntaxException {
        URL url = Config.class.getResource(path);
        return new File(url.toURI());
    }
}
