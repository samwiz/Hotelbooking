package com.reservation.hotel.domain.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROOM")
public class Room {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ROOM_ID")
    private long id;
    @Column(name="NAME")
    private String name;
    @Column(name="ROOM_NUMBER")
    private String number;
    @Column(name="BED_INFO")
    private String bedInfo;
    
    @Column(name="BOOKED")
    private boolean booked;

    public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBedInfo() {
        return bedInfo;
    }

    public void setBedInfo(String bedInfo) {
        this.bedInfo = bedInfo;
    }
}
