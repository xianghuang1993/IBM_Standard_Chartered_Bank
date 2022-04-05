package controller;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Booking;
import resp.RetCode;
import service.HotelManagerService;

/**
 * Servlet implementation class StoreBooking
 */
public class StoreBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreBooking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			String name = request.getParameter("name");
			String room = request.getParameter("room");
			Date date = sdf.parse(request.getParameter("date"));
			Booking booking  = new Booking(name, room, date);
			
			RetCode resp = HotelManagerService.storeBooking(booking);
			
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			Writer out=response.getWriter();
			out.write("Store booking result - " + resp + "!");
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
