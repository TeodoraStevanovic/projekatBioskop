package com.example.projekatbioskop.controller;

import com.example.projekatbioskop.model.Bioskop;
import com.example.projekatbioskop.model.Sala;
import com.example.projekatbioskop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sala")
public class SalaController {

    private SalaService salaService;
    private BioskopService bioskopService;

    public SalaController(SalaService salaService, BioskopService bioskopService) {
        this.salaService = salaService;
        this.bioskopService = bioskopService;
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

    @GetMapping("/showFormAdd")
    public String showFormAdd(@RequestParam("idbioskop") int theId, Model theModel) {
        Sala sala = new Sala();
        Bioskop theCinema = bioskopService.findById(theId);
        System.out.println(theCinema);
        sala.setBioskop(theCinema);

        theModel.addAttribute("sala", sala);
        return "sala/sala-form";
    }

    @PostMapping("/save")
    public String saveSala( @ModelAttribute("sala") Sala theSala) {
        int idBioskopa = theSala.getBioskop().getIdbioskop();
        Bioskop theCinema = bioskopService.findById(idBioskopa);
        System.out.println(theCinema);
        theSala.setBioskop(theCinema);

        salaService.save(theSala);
        return "redirect:/bioskop/list";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("salaid") int theId, Model theModel) {
        Sala theSala = salaService.findById(theId);
        theModel.addAttribute("sala", theSala);
        // send over to our form
        return "sala/sala-form";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("salaid") int theId) {
        salaService.deleteById(theId);
        return "redirect:/bioskop/list";
    }


}
