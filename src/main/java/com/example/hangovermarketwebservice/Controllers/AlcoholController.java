package com.example.hangovermarketwebservice.Controllers;

import com.example.hangovermarketwebservice.Repositories.AlcoholRepository;
import com.example.hangovermarketwebservice.Models.Alcohol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alcohols")
public class AlcoholController {

    @Autowired
    AlcoholRepository alcoholRepository;

    @PostMapping("add-alco")
    public ResponseEntity<?> AddAlcohol (@RequestBody Alcohol alcohol)
    {
        alcoholRepository.save(alcohol);
        return ResponseEntity.ok().body(alcohol);

    }
    
    @PostMapping("/check")
    public String check_commits() {
        return "all good";
    }

}
