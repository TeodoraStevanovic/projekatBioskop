package com.example.projekatbioskop.service;

import com.example.projekatbioskop.model.Bioskop;
import com.example.projekatbioskop.model.Rezervacija;

import java.util.List;

public interface RezervacijaService {

    public List<Rezervacija> findAll();

    public Rezervacija findById(int theId);

    public void save(Rezervacija theRezervacija);

    public void deleteById(int theId);
}
