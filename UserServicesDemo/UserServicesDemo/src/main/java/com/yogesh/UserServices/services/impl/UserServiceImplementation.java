package com.yogesh.UserServices.services.impl;

import com.yogesh.UserServices.entities.Hotel;
import com.yogesh.UserServices.entities.Rating;
import com.yogesh.UserServices.entities.User;
import com.yogesh.UserServices.exception.ResourcenotFound;
import com.yogesh.UserServices.figen.HotelFigenClient;
import com.yogesh.UserServices.figen.RatingFigenClient;
import com.yogesh.UserServices.repositories.UserRepositories;
import com.yogesh.UserServices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepositories userRepositories;

    private final RestTemplate restTemplate;

    private final HotelFigenClient hotelFigenClient;

    private final RatingFigenClient ratingFigenClient;

    @Autowired
    public UserServiceImplementation (UserRepositories userRepositories, RestTemplate restTemplate, HotelFigenClient hotelFigenClient, RatingFigenClient ratingFigenClient){
        this.userRepositories=userRepositories;
        this.restTemplate = restTemplate;
        this.hotelFigenClient = hotelFigenClient;
        this.ratingFigenClient = ratingFigenClient;
    }




    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepositories.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepositories.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepositories.findById(userId).orElseThrow(() -> new ResourcenotFound("Provide correct UserId"));
        List<Rating> ratings = ratingFigenClient.getRatingId(user.getUserId());
       /* Rating[] ratingList = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+user.getUserId(), Rating[].class);
        List<Rating> ratings=Arrays.stream(ratingList).toList();*/


        List<Rating> userRatings=ratings.stream().map(rating->
                        {
                            //Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
                            Hotel hotel = hotelFigenClient.getHotel(rating.getHotelId());
                            rating.setHotel(hotel);
                            return rating;
                        }).collect(Collectors.toList());
        user.setRatings(userRatings);

        return user;
    }
}
