package com.yogesh.Ratings.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String provideCorrectRatingId) {
        super(provideCorrectRatingId);
    }

}
