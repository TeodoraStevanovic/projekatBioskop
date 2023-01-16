package com.example.projekatbioskop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@RestResource(path ="reservations",rel = "reservations")
@Entity
@Table(name="rezervacije")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idrezervacije")
    private int idrezervacija;

    @JsonBackReference(value="rezervacija-projekcije")
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="idprojekcija")
    private Projekcija projekcija;

    @JsonBackReference(value="rezervacija-user")
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="iduser")
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JoinColumn(name="placed_at")
    private LocalDateTime placedAt =LocalDateTime.now();


    public int getIdrezervacija() {
        return idrezervacija;
    }

    public Projekcija getProjekcija() {
        return projekcija;
    }

    public void setIdrezervacija(int idrezervacija) {
        this.idrezervacija = idrezervacija;
    }

    public void setProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(LocalDateTime placedAt) {
        this.placedAt = placedAt;
    }

    @Override
    public String toString() {
        return "Rezervacija{" +
                " projekcija=" + projekcija.getFilm()+" "+projekcija.getDatum()+ " "+projekcija.getVreme()+
                ", user=" + user.getUsername()+" " +user.getIme()+" "+user.getPrezime()+
                ", placedAt=" + placedAt +
                '}';
    }
}
