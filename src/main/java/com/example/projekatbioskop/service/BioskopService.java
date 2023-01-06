package com.example.projekatbioskop.service;


import com.example.projekatbioskop.model.Bioskop;

import java.util.List;

public interface BioskopService {
    public List<Bioskop> findAll();

    public Bioskop findById(int theId);

    public void save(Bioskop theEmployee);

    public void deleteById(int theId);
}
