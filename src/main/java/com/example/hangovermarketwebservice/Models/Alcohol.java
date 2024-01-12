package com.example.hangovermarketwebservice.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
        name = "alcohol",
        schema = "public"
)
public class Alcohol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "description", nullable = false)
    private String description;
}
