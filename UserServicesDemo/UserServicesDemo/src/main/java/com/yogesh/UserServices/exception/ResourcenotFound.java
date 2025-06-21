package com.yogesh.UserServices.exception;

public class ResourcenotFound extends RuntimeException {

    public ResourcenotFound(){
        super("Given resource not found. Please provide correct UserId");
    }

    public ResourcenotFound(String message){
        super(message);
    }
}
