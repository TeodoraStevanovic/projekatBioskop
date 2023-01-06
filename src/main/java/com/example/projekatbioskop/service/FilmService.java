package com.example.projekatbioskop.service;

import com.example.projekatbioskop.model.Film;

import java.util.List;

public interface FilmService {
    public List<Film> findAll();

    public Film findById(int theId);

    public void save(Film theEmployee);

    public void deleteById(int theId);
}
