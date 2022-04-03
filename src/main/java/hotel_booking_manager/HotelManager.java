package hotel_booking_manager;

import java.util.Date;
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
		boolean[] available = {true};
		synchronized(allBookings){
			ArrayList<Booking> singleDayBookings = allBookings.get(booking.getDate());
			if(allBookings.size() > 0 && singleDayBookings != null) { // check if the booking list is empty
				singleDayBookings.stream().filter(existingBooking -> existingBooking.getRoom().equals(booking.getRoom())).forEach(res -> {
					available[0] = false;
				});
				if(available[0] == false) { // check if the room is already booked
					System.out.println("this room has been booked!");
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
	}	
	
	/**
	 * method: find all available rooms for a given date
	 * param: Date
	 * return: ArrayList<String>
	 */
	public ArrayList<String> findAvailableRooms(Date date) { // find available rooms on a given date
		ArrayList<String> allRooms = new ArrayList<String>();
		hotelRooms.stream().forEach(room ->{ // deep copy a local hotel room list
			allRooms.add(room);			
		});
		ArrayList<Booking> singleDayBookings = allBookings.get(date); // get all the existing bookings on a given date
		if(singleDayBookings != null) {
			singleDayBookings.stream().forEach(booking ->{
				allRooms.remove(booking.getRoom());	// remove rooms that are not available				
			});
		}
		return allRooms;
	}
	
	/**
	 * method: find all available rooms for a given date
	 * param: String
	 * return: ArrayList<Booking>
	 */
	public ArrayList<Booking> findBookingsForGuest(String name){ // find all the bookings for a guest
		ArrayList<Booking> guestBookings = new ArrayList<Booking>();
		allBookings.entrySet().stream().forEach(entry -> {
			ArrayList<Booking> singleDayBookings = entry.getValue();
			singleDayBookings.stream().filter(booking -> booking.getName().equals(name)).forEach(booking -> {
				guestBookings.add(booking);
			});	
		});
		return guestBookings;
	}
}
