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
    public String login(@RequestParam Long phone_number,
                        @RequestParam String password,
                        Model model)
    {
        User user = userRepository.findByPhoneNumberAndPassword(phone_number, password);
        if (user != null) {
            return "redirect:/";
        } else {
            return "redirect:/users/login";
        }
    }

    @GetMapping("/users/registration")
    public String reg() {
        return "registration";
    }

    //Метод регистрации пользователя с использованием web-страницы - @RequestParam ← application/x-www-form-urlencoded
    @PostMapping("/users/registration")
    public String registration_web(@RequestParam Long phone_number,
                               @RequestParam String password,
                               @RequestParam String first_name,
                               @RequestParam String second_name)
    {
        userRepository.findByPhoneNumber(phone_number).ifPresent(user -> {
            throw new BadRequestException(String.format("Пользователь с таким телефонным номером '%s' уже существует!", phone_number));
        });
        if(phone_number.toString().length() == 11 && password.length() >= 6) {
            //@Builder
            //Запись практически идентична записи "User userfordb = new User(phone_number, password, first_name, second_name);"
            User userfordb = User.builder().phoneNumber(phone_number).password(password).firstName(first_name).secondName(second_name).build();
            userRepository.save(userfordb);
            return "redirect:/";
        } else {
            return "Регистрация провалена, проверьте правильность вводимость данных!";
        }
    }

    //Метод регистрации пользователя с использованием json (insomnia) - @RequestBody ← application/json
    /*
    json:
        {
	        "phoneNumber": "номер телефона, начиная с 8",
	        "password": "пароль, состоящий более чем из 5 символов",
	        "firstName":"имя",
	        "secondName":"фамилия"
        }
     */
    @PostMapping("/users/registration_json")
    public ResponseEntity<?> registration_json(@RequestBody User userfordb)
    {
        userRepository.findByPhoneNumber(userfordb.getPhoneNumber()).ifPresent(user -> {
            throw new BadRequestException(String.format("Пользователь с таким телефонным номером '%s' уже существует!", userfordb.getPhoneNumber()));
        });
        if (userfordb.getPhoneNumber().toString().length() == 11 && userfordb.getPassword().length() >= 6) {
            userRepository.save(userfordb);
            return ResponseEntity.ok().body(userfordb);
        } else {
            return ResponseEntity.badRequest().body("Регистрация провалена, проверьте правильность вводимость данных!");
        }
    }

}
