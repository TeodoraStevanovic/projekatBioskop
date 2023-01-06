package com.example.projekatbioskop.model;
import javax.persistence.*;

@Entity
@Table(name="users")
public class User  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="iduser")
    private int iduser;

    @Column(name="email")
    private  String email;
    @Column(name="username")
    private  String username;
    @Column(name="password")
    private  String password;
    @Column(name="ime")
    private  String ime;
    @Column(name="prezime")
    private  String prezime;
    @Column(name="brojtelefona")
    private  String brojTelefona;

    @Column(name = "uloga")
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
}
