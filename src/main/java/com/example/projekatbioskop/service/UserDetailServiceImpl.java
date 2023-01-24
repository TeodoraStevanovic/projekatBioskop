package com.example.projekatbioskop.service;

import com.example.projekatbioskop.repository.UserRepository;
import com.example.projekatbioskop.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

           final com.example.projekatbioskop.model.User user = userRepository.findByUsername(username);
           if (user == null) {throw new UsernameNotFoundException("User '" + username + "' not found");
           }
          //  UserDetails user1= User.withUsername(user.getUsername()).password(user.getPassword()).authorities("USER").build();
           // return user1;
     return new CustomUserDetails(user);

        }
    }

