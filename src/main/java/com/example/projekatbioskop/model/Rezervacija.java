package com.example.projekatbioskop.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="rezervacije")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idrezervacije")
    private int idrezervacija;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="idprojekcija")
    private Projekcija projekcija;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="iduser")
    private User user;


    @JoinColumn(name="placed_at")
    private Date placedAt = new Date();


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

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
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
