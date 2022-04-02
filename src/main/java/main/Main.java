package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import hotel_booking_manager.Booking;
import hotel_booking_manager.HotelManager;

public class Main {
	
	public static void main(String[] args) {
		try {
			HotelManager hotelManager = new HotelManager(); // init hotel manager
			hotelManager.addRoom("101"); // add room
			hotelManager.addRoom("201"); // add room
			hotelManager.addRoom("301"); // add room
			  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			
			Booking booking  = new Booking("John", "101", sdf.parse("2022-03-02")); // init booking
			Booking booking2  = new Booking("John", "201", sdf.parse("2022-03-03")); // init booking
			Booking booking3  = new Booking("Kevin", "301", sdf.parse("2022-03-03")); // init booking
			
			hotelManager.storeBooking(booking); // store booking
			hotelManager.storeBooking(booking2); // store booking
			hotelManager.storeBooking(booking3); // store booking
			
			ArrayList<String> allRooms = hotelManager.findAvailableRooms(sdf.parse("2022-03-03")); // find available rooms for a given date
			System.out.printf("available rooms on %s: %s.\n", "2022-03-03", allRooms);
			
			ArrayList<Booking> guestBookings = hotelManager.findBookingsForGuest("John"); // find bookings for guest
			for(Booking guestBooking : guestBookings) {
				System.out.printf("booking for guest: %s, booking room: %s, booking date: %s.\n", guestBooking.getName(), guestBooking.getRoom(), sdf.format(guestBooking.getDate()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			} 
		}
}
