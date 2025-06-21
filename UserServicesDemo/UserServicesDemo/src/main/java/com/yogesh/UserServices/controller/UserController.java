package com.yogesh.UserServices.controller;

import com.yogesh.UserServices.entities.User;
import com.yogesh.UserServices.services.UserService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private static final Logger logger=Logger.getLogger(UserController.class.getName());

    public UserController(UserService userService){
        this.userService=userService;
    }

    //Save user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //get single user
    @GetMapping("/{userId}")
    @Bulkhead(name="hotelRatingBreaker" , type = Bulkhead.Type.THREADPOOL)
    @CircuitBreaker(name = "hotelRatingBreaker" , fallbackMethod = "hotelRatingFallback")
    @Retry(name = "hotelRatingBreaker" )
    @RateLimiter(name = "hotelRatingBreaker")
    public  ResponseEntity<User> singleUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return  ResponseEntity.ok(user);
    }

    public ResponseEntity<User>hotelRatingFallback(String userId , Throwable ex){


        logger.info("Service is unavailable ...please check the service status");

        User user = User.builder()
                .email("dummy_support@gmail.com")
                .about("Please contact given mail Id")
                .name("Service not rechable")
                .userId("1234")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @GetMapping
    public  ResponseEntity<List<User>> getAllUser(){
        List<User> users= userService.getAllUser();
        return  ResponseEntity.ok(users);
    }



}
