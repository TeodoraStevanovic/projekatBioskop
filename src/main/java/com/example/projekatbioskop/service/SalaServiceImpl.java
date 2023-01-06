package com.example.projekatbioskop.service;


import com.example.projekatbioskop.model.Sala;
import com.example.projekatbioskop.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaServiceImpl implements SalaService{

    SalaRepository salaRepository;

    public SalaServiceImpl(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Override
    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    @Override
    public Sala findById(int theId) {
        Optional<Sala> result = salaRepository.findById(theId);

        Sala theSala = null;

        if (result.isPresent()) {
            theSala = result.get();
        }
        else {
            throw new RuntimeException("Did not find sala id - " + theId);
        }
        return theSala;
    }

    @Override
    public void save(Sala theSala) {
        salaRepository.save(theSala);

    }

    @Override
    public void deleteById(int theId) {
salaRepository.deleteById(theId);
    }
}
