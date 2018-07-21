package com.reservation.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.hotel.domain.RoomReservation;
import com.reservation.hotel.domain.entities.Guest;
import com.reservation.hotel.domain.entities.Room;
import com.reservation.hotel.service.ReservationService;

@RestController
@RequestMapping(value = "/hotel")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
    
	//Dummy method to test setup
	@RequestMapping(method = RequestMethod.GET, value="/welcome")
	public String sayHello(){
		return reservationService.showReservations();
	}
	
	//view already reserved rooms by date dd-MM-yyyy Format
	 @RequestMapping(method= RequestMethod.GET, value="/reservations/{date}")
	    public List<RoomReservation> getAllReservationsForDate(@PathVariable(value="date")String dateString){
	        return this.reservationService.getRoomReservationsForDate(dateString);
	    }
	 
	//view  reserved rooms by phone number 
		 @RequestMapping(method= RequestMethod.GET, value="/reservations/phone/{phone}")
		    public List<RoomReservation> getAllReservationsForPhoneNumber(@PathVariable String phone){
		        return this.reservationService.getRoomReservationsForPhoneNumber(phone);
		    }
		 
	 //view reserved rooms by guest Id 
	 @RequestMapping(method= RequestMethod.GET, value="/reservations/guest/{guestId}")
		    public List<RoomReservation> getAllReservationsForGuestId(@PathVariable String guestId){
		        return this.reservationService.getRoomReservationsForGuestId(guestId);
		    }
	 
	//view guest details for all guests 
	 @RequestMapping(method= RequestMethod.GET, value="/guests")
	    public List<Guest> getDetailsOfAllGuests(){
	        return this.reservationService.getDetailOfAllGuests();
	    }
		 
		 
	//view guest details by guest Id 
	 @RequestMapping(method= RequestMethod.GET, value="/rooms")
	    public List<Room> getRoomDetailsOfAllRooms(){
	        return this.reservationService.getDetailOfAllRooms();
	    }
		 
	
}
