package com.example.projekatbioskop.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="iduser")
    private int iduser;

    @NotBlank
    @Size(min=1, message="email mora da ima bar jedan karakter")
    @Size(max=70, message="email sme da ima najvise  70 karaktera")
    @Pattern(regexp = "^.*@.*$",message = "email mora da ima @!")
    @Column(name="email")
    private  String email;
    @NotBlank
    @Size(min=1, message="username mora da ima bar jedan karakter")
    @Size(max=45, message="username sme da ima najvise  45 karaktera")
    @Column(name="username")
    private  String username;
    @NotBlank
    @Size(min=1, message="lozinka mora da ima bar jedan karakter")
    @Column(name="password")
    private  String password;

    @NotBlank
    @Size(min=1, message="ime mora da ima bar jedan karakter")
    @Size(max=45, message="ime sme da ima najvise  45 karaktera")
    @Column(name="ime")
    private  String ime;
    @NotBlank
    @Size(min=1, message="prezime mora da ima bar jedan karakter")
    @Size(max=45, message="prezime sme da ima najvise  45 karaktera")
    @Column(name="prezime")
    private  String prezime;
    @NotBlank
    @Size(min=1, message="telefon mora da ima bar jedan karakter")
    @Size(max=45, message="broj telefona sme da ima najvise  45 karaktera")
    @Pattern(regexp = "^\\+381[0-9]*",message = "mora da bude u formatu +381")
    @Column(name="brojtelefona")
    private  String brojTelefona;

    @NotBlank
    @Size(min=1, message="uloga mora da ima bar jedan karakter")
    @Size(max=45, message="uloga sme da ima najvise  45 karaktera")@Column(name = "uloga")
    private String uloga;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    @Override
    public String toString() {
        return "User{" +
                "iduser=" + iduser +
                ", email='" + email + '\'' +
                ", passowrd='" + password + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", brojTelefona='" + brojTelefona + '\'' +
                '}';
    }

    public User() {
    }

    public User(String email, String username, String password, String ime, String prezime, String brojTelefona) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public User(String email, String username, String password, String ime, String prezime, String brojTelefona, String uloga) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.uloga = uloga;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }


    @JsonManagedReference(value="rezervacija-user")
    @OneToMany(mappedBy="user", cascade = CascadeType.MERGE )
    private List<Rezervacija> rezervacije;


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
        rezervacija.setUser(this);
    }
}
