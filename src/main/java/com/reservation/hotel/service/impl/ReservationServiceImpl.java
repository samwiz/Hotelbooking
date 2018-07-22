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

import com.google.common.collect.Lists;
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
	
	//fetch details of room by id 
	public Room getDetailsOfRoomById(String roomId){
		return roomRepository.findById(Long.parseLong(roomId)).get();	
	}
	
	
	//fetch details of Guests by Id
		public Guest getDetailsOfGuestById(String guestId){		
			return guestRepository.findById(Long.parseLong(guestId)).get();		
			
		}
	
	//fetch all reservations
	public List<RoomReservation> getAllReservations(){
		
	  	List<RoomReservation> roomReservationList = new ArrayList<>();
	  	
		Iterable<Reservation> reservations = reservationRepository.findAll();
		reservations.forEach(reservation->{
			RoomReservation roomReservation = new RoomReservation();
			roomReservation.setDate(reservation.getDate());
			
			Guest guest = getDetailsOfGuestById(Long.toString(reservation.getGuestId()));
			
			roomReservation.setGuestId(guest.getId());					  
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            
            Room room = getDetailsOfRoomById(Long.toString(reservation.getRoomId()));
            
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationList.add(roomReservation);
		});
		
		return roomReservationList;
	}
	//fetch details of all rooms
	public List<Room> getDetailOfAllRooms(){
		return Lists.newArrayList(roomRepository.findAll());
		
	}
	
	//fetch details for all Guests
	public List<Guest> getDetailOfAllGuests(){		
		return Lists.newArrayList(guestRepository.findAll()) ;		
		
	}
	
	//fetch reservations by guest Id 
	public List<RoomReservation> getRoomReservationsForGuestId(String guestId){
		
		
    	List<RoomReservation> roomReservationList = new ArrayList<>();
    	
    	Optional<Guest> guest = this.guestRepository.findById(Long.parseLong(guestId));
    	if(null!=guest){
    		
    	
    	Iterable<Reservation> reservations = this.reservationRepository.findByGuestId(guest.get().getId());
    	if(null!=reservations){
    		 RoomReservation roomReservation = new RoomReservation();
            reservations.forEach(reservation -> {
                
                    Long roomId= reservation.getRoomId();       
                    Optional<Room> room = this.roomRepository.findById(roomId);
                    roomReservation.setDate(reservation.getDate());
                    roomReservation.setFirstName(guest.get().getFirstName());
                    roomReservation.setLastName(guest.get().getLastName());
                    roomReservation.setGuestId(guest.get().getId());
                    roomReservation.setRoomName(room.get().getName());
                    roomReservation.setRoomNumber(room.get().getNumber());
                    roomReservationList.add(roomReservation);
                
            });
        }
    	
    
    	}
    	
    	return roomReservationList;
		
	}
    
	//fetch reservations by phone number
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
