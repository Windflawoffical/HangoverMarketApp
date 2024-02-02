package com.example.hangovermarketwebservice.Controllers;

import com.example.hangovermarketwebservice.Exceptions.BadRequestException;
import com.example.hangovermarketwebservice.Models.Role;
import com.example.hangovermarketwebservice.Models.User;
import com.example.hangovermarketwebservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users/login")
    public String log() {
        return "login";
    }

    //Отпала надобность в методе после реализации в проекте Spring Security
//    @PostMapping("/users/logins")
//    public ResponseEntity<?> login(@RequestBody User userfordb)
//    {
//        userRepository.findByPhoneNumberAndPassword(userfordb.getPhoneNumber(), userfordb.getPassword()).
//                ifPresentOrElse((user) -> {
//                    //setUserRoleAuthorized(userfordb) - установить поле Role для пришедшего юзера в authorized
//                    System.out.println("Пользователь успешно авторизован!");
//                }, () -> {
//                    throw new BadRequestException(String.format("Пользователя с такими данными не существует: %s, %s",
//                            userfordb.getPhoneNumber(), userfordb.getPassword()));
//                });
//        return ResponseEntity.ok().body(userfordb);
//    }

    @GetMapping("/users/registration")
    public String reg() {
        return "registration";
    }

    @PostMapping("/users/registration")
    public ResponseEntity<?> registration(@RequestBody User userfordb)
    {
        userRepository.findByPhoneNumber(userfordb.getPhoneNumber()).ifPresentOrElse(user -> {
            throw new BadRequestException(String.format("Пользователь с таким телефонным номером '%s' уже существует!",
                    userfordb.getPhoneNumber()));
        }, () -> {
            if (userfordb.getPhoneNumber().toString().length() == 11 && userfordb.getPassword().length() >= 6) {
                userfordb.setRole(Role.ROLE_USER);
                userfordb.setPassword(passwordEncoder.encode(userfordb.getPassword()));
                userRepository.save(userfordb);
            } else {
                ResponseEntity.badRequest().body("Проверьте правильность введённых данных и повторите попытку регистрации позже!");
            }
        });
        return ResponseEntity.ok().body(userfordb);
    }
}
