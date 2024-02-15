package com.scaler.selfuserservice.security.services;

import com.scaler.selfuserservice.models.User;
import com.scaler.selfuserservice.repositiories.UserRepository;
import com.scaler.selfuserservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if (optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User with "+ username + " does not exist.");
        }

        CustomUserDetails userDetails = new CustomUserDetails(optionalUser.get());

        return userDetails;
    }
}
