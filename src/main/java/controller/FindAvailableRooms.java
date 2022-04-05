package controller;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.HotelManagerService;

/**
 * Servlet implementation class FindAvailableRooms
 */
public class FindAvailableRooms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAvailableRooms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = request.getParameter("date");
			Date date = sdf.parse(dateString);
			
			ArrayList<String> allRooms = HotelManagerService.findAvailableRooms(date); // find available rooms for a given date
			
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			Writer out=response.getWriter();
			out.write("Available rooms on " + dateString + ": " + allRooms);
			out.write("<html>");
			out.write("<body>");
			out.write("<br>");
			out.write("<a href='http://localhost:8080/hotelmanager'>Back to Home Page</a>");
			out.write("</body></html>");
			out.flush();
			out.close();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
