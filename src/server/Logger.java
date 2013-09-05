package server;

import java.io.*;

public class Logger {

    private enum Type{
        INFO, ERROR;
    }
    //TODO FIX THIS
    public static void info(String message) {
        Writer writer = null;
        try {
            writer = writeMessage(Type.INFO, message);
        } catch (IOException e) {
            System.out.println("Unable to open log file.");
            e.printStackTrace();
        } finally {
            closeFile(writer);
        }
    }

    private static Writer writeMessage(Type type, String message) throws IOException {
        Writer writer = getWriter();
        writer.write(type.name() + ": " + message + "\n");
        return writer;
    }

    private static void closeFile(Writer writer) {
        try {
            if (writer != null){
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Unable to close log file.");
        }
    }

    public static Writer getWriter() throws IOException {
        return new FileWriter(Config.LOG_PATH, true);
    }
}