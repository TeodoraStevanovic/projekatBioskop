package com.example.projekatbioskop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.validation.constraints.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@RestResource(path ="movies",rel = "movies")

@Entity
@Table(name="film")
public class Film {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idfilm")
    private int idfilm;

    @NotBlank
    @Size(min=1, message="Film mora da ima glumce")
    @Size(max=500, message="najvise 500 karaktera")
    @Column(name="glumci")
    private String glumci;


    @NotBlank
    @Size(min=1, message="Naziv filma mora da ima bar jedan karakter")
    @Size(max=100, message="naziv sme da ima najvise  100 karaktera")
    @Column(name="naziv")
    private String naziv;


    @NotBlank
    @Size(min=1, message="Mora da postoji opis filma")
    @Size(max=1000, message="opis filma ne sme da ima vise od 1000 karaktera")
    @Column(name="opis")
    private String opis;


    //@Digits(integer=3,fraction = 0,message = "mora da bude broj")
    @Min(value=1,message ="mora da traje bar jedan minut" )
    @Max(value=240,message ="ne sme da traje duze od 4 sata(240 min)" )
    @Column(name="trajanje")
    private int trajanje;

    @Digits(fraction = 1,message = "mora da bude decimalan broj",integer = 1)
    @Column(name="ocena")
    private double ocena;

    @NotNull
    @Size(min=1, message="Zanr filma mora da se doda")
    @Size(max=100, message="zanr ne sme da ima vise od 100 karaktera")
    @Column(name="zanr")
    private String zanr;


    @NotNull
    @Size(min=1, message="link slike filma mora da se doda")
    @Size(max=1000, message="link slike ne sme da ima vise od 1000 karaktera")
    @Column(name="slika")
    private String slika;


    @JsonManagedReference(value="film-projekcija")
    @OneToMany(mappedBy="film",
            //     cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            //        CascadeType.DETACH, CascadeType.REFRESH}
            cascade = CascadeType.ALL    )
    private List<Projekcija> projekcije;

    public void add(Projekcija projekcija) {
        if (projekcije == null) {
            projekcije = new ArrayList<>();
        }
        projekcije.add(projekcija);
        projekcija.setFilm(this);
    }

    public Film() {

    }

    public Film(int idfilm, String glumci, String naziv, String opis, int trajanje, double ocena, String zanr, String slika) {
        this.idfilm = idfilm;
        this.glumci = glumci;
        this.naziv = naziv;
        this.opis = opis;
        this.trajanje = trajanje;
        this.ocena = ocena;
        this.zanr = zanr;
        this.slika = slika;
    }

    public Film(int idfilm, String glumci, String naziv, String opis, int trajanje, double ocena, String zanr) {
        this.idfilm = idfilm;
        this.glumci = glumci;
        this.naziv = naziv;
        this.opis = opis;
        this.trajanje = trajanje;
        this.ocena = ocena;
        this.zanr = zanr;
    }

    public int getIdfilm() {
        return idfilm;
    }

    public void setIdfilm(int idfilm) {
        this.idfilm = idfilm;
    }

    public String getGlumci() {
        return glumci;
    }

    public void setGlumci(String glumci) {
        this.glumci = glumci;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return idfilm == film.idfilm && Objects.equals(naziv, film.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idfilm, naziv);
    }

    @Override
    public String toString() {
        return "Film{" +
                "idfilm=" + idfilm +
                ", glumci='" + glumci + '\'' +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", trajanje=" + trajanje +
                ", ocena=" + ocena +
                ", zanr='" + zanr + '\'' +
                '}';
    }

    public List<Projekcija> getProjekcije() {
        return projekcije;
    }

    public void setProjekcije(List<Projekcija> projekcije) {
        this.projekcije = projekcije;
    }
}
