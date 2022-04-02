package hotel_booking_manager;

import java.util.Date;
import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * hotel manager 
 * @author Xiang
 */
public class HotelManager {
	
	private static CopyOnWriteArrayList<String> hotelRooms = new CopyOnWriteArrayList<String>(); // define the room list of a hotel
	
	private static ConcurrentHashMap<Date, ArrayList<Booking>> allBookings = new ConcurrentHashMap<Date, ArrayList<Booking>>(); // define the booking list
	
	public HotelManager() { // constructor
	}
	
	public void addRoom(String room) { // add room to hotel
		if (hotelRooms.size() > 0){
			if(!hotelRooms.contains(room)) {
				hotelRooms.add(room);
			}
		}
		else {
			hotelRooms.add(room);
		}
	}
	
	public CopyOnWriteArrayList<String> getHotelRooms() { // get hotel room
		return hotelRooms;
	}
	
	public ConcurrentHashMap<Date, ArrayList<Booking>> getBookingsList() { // get all bookings list
		return allBookings;
	}
	
	/**
	 * method: store a booking to booking list
	 * param: Booking
	 * return: void
	 */
	public void storeBooking(Booking booking) { // add a booking to booking list
		Boolean available = true;
		ArrayList<Booking> singleDayBookings = allBookings.get(booking.getDate());
		if(allBookings.size() > 0 && singleDayBookings != null) { // check if the booking list is empty
			for(Booking existingBooking : singleDayBookings) { // check if the room is already booked
				if(existingBooking.getRoom().equals(booking.getRoom())) {
					available = false;
				}
			}
			if(available == false) { // check if the room is already booked
				System.out.println("this room has been booked!");
			}
			else if(singleDayBookings.size() >= hotelRooms.size()) { // check if all the rooms have been booked
				System.out.println("all rooms have been booked!");
			}
			else { // available for storing a booking to booking list
				singleDayBookings.add(booking);
				allBookings.put(booking.getDate(), singleDayBookings);
				System.out.printf("stored booking name: %s, booking room: %s, booking date: %s successfully!\n", booking.getName(), booking.getRoom(), booking.getDate());	
			}
		}
		else { // available for storing a booking to booking list
			ArrayList<Booking> initSingleDayBookings = new ArrayList<Booking>();
			initSingleDayBookings.add(booking);
			allBookings.put(booking.getDate(), initSingleDayBookings);
			System.out.printf("stored booking name: %s, booking room: %s, booking date: %s successfully!\n", booking.getName(), booking.getRoom(), booking.getDate());
		}
	}	
	
	/**
	 * method: find all available rooms for a given date
	 * param: Date
	 * return: ArrayList<String>
	 */
	public ArrayList<String> findAvailableRooms(Date date) { // find available rooms on a given date
		ArrayList<String> allRooms = new ArrayList<String>();
		for(int i = 0; i < hotelRooms.size(); i++) { // deep copy a local hotel room list
			allRooms.add(hotelRooms.get(i));	
		}
		ArrayList<Booking> singleDayBookings = allBookings.get(date);
		if(singleDayBookings != null) {
			for(Booking booking : singleDayBookings) {
				allRooms.remove(booking.getRoom());	// remove rooms that are not available
			}		
		}
		return allRooms;
	}
	
	/**
	 * method: find all available rooms for a given date
	 * param: Date
	 * return: ArrayList<Booking>
	 */
	public ArrayList<Booking> findBookingsForGuest(String name){ // find all the bookings for a guest
		ArrayList<Booking> guestBookings = new ArrayList<Booking>();
		for(Map.Entry<Date, ArrayList<Booking>> entry : allBookings.entrySet()) {
			ArrayList<Booking> singleDayBookings = entry.getValue();
			for(Booking booking : singleDayBookings) {
				if(booking.getName().equals(name)) {
					guestBookings.add(booking);
				}
			}
		}
		return guestBookings;
	}
}
