package controller;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Booking;
import service.HotelManagerService;

/**
 * Servlet implementation class FindBookingsForGuest
 */
public class FindBookingsForGuest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindBookingsForGuest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String name = request.getParameter("name");
		
		ArrayList<Booking> guestBookings = HotelManagerService.findBookingsForGuest(name); // find available rooms for a given date
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		Writer out=response.getWriter();
		for(Booking guestBooking : guestBookings) {
			out.write("Booking information for guest: " + guestBooking.getName() + ", room: " + guestBooking.getRoom() + ", date: " + sdf.format(guestBooking.getDate()));
		}
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
