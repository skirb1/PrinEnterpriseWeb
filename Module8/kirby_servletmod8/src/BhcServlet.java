
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/BhcResult")
public class BhcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BhcServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet SimpleForm</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Booking Result</h1>");
        
        Rates rateCalculator = null;
        String hikeStr = null;
        String durationStr = null;
        String startDateStr = null;
        String participantStr = null;
        double cost = -1.0;
        
        try {
            hikeStr = request.getParameter("hike");
            if (hikeStr != null) {
                rateCalculator = new Rates(Rates.HIKE.valueOf(hikeStr));
                out.println("Hike: " + rateCalculator.getHike().toString());
                
                durationStr = request.getParameter("duration");
                if(durationStr != null) {
		            int duration = Integer.valueOf(durationStr);
		            out.println("<br />Days: " + duration);
		            rateCalculator.setDuration(duration);
		            
		            startDateStr = request.getParameter("startDate");
		            if(startDateStr != null) {
			            String[] splitDate = startDateStr.split("-");
			            int year = Integer.valueOf(splitDate[0]);
			            int month = Integer.valueOf(splitDate[1]);
			            int day = Integer.valueOf(splitDate[2]);
			            BookingDay startDate = new BookingDay(year, month - 1, day);
			            out.println("<br />Start date: " + startDate.toString());
			            rateCalculator.setBeginDate(startDate);
			            
			            BookingDay endDate = rateCalculator.getEndBookingDay();
			            if(endDate != null) {
			            	out.println("<br />End date: " + endDate.toString());
			            }
			            
			            participantStr = request.getParameter("participants");
			            if(participantStr != null) {
			            	int participantCount = Integer.valueOf(participantStr);
			            	if(participantCount >= 1 && participantCount <= 10) {
			            		out.println("<br/>Participants: " + participantCount);
				            	rateCalculator.setParticipantCount(participantCount);
				            	
				            	cost = rateCalculator.getCost();
					            if(cost > 0.0) {
					            	out.println("<br/><br/>Total cost: $" + cost);
					            }
					            else {
					            	out.println("<br/><br/>Error: " + rateCalculator.getDetails());
					            }
			            	}
			            	else {
			            		out.println("<br/><br/> Error: Number of participants must be between 1 and 10");
			            	}
			            }
			            else {
			            	out.println("<br/><br/> Error: Could not retrieve number of participants");
			            }
		            }
		            else {
		            	out.println("<br/><br/> Error: No start date selected");
		            }
                }
                else {
                	out.println("<br/><br/> Error: No hike duration selected");
                }
            }
            else {
            	out.println("<br/><br/> Error: No hike selected");
            }

        }
        catch (NumberFormatException ex) {
        	out.println("<br/><br/>Error parsing input");
        }
        finally {
        	out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
