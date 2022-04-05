package service;

import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import bean.Booking;
import bean.HotelManager;
import resp.RetCode;

/**
 * hotel manager 
 * @author Xiang
 */
public class HotelManagerService {
	
	/**
	 * method: store a booking to booking list
	 * param: Booking
	 * return: RetCode
	 */
	public static synchronized RetCode storeBooking(Booking booking) { // add a booking to booking list
		HotelManager hotelManager = HotelManager.getHotelManager();
		ConcurrentHashMap<Date, ArrayList<Booking>> allBookings = hotelManager.getHotelBookings();
		boolean[] available = {true};		
		ArrayList<Booking> singleDayBookings = allBookings.get(booking.getDate());
		if(allBookings.size() > 0 && singleDayBookings != null) { // check if the booking list is empty
			singleDayBookings.stream().filter(existingBooking -> existingBooking.getRoom().equals(booking.getRoom())).forEach(res -> {
				available[0] = false;
			});
			if(available[0] == false) { // check if the room is already booked
				System.out.println("this room has been booked!");
				return RetCode.FAILED;
			}
			else { // available for storing a booking to booking list
				singleDayBookings.add(booking);
				allBookings.put(booking.getDate(), singleDayBookings);
				System.out.printf("stored booking name: %s, booking room: %s, booking date: %s successfully!\n", booking.getName(), booking.getRoom(), booking.getDate());	
				return RetCode.SUCCESS;
			}
		}
		else { // available for storing a booking to booking list
			ArrayList<Booking> initSingleDayBookings = new ArrayList<Booking>();
			initSingleDayBookings.add(booking);
			allBookings.put(booking.getDate(), initSingleDayBookings);
			System.out.printf("stored booking name: %s, booking room: %s, booking date: %s successfully!\n", booking.getName(), booking.getRoom(), booking.getDate());
			return RetCode.SUCCESS;
		}
	}	
	
	/**
	 * method: find all available rooms for a given date
	 * param: Date
	 * return: ArrayList<String>
	 */
	public static synchronized ArrayList<String> findAvailableRooms(Date date) { // find available rooms on a given date
		HotelManager hotelManager = HotelManager.getHotelManager();
		ConcurrentHashMap<Date, ArrayList<Booking>> allBookings = hotelManager.getHotelBookings();
		CopyOnWriteArrayList<String> hotelRooms = hotelManager.getHotelRooms();
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
	public static synchronized ArrayList<Booking> findBookingsForGuest(String name){ // find all the bookings for a guest
		HotelManager hotelManager = HotelManager.getHotelManager();		
		ConcurrentHashMap<Date, ArrayList<Booking>> allBookings = hotelManager.getHotelBookings();
		ArrayList<Booking> guestBookings = new ArrayList<Booking>();
		allBookings.entrySet().stream().forEach(entry -> {
			ArrayList<Booking> singleDayBookings = entry.getValue();
			singleDayBookings.stream().filter(booking -> booking.getName().equals(name)).forEach(booking -> {
				guestBookings.add(booking);
			});	
		});
		return guestBookings;	
	}
	
	/**
	 * method: add a room to hotel rooms
	 * param: String
	 * return: RetCode
	 */
	public static synchronized RetCode addRoom(String room) { // add room to hotel
		HotelManager hotelManager = HotelManager.getHotelManager();
		CopyOnWriteArrayList<String> hotelRooms = hotelManager.getHotelRooms();	
		if (hotelRooms.size() > 0){
			if(!hotelRooms.contains(room)) {
				hotelRooms.add(room);
				return RetCode.SUCCESS;
			}
			else {
				return RetCode.FAILED;
			}
		}
		else {
			hotelRooms.add(room);
			return RetCode.SUCCESS;
		}
	}
	
	/**
	 * method: reset hotel room and booking information in the system
	 * param: 
	 * return: RetCode
	 */
	public static synchronized RetCode resetHotelInfo() { // remove room to hotel
		HotelManager hotelManager = HotelManager.getHotelManager();
		CopyOnWriteArrayList<String> hotelRooms = hotelManager.getHotelRooms();	
		ConcurrentHashMap<Date, ArrayList<Booking>> allBookings = hotelManager.getHotelBookings();
		hotelRooms.clear();
		allBookings.clear();
		return RetCode.SUCCESS;
	}
}
