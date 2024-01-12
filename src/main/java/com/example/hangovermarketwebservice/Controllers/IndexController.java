package com.example.hangovermarketwebservice.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String hello() {
        return "Hello, alcash";
    }
}