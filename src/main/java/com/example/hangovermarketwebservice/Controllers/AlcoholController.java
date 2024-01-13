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
// @RequestMapping(value = "/alcohols") //, method = { RequestMethod.GET, RequestMethod.POST })
public class AlcoholController {

    @Autowired
    private AlcoholRepository alcoholRepository;

    @GetMapping("/alcohols/add")
    public String AddAlcohol(Model model) {
        return "add";
    }

    @PostMapping("/alcohols/add")
    public String AddPostAlcohol(@RequestParam String name,
                                @RequestParam String description,
                                @RequestParam float price,
                                @RequestParam String manufacturer)
    {
        Alcohol alcohol = new Alcohol(name, description, price, manufacturer);
        alcoholRepository.save(alcohol);
            return "redirect:/check";
    }

    // RestController required for working
    @PostMapping("/alcohols/add-alco")
    public ResponseEntity<?> AddAlcohol (@RequestBody Alcohol alcohol) {   
        try {
            alcoholRepository.save(alcohol);
            return ResponseEntity.ok().body(alcohol);
            }      
        catch (DataIntegrityViolationException exceptionHangMarket) {
            return ResponseEntity.badRequest().body("Wrong name!");
        }
    }

    @GetMapping("/alcohols/delete-alco")
    public String delete() {
        alcoholRepository.deleteAll();
        return "deleted";
    }

    @GetMapping("/alcohols/getall")
    public String getall(Model model) {
        List<Alcohol> result = alcoholRepository.findAll();
        model.addAttribute("all_alc", result);
        return "getall";
    }
}
