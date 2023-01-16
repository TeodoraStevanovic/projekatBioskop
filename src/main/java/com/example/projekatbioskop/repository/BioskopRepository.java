package com.example.projekatbioskop.repository;

import com.example.projekatbioskop.model.Bioskop;
import com.example.projekatbioskop.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

//@RestResource(path = "cinemas")
//@RepositoryRestResource(path = "cinemas",collectionResourceRel = "bioskop")
public interface BioskopRepository extends JpaRepository<Bioskop,Integer> {
}
