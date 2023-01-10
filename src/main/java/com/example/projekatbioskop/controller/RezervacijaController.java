package com.example.projekatbioskop.controller;

import com.example.projekatbioskop.model.*;
import com.example.projekatbioskop.security.CustomUserDetails;
import com.example.projekatbioskop.service.ProjekcijaService;
import com.example.projekatbioskop.service.RezervacijaService;
import com.example.projekatbioskop.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/rezervacija")
public class RezervacijaController {

    RezervacijaService rezervacijaService;
    ProjekcijaService projekcijaService;
    //vidi da li treba user detail ili user service
    UserService userService;

    public RezervacijaController(RezervacijaService rezervacijaService, ProjekcijaService projekcijaService, UserService userService) {
        this.rezervacijaService = rezervacijaService;
        this.projekcijaService = projekcijaService;
        this.userService = userService;
    }

  //  @GetMapping("/list")
  //  public String listRezervacije(Model theModel) {
   //     List<Rezervacija> rezervacije =rezervacijaService.findAll();
   //     for (Rezervacija b: rezervacije) {
   //         System.out.println(b);
   //     }
       // theModel.addAttribute("noviBioskop",new Bioskop());
   //     theModel.addAttribute("rezervations", rezervacije);
    //    return "rezervacije/rezervacije";
  //  }


   // @GetMapping("/showFormAdd")
   // public String showFormAdd(Model theModel) {
     //   Projekcija projekcija = new Projekcija();
     //   List<Sala> sale=salaService.findAll();
     //   List<Film> filmovi =filmService.findAll();
       // theModel.addAttribute("movies", filmovi);
      //  theModel.addAttribute("sale", sale);
      //  theModel.addAttribute("project", projekcija);
      //  return "projekcija/projekcija-form";
  //  }

    @RequestMapping("/save")
    public String saveRezervacija(@AuthenticationPrincipal CustomUserDetails loggedUser, @RequestParam("idprojekcija") int theId) {
        Rezervacija rezervacija=new Rezervacija();
        User korisnik=userService.findByUsername(loggedUser.getUser().getUsername());
rezervacija.setUser(korisnik);
Projekcija theProjekcija=projekcijaService.findById(theId);
        System.out.println(theProjekcija);
rezervacija.setProjekcija(theProjekcija);
rezervacija.setPlacedAt(new Date());
        System.out.println(rezervacija);
        if (theProjekcija.getPreostaoBrojMesta()>0){
            theProjekcija.setPreostaoBrojMesta(theProjekcija.getPreostaoBrojMesta()-1);
        rezervacijaService.save(rezervacija);
        }

        return "redirect:/projekcija/list";
    }

}
