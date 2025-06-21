package com.yogesh.UserServices.figen;

import com.yogesh.UserServices.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "HOTEL-SERVICE")
public interface HotelFigenClient {

    @GetMapping("/hotel/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
