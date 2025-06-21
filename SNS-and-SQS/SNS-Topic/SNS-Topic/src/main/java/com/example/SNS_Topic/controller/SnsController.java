package com.example.SNS_Topic.controller;

import com.example.SNS_Topic.service.Snsservice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SnsController {

    private final Snsservice snsservice;


    public SnsController(Snsservice snsservice) {
        this.snsservice = snsservice;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody String orderData){
            snsservice.publishMessage( orderData);

    return ResponseEntity.ok("Order published");
    }
}
