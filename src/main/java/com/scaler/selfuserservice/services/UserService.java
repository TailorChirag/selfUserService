package com.scaler.selfuserservice.services;

import com.scaler.selfuserservice.exceptions.PasswordNotFoundException;
import com.scaler.selfuserservice.exceptions.UsernameNotFoundException;
import com.scaler.selfuserservice.models.Token;
import com.scaler.selfuserservice.models.User;
import com.scaler.selfuserservice.repositiories.TokenRepository;
import com.scaler.selfuserservice.repositiories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public User signUp(String fullName, String email, String password){

        User user = new User();
        user.setName(fullName);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));

        User user1 = userRepository.save(user);

        return user1;
    }

    public Token login(String email, String password) throws PasswordNotFoundException, UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User does not exist");
        }

        User user = optionalUser.get();

        if(!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
            throw new PasswordNotFoundException("Password does not match");
        }


        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = currentDate.plusDays(30);

        // Convert LocalDate to java.util.Date
        Date expiryDateAsDate = Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token = new Token();
        token.setUser(user);
        token.setExpiryAt(expiryDateAsDate);
        token.setValue(RandomStringUtils.randomAlphabetic(128));

        return tokenRepository.save(token);
    }



}
