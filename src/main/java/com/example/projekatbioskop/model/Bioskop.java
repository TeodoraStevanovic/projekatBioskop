package com.example.projekatbioskop.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="bioskop")
public class Bioskop {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idbioskop")
    private int idbioskop;

    @NotBlank
    @Size(min=1, message="Naziv  mora da ima bar jedan karakter")
    @Size(max=100, message="naziv sme da ima najvise  100 karaktera")
    @Column(name="nazivbioskopa")
    private String nazivbioskopa;

    @NotBlank
    @Size(min=1, message="adresa mora da ima bar jedan karakter")
    @Size(max=1000, message="adresa sme da ima najvise  100 karaktera")
    @Column(name="adresa")
    private String adresa;

    @NotBlank
    @Size(min=1, message="telefon mora da ima bar jedan karakter")
    @Size(max=20, message="broj telefona sme da ima najvise  20 karaktera")
    @Column(name="telefon")
  @Pattern(regexp = "^\\+381[0-9]*",message = "mora da bude u formatu +381")
    private String telefon;

   @OneToMany(mappedBy="bioskop",
      //     cascade= {CascadeType.PERSIST, CascadeType.MERGE,
          //        CascadeType.DETACH, CascadeType.REFRESH}
          cascade = CascadeType.ALL    )
    private List<Sala> sale;

    public void add(Sala tempSala) {
        if (sale == null) {
            sale = new ArrayList<>();
        }
        sale.add(tempSala);
        tempSala.setBioskop(this);
    }


    public Bioskop(int idbioskop, String nazivbioskopa, String adresa, String telefon) {
        this.idbioskop = idbioskop;
        this.nazivbioskopa = nazivbioskopa;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public Bioskop() {
    }

    public int getIdbioskop() {
        return idbioskop;
    }

    public void setIdbioskop(int idbioskop) {
        this.idbioskop = idbioskop;
    }

    public String getNazivbioskopa() {
        return nazivbioskopa;
    }

    public void setNazivbioskopa(String nazivbioskopa) {
        this.nazivbioskopa = nazivbioskopa;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public List<Sala> getSale() {
       return sale;
    }

   public void setSale(List<Sala> sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Bioskop{" +
                "idbioskop=" + idbioskop +
                ", nazivbioskopa='" + nazivbioskopa + '\'' +
                ", adresa='" + adresa + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bioskop bioskop = (Bioskop) o;
        return idbioskop == bioskop.idbioskop && Objects.equals(nazivbioskopa, bioskop.nazivbioskopa) && Objects.equals(adresa, bioskop.adresa) && Objects.equals(telefon, bioskop.telefon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idbioskop, nazivbioskopa, adresa, telefon);
    }
}
