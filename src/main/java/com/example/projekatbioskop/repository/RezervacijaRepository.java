package com.example.projekatbioskop.repository;

import com.example.projekatbioskop.model.Projekcija;
import com.example.projekatbioskop.model.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RezervacijaRepository extends JpaRepository<Rezervacija,Integer> {
}
