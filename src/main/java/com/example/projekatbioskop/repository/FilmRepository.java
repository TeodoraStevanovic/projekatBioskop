package com.example.projekatbioskop.repository;


import com.example.projekatbioskop.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Integer> {
}
