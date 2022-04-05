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
 * Servlet implementation class ClearRoom
 */
public class ResetHotelInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetHotelInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		RetCode resp = HotelManagerService.resetHotelInfo();
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		Writer out=response.getWriter();
		out.write("Clear room result - " + resp + "!");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
