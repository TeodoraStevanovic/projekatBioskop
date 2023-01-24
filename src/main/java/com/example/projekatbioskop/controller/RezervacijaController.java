package com.example.projekatbioskop.controller;

import com.example.projekatbioskop.model.*;
import com.example.projekatbioskop.security.CustomUserDetails;
import com.example.projekatbioskop.service.ProjekcijaService;
import com.example.projekatbioskop.service.RezervacijaService;
import com.example.projekatbioskop.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
@Controller
@RequestMapping("/rezervacija")
public class RezervacijaController {

    RezervacijaService rezervacijaService;
    ProjekcijaService projekcijaService;

    UserService userService;
    private PasswordEncoder passwordEncoder;

    public RezervacijaController(RezervacijaService rezervacijaService, ProjekcijaService projekcijaService, UserService userService, PasswordEncoder passwordEncoder) {
        this.rezervacijaService = rezervacijaService;
        this.projekcijaService = projekcijaService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/save")
    public String saveRezervacija(@AuthenticationPrincipal CustomUserDetails loggedUser, @RequestParam("idprojekcija") int theId) {
        Rezervacija rezervacija=new Rezervacija();
        User korisnik=userService.findByUsername(loggedUser.getUser().getUsername());
rezervacija.setUser(korisnik);
Projekcija theProjekcija=projekcijaService.findById(theId);
        System.out.println(theProjekcija);
rezervacija.setProjekcija(theProjekcija);

rezervacija.setPlacedAt(LocalDateTime.now());

        System.out.println(rezervacija);
        if (theProjekcija.getPreostaoBrojMesta()>0){
            theProjekcija.setPreostaoBrojMesta(theProjekcija.getPreostaoBrojMesta()-1);
        rezervacijaService.save(rezervacija);
        }

        return "redirect:/projekcija/list";
    }


    //da se korisniku prikazu sve njegove rezervacije
    @GetMapping("/list")
    public String listRezervacije(Model theModel,@AuthenticationPrincipal CustomUserDetails loggedUser) {
        User korisnik=userService.findByUsername(loggedUser.getUser().getUsername());
        List<Rezervacija> rezervacije  =korisnik.getRezervacije();
        for (Rezervacija b: rezervacije) {System.out.println(b);
        }
        theModel.addAttribute("user",korisnik);
        theModel.addAttribute("rezervations", rezervacije);
       return "user/user";
      }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int theId) {
        Rezervacija rezervacija=rezervacijaService.findById(theId);
        rezervacija.getProjekcija().setPreostaoBrojMesta(rezervacija.getProjekcija().getPreostaoBrojMesta()+1);
        rezervacijaService.deleteById(theId);
        return "redirect:/rezervacija/list";
    }


    @GetMapping("/showFormForUpdateUser")
    public String showFormForUpdateUser(@RequestParam("iduser") int theId, Model theModel) {
        User user = userService.findById(theId);
        theModel.addAttribute("user", user);
        theModel.addAttribute("newPassword","");

        return "user/user-form";
    }
    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User theUser, @RequestParam("newPassword") String lozinka, Errors errors) {
        if (errors.hasErrors()) {
            return "user/user-form";
        }

        if (!StringUtils.isEmpty(lozinka)) {

            theUser.setPassword(passwordEncoder.encode(lozinka));
        }
        System.out.println(lozinka);
        userService.save(theUser);
        return "redirect:/rezervacija/list";
    }
}
