package com.yogesh.Hotel.service;

import com.yogesh.Hotel.entity.Hotel;

import java.util.List;

public interface Hotelservice {

    //create user
    public Hotel saveHotel(Hotel hotel);

    //get all user
    List<Hotel> getAll();

    //get single user
    Hotel getHotel(String id);
}
