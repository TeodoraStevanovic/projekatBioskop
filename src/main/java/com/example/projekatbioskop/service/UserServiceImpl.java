package com.example.projekatbioskop.service;

import com.example.projekatbioskop.model.User;
import com.example.projekatbioskop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User theUser) {
        userRepository.save(theUser);

    }


    public List<com.example.projekatbioskop.model.User> findAll() {
        return (List<User>) userRepository.findAll();
    }


    public com.example.projekatbioskop.model.User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);

     User userr = null;

        if (result.isPresent()) {
            userr = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find user id - " + theId);
        }

        return userr;
    }
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }




    //keiraj user-a?
}
