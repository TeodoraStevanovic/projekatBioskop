package com.example.projekatbioskop.controller;

import com.example.projekatbioskop.model.Bioskop;
import com.example.projekatbioskop.model.Projekcija;
import com.example.projekatbioskop.model.Sala;
import com.example.projekatbioskop.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sala")
public class SalaController {

    private SalaService salaService;
    private BioskopService bioskopService;
    private ProjekcijaService projekcijaService;

    public SalaController(SalaService salaService, BioskopService bioskopService, ProjekcijaService projekcijaService) {
        this.salaService = salaService;
        this.bioskopService = bioskopService;
        this.projekcijaService=projekcijaService;
    }

    @GetMapping("/list")
    public String listSale(Model theModel) {

        List<Sala> theSale = salaService.findAll();

        theModel.addAttribute("sale", theSale);

        for (Sala f : theSale) {
            System.out.println(f);
        }

        return "film/repertoar";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/showFormAdd")
    public String showFormAdd(@RequestParam("idbioskop") int theId, Model theModel) {
        Sala sala = new Sala();
        Bioskop theCinema = bioskopService.findById(theId);
        System.out.println(theCinema);
        sala.setBioskop(theCinema);

        theModel.addAttribute("sala", sala);
        return "sala/sala-form";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public String saveSala(@Valid @ModelAttribute("sala") Sala theSala, Errors errors) {

        if (errors.hasErrors()) {
            return "sala/sala-form";
        }
        int idBioskopa = theSala.getBioskop().getIdbioskop();
        Bioskop theCinema = bioskopService.findById(idBioskopa);
        //makon toga nadji sve postojece projekcije za taj bioskop
        //nadji sve projekcije za bioskop
        List<Projekcija> projekcije=projekcijaService.findAll();
        List<Projekcija> projekcijeZaIzmenu = new ArrayList<>();

        for (Projekcija p: projekcije) {
            if(p.getSala().getIdsala()==theSala.getIdsala())
            {projekcijeZaIzmenu.add(p);}

        }
        for (Projekcija g: projekcijeZaIzmenu) {
g.setBrojMesta(theSala.getKapacitet());
g.setPreostaoBrojMesta(theSala.getKapacitet());
        }

        System.out.println(theCinema);
        theSala.setBioskop(theCinema);

        salaService.save(theSala);
        return "redirect:/bioskop/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("salaid") int theId, Model theModel) {
        Sala theSala = salaService.findById(theId);
        theModel.addAttribute("sala", theSala);
        // send over to our form
        return "sala/sala-form";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam("salaid") int theId) {
        salaService.deleteById(theId);
        return "redirect:/bioskop/list";
    }


}
