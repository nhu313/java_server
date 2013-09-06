package server;

import java.io.*;

public class Logger {

    private enum Type{
        INFO, ERROR;
    }

    public static void info(String message) {
        log(Type.INFO, message);
    }

    public static void error(String message, Exception e) {
        e.printStackTrace();
        log(Type.ERROR, message);

        PrintWriter writer = getWriter();
        e.printStackTrace(writer);
        closeWriter(writer);
    }

    private static void log(Type type, String message) {
        Writer writer = null;
        try {
            writer = writeMessage(type, message);
        } catch (IOException e) {
            System.out.println("Unable to write message");
            e.printStackTrace();
        } finally {
            closeWriter(writer);
        }
    }

    private static Writer writeMessage(Type type, String message) throws IOException {
        Writer writer = getWriter();
        writer.append(type.name() + ": " + message + "\n");
        return writer;
    }

    private static void closeWriter(Writer writer) {
        try {
            if (writer != null){
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Unable to close log file.");
        }
    }

    public static PrintWriter getWriter() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(new File(Config.LOG_PATH), true));
        } catch (IOException e) {
            handleException(e);
            writer = new PrintWriter(System.out);
        }
        return writer;
    }

    private static void handleException(IOException e) {
        System.out.println("Unable to open log file.");
        e.printStackTrace();
    }
}