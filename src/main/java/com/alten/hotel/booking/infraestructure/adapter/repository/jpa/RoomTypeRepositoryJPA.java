package com.alten.hotel.booking.infraestructure.adapter.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alten.hotel.booking.domain.entity.RoomTypeEntity;

@Repository
public interface RoomTypeRepositoryJPA extends JpaRepository<RoomTypeEntity,Integer>{

}
