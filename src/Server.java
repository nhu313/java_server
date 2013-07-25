import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket socket;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }

    public void setSocket(ServerSocket socket) {
        this.socket = socket;
    }

    private ServerSocket getSocket() throws IOException {
        if (socket == null){
            socket = new ServerSocket(5000);
        }
        return socket;
    }

    public void start() {
        try {
            Socket clientSocket = getSocket().accept();
            PrintWriter response = new PrintWriter(clientSocket.getOutputStream(), true);
            response.println("HTTP/1.1 200 OK");
            response.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}