package com.reservation.hotel.service;

import java.util.List;

import com.reservation.hotel.domain.RoomReservation;

public interface ReservationService {

 String showReservations();

 List<RoomReservation> getRoomReservationsForDate(String dateString);

 List<RoomReservation> getRoomReservationsForPhoneNumber(String phone);

}
