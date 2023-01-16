package com.example.projekatbioskop.repository;


import com.example.projekatbioskop.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "movies")
public interface FilmRepository extends JpaRepository<Film,Integer> {
}
