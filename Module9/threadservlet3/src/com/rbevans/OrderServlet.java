package com.rbevans;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderManager orderManager = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() {
       orderManager = new OrderManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    HttpSession session = request.getSession();
		    try {
		        out.println("<html>");
		        out.println("<head>");
		        out.println("<title>OrderServlet</title>");  
		        out.println("</head>");
		        out.println("<body>");
		        out.println("<h1>OrderServlet</h1>");
		        String item = request.getParameter("hike");
		        if (item != null) {
		            orderManager.putInCart(item, session);
		        }
		        out.println("<p>Your order Number is " + orderManager.getOrder(session).getOrderNumber() + "</p>");
		        int numItems = orderManager.cartSize(session);
		        out.println("<p>You have ordered " + numItems + " items</p>");            
		        for (int i = 0; i < numItems; i++) {
		           out.println("Item " + (i +1) + ": " + orderManager.getItem(i, session) + "<br>");
		        }
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
