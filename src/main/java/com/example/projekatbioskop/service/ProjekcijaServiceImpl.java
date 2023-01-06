package com.example.projekatbioskop.service;


import com.example.projekatbioskop.model.Projekcija;
import com.example.projekatbioskop.repository.ProjekcijaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjekcijaServiceImpl implements ProjekcijaService{

ProjekcijaRepository projekcijaRepository;

    public ProjekcijaServiceImpl(ProjekcijaRepository projekcijaRepository) {
        this.projekcijaRepository = projekcijaRepository;
    }

    @Override
    public List<Projekcija> findAll() {
        return projekcijaRepository.findAll();
    }

    @Override
    public Projekcija findById(int theId) {
        Optional<Projekcija> result=projekcijaRepository.findById(theId);
        Projekcija theProjekcija=null;
        if (result.isPresent()) {
            theProjekcija = result.get();
        }
        else {
            throw new RuntimeException("Did not find projekcija id - " + theId);
        }

        return theProjekcija;
    }

    @Override
    public void save(Projekcija theProjekcija) {
        projekcijaRepository.save(theProjekcija);

    }

    @Override
    public void deleteById(int theId) {
        projekcijaRepository.deleteById(theId);

    }
}
