package com.example.hangovermarketwebservice.Repositories;

import com.example.hangovermarketwebservice.Models.Alcohol;

import com.example.hangovermarketwebservice.Models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {
    Alcohol findByName(String name);

    List<Alcohol> findByType(Type type);

}
