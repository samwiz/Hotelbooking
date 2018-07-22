package com.reservation.hotel.service;

import java.util.List;

import com.reservation.hotel.domain.RoomReservation;
import com.reservation.hotel.domain.entities.Guest;
import com.reservation.hotel.domain.entities.Room;

public interface ReservationService {

 String showReservations();

 List<RoomReservation> getRoomReservationsForDate(String dateString);

 List<RoomReservation> getRoomReservationsForPhoneNumber(String phone);
 
 List<RoomReservation> getRoomReservationsForGuestId(String guestId);
 
 List<Guest> getDetailOfAllGuests();
 
 List<Room> getDetailOfAllRooms();
 
 List<RoomReservation> getAllReservations();
 
 Guest getDetailsOfGuestById(String guestId);
 
 Room getDetailsOfRoomById(String roomId);
 
}
