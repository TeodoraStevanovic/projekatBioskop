package com.example.projekatbioskop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="projekcija")
public class Projekcija {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idprojekcija")
    private int idprojekcija;


    @Column(name="brojmesta")
    private int brojMesta;

    @Column(name="datum")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date datum;

    @Column(name="vreme")
    private String vreme;


    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="idfilm")
    private Film film;
//vidi da li da povezes ipak sa bioskopom
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="idsala")
    private Sala sala;

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

    public Projekcija(int idprojekcija, int brojMesta, Date datum, String vreme, Film film, Sala sala) {
        this.idprojekcija = idprojekcija;
        this.brojMesta = brojMesta;
        this.datum = datum;
        this.vreme = vreme;
        this.film = film;
        this.sala = sala;
    }
}
