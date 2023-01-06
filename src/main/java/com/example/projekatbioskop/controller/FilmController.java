package com.example.projekatbioskop.controller;



import com.example.projekatbioskop.model.Film;
import com.example.projekatbioskop.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

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

        for (Film f:theMovies) { System.out.println(f); }

        return "film/repertoar";
    }

    @GetMapping("/showDetail")
    public String showDetail(@RequestParam("idfilm") int theId, Model theModel) {


        Film theMovie = filmService.findById(theId);


        theModel.addAttribute("movie", theMovie);

        // send over to our form
        return "film/film-detail";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("idfilm") int theId,Model theModel) {
        Film theMovie = filmService.findById(theId);
        theModel.addAttribute("movie", theMovie);
        // send over to our form
        return "film/film-form";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("idfilm") int theId) {
        filmService.deleteById(theId);
        return "redirect:/film/list";
    }
    @PostMapping("/save")
    public String saveEmployee( @ModelAttribute("movie") Film theMovie) {
//, Errors errors
      //  if (errors.hasErrors()) {
        //    return "design";
      //  }
        filmService.save(theMovie);
        return "redirect:/film/list";
    }


    @GetMapping("/showFormAdd")
    public String showFormAdd(Model theModel) {

        // create model attribute to bind form data
        Film theMovie = new Film();

        theModel.addAttribute("movie", theMovie);

        return "film/film-form";
    }

////////////////////////////


}
