package com.scaler.selfuserservice.controllers;

import com.scaler.selfuserservice.dtos.SignUpRequestDto;
import com.scaler.selfuserservice.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping()
    public User signUp(@RequestBody() SignUpRequestDto request){
        String email = request.getEmail();
        String name = request.getName();
        String password = request.getPassword();

        return null;
    }
}
