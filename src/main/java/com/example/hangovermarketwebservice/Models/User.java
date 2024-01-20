package com.example.hangovermarketwebservice.Models;


import jakarta.persistence.*;

//TODO: Реализовать ролевую модель.
@Entity
@Table (name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "phonenumber", nullable = false)
    public long phoneNumber;
    @Column(name = "password", nullable = false)
    public String password;
    @Column(name = "firstname")
    public String firstName;
    @Column(name = "secondname")
    public String secondName;

    public User() {}

    public User(long phoneNumber, String password, String firstName, String secondName) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
