package com.example.projekatbioskop.service;

import com.example.projekatbioskop.model.Film;
import org.springframework.data.domain.PageRequest;

import java.net.ContentHandler;
import java.util.List;

public interface FilmService {
    public List<Film> findAll();

    public Film findById(int theId);

    public void save(Film theEmployee);

    public void deleteById(int theId);


     public Film create(Film theMovie);

    Film update(Film film);


}
