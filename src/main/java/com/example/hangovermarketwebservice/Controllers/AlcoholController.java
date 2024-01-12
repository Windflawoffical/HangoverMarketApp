package com.example.hangovermarketwebservice.Controllers;

import com.example.hangovermarketwebservice.Repositories.AlcoholRepository;
import com.example.hangovermarketwebservice.Models.Alcohol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alcohols")
public class AlcoholController {

    @Autowired
    AlcoholRepository alcoholRepository;

    @PostMapping("add-alco")
    public ResponseEntity<?> AddAlcohol (@RequestBody Alcohol alcohol) {   
        try {
            alcoholRepository.save(alcohol);
            return ResponseEntity.ok().body(alcohol);
            }      
        catch (DataIntegrityViolationException exceptionHangMarket) {
            return ResponseEntity.badRequest().body("Wrong name!");
    }
        
    }
    
    @DeleteMapping("delete-alco")
    public String delete() {
        alcoholRepository.deleteAll();
        return "Deleted successfully!";
    }

    @GetMapping("/getall")
    public List<Alcohol> getall() {
        List<Alcohol> result = alcoholRepository.findAll();
        return result;
    }

    @GetMapping("/check")
    public String check_commits() {
        return "all good";
    }

}
