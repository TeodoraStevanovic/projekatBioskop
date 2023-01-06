package com.example.projekatbioskop.service;


import com.example.projekatbioskop.model.Projekcija;

import java.util.List;

public interface ProjekcijaService {

    public List<Projekcija> findAll();

    public Projekcija findById(int theId);

    public void save(Projekcija theProjekcija);

    public void deleteById(int theId);
}
