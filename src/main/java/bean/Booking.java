package bean;

import java.util.Date;

/**
 * individual booking object and details 
 * @author Xiang
 */
public class Booking {
	
	private String name;
	private String room;
	private Date date;
	
	public Booking(String name, String room, Date date) { // constructor, parameters: name, room, date
		this.name = name;
		this.room = room;
		this.date = date; 
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getRoom() {
		return this.room;
	}
	
	public Date getDate() {
		return this.date;
	}

}
