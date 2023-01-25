package com.example.projekatbioskop.controller;



import com.example.projekatbioskop.model.Film;
import com.example.projekatbioskop.model.Projekcija;
import com.example.projekatbioskop.service.FilmService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/film")
public class FilmController {

    private FilmService filmService;

    public FilmController(FilmService thefilmService) {
        filmService = thefilmService;

    }

  //  @GetMapping("/index")
   // public String HomePage() {

      //  return "homepage";
   // }
    @GetMapping("/list")
    public String listMovies(Model theModel) {
        List<Film> theMovies = filmService.findAll();

        theModel.addAttribute("movies", theMovies);

      //  for (Film f:theMovies) { System.out.println(f); }

        return "film/repertoar";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("idfilm") int theId,Model theModel) {
        Film theMovie = filmService.findById(theId);
        theModel.addAttribute("movie", theMovie);
        // send over to our form
        return "film/film-form";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam("idfilm") int theId) {
        filmService.deleteById(theId);
        return "redirect:/film/list";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("movie") Film theMovie, Errors errors) {

       if (errors.hasErrors()) {
            return "film/film-form";
       }
        filmService.save(theMovie);
        return "redirect:/film/list";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/showFormAdd")
    public String showFormAdd(Model theModel) {
        Film theMovie = new Film();
        theModel.addAttribute("movie", theMovie);
        return "film/film-form";}

////////////////////////////
    //za jedan film prikazati sve projekcije
@GetMapping("/showDetail")
public String showDetail(@RequestParam("idfilm") int theId, Model theModel) {
        Film theMovie = filmService.findById(theId);
        theModel.addAttribute("movie", theMovie);
//za ovaj film naci sve projekcije
    List<Projekcija> projekcije = new ArrayList<>();
    projekcije=theMovie.getProjekcije();
    for (Projekcija p: projekcije
         ) {
        System.out.println(p);
    }
    theModel.addAttribute("projects", projekcije);
    // send over to our form
    return "film/film-detail";
}

}
