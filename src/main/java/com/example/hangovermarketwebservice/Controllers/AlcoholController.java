package com.example.hangovermarketwebservice.Controllers;

import com.example.hangovermarketwebservice.Exceptions.BadRequestException;
import com.example.hangovermarketwebservice.Repositories.AlcoholRepository;
import com.example.hangovermarketwebservice.Models.Alcohol;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class AlcoholController {

    @Autowired
    private AlcoholRepository alcoholRepository;

    @GetMapping("/alcohols/add")
    public String AddAlcohol(Model model) {
        return "add";
    }

    private void DecodeAndSave(String base64Img, String path) throws IOException {
        // System.out.println(base64Img);
        byte[] decoded = Base64.getDecoder().decode(base64Img);
        ByteArrayInputStream bis = new ByteArrayInputStream(decoded);
        var image = ImageIO.read(bis);
        bis.close();
        File saved = new File(path);
        ImageIO.write(image, "png", saved);
    }

    @PostMapping("/alcohols/add")
    public ResponseEntity<?> AddAlcohol (@RequestBody Alcohol alcohol) throws IOException {
        String alcoholName = alcohol.getName();
        var base64 = alcohol.getImg().split(",")[1];
        Alcohol db_alc = alcoholRepository.findByName(alcoholName);
        String img_path = "./src/main/resources/static/images/item_images/" + alcoholName + ".png";
        alcohol.setImg(img_path);
        DecodeAndSave(base64, img_path);
        if (db_alc == null) { //если в бд нет такой записи      
            alcoholRepository.save(alcohol);
            return ResponseEntity.ok().body(alcohol);
        } 
        else {
            return ResponseEntity.badRequest().body("Такой товар уже существует!");
        }
    }

    // @PostMapping("alcoholc/imgHandler")
    // public void upload(@RequestParam MultipartFile img) {
    //     Files.write(Paths.get(, null))
    // }


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
