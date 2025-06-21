package com.yogesh.UserServices.figen;

import com.yogesh.UserServices.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingFigenClient {

    @GetMapping("/rating/user/{userId}")
    List<Rating> getRatingId(@PathVariable("userId") String userId);

}
