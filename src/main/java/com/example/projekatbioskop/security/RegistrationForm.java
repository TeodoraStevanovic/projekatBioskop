package com.example.projekatbioskop.security;


import com.example.projekatbioskop.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String ime;
    private String prezime;
    private String email;
    private String brojtelefona;


    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(email,username,passwordEncoder.encode(password),
                ime, prezime, brojtelefona,"ROLE_USER");
    }
}
