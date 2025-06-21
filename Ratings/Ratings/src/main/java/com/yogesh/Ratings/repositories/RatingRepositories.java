package com.yogesh.Ratings.repositories;

import com.yogesh.Ratings.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepositories extends JpaRepository<Rating , String> {

    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);

}
