package com.yogesh.Ratings.controller;

import com.yogesh.Ratings.entities.Rating;
import com.yogesh.Ratings.services.RatingService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService){
        this.ratingService=ratingService;
    }

    @PostMapping
    ResponseEntity<Rating> createRating(@RequestBody Rating rating){

        Rating rating1 = ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping("/{id}")
    ResponseEntity<Rating> getRatingById(@PathVariable String id){
        Rating byId = ratingService.getById(id);
        return ResponseEntity.ok(byId);
    }

    @GetMapping
    ResponseEntity<List<Rating>> getRatings(){
        List<Rating> allRating = ratingService.getAllRating();

        return ResponseEntity.ok(allRating);
    }

    @GetMapping("user/{userId}")
    ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        List<Rating> ratingByUserId = ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok(ratingByUserId);
    }
    @GetMapping("hotel/{hotelId}")
    ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        List<Rating> ratingByHotelId = ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.ok(ratingByHotelId);
    }


}

