package com.yogesh.Hotel.exception;

import com.yogesh.Hotel.Payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>handleResourceNotFoundException(ResourceNotFoundException ex){

        String message = ex.getMessage();
           /* ApiResponse response=ApiResponse.builder()
                    .message(message)
                    .success(true)
                    .status(HttpStatus.NOT_FOUND)
                    .build();*/

        ApiResponse response = new ApiResponse(message, HttpStatus.NOT_FOUND, true);

        return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);


    }
}
