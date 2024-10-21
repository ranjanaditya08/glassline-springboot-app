package com.spring.Glassline.exception;

import com.spring.Glassline.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> resourceNotFoundExceptionHandler(ResourceNotFoundException e){

        ApiResponse<?> response = new ApiResponse<>("",e.getMessage(),false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
