package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author evansrb1
 */
public class ClientThread extends Thread {

    private final Socket socket;
    public ClientThread(Socket clientSocket) {
        this.socket = clientSocket;
    }
    public void run () {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("Welcome to en605481 echo server\n\rType \"bye\" to disconnect\n\r");
            String outputLine = null;
            while (!socket.isClosed()) {
                outputLine = in.readLine();
                if (outputLine == null) {
                    break;
                }
                if (outputLine.equalsIgnoreCase("bye")) {
                    break;
                } else {
                    out.println("Echo: " + outputLine);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

