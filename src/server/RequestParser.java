package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class RequestParser {


    public HttpRequest parse(InputStream requestString) {
        Scanner scanner = new Scanner(requestString);
        String httpMethod = getNextValue((scanner));
        String route = getNextValue(scanner);
        getHeader(scanner);
        return new HttpRequest(httpMethod, route, getBody(scanner));
    }

    private String getNextValue(Scanner scanner) {
        return (scanner.hasNext())? scanner.next() : "";
    }

    private void parseHeader(Scanner scanner){
        String method = null;
        String route = null;
        if (scanner.hasNext()){
            method = scanner.next();
            if (scanner.hasNext()){
                route = scanner.next();
            }
        }
        getHeader(scanner);

    }

    private String getHeader(Scanner scanner) {
        StringBuffer header = new StringBuffer();
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (endOfHeader(line)){
                break;
            } else {
                header.append(line + "\n");
            }
        }
        return header.toString();
    }

    private boolean endOfHeader(String line) {
        return line.length() == 0;
    }

    private String getBody(Scanner scanner) {
        StringBuffer header = new StringBuffer();
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (endOfHeader(line)){
                break;
            } else {
                header.append(line + "\n");
            }
        }
        return header.toString();
    }
}
