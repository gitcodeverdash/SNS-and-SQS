package com.yogesh.Ratings.services;

import com.yogesh.Ratings.entities.Rating;

import java.util.List;

public interface RatingService {

    //create rating
    Rating createRating(Rating rating);

    //get all rating
    List<Rating>getAllRating();

    //get ratiung by id
    Rating getById( String ratingId);

    //get rating by userid
    List<Rating> getRatingByUserId(String userId);

    //get rating by Hotelid
    List<Rating> getRatingByHotelId(String hotelId);

}
