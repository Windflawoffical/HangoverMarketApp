package com.example.hangovermarketwebservice.Controllers;

import com.example.hangovermarketwebservice.Repositories.AlcoholRepository;
import com.example.hangovermarketwebservice.Models.Alcohol;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AlcoholController {

    @Autowired
    private AlcoholRepository alcoholRepository;

    @GetMapping("/alcohols/add")
    public String AddAlcohol(Model model) {
        return "add";
    }

    //Метод добавления в бд алкоголя с использованием использованием web-страницы - @RequestParam ← application/x-www-form-urlencoded
    @PostMapping("/alcohols/add")
    public String AddPostAlcohol(@RequestParam String name,
                                @RequestParam String description,
                                @RequestParam float price,
                                @RequestParam String manufacturer)
    {
        //@Builder - запись идентична записи "Alcohol alcohol = new Alcohol(name, description, price, manufacturer);"
        Alcohol alcohol = Alcohol.builder().name(name).description(description).price(price).manufacturer(manufacturer).build();
        alcoholRepository.save(alcohol);
            return "redirect:/check";
    }

    //Метод удаления из таблицы (alcohol) всех записей
    @GetMapping("/alcohols/delete-alco")
    public String delete() {
        alcoholRepository.deleteAll();
        return "deleted";
    }

    //Метод вывода всех записей из таблицы (alcohol)
    @GetMapping("/alcohols/getall")
    public String getall(Model model) {
        List<Alcohol> result = alcoholRepository.findAll();
        model.addAttribute("all_alc", result);
        return "getall";
    }

    //Метод добавления в бд алкоголя с использованием json (insomnia) - @RequestBody ← application/json
    /*
    json:
            {
                "name": "имя",
                "description": "описание",
                "price":цена,
                "manufacturer":"производитель"
            }
     */
    @PostMapping("/alcohols/add_json")
    public ResponseEntity<?> AddAlcohol (@RequestBody Alcohol alcohol) {
        try {
            alcoholRepository.save(alcohol);
            return ResponseEntity.ok().body(alcohol);
        }
        catch (DataIntegrityViolationException exceptionHangMarket) {
            return ResponseEntity.badRequest().body("Wrong name!");
        }
    }
}
