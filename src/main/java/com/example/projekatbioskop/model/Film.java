package com.example.projekatbioskop.model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="film")
public class Film {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idfilm")
    private int idfilm;


    @Column(name="glumci")
    private String glumci;


    @Column(name="naziv")
    private String naziv;


 //   @NotNull
  //  @Size(min=1, message="Mora da postoji opis filma")
    @Column(name="opis")
    private String opis;

    @Column(name="trajanje")
    private int trajanje;

    @Column(name="ocena")
    private double ocena;

    @Column(name="zanr")
    private String zanr;

    @Column(name="slika")
    private String slika;

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
