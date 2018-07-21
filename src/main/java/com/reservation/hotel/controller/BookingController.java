package com.reservation.hotel.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.hotel.domain.RoomReservation;
import com.reservation.hotel.service.BookingService;

@RestController
@RequestMapping("/hotel")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@RequestMapping("/book/{date}/{roomId}")
	public List<RoomReservation> bookRoom(@PathVariable String date, @PathVariable String roomId, @PathVariable String guestId){
		
		//book room for date 			
		return bookingService.bookRoom(roomId, guestId, date);
	}

}
