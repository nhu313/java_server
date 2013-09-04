package server;

import java.io.*;

public class Logger {

    public static void info(String message) {
        try {
            Writer writer = getWriter();
            writer.write(message + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static Writer getWriter() throws IOException {
        return new FileWriter(Config.LOG_PATH, true);
    }
}
