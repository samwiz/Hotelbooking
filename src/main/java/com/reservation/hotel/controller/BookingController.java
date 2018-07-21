package com.reservation.hotel.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.hotel.domain.RoomReservation;
import com.reservation.hotel.service.BookingService;

@RestController
@RequestMapping("/hotel")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@RequestMapping(value="/book", method=RequestMethod.GET)
	public List<RoomReservation> bookRoom( @RequestParam String roomId, @RequestParam String guestId, @RequestParam String date){
		
//		//book room for date 			
		return bookingService.bookRoom(roomId, guestId, date);
		
		//return "Booked !!!!";
		
		
	}

}
