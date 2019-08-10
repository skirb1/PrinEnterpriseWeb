package com.bhc;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/BhcController")
public class BhcController extends HttpServlet {
	public static final String RATES = "rates";
	public static final String IS_REQUEST_VALID = "isRequestValid";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BhcController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext servletContext = getServletContext();
		
		Rates rateCalculator = (Rates) session.getAttribute(RATES);
		if (rateCalculator == null) {
			rateCalculator = new Rates();
			session.setAttribute(RATES, rateCalculator);
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else {
			String hikeStr = request.getParameter("hike");
	        String durationStr = request.getParameter("duration");
	        String startDateStr = request.getParameter("startDate");
	        String participantStr = request.getParameter("participants");
	        
	        try {
	            if (hikeStr != null && durationStr != null && 
	            		startDateStr != null && participantStr != null) {
	            	
	            	int duration = Integer.valueOf(durationStr);
	            	int participantCount = Integer.valueOf(participantStr);
	            	
	            	String[] splitDate = startDateStr.split("-");
		            int year = Integer.valueOf(splitDate[0]);
		            int month = Integer.valueOf(splitDate[1]);
		            int day = Integer.valueOf(splitDate[2]);
		            BookingDay startDate = new BookingDay(year, month - 1, day);
		            
	            	rateCalculator.setHike(Rates.HIKE.valueOf(hikeStr));
			        rateCalculator.setDuration(duration);
		            rateCalculator.setBeginDate(startDate);
	            	rateCalculator.setParticipantCount(participantCount);
	            	
	            	request.setAttribute(IS_REQUEST_VALID, true);    
	            } else {
	            	request.setAttribute(IS_REQUEST_VALID, false);    
	            }
	            
	            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
    			dispatcher.forward(request, response);
	        }
	        catch (NumberFormatException ex) {
	        	request.setAttribute(IS_REQUEST_VALID, false);
	        	RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
	        }
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
