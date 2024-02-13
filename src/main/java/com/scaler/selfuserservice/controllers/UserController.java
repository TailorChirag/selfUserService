package com.scaler.selfuserservice.controllers;

import com.scaler.selfuserservice.dtos.LoginRequestDto;
import com.scaler.selfuserservice.dtos.SignUpRequestDto;
import com.scaler.selfuserservice.exceptions.PasswordNotFoundException;
import com.scaler.selfuserservice.exceptions.UsernameNotFoundException;
import com.scaler.selfuserservice.models.Token;
import com.scaler.selfuserservice.models.User;
import com.scaler.selfuserservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody() SignUpRequestDto request){

        String name = request.getName();
        String email = request.getEmail();
        String password = request.getPassword();

        return userService.signUp(name,email,password);
    }

    @PostMapping("/login")
    public Token login(@RequestBody()LoginRequestDto requestDto) throws PasswordNotFoundException, UsernameNotFoundException {
        return userService.login(requestDto.getEmail(), requestDto.getPassword());
    }

}
