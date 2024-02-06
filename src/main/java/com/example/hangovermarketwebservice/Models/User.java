package com.example.hangovermarketwebservice.Models;


import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    private String firstName;
    private String secondName;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

}

