package com.example.projekatbioskop.service;

import com.example.projekatbioskop.model.Film;
import com.example.projekatbioskop.model.Sala;

import java.util.List;

public interface SalaService {

    public List<Sala> findAll();

    public Sala findById(int theId);

    public void save(Sala theSala);

    public void deleteById(int theId);
}
