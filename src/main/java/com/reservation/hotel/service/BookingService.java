package com.reservation.hotel.service;

import java.util.List;

import com.reservation.hotel.domain.RoomReservation;

public interface BookingService {
	 List<RoomReservation> bookRoom(String roomId, String guestId, String date);
}
