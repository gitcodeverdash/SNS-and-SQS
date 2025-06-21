package com.yogesh.Hotel.controller;

import com.yogesh.Hotel.entity.Hotel;
import com.yogesh.Hotel.service.Hotelservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final Hotelservice hotelservice;

    @Autowired
    public HotelController(Hotelservice hotelservice){this.hotelservice=hotelservice;}

    @PostMapping
    ResponseEntity<Hotel> saveHoteldetails(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelservice.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @GetMapping("/{id}")
    ResponseEntity<Hotel> getHotel(@PathVariable String id){
        Hotel hotel = hotelservice.getHotel(id);
        return ResponseEntity.ok(hotel);

    }

    @GetMapping
    ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> all = hotelservice.getAll();
        return  ResponseEntity.ok(all);
    }



}
