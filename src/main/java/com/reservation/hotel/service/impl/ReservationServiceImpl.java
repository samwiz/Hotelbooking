package com.reservation.hotel.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.hotel.data.repositories.GuestRepository;
import com.reservation.hotel.data.repositories.ReservationRepository;
import com.reservation.hotel.data.repositories.RoomRepository;
import com.reservation.hotel.service.ReservationService;
import com.reservation.hotel.domain.RoomReservation;
import com.reservation.hotel.domain.entities.Guest;
import com.reservation.hotel.domain.entities.Reservation;
import com.reservation.hotel.domain.entities.Room;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
    private GuestRepository guestRepository;
	
	@Autowired
    private ReservationRepository reservationRepository;

    //private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");//

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	
    //demo method for flow test
	public String showReservations(){
		
		return "Reservation Details !!";
	}	

    public List<RoomReservation> getRoomReservationsForPhoneNumber(String phone){
    	
    	List<RoomReservation> roomReservationList = new ArrayList<>();
    	
    	Guest guest = this.guestRepository.findByPhoneNumber(phone);
    	if(null!=guest){
    		
    	
    	Iterable<Reservation> reservations = this.reservationRepository.findByGuestId(guest.getId());
    	if(null!=reservations){
    		 RoomReservation roomReservation = new RoomReservation();
            reservations.forEach(reservation -> {
                
                    Long roomId= reservation.getRoomId();       
                    Optional<Room> room = this.roomRepository.findById(roomId);
                    roomReservation.setDate(reservation.getDate());
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setGuestId(guest.getId());
                    roomReservation.setRoomName(room.get().getName());
                    roomReservation.setRoomNumber(room.get().getNumber());
                    roomReservationList.add(roomReservation);
                
            });
        }
    	
    
    	}
    	
    	return roomReservationList;
    	
    }

	
    /**
     * Fetch reservations for a particular date
     */
    public List<RoomReservation> getRoomReservationsForDate(String dateString){
        Date date = this.createDateFromDateString(dateString);
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room->{
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
        if(null!=reservations){
            reservations.forEach(reservation -> {
                Optional<Guest> guest = this.guestRepository.findById(reservation.getGuestId());
                if(null!=guest){
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
                    roomReservation.setDate(date);
                    roomReservation.setFirstName(guest.get().getFirstName());
                    roomReservation.setLastName(guest.get().getLastName());
                    roomReservation.setGuestId(guest.get().getId());
                }
            });
        }
        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long roomId:roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(roomId));
        }
        return roomReservations;
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
