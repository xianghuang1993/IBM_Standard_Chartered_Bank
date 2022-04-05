package controller;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resp.RetCode;
import service.HotelManagerService;

/**
 * Servlet implementation class AddRoom
 */
public class AddRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String room = request.getParameter("roomnumber");
		
		RetCode resp = HotelManagerService.addRoom(room);
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		Writer out=response.getWriter();
		out.write("Add room result - " + resp + "!\n");
		out.write("<html>");
		out.write("<body>");
		out.write("<br>");
		out.write("<a href='http://localhost:8080/hotelmanager'>Back to Home Page</a>");
		out.write("</body></html>");
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
