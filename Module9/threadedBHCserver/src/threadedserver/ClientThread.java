package threadedserver;

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
            
            String inputLine = in.readLine();
            
            System.out.println(inputLine);
            out.print(parseInput(inputLine));
            
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
                if(socket != null) {
                	socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /*
     * Accepts input in the form of "hike_id:begin_year:begin_month:begin_day:duration"
     * (e.g: 1:2008:7:1:3)
     * 
     * Returns a string in the format cost:message (eg. 200.00:Success or -0.01:Error)
     */
    public static String parseInput(String input) {
    	if(input == null) {
    		return getError();
    	}
    	
    	String[] split = input.split(":");
    	
    	int hikeId;
    	int beginYear;
    	int beginMonth;
    	int beginDay;
    	int duration;
    	
    	try {
    		hikeId = Integer.parseInt(split[0]);
    		beginYear = Integer.parseInt(split[1]);
    		beginMonth = Integer.parseInt(split[2]);
    		beginDay = Integer.parseInt(split[3]);
    		duration = Integer.parseInt(split[4]);
    		
    		Booking booking = new Booking(Booking.HIKE.values()[hikeId]);
    		booking.setBeginDate(new BookingDay(beginYear, beginMonth - 1, beginDay));
    		booking.setDuration(duration);
    		
    		System.out.println("Start date: " + booking.getBeginBookingDay().toString());
    		System.out.println("End date: " + booking.getEndBookingDay().toString());
    		System.out.println(booking.getResult());
    		
    		return booking.getResult();
    	}
    	catch (Exception ex) {
    		return getError();
    	} 
    }
    
    public static String getError() {
    	return "-0.01:Error parsing input";
    }
    
}

