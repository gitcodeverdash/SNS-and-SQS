package com.yogesh.Hotel.service.impl;

import com.yogesh.Hotel.entity.Hotel;
import com.yogesh.Hotel.exception.ResourceNotFoundException;
import com.yogesh.Hotel.repository.HotelRepository;
import com.yogesh.Hotel.service.Hotelservice;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImplementation implements Hotelservice {

    private final HotelRepository hotelRepository;

    public HotelServiceImplementation (HotelRepository hotelRepository){
        this.hotelRepository=hotelRepository;
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String id = UUID.randomUUID().toString();
        hotel.setId(id);

        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Provide correct Hotel id"));
    }
}
