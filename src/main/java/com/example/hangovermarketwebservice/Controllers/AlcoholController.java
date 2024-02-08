package com.example.hangovermarketwebservice.Controllers;

import com.example.hangovermarketwebservice.Models.Type;
import com.example.hangovermarketwebservice.Repositories.AlcoholRepository;
import com.example.hangovermarketwebservice.Services.ImageUploadSendHandlerService;
import com.example.hangovermarketwebservice.Models.Alcohol;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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


    @GetMapping("/alcohols/get_all_{type}")
    public ResponseEntity<?> get_all_by_type(@PathVariable Type type) throws IOException {
        List<Alcohol> all_alcohol = alcoholRepository.findByType(type);
        for (Alcohol alcohol : all_alcohol) {
            alcohol.setImg(imageHandler.EncodeImage(alcohol.getImg()));
        }
        return ResponseEntity.ok().body(all_alcohol);
    }

    @GetMapping("/alcohols/wine")
    public String pageForWines() {
        return "wine";
    }

    @GetMapping("/alcohols/liquor")
    public String pageForliquor() {
        return "liquor";
    }


    @GetMapping("/alcohols/beer")
    public String pageForbeer() {
        return "beer";
    }


    @GetMapping("/alcohols/vodka")
    public String pageForvodka() {
        return "vodka";
    }


    @GetMapping("/alcohols/whiskey")
    public String pageForwhiskey() {
        return "whiskey";
    }


    @GetMapping("/alcohols/sparkling")
    public String pageForsparkling() {
        return "sparkling";
    }


    @GetMapping("/alcohols/getBy{id}")
    public ResponseEntity<?> getById(@PathVariable long id) throws IOException {
        Optional<Alcohol> alcohol = alcoholRepository.findById(id);
        alcohol.get().setImg(imageHandler.EncodeImage(alcohol.get().getImg()));
        return ResponseEntity.ok().body(alcohol);
    }

    @GetMapping("/alcohols/{id}")
    public String getPageById(@PathVariable long id) {
        return "alcoholbyid";
    }


    @GetMapping("/alcohols/add")
    public String AddAlcohol(Model model) {
        return "add";
    }

    // Метод добавления товара в базу данных + сохранение картинки товара на диске
    @PostMapping("/alcohols/add")
    public ResponseEntity<?> AddAlcohol (@RequestBody Alcohol alcohol) throws IOException {

        Alcohol db_alc = alcoholRepository.findByName(alcohol.getName());
        if (db_alc == null) {
            var base64 = alcohol.getImg().split(",")[1];
            String img_path = "./src/main/resources/static/images/item_images/" + alcohol.getName() + ".png";
            imageHandler.DecodeAndSave(base64, img_path);
            alcohol.setImg(img_path);
            alcoholRepository.save(alcohol);
            return ResponseEntity.ok().body(alcohol);
        } else {
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
