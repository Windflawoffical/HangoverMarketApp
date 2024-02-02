package com.example.hangovermarketwebservice.Repositories;

import com.example.hangovermarketwebservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //Проверка: существует ли пользователь с таким телефонным номером в бд?
    Optional<User> findByPhoneNumber(Long PhoneNumber);

    Optional<User> findByPhoneNumberAndPassword(Long PhoneNumber, String password);

}
