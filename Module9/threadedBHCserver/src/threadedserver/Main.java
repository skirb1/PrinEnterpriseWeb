package threadedserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	
	private static final int PORT = 20009;
	
	 /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
    	
        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(PORT);
            System.err.println("Listening on port: " + PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + PORT);
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
