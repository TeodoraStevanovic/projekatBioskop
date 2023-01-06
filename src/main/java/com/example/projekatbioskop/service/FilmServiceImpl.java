package com.example.projekatbioskop.service;

import com.example.projekatbioskop.model.Film;
import com.example.projekatbioskop.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService{
    FilmRepository filmRepository;

@Autowired
    public FilmServiceImpl(FilmRepository thefilmRepository) {
       filmRepository = thefilmRepository;
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film findById(int theId) {
        Optional<Film> result = filmRepository.findById(theId);

        Film theFilm = null;

        if (result.isPresent()) {
            theFilm = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find film id - " + theId);
        }

        return theFilm;
    }

    @Override
    public void save(Film theFilm) {
        filmRepository.save(theFilm);
    }

    @Override
    public void deleteById(int theId) {
    filmRepository.deleteById(theId);

    }
}
