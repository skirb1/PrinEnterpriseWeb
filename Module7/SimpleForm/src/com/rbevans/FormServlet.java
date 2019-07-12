package com.rbevans;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        String firstName = null;
	        String lastName = null;        
	        try {
	            firstName = request.getParameter("firstName");
	            if (firstName == null) {
	                firstName = "<none entered>";
	            }
	            lastName = request.getParameter("lastName");
	            if (lastName == null) {
	                lastName = "<none entered>";
	            }
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Servlet SimpleForm</title>");  
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<h1>Servlet SimpleForm at " +
	                    request.getContextPath() + "</h1>");
	            out.println("Welcome " + firstName + " " + lastName);
	            out.println("</body>");
	            out.println("</html>");
	        } finally { 
	            out.close();
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
