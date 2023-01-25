package com.example.projekatbioskop.service;

import com.example.projekatbioskop.model.Rezervacija;
import com.example.projekatbioskop.repository.RezervacijaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RezervacijaServiceImpl  implements RezervacijaService{


    RezervacijaRepository rezervacijaRepository;

    public RezervacijaServiceImpl(RezervacijaRepository rezervacijaRepository) {
        this.rezervacijaRepository = rezervacijaRepository;
    }

    @Override
    public List<Rezervacija> findAll() {
        return rezervacijaRepository.findAll();
    }

    @Override
    public Rezervacija findById(int theId) {
        Optional<Rezervacija> result=rezervacijaRepository.findById(theId);
        Rezervacija rezervacija=null;
        if (result.isPresent()) {
            rezervacija = result.get();
        }
        else {
            throw new RuntimeException("Did not find rezervacija id - " + theId);
        }

        return rezervacija;
    }

    @Override
    public void save(Rezervacija theRezervacija) {
        rezervacijaRepository.save(theRezervacija);

    }

    @Override
    public void deleteById(int theId) {
        rezervacijaRepository.deleteById(theId);

    }
}
