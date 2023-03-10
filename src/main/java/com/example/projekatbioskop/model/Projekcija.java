package com.example.projekatbioskop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestResource(path ="projections",rel = "projections")
@Entity
@Table(name="projekcija")
public class Projekcija {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idprojekcija")
    private int idprojekcija;

//ukupan broj mesta
    @Column(name="brojmesta")
    private int brojMesta;

    @Future(message = "datum mora da bude u buducnosti")
    @Column(name="datum")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date datum;

   @NotBlank(message = "vreme je obavezno polje")
   @Column(name="vreme")
    private String vreme;
@Column(name="preostao_broj_mesta")
private int preostaoBrojMesta;

    @JsonBackReference(value="film-projekcija")
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="idfilm")
    private Film film;
//vidi da li da povezes ipak sa bioskopom
@JsonBackReference(value="sala-projekcija")
@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
@JoinColumn(name="idsala")
    private Sala sala;

    @JsonManagedReference(value="rezervacija-projekcije")
    @OneToMany(mappedBy="projekcija",
            cascade = CascadeType.MERGE    )
    private List<Rezervacija> rezervacije;

    public int getIdprojekcija() {
        return idprojekcija;
    }

    public void setIdprojekcija(int idprojekcija) {
        this.idprojekcija = idprojekcija;
    }

    public int getBrojMesta() {
        return brojMesta;
    }

    public void setBrojMesta(int brojMesta) {
        this.brojMesta = brojMesta;
    }


    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Projekcija() {
    }

    @Override
    public String toString() {
        return "Projekcija{" +
                "idprojekcija=" + idprojekcija +
                ", brojMesta=" + brojMesta +
                ", datum=" + datum +
                ", vreme='" + vreme + '\'' +
                ", film=" + film.getNaziv() +
                ", sala=" + sala.getNaziv() +
                '}';
    }


    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

    public void add(Rezervacija rezervacija) {
        if (rezervacije == null) {
            rezervacije = new ArrayList<>();
        }
        rezervacije.add(rezervacija);
        rezervacija.setProjekcija(this);
    }

    public int getPreostaoBrojMesta() {
        return preostaoBrojMesta;
    }

    public void setPreostaoBrojMesta(int preostaoBrojMesta) {
        this.preostaoBrojMesta = preostaoBrojMesta;
    }

    public Projekcija(int brojMesta, Date datum, String vreme, int preostaoBrojMesta, Film film, Sala sala) {
        this.brojMesta = brojMesta;
        this.datum = datum;
        this.vreme = vreme;
        this.preostaoBrojMesta = preostaoBrojMesta;
        this.film = film;
        this.sala = sala;
    }
}
