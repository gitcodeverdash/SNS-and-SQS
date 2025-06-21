package com.yogesh.Ratings.services.impl;

import com.yogesh.Ratings.entities.Rating;
import com.yogesh.Ratings.exception.ResourceNotFoundException;
import com.yogesh.Ratings.repositories.RatingRepositories;
import com.yogesh.Ratings.services.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceimplementation implements RatingService {

    private final RatingRepositories ratingRepositories;

    public RatingServiceimplementation (RatingRepositories ratingRepositories){
        this.ratingRepositories=ratingRepositories;
    }

    @Override
    public Rating createRating(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepositories.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepositories.findAll();
    }

    @Override
    public Rating getById(String ratingId) {
        return ratingRepositories.findById(ratingId).orElseThrow(()->new ResourceNotFoundException("Provide correct rating id"));
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepositories.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepositories.findByHotelId(hotelId);
    }
}
