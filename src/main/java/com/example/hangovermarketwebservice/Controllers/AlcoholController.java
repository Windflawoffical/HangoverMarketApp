package com.example.hangovermarketwebservice.Controllers;

import com.example.hangovermarketwebservice.Exceptions.BadRequestException;
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

    @PostMapping("/alcohols/add")
    public ResponseEntity<?> AddAlcohol (@RequestBody Alcohol alcohol) {
        if(!alcohol.getName().isBlank() &&
                !alcohol.getDescription().isBlank() &&
                alcohol.getPrice() != 0.0f &&
                !alcohol.getManufacturer().isBlank() &&
                !alcohol.getType().isBlank()) {
            alcoholRepository.save(alcohol);
            return ResponseEntity.ok().body(alcohol);
        } else {
            return ResponseEntity.badRequest()
                    .body("Произошла ошибка при выполнении запроса, проверьте правильность введённых данных и повторите попытку!");
        }
    }


    //Метод удаления из таблицы (alcohol) всех записей
    @DeleteMapping("/alcohols/delete_all")
    public ResponseEntity<?> delete() {
        alcoholRepository.deleteAll();
        return ResponseEntity.ok().body("Все записи успешно удалены!");
    }

    //Метод вывода всех записей из таблицы (alcohol)
    @GetMapping("/alcohols/get_all")
    public ResponseEntity<?> getall() {
        List<Alcohol> all_alcohol = alcoholRepository.findAll();
        return ResponseEntity.ok().body(all_alcohol);
    }

}
