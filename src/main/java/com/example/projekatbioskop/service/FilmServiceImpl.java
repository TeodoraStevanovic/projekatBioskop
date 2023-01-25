package com.example.projekatbioskop.service;

import com.example.projekatbioskop.model.Film;
import com.example.projekatbioskop.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
           //  we didn't find the employee
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


    @Override
    public Film create(Film film) {
        if(film.getNaziv()== null){
            film.setNaziv("Nepoznat");
        } else if(film.getGlumci()== null){
            film.setGlumci("Nepoznati");
        } else if(film.getOpis() == null){
            film.setOpis("bez opisa");
        } else if(film.getProjekcije() == null){
            film.setProjekcije(new ArrayList<>());

        } else if(film.getOcena() ==0){
            film.setOcena(0.0);
        } else if(film.getZanr() == null){
            film.setZanr("Nepoznat");
        } else if (film.getSlika()==null) {
           film.setSlika("https://i.pinimg.com/564x/51/70/7f/51707f5c6c1d2267a06df12a7e8b5eb8.jpg");
        }
        filmRepository.save(film);
        return film;
    }

    @Override
    public Film update(Film film) {
        filmRepository.save(film);
        return film;
    }



}
