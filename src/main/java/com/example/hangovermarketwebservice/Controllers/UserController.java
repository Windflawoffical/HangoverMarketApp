package com.example.hangovermarketwebservice.Controllers;

import com.example.hangovermarketwebservice.Exceptions.BadRequestException;
import com.example.hangovermarketwebservice.Models.User;
import com.example.hangovermarketwebservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/login")
    public String log() {
        return "login";
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody User userfordb)
    {
        userRepository.findByPhoneNumberAndPassword(userfordb.getPhoneNumber(), userfordb.getPassword()).
                ifPresentOrElse((user) -> {
                    //setUserRoleAuthorized(userfordb) - установить поле Role для пришедшего юзера в authorized
                    System.out.println("Пользователь успешно авторизован!");
                }, () -> {
                    throw new BadRequestException(String.format("Пользователя с таким данными не существует: %s, %s",
                            userfordb.getPhoneNumber(), userfordb.getPassword()));
                });
        return ResponseEntity.ok().body(userfordb);
    }

    @GetMapping("/users/registration")
    public String reg() {
        return "registration";
    }

    @PostMapping("/users/registration")
    public ResponseEntity<?> registration(@RequestBody User userfordb)
    {
        userRepository.findByPhoneNumber(userfordb.getPhoneNumber()).ifPresent(user -> {
            throw new BadRequestException(String.format("Пользователь с таким телефонным номером '%s' уже существует!",
                    userfordb.getPhoneNumber()));
        });
        if (userfordb.getPhoneNumber().toString().length() == 11 && userfordb.getPassword().length() >= 6) {
            userRepository.save(userfordb);
            return ResponseEntity.ok().body(userfordb);
        } else {
            return ResponseEntity.badRequest().body("Регистрация провалена, проверьте правильность вводимость данных!");
        }
    }

}
