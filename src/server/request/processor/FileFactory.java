package server.request.processor;

import server.Config;

import java.io.File;

public class FileFactory {

    public static String[] getPublicFileNames() {
        return getFile("").list();
    }

    public static File getFile(String path) {
        return new File(Config.PUBLIC_DIRECTORY + path);
    }
}
