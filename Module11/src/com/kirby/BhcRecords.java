package com.kirby;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BhcRecords
 */
@WebServlet("/BhcRecords")
public class BhcRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String RESULT_SET = "records";
	
	private static final DbConnection dbConnection = new DbConnection();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BhcRecords() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext servletContext = getServletContext();
		
		String inputDateStr = request.getParameter("startDate");
		
		//TODO Check format of date string: YYYY-MM-DD
		
		List<RecordBean> recordList = dbConnection.getRecords(inputDateStr);
		session.setAttribute(RESULT_SET, recordList);

		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
