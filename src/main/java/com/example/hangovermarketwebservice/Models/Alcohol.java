package com.example.hangovermarketwebservice.Models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "alcohol",
        schema = "public"
)
public class Alcohol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String type;

}
