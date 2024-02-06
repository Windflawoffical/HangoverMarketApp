package com.example.hangovermarketwebservice.Controllers;

import com.example.hangovermarketwebservice.Repositories.AlcoholRepository;
import com.example.hangovermarketwebservice.Services.ImageUploadSendHandlerService;
import com.example.hangovermarketwebservice.Models.Alcohol;

import java.io.IOException;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AlcoholController {

    @Autowired
    private AlcoholRepository alcoholRepository;

    @Autowired
    private ImageUploadSendHandlerService imageHandler;

    @GetMapping("/alcohols/add")
    public String AddAlcohol(Model model) {
        return "add";
    }

    // Метод добавления товара в базу данных + сохранение картинки товара на диске
    @PostMapping("/alcohols/add")
    public ResponseEntity<?> AddAlcohol (@RequestBody Alcohol alcohol) throws IOException {

        String alcoholName = alcohol.getName();
        var base64 = alcohol.getImg().split(",")[1];
        Alcohol db_alc = alcoholRepository.findByName(alcoholName);
        String img_path = "./src/main/resources/static/images/item_images/" + alcoholName + ".png";

        imageHandler.DecodeAndSave(base64, img_path);
        alcohol.setImg(img_path);

        if (db_alc == null) { //если в бд нет такой записи      
            alcoholRepository.save(alcohol);
            return ResponseEntity.ok().body(alcohol);
        } 
        else {
            return ResponseEntity.badRequest().body("Такой товар уже существует!");
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
    public ResponseEntity<?> getall() throws IOException {
        List<Alcohol> all_alcohol = alcoholRepository.findAll();
        for (Alcohol alcohol : all_alcohol) {
            alcohol.setImg(imageHandler.EncodeImage(alcohol.getImg()));
        }
        return ResponseEntity.ok().body(all_alcohol);
    }

}
