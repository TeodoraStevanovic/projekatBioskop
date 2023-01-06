package com.example.projekatbioskop.model;

import javax.persistence.*;

import javax.persistence.Table;
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

    @Column(name="nazivsale")
    private String naziv;
    @Column(name="kapacitet")
    private int kapacitet;
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="idbioskop")
    private Bioskop bioskop;
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
