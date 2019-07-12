package com.rbevans.bookingrate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class BookingIO {
    private static String HOST = "web7.jhuep.com";

    private static int PORT = 20025;

    public BookingIO(){ }

    public String sendRequest(String request){
        System.out.println(request);
        try {
            Socket socket = new Socket(HOST, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            if (request != null) {
                out.println(request);
                String response = in.readLine();
                System.out.println(response);
                return response;
            }

            out.close();
            in.close();
            socket.close();
        }
        catch(UnknownHostException ex){
            System.err.println("Don't know about host: " + HOST);
            System.exit(1);
        }
        catch(IOException ex){
            System.err.println("Couldn't get I/O for the connection to: " + HOST);
            System.exit(1);
        }
        return null;
    }

    public static double getCost(String response){
        double cost = -0.01;
        if(response != null) {
            String[] split = response.split(":");
            cost = Double.parseDouble(split[0]);
        }
        return cost;
    }

    public static String getDetails(String response){
        String result = "No response";
        if(response != null) {
            String[] split = response.split(":");
            result = split[1];
        }
        return result;
    }

}
