package com.example.projekatbioskop.repository;

import com.example.projekatbioskop.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);


    List<User> searchUsersByUsernameIsContainingIgnoreCase(String username);

}
