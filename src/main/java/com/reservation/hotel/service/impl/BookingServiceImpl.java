package com.reservation.hotel.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.reservation.hotel.data.repositories.ReservationRepository;
import com.reservation.hotel.data.repositories.RoomRepository;
import com.reservation.hotel.domain.RoomReservation;
import com.reservation.hotel.domain.entities.Reservation;
import com.reservation.hotel.domain.entities.Room;
import com.reservation.hotel.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	private static final String getReservationsByDateURL = "http://localhost:8088/hotel/reservations/";
	
	//@Transactional
	public List<RoomReservation> bookRoom(String roomId, String guestId, String date){
		//List<RoomReservation> reservations= new ArrayList<>();	

		
		//change status of booked room to true		
		Optional<Room> room = roomRepository.findById(Long.parseLong(roomId));		
		room.get().setBooked(true);		
		roomRepository.save(room.get());		
		
		Reservation reservation = new Reservation();
		
		reservation.setGuestId(Long.parseLong(guestId));
		reservation.setRoomId(Long.parseLong(roomId));
		reservation.setDate(new java.sql.Date(createDateFromDateString(date).getTime()));
		
		reservationRepository.save(reservation);
		
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.getForObject(getReservationsByDateURL+date, List.class);
		
	}
	
	private Date createDateFromDateString(String dateString){
        Date date = null;
        if(null!=dateString) {
            try {
                date = DATE_FORMAT.parse(dateString);
            }catch(ParseException pe){
                date = new Date();
            }
        }else{
            date = new Date();
        }
        return date;
    }
	
	

}
