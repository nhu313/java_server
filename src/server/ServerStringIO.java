package server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerStringIO implements ServerIO {

    public void writeResponse(Socket socket, String message) throws IOException {
        PrintWriter response = new PrintWriter(socket.getOutputStream(), true);
        response.println(message);
        response.close();
    }

    public String readRequest(Socket socket) throws IOException {
        InputStream input = socket.getInputStream();
//        Scanner scanner = new Scanner(input);
        String request = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = reader.readLine();

        while (line != null){
            request += line + '\n';
            line = reader.readLine();
        }
//                scanner.useDelimiter("\\A");
//        while(scanner.hasNextLine()){
//            String line = scanner.nextLine();
//            if (line.length() == 0){
//                break;
//            }
//            request += line + '\n';
//        }

//        request = scanner.hasNext() ? scanner.next() : "";
//        request += scanner.hasNext()? scanner.next() : "";
//        input.close();
//        scanner.close();
        return request;
    }
}
