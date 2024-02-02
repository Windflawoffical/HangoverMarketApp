package com.example.hangovermarketwebservice.Repositories;

import com.example.hangovermarketwebservice.Models.Alcohol;

import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {
    Alcohol findByName(String name);
}
