package echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	 /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(8889);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 8889.");
            System.exit(1);
        }

        Socket clientSocket = null;
        while (true) {
            clientSocket = serverSocket.accept();
            ClientThread thread = new ClientThread(clientSocket);
            thread.start();
        }
    }
}
