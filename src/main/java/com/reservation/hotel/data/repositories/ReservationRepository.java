package com.reservation.hotel.data.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.reservation.hotel.domain.entities.Reservation;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findByDate(Date date);
    
    List<Reservation> findByGuestId(Long guestId);
    
  
}