package com.example.Auth_Api.service;

import com.example.Auth_Api.dto.RegisterDto;
import com.example.Auth_Api.entity.User;
import com.example.Auth_Api.exception.NotUniqueEmailException;
import com.example.Auth_Api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public User register(RegisterDto data){
        if (userRepository.findByEmail(data.email()) != null){
            throw new NotUniqueEmailException("This email is already used.");
        }

        String password = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), password, data.role());

        return userRepository.save(newUser);
    }
}
