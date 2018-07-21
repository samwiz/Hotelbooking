package com.reservation.hotel.data.repositories;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.reservation.hotel.domain.entities.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{
    
	Room findByNumber(String number);
}
