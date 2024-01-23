package com.example.hangovermarketwebservice.Models;


import jakarta.persistence.*;
import lombok.*;

//TODO: Реализовать ролевую модель.
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (
        name = "user",
        schema = "public"
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long phoneNumber;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String secondName;

}
