package com.example.hangovermarketwebservice.Repositories;

import com.example.hangovermarketwebservice.Models.Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, Integer> {

}
