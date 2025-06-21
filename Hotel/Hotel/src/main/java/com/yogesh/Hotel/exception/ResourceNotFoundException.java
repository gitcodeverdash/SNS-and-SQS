package com.yogesh.Hotel.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String provideCorrectHotelId) {
        super(provideCorrectHotelId);
    }

    public ResourceNotFoundException (){
        super("Given resource not found. Please provide correct UserId");
    }
}
