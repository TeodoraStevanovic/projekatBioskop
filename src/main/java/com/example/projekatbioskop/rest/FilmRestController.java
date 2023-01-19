package com.example.projekatbioskop.rest;

import com.example.projekatbioskop.model.Film;
import com.example.projekatbioskop.repository.FilmRepository;
import com.example.projekatbioskop.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(path="/api")
public class FilmRestController {
    private FilmService filmService;
@Autowired private FilmRepository filmRepository;

   public FilmRestController(FilmService filmService) {
       this.filmService = filmService;}

   @GetMapping("/movies")
    public List<Film> listFilms() {
         return filmService.findAll();
    }
//prva verzija-radi
    /*
    @GetMapping("/movies/{id}")
    public ResponseEntity<Film> filmByid(@PathVariable("id") int id) {
        Film film = filmService.findById(id);
        if(film == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(film);
        }
    }
    */
    //druga verzija -radi
    /*
    @GetMapping("/movies/{id}")
    public Film getFilm(@PathVariable("id") int id) {
        Film film = filmService.findById(id);

        if (film == null) {
            throw new RuntimeException("film id not found - " + id);
        }

        return film;
    }

     */

    //treca verzija-radi
    @GetMapping("/movies/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable("id") int id) {
        Optional<Film> opt = filmRepository.findById(id);
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
// add mapping for POST /movies - add new employee

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public Film addmovie(@RequestBody Film theMovie) {

            Film f1 = filmService.create(theMovie);
            return f1;
        }


    @PutMapping(path="/movies/{id}")
    public Film putMovie(@PathVariable("id") int id, @RequestBody Film film) {
        //odseca podatke koji nisu uneti prilikom slanja zahteva zato sto ova metoda
        //zapravo zamenjuje ceo objekat
        film.setIdfilm(id);
        return filmService.update(film);
    }
    @PatchMapping(path="/movies/{id}", consumes="application/json")
    public Film patchMovie(@PathVariable("id") int id, @RequestBody Film patch) {

        Film film = filmService.findById(id);
        if (patch.getNaziv() != null) {
            film.setNaziv(patch.getNaziv());
        }
        if (patch.getGlumci() != null) {
            film.setGlumci(patch.getGlumci());
        }
        if (patch.getOpis() != null) {
            film.setOpis(patch.getOpis());
        }
        if (patch.getTrajanje() != 0) {
            film.setTrajanje(patch.getTrajanje());
        }
        if (patch.getOcena() != 0.0) {
            film.setOcena(patch.getOcena());
        }
        if (patch.getZanr() != null) {
            film.setZanr(patch.getZanr());
        }
        if (patch.getSlika() != null) {
            film.setSlika(patch.getSlika());
        }
        if (patch.getProjekcije() != null) {
            film.setProjekcije(patch.getProjekcije());
        }
        return filmService.update(film);
    }
    @DeleteMapping("/movies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable("id") int id) {
        try {
            filmService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("odabran id filma ne postoji");

        }    }

}
