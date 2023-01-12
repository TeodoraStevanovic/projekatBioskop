package com.example.projekatbioskop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import javax.persistence.Table;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="sala")
public class Sala {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idsala")
    private int idsala;

    @NotBlank
    @Size(min=1, message="naziv sale mora da ima bar jedan karakter")
    @Size(max=100, message="naziv sme da ima najvise  100 karaktera")
    @Column(name="nazivsale")
    private String naziv;
    @Min(value=1,message ="sala mora da ima bar jedno mesto" )
    @Max(value=1000,message ="ne moze da bude veci od 1000" )
    @Column(name="kapacitet")
    private int kapacitet;

    @JsonBackReference(value="sala-bioskop")
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="idbioskop")
    private Bioskop bioskop;
    @JsonManagedReference(value="sala-projekcija")
    @OneToMany(mappedBy="sala",
            //     cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            //        CascadeType.DETACH, CascadeType.REFRESH}
            cascade = CascadeType.ALL    )
    private List<Projekcija> projekcije;
    public Sala() {
    }

    public int getIdsala() {
        return idsala;
    }

    public void setIdsala(int idsala) {
        this.idsala = idsala;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Bioskop getBioskop() {
        return bioskop;
    }

    public void setBioskop(Bioskop bioskop) {
        this.bioskop = bioskop;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "idsala=" + idsala +
                ", naziv='" + naziv + '\'' +
                ", bioskop=" + bioskop +
                ", kapacitet"+ kapacitet+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sala sala = (Sala) o;
        return idsala == sala.idsala && Objects.equals(naziv, sala.naziv) && Objects.equals(bioskop, sala.bioskop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsala, naziv, bioskop);
    }

    public Sala(String naziv, Bioskop bioskop, int kapacitet) {
        this.naziv = naziv;
        this.bioskop = bioskop;
        this.kapacitet=kapacitet;
    }

    public Sala(String naziv,int kapacitet) {
        this.naziv = naziv;
        this.kapacitet=kapacitet;
    }
    public void add(Projekcija projekcija) {
        if (projekcije == null) {
            projekcije = new ArrayList<>();
        }
        projekcije.add(projekcija);
        projekcija.setSala(this);
    }

    public List<Projekcija> getProjekcije() {
        return projekcije;
    }

    public void setProjekcije(List<Projekcija> projekcije) {
        this.projekcije = projekcije;
    }
}
