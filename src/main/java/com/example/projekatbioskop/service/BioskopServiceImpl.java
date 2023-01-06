package com.example.projekatbioskop.service;


import com.example.projekatbioskop.model.Bioskop;
import com.example.projekatbioskop.repository.BioskopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BioskopServiceImpl implements BioskopService{

    BioskopRepository bioskopRepository;

    public BioskopServiceImpl(BioskopRepository thebioskopRepository) {
        bioskopRepository = thebioskopRepository;
    }

    @Override
    public List<Bioskop> findAll() {
        return bioskopRepository.findAll();
    }

    @Override
    public Bioskop findById(int theId) {
        Optional<Bioskop> result = bioskopRepository.findById(theId);

        Bioskop theBioskop = null;

        if (result.isPresent()) {
            theBioskop = result.get();
        }
        else {
            throw new RuntimeException("Did not find bioskop id - " + theId);
        }
        return theBioskop;
    }


    @Override
    public void save(Bioskop theBioskop) {

        bioskopRepository.save(theBioskop);
    }

    @Override
    public void deleteById(int theId) {
        bioskopRepository.deleteById(theId);

    }
}
