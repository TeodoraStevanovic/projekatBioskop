package com.example.projekatbioskop.controller;


import com.example.projekatbioskop.model.Bioskop;
import com.example.projekatbioskop.model.*;
import com.example.projekatbioskop.service.*;
import com.example.projekatbioskop.service.FilmService;
import com.example.projekatbioskop.service.ProjekcijaService;
import com.example.projekatbioskop.service.SalaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/projekcija")
public class ProjekcijaControler {

    private ProjekcijaService projekcijaService;
    private SalaService salaService;
    private FilmService filmService;
    private BioskopService bioskopService;

    public ProjekcijaControler(ProjekcijaService projekcijaService, SalaService salaService, FilmService filmService,BioskopService bioskopService) {
        this.projekcijaService = projekcijaService;
        this.salaService = salaService;
        this.filmService = filmService;
        this.bioskopService=bioskopService;
    }

    @GetMapping("/list")
    public String listProjekcije(Model theModel) {
        List<Bioskop> bioskopi =bioskopService.findAll();
      //  for (Bioskop b: bioskopi) {System.out.println(b);}
        theModel.addAttribute("noviBioskop",new Bioskop());
        theModel.addAttribute("cinemas", bioskopi);
        return "projekcija/projekcije";
    }
    @GetMapping("/listProjekcije")
    public String listProjekcijeZaBioskop(Model theModel,@ModelAttribute("noviBioskop") Bioskop bioskop1) {
        //
        List<Bioskop> bioskopi =bioskopService.findAll();
        theModel.addAttribute("cinemas", bioskopi);
        //
        Bioskop theBioskop = bioskopService.findById(bioskop1.getIdbioskop());
        System.out.println(theBioskop);
       List<Sala> sale=theBioskop.getSale();
        List<Projekcija> projekcije = new ArrayList<>();
        for (Sala s:sale) {
           for (Projekcija p: s.getProjekcije()) {
               projekcije.add(p);
           }}

       System.out.println(projekcije);
      //  List<Projekcija> theProjekcija = projekcijaService.findAll();
       theModel.addAttribute("projekcije", projekcije);
        return "projekcija/projekcije";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/showFormAdd")
    public String showFormAdd(Model theModel) {
        Projekcija projekcija = new Projekcija();
       List<Sala> sale=salaService.findAll();
       List<Film> filmovi =filmService.findAll();
        theModel.addAttribute("movies", filmovi);
        theModel.addAttribute("sale", sale);
        theModel.addAttribute("project", projekcija);
        return "projekcija/projekcija-form";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("idprojekcija") int theId, Model theModel) {
        Projekcija theProjekcija = projekcijaService.findById(theId);
        System.out.println(theProjekcija);
        List<Sala> sale =salaService.findAll();
        List<Film> filmovi =filmService.findAll();
        theModel.addAttribute("movies", filmovi);
        theModel.addAttribute("sale", sale);
        theModel.addAttribute("project", theProjekcija);
        // send over to our form
        return "projekcija/projekcija-form";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public String saveCinema(@ModelAttribute("project") Projekcija projekcija) {
        projekcija.setBrojMesta(projekcija.getSala().getKapacitet());
        projekcija.setPreostaoBrojMesta(projekcija.getSala().getKapacitet());
        projekcijaService.save(projekcija);
        return "redirect:/projekcija/list";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam("idprojekcija") int theId) {
        projekcijaService.deleteById(theId);
        return "redirect:/projekcija/list";
    }
    @GetMapping("/showDetail")
    public String showDetail(@RequestParam("idprojekcija") int theId, Model theModel) {
        Projekcija theProjekcija = projekcijaService.findById(theId);
        theModel.addAttribute("project", theProjekcija);
        return "projekcija/projekcija-detail";
    }
}
