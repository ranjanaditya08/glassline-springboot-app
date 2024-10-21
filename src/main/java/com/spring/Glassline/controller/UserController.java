package com.spring.Glassline.controller;

import com.spring.Glassline.entity.User;
import com.spring.Glassline.service.UserService;
import com.spring.Glassline.utils.ApiResponse;
import com.spring.Glassline.utils.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> signUp(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest loginRequest){
      //  System.out.println("DEBUG Controller: "+loginRequest.getEmail() + "  "+ loginRequest.getPassword());
        return new ResponseEntity<>(userService.validateUser(loginRequest), HttpStatus.OK);
    }

}
