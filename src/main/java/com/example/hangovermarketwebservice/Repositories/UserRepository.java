package com.example.hangovermarketwebservice.Repositories;

import com.example.hangovermarketwebservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    //Проверка: существует ли пользователь с таким телефонным номером в бд?
    boolean existsByPhoneNumber(Long PhoneNumber);

}
