package com.example.projekatbioskop.repository;

import com.example.projekatbioskop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
