package com.example.projekatbioskop.controller;

import com.example.projekatbioskop.model.Rezervacija;
import com.example.projekatbioskop.model.User;
import com.example.projekatbioskop.service.RezervacijaService;
import com.example.projekatbioskop.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RezervacijaService rezervacijaService;

    public AdminController(UserService userService,RezervacijaService projekcijaService) {
        this.userService = userService;
        this.rezervacijaService =projekcijaService;
    }
    @GetMapping
    public String adminPanel(Model theModel){
      List<User> users=userService.findAll();
      theModel.addAttribute("users",users);
      theModel.addAttribute("username",new String());

      List<Rezervacija> rezervacije=rezervacijaService.findAll();
      theModel.addAttribute("rezervations",rezervacije);
        return "admin/admin";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("iduser") int theId) {
        userService.deleteById(theId);
        return "redirect:/admin";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("iduser") int theId, Model theModel) {
        List<String> list = new ArrayList<>();
        list.add("ADMIN");
        list.add("USER");
        theModel.addAttribute("list",list);
        User user = userService.findById(theId);
        theModel.addAttribute("user", user);
        // send over to our form
        return "admin/user-form";
    }

    @PostMapping("/save")
    public String saveUser( @ModelAttribute("user") User theUser) {
        userService.save(theUser);
        return "redirect:/admin";
    }

    @RequestMapping("/search")
    public String search(@RequestParam("username")String  username1, Model model){
        System.out.println(username1);
        List<User> users=userService.searchUsersByUsernameIsContainingIgnoreCase(username1);
        for (User u: users) {System.out.println(u);}
        if(users.isEmpty()){
             users=userService.findAll();
             model.addAttribute("poruka",true);
        }
        model.addAttribute("users",users);
        return "admin/admin";
    }

    @GetMapping("/deleteReservation")
    public String deleteReservation(@RequestParam("id") int theId) {
        Rezervacija rezervacija=rezervacijaService.findById(theId);
        rezervacija.getProjekcija().setPreostaoBrojMesta(rezervacija.getProjekcija().getPreostaoBrojMesta()+1);


        rezervacijaService.deleteById(theId);
        return "redirect:/admin";
    }

  /*  @GetMapping("/showFormForUpdateReservation")
    public String showFormForUpdateReservation(@RequestParam("id") int theId, Model theModel) {
        Rezervacija rez = rezervacijaService.findById(theId);
        theModel.addAttribute("rezervation", rez);
        // send over to our form
        return "admin/rezervation-form";
    }

    @PostMapping("/saveReservation")
    public String saveReservation( @ModelAttribute("rezervation") Rezervacija rez) {
        rezervacijaService.save(rez);
        return "redirect:/admin";
    }
*/
}
