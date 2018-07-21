package com.reservation.hotel.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.reservation.hotel.domain.entities.Guest;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {
	
	Guest findByPhoneNumber(String phoneNumber);

}