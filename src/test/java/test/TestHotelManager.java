package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import hotel_booking_manager.Booking;
import hotel_booking_manager.HotelManager;

public class TestHotelManager {

	HotelManager hotelManagerTest = new HotelManager(); // init hotel manager
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	@Test
	/**
	 * method: test if add room method works correctly with new room added and existing room added
	 */
	public void testAddRoom() {
		hotelManagerTest.addRoom("101"); // add rooms
		hotelManagerTest.addRoom("201"); // add rooms
		hotelManagerTest.addRoom("301"); // add rooms
		hotelManagerTest.addRoom("301"); // add duplicate rooms
		
		int hotelNumber = hotelManagerTest.getHotelRooms().size(); // get total hotel number
		int expectedResult = 3; // expected number
		
		assertEquals(expectedResult, hotelNumber);
	}

	@Test
	/**
	 * method: test if store booking method works correctly
	 */
	public void testStoreBooking() throws ParseException {
		Booking booking  = new Booking("John", "101", sdf.parse("2022-03-02")); // init booking
		Booking booking2  = new Booking("John", "201", sdf.parse("2022-03-03")); // init booking
		Booking booking3  = new Booking("Kevin", "301", sdf.parse("2022-03-03")); // init booking
		
		hotelManagerTest.storeBooking(booking); // store booking
		hotelManagerTest.storeBooking(booking2); // store booking
		hotelManagerTest.storeBooking(booking3); // store booking
		
		ConcurrentHashMap<Date, ArrayList<Booking>> bookingsList = hotelManagerTest.getBookingsList(); // get bookings list
		
		int bookingNumber = 0;
		for(Map.Entry<Date, ArrayList<Booking>> entry : bookingsList.entrySet()) { // get total booking number
			bookingNumber += entry.getValue().size();
		}
		int expectedResult = 3; // expected number
		assertEquals(expectedResult, bookingNumber);
	}
	
	@Test
	/**
	 * method: test if find available rooms on a given date method works correctly
	 */
	public void testFindAvailableRooms() throws ParseException {
		Booking booking  = new Booking("John", "101", sdf.parse("2022-03-02")); // init booking
		Booking booking2  = new Booking("John", "201", sdf.parse("2022-03-03")); // init booking
		Booking booking3  = new Booking("Kevin", "301", sdf.parse("2022-03-03")); // init booking
		
		hotelManagerTest.storeBooking(booking); // store booking
		hotelManagerTest.storeBooking(booking2); // store booking
		hotelManagerTest.storeBooking(booking3); // store booking
		
		ArrayList<String> allRooms = hotelManagerTest.findAvailableRooms(sdf.parse("2022-03-03")); // find available rooms for a given date
		
		int bookingNumber = allRooms.size(); // get available room number
		int expectedResult = 1; // expected number
		assertEquals(expectedResult, bookingNumber);
	}
	
	@Test
	/**
	 * method: test if find bookings for a guest method works correctly
	 */
	public void testFindBookingsForGuest() throws ParseException {
		Booking booking  = new Booking("John", "101", sdf.parse("2022-03-02")); // init booking
		Booking booking2  = new Booking("John", "201", sdf.parse("2022-03-03")); // init booking
		Booking booking3  = new Booking("Kevin", "301", sdf.parse("2022-03-03")); // init booking
		
		hotelManagerTest.storeBooking(booking); // store booking
		hotelManagerTest.storeBooking(booking2); // store booking
		hotelManagerTest.storeBooking(booking3); // store booking
		
		ArrayList<Booking> guestBookings = hotelManagerTest.findBookingsForGuest("John"); // find bookings for guest
		
		int bookingNumber = guestBookings.size(); // get available room number
		int expectedResult = 2; // expected number
		assertEquals(expectedResult, bookingNumber);
	}

}
