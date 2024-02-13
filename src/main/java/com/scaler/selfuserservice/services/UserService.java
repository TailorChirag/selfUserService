package com.scaler.selfuserservice.services;

import com.scaler.selfuserservice.exceptions.UsernameNotFoundException;
import com.scaler.selfuserservice.models.User;
import com.scaler.selfuserservice.repositiories.UserRepositiory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepositiory userRepositiory;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepositiory userRepositiory,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepositiory = userRepositiory;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User signUp(String fullName, String email, String password){

        User user = new User();
        user.setName(fullName);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));

        User user1 = userRepositiory.save(user);

        return user1;
    }

    public UsernameNotFoundException login(String email, String password){

        Optional<User> optionalUser = userRepositiory.findByEmail(email);

        if (optionalUser.isEmpty()){
            return new UsernameNotFoundException("This username does not exist");
        }

        return null;
    }
}
