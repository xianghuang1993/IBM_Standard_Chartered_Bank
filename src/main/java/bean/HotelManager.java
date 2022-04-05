package bean;

import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * hotel manager 
 * @author Xiang
 */
public class HotelManager {
	
	private static HotelManager hotelManager; //singleton
	
	private CopyOnWriteArrayList<String> hotelRooms = new CopyOnWriteArrayList<String>(); // define the room list of a hotel
	
	private ConcurrentHashMap<Date, ArrayList<Booking>> allBookings = new ConcurrentHashMap<Date, ArrayList<Booking>>(); // define the booking list
	
	private HotelManager() { // constructor
	}
	
    public static synchronized HotelManager getHotelManager(){
        if(hotelManager == null){
            hotelManager = new HotelManager();
        }
        return hotelManager;
    }
    
    public synchronized CopyOnWriteArrayList<String> getHotelRooms(){
		return this.hotelRooms;
    }
    
    public synchronized ConcurrentHashMap<Date, ArrayList<Booking>> getHotelBookings(){
		return this.allBookings;
    }
}
