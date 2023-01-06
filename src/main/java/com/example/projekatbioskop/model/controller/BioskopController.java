package com.example.projekatbioskop.model.controller;

import com.example.projekatbioskop.model.Bioskop;
import com.example.projekatbioskop.service.BioskopService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bioskop")
public class BioskopController {

    private BioskopService bioskopService;

    public BioskopController(BioskopService bioskopService) {
        this.bioskopService = bioskopService;
    }

    @GetMapping("/list")
    public String listCinemas(Model theModel) {
        List<Bioskop> theBioskop = bioskopService.findAll();
        theModel.addAttribute("cinemas", theBioskop);
        for (Bioskop s:theBioskop) { System.out.println(s); }
        return "bioskop/svibioskopi";
    }
    @GetMapping("/showDetail")
    public String showDetail(@RequestParam("idbioskop") int theId, Model theModel) {
        Bioskop theCinema = bioskopService.findById(theId);
        System.out.println(theCinema);
        theModel.addAttribute("cinema", theCinema);
       return "bioskop/bioskop-detail";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/showFormAdd")
    public String showFormAdd(Model theModel) {
        Bioskop theCinema = new Bioskop();
        theModel.addAttribute("cinema", theCinema);
        return "bioskop/bioskop-form";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public String saveCinema( @ModelAttribute("cinema") Bioskop theCinema) {
        bioskopService.save(theCinema);
        return "redirect:/bioskop/list";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam("idbioskop") int theId) {
        bioskopService.deleteById(theId);
        return "redirect:/bioskop/list";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("idbioskop") int theId,Model theModel) {
        Bioskop theCinema = bioskopService.findById(theId);
        theModel.addAttribute("cinema", theCinema);
        return "bioskop/bioskop-form";
    }
}
